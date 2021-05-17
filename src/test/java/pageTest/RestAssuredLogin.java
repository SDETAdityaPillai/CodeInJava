package pageTest;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

public class RestAssuredLogin {

	public List<String> locale_language = new ArrayList<String>();
	
	
	public String appId = "378891160072557";
	public String app_secret = "4ba90bd8aa8dd5b55c3b28ca8241367a";
	public String redirect_URI= "https://dibakar.sreedharacharya945231.com/";
	public String Encoded_URI = "https%3A%2F%2Fdibakar.sreedharacharya945231.com%2F";
	public String state = "987654321";
	public String baseURI = "https://www.facebook.com/v10.0/dialog/oauth";
	
	//public String baseURI = "https://graph.facebook.com/me";
	public String locale="en_GB";
	//public String access_token = "EAAFYmXkBMW0BAAiDAKzJD4oY9ZCfPhByYoq0h2nnkQI7QPaYUPniPWZBOZC18P8gxbf045px4SDwCiWCZBZCS9UtrvajArDvABXRHk4BYdocc2YrRspGQ42J6MpHZBh6dvQzdBouNp01vSBCqhqbPDmfIORTfSQZAcV4aGjiGmCwiMZBKeARVTkM2dRqCJdX4zIZD";
	//curl -H "Accept: application/json" -H "Authorization: Bearer EAAFYmXkBMW0BAAiDAKzJD4oY9ZCfPhByYoq0h2nnkQI7QPaYUPniPWZBOZC18P8gxbf045px4SDwCiWCZBZCS9UtrvajArDvABXRHk4BYdocc2YrRspGQ42J6MpHZBh6dvQzdBouNp01vSBCqhqbPDmfIORTfSQZAcV4aGjiGmCwiMZBKeARVTkM2dRqCJdX4zIZD" "https://graph.facebook.com/me"
	
	
	@Test
	public void signUpPage(){
		
		JSONObject jsonAsMap = new JSONObject();
	     jsonAsMap.put("appId", appId);
	     jsonAsMap.put("app_secret",app_secret); 
	     jsonAsMap.put("redirect_uri",Encoded_URI);
	     jsonAsMap.put("state","92744392777");
	    
		
	//	jsonAsMap.put("access_token", access_token);
		
	    
	     
	   Response res =   given()
	 	.contentType("application/json")
	 	.body(jsonAsMap)
	 	.when()
	    .post(baseURI);
	  
	   
	   System.out.println(res.getStatusCode());
      // System.out.println(res.asString());
     //  String response = res.jsonPath().getJsonObject("asset_token").toString();
       
       
       //System.out.println(RestAssured.baseURI + ":" + RestAssured.port + RestAssured.basePath + EndPoint.GET_ENDPOINT);
       
       Assert.assertEquals(res.getStatusCode(),200);
       
       
	}
	
			
}
