package OnlineBookstoreSystem.noSql.dao;

import OnlineBookstoreSystem.model.Book;
import OnlineBookstoreSystem.model.OrderModel;
import OnlineBookstoreSystem.model.User;
import OnlineBookstoreSystem.noSql.util.DynamoDBConnection;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDAO {
    private static final String ORDERS_TABLE = "Orders";
    private final DynamoDbClient dynamoDbClient;
    private User user;
    
    public OrderDAO(User user) {
        this.user = user;
        this.dynamoDbClient = DynamoDBConnection.getClient();
    }
    
    public void addOrder() {
        CartDAO cartDAO = new CartDAO(user);
        List<Book> cartBooks = cartDAO.getCart(user.getUserName());
        
        if (cartBooks.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }
        
        int orderId = generateOrderId();
        String orderDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        for (Book book : cartBooks) {
            Map<String, AttributeValue> item = new HashMap<>();
            item.put("orderId", AttributeValue.builder().n(String.valueOf(orderId)).build());
            item.put("userName", AttributeValue.builder().s(user.getUserName()).build());
            item.put("bookId", AttributeValue.builder().n(String.valueOf(book.getId())).build());
            item.put("quantity", AttributeValue.builder().n(String.valueOf(book.getQuantity())).build());
            item.put("orderStatus", AttributeValue.builder().s("CONFIRMED").build());
            item.put("orderDate", AttributeValue.builder().s(orderDate).build());
            
            PutItemRequest request = PutItemRequest.builder()
                    .tableName(ORDERS_TABLE)
                    .item(item)
                    .build();
            
            try {
                dynamoDbClient.putItem(request);
            } catch (Exception e) {
                System.err.println("Error adding order item: " + e.getMessage());
            }
        }
        
        cartDAO.removeAllBooks(user.getUserName());
        System.out.println("Order placed successfully");
    }
    
    public void addOrder1() {
        int orderId = generateOrderId();
        String orderDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("orderId", AttributeValue.builder().n(String.valueOf(orderId)).build());
        item.put("userName", AttributeValue.builder().s(user.getUserName()).build());
        item.put("orderStatus", AttributeValue.builder().s("PENDING").build());
        item.put("orderDate", AttributeValue.builder().s(orderDate).build());
        
        PutItemRequest request = PutItemRequest.builder()
                .tableName(ORDERS_TABLE)
                .item(item)
                .build();
        
        try {
            dynamoDbClient.putItem(request);
        } catch (Exception e) {
            System.err.println("Error adding pending order: " + e.getMessage());
        }
    }
    
    public List<OrderModel> viewOrder() {
        List<OrderModel> orderModels = new ArrayList<>();
        BookDAO bookDAO = new BookDAO();
        
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(ORDERS_TABLE)
                .filterExpression("userName = :userName")
                .expressionAttributeValues(Map.of(":userName", AttributeValue.builder().s(user.getUserName()).build()))
                .build();
        
        try {
            ScanResponse response = dynamoDbClient.scan(scanRequest);
            for (Map<String, AttributeValue> item : response.items()) {
                if (item.containsKey("bookId")) {
                    int bookId = Integer.parseInt(item.get("bookId").n());
                    Book book = bookDAO.getBookById(bookId);
                    
                    if (book != null) {
                        OrderModel orderModel = new OrderModel(
                            Integer.parseInt(item.get("orderId").n()),
                            book.getTitle(),
                            book.getAuthor(),
                            bookId,
                            book.getPrice(),
                            Integer.parseInt(item.get("quantity").n()),
                            item.get("orderDate").s(),
                            item.get("orderStatus").s(),
                            0
                        );
                        orderModels.add(orderModel);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error retrieving orders: " + e.getMessage());
        }
        
        return orderModels.isEmpty() ? null : orderModels;
    }
    
    public int calculateTotal(String userName) {
        int totalAmount = 0;
        BookDAO bookDAO = new BookDAO();
        
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(ORDERS_TABLE)
                .filterExpression("userName = :userName")
                .expressionAttributeValues(Map.of(":userName", AttributeValue.builder().s(userName).build()))
                .build();
        
        try {
            ScanResponse response = dynamoDbClient.scan(scanRequest);
            for (Map<String, AttributeValue> item : response.items()) {
                if (item.containsKey("bookId")) {
                    int bookId = Integer.parseInt(item.get("bookId").n());
                    int quantity = Integer.parseInt(item.get("quantity").n());
                    
                    Book book = bookDAO.getBookById(bookId);
                    if (book != null) {
                        totalAmount += book.getPrice() * quantity;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error calculating order total: " + e.getMessage());
        }
        
        return totalAmount;
    }
    
    private int generateOrderId() {
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(ORDERS_TABLE)
                .projectionExpression("orderId")
                .build();
        
        int maxOrderId = 0;
        try {
            ScanResponse response = dynamoDbClient.scan(scanRequest);
            for (Map<String, AttributeValue> item : response.items()) {
                if (item.containsKey("orderId")) {
                    int orderId = Integer.parseInt(item.get("orderId").n());
                    maxOrderId = Math.max(maxOrderId, orderId);
                }
            }
        } catch (Exception e) {
            System.err.println("Error generating order ID: " + e.getMessage());
        }
        
        return maxOrderId + 1;
    }
}