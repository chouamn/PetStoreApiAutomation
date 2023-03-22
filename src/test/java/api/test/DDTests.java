package api.test;


import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {



	@Test(priority = 1, dataProvider = "Data" , dataProviderClass = DataProviders.class)
	public void testpostuser(String userID, String userName, String Fname, String Lname, String useremail, String pwd, String phone) 
	{
		User userPayload= new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(Fname);
		userPayload.setLastName(Lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phone);

		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testGetUserByName(String userName) 
	{
		Response response	=UserEndPoints.readUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);

	}

	
	@Test(priority = 3, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testdeleteuserByName(String userName) 
	{
		Response response	=UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);

	}


























}
