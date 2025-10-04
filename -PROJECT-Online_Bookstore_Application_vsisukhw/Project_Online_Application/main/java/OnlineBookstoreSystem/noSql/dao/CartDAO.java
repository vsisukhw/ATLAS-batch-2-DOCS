package OnlineBookstoreSystem.noSql.dao;

import OnlineBookstoreSystem.model.Book;
import OnlineBookstoreSystem.model.User;
import OnlineBookstoreSystem.noSql.util.DynamoDBConnection;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartDAO {
    private static final String CART_TABLE = "Cart";
    private static final String BOOKS_TABLE = "Books";
    private final DynamoDbClient dynamoDbClient;
    private User user;
    
    public CartDAO(User user) {
        this.user = user;
        this.dynamoDbClient = DynamoDBConnection.getClient();
    }
    
    public void addBook(Book book, int quantity, String userName) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("userName", AttributeValue.builder().s(userName).build());
        item.put("bookId", AttributeValue.builder().n(String.valueOf(book.getId())).build());
        item.put("quantity", AttributeValue.builder().n(String.valueOf(quantity)).build());
        
        PutItemRequest request = PutItemRequest.builder()
                .tableName(CART_TABLE)
                .item(item)
                .build();
        
        try {
            dynamoDbClient.putItem(request);
            System.out.println("Book added to cart successfully");
        } catch (Exception e) {
            System.err.println("Error adding book to cart: " + e.getMessage());
        }
    }
    
    public void removeAllBooks(String userName) {
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(CART_TABLE)
                .filterExpression("userName = :userName")
                .expressionAttributeValues(Map.of(":userName", AttributeValue.builder().s(userName).build()))
                .build();
        
        try {
            ScanResponse scanResponse = dynamoDbClient.scan(scanRequest);
            for (Map<String, AttributeValue> item : scanResponse.items()) {
                Map<String, AttributeValue> key = new HashMap<>();
                key.put("userName", item.get("userName"));
                key.put("bookId", item.get("bookId"));
                
                DeleteItemRequest deleteRequest = DeleteItemRequest.builder()
                        .tableName(CART_TABLE)
                        .key(key)
                        .build();
                
                dynamoDbClient.deleteItem(deleteRequest);
            }
            System.out.println("All books from cart deleted successfully");
        } catch (Exception e) {
            System.err.println("Error deleting books from cart: " + e.getMessage());
        }
    }
    
    public void removeBook(int bookId, String userName) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("userName", AttributeValue.builder().s(userName).build());
        key.put("bookId", AttributeValue.builder().n(String.valueOf(bookId)).build());
        
        DeleteItemRequest request = DeleteItemRequest.builder()
                .tableName(CART_TABLE)
                .key(key)
                .build();
        
        try {
            dynamoDbClient.deleteItem(request);
            System.out.println("Book from cart deleted successfully");
        } catch (Exception e) {
            System.err.println("Error deleting book from cart: " + e.getMessage());
        }
    }
    
    public int getQuantity(int bookId, String userName) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("userName", AttributeValue.builder().s(userName).build());
        key.put("bookId", AttributeValue.builder().n(String.valueOf(bookId)).build());
        
        GetItemRequest request = GetItemRequest.builder()
                .tableName(CART_TABLE)
                .key(key)
                .build();
        
        try {
            GetItemResponse response = dynamoDbClient.getItem(request);
            if (response.hasItem()) {
                return Integer.parseInt(response.item().get("quantity").n());
            }
        } catch (Exception e) {
            System.err.println("Error retrieving quantity: " + e.getMessage());
        }
        
        return 0;
    }
    
    public int calculateTotal(String userName) {
        int totalAmount = 0;
        List<Book> cartBooks = getCart(userName);
        
        for (Book book : cartBooks) {
            totalAmount += book.getPrice() * book.getQuantity();
        }
        
        return totalAmount;
    }
    
    public List<Book> getCart(String userName) {
        List<Book> books = new ArrayList<>();
        BookDAO bookDAO = new BookDAO();
        
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(CART_TABLE)
                .filterExpression("userName = :userName")
                .expressionAttributeValues(Map.of(":userName", AttributeValue.builder().s(userName).build()))
                .build();
        
        try {
            ScanResponse response = dynamoDbClient.scan(scanRequest);
            for (Map<String, AttributeValue> item : response.items()) {
                int bookId = Integer.parseInt(item.get("bookId").n());
                int quantity = Integer.parseInt(item.get("quantity").n());
                
                Book book = bookDAO.getBookById(bookId);
                if (book != null) {
                    book.setQuantity(quantity);
                    books.add(book);
                }
            }
        } catch (Exception e) {
            System.err.println("Error retrieving cart: " + e.getMessage());
        }
        
        return books;
    }
}