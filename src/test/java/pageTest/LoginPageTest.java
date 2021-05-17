package pageTest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.LoginPage;
import pageobjects.SignUpPage;
import testUtils.Screenshots;
import testUtils.TestBase;
import testUtils.TestUtil;

public class LoginPageTest extends TestBase{
	
	LoginPage loginPage;
	SignUpPage signup;
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws Exception
	{
		initialization();
		loginPage = new LoginPage();
		String lang = loginPage.setLanguagePref(TestBase.language);
		System.out.println("User prefers <"+ lang + "> language");
	}
	
	@Test(priority=1, dataProvider = "getData")
	public void signUpPage(String firstName , String lastName , String mobile , String email , String password , String dobDay , String dobMonth , String dobYear , String genderRadio , String pronoun , String genderComment) throws Exception{
		
		String loginPageTitle = loginPage.verifyLoginPageTitle();
		Assert.assertEquals(loginPageTitle, "Facebook – log in or sign up","Login page title not matched");
		
		Thread.sleep(1000);
		
		loginPage.clickCreateAccount();
			Thread.sleep(2000);
		loginPage.filldetails(firstName ,lastName ,mobile ,email ,password ,dobDay ,dobMonth ,dobYear ,genderRadio ,pronoun ,genderComment);
	
		validateSignUpPageTest(firstName, email);
	}

	public void validateSignUpPageTest(String firstName, String email) throws IOException{
	//	Assert.assertEquals(signup.validateSignUpPage(), email,"Sign Up page email id not matched");
		
		System.out.println("Email id as on the Signup Confirmation Page ---->"+ signup.validateSignUpPage());
		System.out.println("Sign Up Page ...");
		Screenshots.takeScreenshot(firstName+"_signUp_page");
			
	}
	
	@AfterMethod
	public void closeBrowser()
	{
		System.out.println("Closing the browser");
		driver.close();
	}
	
		
	@DataProvider
	public Object[][] getData() throws InvalidFormatException
	{	Object[][] data = TestUtil.getDataFromSheet();
		return data;
	}
	
}