package OnlineBookstoreSystem.noSql.dao;

import OnlineBookstoreSystem.model.User;
import OnlineBookstoreSystem.noSql.util.DynamoDBConnection;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO {
    private static final String TABLE_NAME = "Users";
    private final DynamoDbClient dynamoDbClient;
    
    public UserDAO() {
        this.dynamoDbClient = DynamoDBConnection.getClient();
    }
    
    public void createUser(User user) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("userName", AttributeValue.builder().s(user.getUserName()).build());
        item.put("role", AttributeValue.builder().s(user.getRole()).build());
        
        PutItemRequest request = PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(item)
                .build();
        
        try {
            dynamoDbClient.putItem(request);
            System.out.println("User created successfully");
        } catch (Exception e) {
            System.err.println("Error creating user: " + e.getMessage());
        }
    }
    
    public User getUserByName(String userName) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("userName", AttributeValue.builder().s(userName).build());
        
        GetItemRequest request = GetItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(key)
                .build();
        
        try {
            GetItemResponse response = dynamoDbClient.getItem(request);
            if (response.hasItem()) {
                Map<String, AttributeValue> item = response.item();
                User user = new User();
                user.setUserName(item.get("userName").s());
                user.setRole(item.get("role").s());
                return user;
            }
        } catch (Exception e) {
            System.err.println("Error retrieving user: " + e.getMessage());
        }
        
        return null;
    }
    
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(TABLE_NAME)
                .build();
        
        try {
            ScanResponse response = dynamoDbClient.scan(scanRequest);
            for (Map<String, AttributeValue> item : response.items()) {
                User user = new User();
                user.setUserName(item.get("userName").s());
                user.setRole(item.get("role").s());
                users.add(user);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving users: " + e.getMessage());
        }
        
        return users;
    }
    
    public void updateUser(User user) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("userName", AttributeValue.builder().s(user.getUserName()).build());
        
        Map<String, AttributeValueUpdate> updates = new HashMap<>();
        updates.put("role", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(user.getRole()).build())
                .action(AttributeAction.PUT)
                .build());
        
        UpdateItemRequest request = UpdateItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(key)
                .attributeUpdates(updates)
                .build();
        
        try {
            dynamoDbClient.updateItem(request);
            System.out.println("User updated successfully");
        } catch (Exception e) {
            System.err.println("Error updating user: " + e.getMessage());
        }
    }
    
    public void deleteUser(String userName) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("userName", AttributeValue.builder().s(userName).build());
        
        DeleteItemRequest request = DeleteItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(key)
                .build();
        
        try {
            dynamoDbClient.deleteItem(request);
            System.out.println("User deleted successfully");
        } catch (Exception e) {
            System.err.println("Error deleting user: " + e.getMessage());
        }
    }
}