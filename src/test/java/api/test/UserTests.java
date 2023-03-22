package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	Faker faker;
	User userPayload;
public Logger logger;
	@BeforeClass
	public void setup()
	{
		faker= new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		// logs
		logger= LogManager.getLogger(this.getClass());
	}


	@Test (priority = 1)

	public void testPostuser()
	{
		logger.info("*************Creating user ****************");
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*************User is created ****************");
	}

	@Test(priority = 2)
	public void testgetUserByName() 
	{
		logger.info("*************Reading User Info ****************");
		Response response =UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*************User info is displayed ****************");
	}

	@Test(priority = 3)
	public void testupdetuserByname() 
	{
		logger.info("*************updating User Info ****************");
		// update data by using payload 
		userPayload.setUsername(faker.name().username());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setFirstName(faker.name().firstName());
		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body().statusCode(200);
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************* User is updated Info ****************");
		// Checking data after update
		Response responseAfterUpdate =UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	
		
	}

	@Test(priority = 4)
	public void testDeleteUserByName() 
	{
		logger.info("*************Deleting User ****************");
		Response response =UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*************User is Deleted  ****************");
	}







}
