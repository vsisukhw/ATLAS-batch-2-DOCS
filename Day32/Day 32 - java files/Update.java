package CreatingDynamoDB;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class Update {

    static DynamoDbClient client = DynamoDbClient.builder()
            .endpointOverride(URI.create("http://localhost:8000"))
            .region(Region.AP_SOUTH_1)
            .credentialsProvider(StaticCredentialsProvider.create(
                    AwsBasicCredentials.create("fakeAccesskey","fakeSecretKey")))
            .build();

    static String tableName = "Employees_new_02";

    private static void createItems() {
        try{
            Map<String, AttributeValue> item = new HashMap<>();
            item.put("ID", AttributeValue.builder().n("1001").build());
            item.put("Name", AttributeValue.builder().s("John Doe").build());
            item.put("Address", AttributeValue.builder().s("123 Main St").build());

            PutItemRequest putRequest = PutItemRequest.builder()
                    .tableName(tableName)
                    .item(item)
                    .build();

            client.putItem(putRequest);
            System.out.println("Item created and added to table");
        }catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    private static void updateAttribute() {
        try {
            Map<String, AttributeValue> key = new HashMap<>();
            key.put("ID", AttributeValue.builder().n("1001").build());

            Map<String, AttributeValueUpdate> updates = new HashMap<>();
            updates.put("Address", AttributeValueUpdate.builder()
                    .value(AttributeValue.builder().s("456 New Street").build())
                    .action(AttributeAction.PUT)
                    .build());

            UpdateItemRequest updateRequest = UpdateItemRequest.builder()
                    .tableName(tableName)
                    .key(key)
                    .attributeUpdates(updates)
                    .build();

            client.updateItem(updateRequest);
            System.out.println("Item updated successfully");

        }catch (Exception ex) {
            System.err.println("Error updating item: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        createItems();
        updateAttribute();
        client.close();
    }
}