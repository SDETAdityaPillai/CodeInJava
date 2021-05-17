package pageTest;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pageobjects.LoginPage;
import pageobjects.SignUpPage;
import testUtils.TestBase;

public class LoginUsingAPI extends TestBase{

	public static String code;
	public static String access_token;
	public static String appId = "378891160072557";
	public static String app_secret = "4ba90bd8aa8dd5b55c3b28ca8241367a";
	public static String redirect_URI= "https://dibakar.sreedharacharya945231.com/";
	public static String Encoded_URI = "https%3A%2F%2Fdibakar.sreedharacharya945231.com%2F";
	public static String state = "987654321";
	public static String baseURI = "https://www.facebook.com/v10.0/dialog/oauth";
	public static String graphBaseURI = "https://graph.facebook.com/v10.0/oauth/access_token"; 
	public static String emailUser = "eintisep.hiegynix@gmail.com";
	
	LoginPage loginPage;
	SignUpPage signup;
	public LoginUsingAPI() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws Exception
	{
		initialization();
		loginPage = new LoginPage();
		
	}

	@Test
	public void loginNewUser() throws InterruptedException{
		String updatedURL = loginPage.logtheUser(emailUser,pswd);
			
		System.out.println(updatedURL);
		
		code = updatedURL.substring(updatedURL.indexOf("?")+1,updatedURL.indexOf("&"));
		System.out.println(code);	  
		
		//driver.close();
		
		JSONObject jsonAsMap = new JSONObject();
	     jsonAsMap.put("client_id", appId);
	     jsonAsMap.put("client_secret",app_secret); 
	     jsonAsMap.put("redirect_uri",Encoded_URI);
	     jsonAsMap.put("code",code);
	     
	     String getaccess = graphBaseURI + "?client_id="+appId+"&client_secret="+app_secret+"&redirect_uri="+Encoded_URI+"&"+code;
	     //RequestSpecification mySpec = new RequestSpecBuilder().setUrlEncodingEnabled(false).build();
	     
	  //   Response res =   given()
	    	//	 		.spec(mySpec)
	    /*		 	.contentType("application/json")
	    		 	.queryParam("client_id", appId)
	    		 	.queryParam("client_secret", app_secret)
	    		 	.queryParam("redirect_uri", Encoded_URI)
	    		 	.queryParam("code",code)
	    		 //	.body(jsonAsMap)
	    		 	.when()
	    		   // .get(graphBaseURI + "?client_id=" + appId + "&client_secret=" + app_secret + "&redirect_uri" + Encoded_URI + "&" + code);
	    		 	//.get(graphBaseURI);
	    		 	//.get(getaccess);*/
	    	//	 	.get("https://graph.facebook.com/v10.0/oauth/access_token?client_id=378891160072557&client_secret=4ba90bd8aa8dd5b55c3b28ca8241367a&redirect_uri=https%3A%2F%2Fdibakar.sreedharacharya945231.com%2F&code="+code);
	     
	     //System.out.println(getaccess);
	    // System.out.println(res.getStatusCode());
	    // System.out.println(res.prettyPrint());
	     
	     String token = loginPage.getAccessToken(getaccess);
	     System.out.println("access token = "+ token );
	     
	     
	}
	
}
