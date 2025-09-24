package Dax;// DAX demo...
//annotations @
//@DynamoDBTable
//@DynamoDBHashKey
//@DynamoDBRangeKey
//@Service
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dax.DaxClient;
import software.amazon.awssdk.services.dax.endpoints.internal.Value;


import java.net.URI;
@DynamoDBTable(tableName = "DaxDynamoTable")
class DaxTable {
    @DynamoDBHashKey
    private String hashKey;


    @DynamoDBRangeKey
    private String range;


    public String getHashKey() {
        return hashKey;
    }


    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }


    public DaxTable(){


    }
    public DaxTable UsingHashKey (String hashKey) {
        this.hashKey=hashKey;
        return this;
    }
    public String getRange() {
        return range;
    }
    public void setRange(String range) {
        this.range=range;
    }
    public DaxTable usingRange(String range) {
        this.range = range;
        return this;
    }
}


public class AcceeleratorDAXDemo {


    public static void main(String[] args) {
//        DynamoDbClient


        String daxEndpoint = " ";


        DaxClient daxClient = DaxClient.builder()
                .endpointOverride(URI.create(daxEndpoint))
                .region(Region.AP_SOUTH_1)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
        //table name
        //key name
        // kay value
    }


}
