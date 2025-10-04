package OnlineBookstoreSystem.noSql.dao;

import OnlineBookstoreSystem.model.Book;
import OnlineBookstoreSystem.model.User;
import OnlineBookstoreSystem.noSql.util.DynamoDBConnection;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrowsingHistoryDAO {
    private static final String TABLE_NAME = "BrowsingHistory";
    private final DynamoDbClient dynamoDbClient;
    private User user;
    
    public BrowsingHistoryDAO(User user) {
        this.user = user;
        this.dynamoDbClient = DynamoDBConnection.getClient();
    }
    
    public void addToHistory(Book book) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("userName", AttributeValue.builder().s(user.getUserName()).build());
        item.put("bookId", AttributeValue.builder().n(String.valueOf(book.getId())).build());
        item.put("timestamp", AttributeValue.builder().s(timestamp).build());
        item.put("title", AttributeValue.builder().s(book.getTitle()).build());
        item.put("author", AttributeValue.builder().s(book.getAuthor()).build());
        
        PutItemRequest request = PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(item)
                .build();
        
        try {
            dynamoDbClient.putItem(request);
        } catch (Exception e) {
            System.err.println("Error adding to browsing history: " + e.getMessage());
        }
    }
    
    public List<Book> getBrowsingHistory() {
        List<Book> books = new ArrayList<>();
        BookDAO bookDAO = new BookDAO();
        
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(TABLE_NAME)
                .filterExpression("userName = :userName")
                .expressionAttributeValues(Map.of(":userName", AttributeValue.builder().s(user.getUserName()).build()))
                .build();
        
        try {
            ScanResponse response = dynamoDbClient.scan(scanRequest);
            for (Map<String, AttributeValue> item : response.items()) {
                int bookId = Integer.parseInt(item.get("bookId").n());
                Book book = bookDAO.getBookById(bookId);
                if (book != null) {
                    books.add(book);
                }
            }
        } catch (Exception e) {
            System.err.println("Error retrieving browsing history: " + e.getMessage());
        }
        
        return books;
    }
    
    public void clearHistory() {
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(TABLE_NAME)
                .filterExpression("userName = :userName")
                .expressionAttributeValues(Map.of(":userName", AttributeValue.builder().s(user.getUserName()).build()))
                .build();
        
        try {
            ScanResponse scanResponse = dynamoDbClient.scan(scanRequest);
            for (Map<String, AttributeValue> item : scanResponse.items()) {
                Map<String, AttributeValue> key = new HashMap<>();
                key.put("userName", item.get("userName"));
                key.put("bookId", item.get("bookId"));
                
                DeleteItemRequest deleteRequest = DeleteItemRequest.builder()
                        .tableName(TABLE_NAME)
                        .key(key)
                        .build();
                
                dynamoDbClient.deleteItem(deleteRequest);
            }
            System.out.println("Browsing history cleared successfully");
        } catch (Exception e) {
            System.err.println("Error clearing browsing history: " + e.getMessage());
        }
    }
}