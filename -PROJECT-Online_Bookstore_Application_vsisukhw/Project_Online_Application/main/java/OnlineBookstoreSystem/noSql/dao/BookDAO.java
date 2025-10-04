package OnlineBookstoreSystem.noSql.dao;

import OnlineBookstoreSystem.model.Book;
import OnlineBookstoreSystem.noSql.dao.BrowsingHistoryDAO;
import OnlineBookstoreSystem.noSql.util.DynamoDBConnection;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookDAO {
    private static final String TABLE_NAME = "Books";
    private final DynamoDbClient dynamoDbClient;
    
    public BookDAO() {
        this.dynamoDbClient = DynamoDBConnection.getClient();
    }
    
    public void createBook(Book book) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.builder().n(String.valueOf(book.getId())).build());
        item.put("title", AttributeValue.builder().s(book.getTitle()).build());
        item.put("author", AttributeValue.builder().s(book.getAuthor()).build());
        item.put("price", AttributeValue.builder().n(String.valueOf(book.getPrice())).build());
        item.put("quantity", AttributeValue.builder().n(String.valueOf(book.getQuantity())).build());
        item.put("recommended", AttributeValue.builder().bool(book.getRecommended()).build());
        
        PutItemRequest request = PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(item)
                .build();
        
        try {
            dynamoDbClient.putItem(request);
            System.out.println("Book added to DynamoDB successfully");
        } catch (Exception e) {
            System.err.println("Error adding book: " + e.getMessage());
        }
    }
    
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(TABLE_NAME)
                .build();
        
        try {
            ScanResponse response = dynamoDbClient.scan(scanRequest);
            for (Map<String, AttributeValue> item : response.items()) {
                Book book = new Book(
                    item.get("title").s(),
                    item.get("author").s(),
                    Integer.parseInt(item.get("id").n()),
                    Integer.parseInt(item.get("price").n()),
                    Integer.parseInt(item.get("quantity").n())
                );
                book.setRecommended(item.get("recommended").bool());
                books.add(book);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving books: " + e.getMessage());
        }
        
        return books;
    }
    
    public Book getBookById(int id) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("id", AttributeValue.builder().n(String.valueOf(id)).build());
        
        GetItemRequest request = GetItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(key)
                .build();
        
        try {
            GetItemResponse response = dynamoDbClient.getItem(request);
            if (response.hasItem()) {
                Map<String, AttributeValue> item = response.item();
                Book book = new Book(
                    item.get("title").s(),
                    item.get("author").s(),
                    Integer.parseInt(item.get("id").n()),
                    Integer.parseInt(item.get("price").n()),
                    Integer.parseInt(item.get("quantity").n())
                );
                book.setRecommended(item.get("recommended").bool());
                return book;
            }
        } catch (Exception e) {
            System.err.println("Error retrieving book: " + e.getMessage());
        }
        
        return null;
    }
    
    public void updateBook(Book book) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("id", AttributeValue.builder().n(String.valueOf(book.getId())).build());
        
        Map<String, AttributeValueUpdate> updates = new HashMap<>();
        updates.put("title", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(book.getTitle()).build())
                .action(AttributeAction.PUT)
                .build());
        updates.put("author", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(book.getAuthor()).build())
                .action(AttributeAction.PUT)
                .build());
        updates.put("price", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().n(String.valueOf(book.getPrice())).build())
                .action(AttributeAction.PUT)
                .build());
        updates.put("quantity", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().n(String.valueOf(book.getQuantity())).build())
                .action(AttributeAction.PUT)
                .build());
        updates.put("recommended", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().bool(book.getRecommended()).build())
                .action(AttributeAction.PUT)
                .build());
        
        UpdateItemRequest request = UpdateItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(key)
                .attributeUpdates(updates)
                .build();
        
        try {
            dynamoDbClient.updateItem(request);
            System.out.println("Book updated successfully");
        } catch (Exception e) {
            System.err.println("Error updating book: " + e.getMessage());
        }
    }
    
    public void deleteBook(int id) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("id", AttributeValue.builder().n(String.valueOf(id)).build());
        
        DeleteItemRequest request = DeleteItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(key)
                .build();
        
        try {
            dynamoDbClient.deleteItem(request);
            System.out.println("Book deleted successfully");
        } catch (Exception e) {
            System.err.println("Error deleting book: " + e.getMessage());
        }
    }

    public List<Book> searchBooksByTitle(String title) {
        List<Book> books = new ArrayList<>();
        String searchKey = title.toLowerCase();

        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(TABLE_NAME)
                .build(); // no filter here, fetch all

        try {
            ScanResponse response = dynamoDbClient.scan(scanRequest);
            for (Map<String, AttributeValue> item : response.items()) {
                String dbTitle = item.get("title").s();
                if (dbTitle.toLowerCase().contains(searchKey)) { // case-insensitive match
                    Book book = new Book(
                            dbTitle,
                            item.get("author").s(),  // keep original case for author
                            Integer.parseInt(item.get("id").n()),
                            Integer.parseInt(item.get("price").n()),
                            Integer.parseInt(item.get("quantity").n())
                    );
                    book.setRecommended(item.get("recommended").bool());
                    books.add(book);

                    //addToHistory(book);
                }
            }
        } catch (Exception e) {
            System.err.println("Error searching books by title: " + e.getMessage());
        }

        return books;
    }


    public List<Book> searchBooksByAuthor(String author) {
        List<Book> books = new ArrayList<>();
        String searchKey = author.toLowerCase();

        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(TABLE_NAME)
                .build(); // no filter here

        try {
            ScanResponse response = dynamoDbClient.scan(scanRequest);
            for (Map<String, AttributeValue> item : response.items()) {
                String dbAuthor = item.get("author").s();
                if (dbAuthor.toLowerCase().contains(searchKey)) {
                    Book book = new Book(
                            item.get("title").s(),
                            dbAuthor,
                            Integer.parseInt(item.get("id").n()),
                            Integer.parseInt(item.get("price").n()),
                            Integer.parseInt(item.get("quantity").n())
                    );
                    book.setRecommended(item.get("recommended").bool());
                    books.add(book);
                }
            }
        } catch (Exception e) {
            System.err.println("Error searching books by author: " + e.getMessage());
        }

        return books;
    }


    public List<Book> getRecommendedBooks() {
        List<Book> books = new ArrayList<>();
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(TABLE_NAME)
                .filterExpression("recommended = :recommended")
                .expressionAttributeValues(Map.of(":recommended", AttributeValue.builder().bool(true).build()))
                .build();
        
        try {
            ScanResponse response = dynamoDbClient.scan(scanRequest);
            for (Map<String, AttributeValue> item : response.items()) {
                Book book = new Book(
                    item.get("title").s(),
                    item.get("author").s(),
                    Integer.parseInt(item.get("id").n()),
                    Integer.parseInt(item.get("price").n()),
                    Integer.parseInt(item.get("quantity").n())
                );
                book.setRecommended(item.get("recommended").bool());
                books.add(book);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving recommended books: " + e.getMessage());
        }
        
        return books;
    }
}