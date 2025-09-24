package CreatingDynamoDB;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class DeleteItem {

    static DynamoDbClient client = DynamoDbClient.builder()
            .endpointOverride(URI.create("http://localhost:8000"))   // Local DynamoDB
            .region(Region.AP_SOUTH_1)                               // Use your region for real DynamoDB
            .credentialsProvider(StaticCredentialsProvider.create(
                    AwsBasicCredentials.create("fakeAccesskey", "fakeSecretKey")))
            .build();

    static String tableName = "Employees_new_02";

    private static void deleteItem() {
        try {
            // Key of the item to delete
            Map<String, AttributeValue> key = new HashMap<>();
            key.put("ID", AttributeValue.builder().n("1001").build());

            // Delete request
            DeleteItemRequest deleteRequest = DeleteItemRequest.builder()
                    .tableName(tableName)
                    .key(key)
                    .build();

            client.deleteItem(deleteRequest);
            System.out.println("✅ Item deleted successfully");

        } catch (Exception ex) {
            System.err.println("Error deleting item: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        deleteItem();
        client.close();
    }
}
