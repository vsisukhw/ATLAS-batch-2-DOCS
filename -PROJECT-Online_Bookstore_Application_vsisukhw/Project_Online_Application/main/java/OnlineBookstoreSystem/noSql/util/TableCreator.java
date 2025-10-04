package OnlineBookstoreSystem.noSql.util;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

public class TableCreator {
    private static final DynamoDbClient dynamoDbClient = DynamoDBConnection.getClient();
    
    public static void createAllTables() {
        createBooksTable();
        createUsersTable();
        createCartTable();
        createOrdersTable();
        createBrowsingHistoryTable();
        createRecommendedBooksTable();
    }
    
    private static void createBooksTable() {
        CreateTableRequest request = CreateTableRequest.builder()
                .tableName("Books")
                .keySchema(KeySchemaElement.builder()
                        .attributeName("id")
                        .keyType(KeyType.HASH)
                        .build())
                .attributeDefinitions(AttributeDefinition.builder()
                        .attributeName("id")
                        .attributeType(ScalarAttributeType.N)
                        .build())
                .billingMode(BillingMode.PAY_PER_REQUEST)
                .build();
        
        try {
            dynamoDbClient.createTable(request);
            System.out.println("Books table created");
        } catch (ResourceInUseException e) {
            System.out.println("Books table already exists");
        }
    }
    
    private static void createUsersTable() {
        CreateTableRequest request = CreateTableRequest.builder()
                .tableName("Users")
                .keySchema(KeySchemaElement.builder()
                        .attributeName("userName")
                        .keyType(KeyType.HASH)
                        .build())
                .attributeDefinitions(AttributeDefinition.builder()
                        .attributeName("userName")
                        .attributeType(ScalarAttributeType.S)
                        .build())
                .billingMode(BillingMode.PAY_PER_REQUEST)
                .build();
        
        try {
            dynamoDbClient.createTable(request);
            System.out.println("Users table created");
        } catch (ResourceInUseException e) {
            System.out.println("Users table already exists");
        }
    }
    
    private static void createCartTable() {
        CreateTableRequest request = CreateTableRequest.builder()
                .tableName("Cart")
                .keySchema(
                        KeySchemaElement.builder()
                                .attributeName("userName")
                                .keyType(KeyType.HASH)
                                .build(),
                        KeySchemaElement.builder()
                                .attributeName("bookId")
                                .keyType(KeyType.RANGE)
                                .build())
                .attributeDefinitions(
                        AttributeDefinition.builder()
                                .attributeName("userName")
                                .attributeType(ScalarAttributeType.S)
                                .build(),
                        AttributeDefinition.builder()
                                .attributeName("bookId")
                                .attributeType(ScalarAttributeType.N)
                                .build())
                .billingMode(BillingMode.PAY_PER_REQUEST)
                .build();
        
        try {
            dynamoDbClient.createTable(request);
            System.out.println("Cart table created");
        } catch (ResourceInUseException e) {
            System.out.println("Cart table already exists");
        }
    }
    
    private static void createOrdersTable() {
        CreateTableRequest request = CreateTableRequest.builder()
                .tableName("Orders")
                .keySchema(
                        KeySchemaElement.builder()
                                .attributeName("orderId")
                                .keyType(KeyType.HASH)
                                .build(),
                        KeySchemaElement.builder()
                                .attributeName("userName")
                                .keyType(KeyType.RANGE)
                                .build())
                .attributeDefinitions(
                        AttributeDefinition.builder()
                                .attributeName("orderId")
                                .attributeType(ScalarAttributeType.N)
                                .build(),
                        AttributeDefinition.builder()
                                .attributeName("userName")
                                .attributeType(ScalarAttributeType.S)
                                .build())
                .billingMode(BillingMode.PAY_PER_REQUEST)
                .build();
        
        try {
            dynamoDbClient.createTable(request);
            System.out.println("Orders table created");
        } catch (ResourceInUseException e) {
            System.out.println("Orders table already exists");
        }
    }
    
    private static void createBrowsingHistoryTable() {
        CreateTableRequest request = CreateTableRequest.builder()
                .tableName("BrowsingHistory")
                .keySchema(
                        KeySchemaElement.builder()
                                .attributeName("userName")
                                .keyType(KeyType.HASH)
                                .build(),
                        KeySchemaElement.builder()
                                .attributeName("bookId")
                                .keyType(KeyType.RANGE)
                                .build())
                .attributeDefinitions(
                        AttributeDefinition.builder()
                                .attributeName("userName")
                                .attributeType(ScalarAttributeType.S)
                                .build(),
                        AttributeDefinition.builder()
                                .attributeName("bookId")
                                .attributeType(ScalarAttributeType.N)
                                .build())
                .billingMode(BillingMode.PAY_PER_REQUEST)
                .build();
        
        try {
            dynamoDbClient.createTable(request);
            System.out.println("BrowsingHistory table created");
        } catch (ResourceInUseException e) {
            System.out.println("BrowsingHistory table already exists");
        }
    }
    
    private static void createRecommendedBooksTable() {
        CreateTableRequest request = CreateTableRequest.builder()
                .tableName("RecommendedBooks")
                .keySchema(KeySchemaElement.builder()
                        .attributeName("Id")
                        .keyType(KeyType.HASH)
                        .build())
                .attributeDefinitions(AttributeDefinition.builder()
                        .attributeName("Id")
                        .attributeType(ScalarAttributeType.N)
                        .build())
                .billingMode(BillingMode.PAY_PER_REQUEST)  // ✅ match others
                .build();


        try {
            dynamoDbClient.createTable(request);
            System.out.println("RecommendedBooks table created");
        } catch (ResourceInUseException e) {
            System.out.println("RecommendedBooks table already exists");
        }
    }
}