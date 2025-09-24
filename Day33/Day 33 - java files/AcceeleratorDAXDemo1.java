package org.example;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.EnhancedAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.dax.ClusterDaxAsyncClient;
import software.amazon.awssdk.utils.CompletableFutureUtils;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class AcceeleratorDAXDemo06 {
    public static void main(String[] args) throws Exception {
        // Region where your DAX & DynamoDB are
        Region region = Region.US_EAST_1;

        String daxEndpoint = "daxs://daxcluster01.ee3lf0.dax-clusters.ap-south-1.amazonaws.com";

        ClusterDaxAsyncClient daxAsyncClient = ClusterDaxAsyncClient.builder()
                .overrideConfiguration(conf -> conf.url(daxEndpoint))
                .region(region)
                .build();

        GetItemRequest getReq = GetItemRequest.builder()
                .tableName("MyTable")
                .key(Map.of("PrimaryKey", AttributeValue.builder().s("myKeyValue").build()))
                .build();

        CompletableFuture<GetItemResponse> futureResp = daxAsyncClient.getItem(getReq);

        futureResp.whenComplete((resp, err) -> {
            if (err != null) {
                System.err.println("Failed to get item: " + err.getMessage());
            } else {
                Map<String, AttributeValue> item = resp.item();
                if (item == null || item.isEmpty()) {
                    System.out.println("No item found.");
                } else {
                    System.out.println("Item: " + item);
                }
            }
        }).join();

        EnhancedAsyncClient enhanced = EnhancedAsyncClient.builder()
                .dynamoDbClient(daxAsyncClient)   // use the dax client in place of DynamoDbAsyncClient
                .build();

        daxAsyncClient.close();
    }
}
