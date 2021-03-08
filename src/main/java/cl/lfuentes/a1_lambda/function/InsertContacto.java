package cl.lfuentes.a1_lambda.function;



import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import cl.lfuentes.a1_lambda.model.ContactoRequest;
import cl.lfuentes.a1_lambda.model.ContactoResponse;

import java.util.UUID;


public class InsertContacto implements RequestHandler<ContactoRequest, ContactoResponse> {

	private DynamoDB dynamoDb;
	private String DYNAMODB_TABLE_NAME = "ContactoLFV";
	private Regions REGION = Regions.US_EAST_1;

	@Override
	public ContactoResponse handleRequest(ContactoRequest input, Context context) {

		input.setId(UUID.randomUUID().toString());
		input.setStatus("CREATED");

		this.initDynamoDbClient();

		persistData(input);

		return (new ContactoResponse("Se guardo el contacto:" + input.getId()));
	}

	private PutItemOutcome persistData(ContactoRequest request) throws ConditionalCheckFailedException {
		return this.dynamoDb.getTable(DYNAMODB_TABLE_NAME)
				.putItem(new PutItemSpec().withItem(new Item().withString("id", request.getId())
						.withString("firstName", request.getFirstName()).withString("lastName", request.getLastName())
						.withString("status", request.getStatus())));
	}

	private void initDynamoDbClient() {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(REGION).build();
		
		this.dynamoDb = new DynamoDB(client);
	}

}