package OnlineBookstoreSystem.noSql.util;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

public class DynamoDBConnection {
    private static DynamoDbClient dynamoDbClient;
    
    public static DynamoDbClient getClient() {
        if (dynamoDbClient == null) {
            dynamoDbClient = DynamoDbClient.builder()
                    .endpointOverride(URI.create("http://localhost:8000"))
                    .region(Region.US_EAST_1)
                    .credentialsProvider(StaticCredentialsProvider.create(
                            AwsBasicCredentials.create("dummy", "dummy")))
                    .build();
        }
        return dynamoDbClient;
    }
    
    public static void closeClient() {
        if (dynamoDbClient != null) {
            dynamoDbClient.close();
        }
    }
}