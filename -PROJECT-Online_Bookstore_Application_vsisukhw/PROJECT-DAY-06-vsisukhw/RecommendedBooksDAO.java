package OnlineBookstoreSystem.noSql.dao;

import OnlineBookstoreSystem.model.Book;
import OnlineBookstoreSystem.noSql.util.DynamoDBConnection;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommendedBooksDAO {
    private static final String TABLE_NAME = "RecommendedBooks";
    private final DynamoDbClient dynamoDbClient;
    
    public RecommendedBooksDAO() {
        this.dynamoDbClient = DynamoDBConnection.getClient();
    }

    public void addRecommendedBook(Book book) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("Id", AttributeValue.builder().n(String.valueOf(book.getId())).build());
        item.put("title", AttributeValue.builder().s(book.getTitle()).build());
        item.put("author", AttributeValue.builder().s(book.getAuthor()).build());
        item.put("price", AttributeValue.builder().n(String.valueOf(book.getPrice())).build());
        item.put("quantity", AttributeValue.builder().n(String.valueOf(book.getQuantity())).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(item)
                .conditionExpression("attribute_not_exists(Id)") // ✅ prevents overwrite
                .build();

        try {
            dynamoDbClient.putItem(request);
           // System.out.println("Successfully added book to RecommendedBooks table: " + book.getTitle());
        } catch (ConditionalCheckFailedException e) {
            System.out.println("Book already exists in RecommendedBooks table: " + book.getTitle());
        } catch (Exception e) {
            System.err.println("Error adding recommended book: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public List<Book> getAllRecommendedBooks() {
        List<Book> books = new ArrayList<>();
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(TABLE_NAME)
                .build();
        
        try {
            ScanResponse response = dynamoDbClient.scan(scanRequest);
            System.out.println("Found " + response.items().size() + " items in RecommendedBooks table");
            for (Map<String, AttributeValue> item : response.items()) {
                Book book = new Book(
                    item.get("title").s(),
                    item.get("author").s(),
                    Integer.parseInt(item.get("Id").n()),
                    Integer.parseInt(item.get("price").n()),
                    Integer.parseInt(item.get("quantity").n())
                );
                book.setRecommended(true);
                books.add(book);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving recommended books: " + e.getMessage());
            e.printStackTrace();
        }
        
        return books;
    }
}