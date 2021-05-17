package pageobjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import testUtils.Screenshots;
import testUtils.TestBase;
import testUtils.TestUtil;


public class LoginPage  extends TestBase{

public static Select select;	
	
	public static String signUpStatus;
    @FindBy(xpath = "//input[@id='email']")
    private WebElement userEmail;

    @FindBy(xpath = "//input[@id='pass']")
    private WebElement userPassword;

    @FindBy(xpath = "//button[@name='login']")
    private WebElement clickLogin;

    @FindBy(xpath = "//div/a[@role='button' and @rel='async']")
    private WebElement createAccount;

    @FindBy(name = "firstname")
    private WebElement firstName;

    @FindBy(name = "lastname")
    private WebElement lastName;
    
    @FindBy(xpath = "//input[@name='reg_email__']") 
    private WebElement mobileEmailInput;
    
    @FindBy(xpath = "//input[@name='reg_email_confirmation__']")
    private WebElement reconfirmEmailInput;
    
    @FindBy(xpath = "//input[@name='reg_passwd__']") 
    private WebElement passwordInput;

    @FindBy(xpath = "//select[@title='Month']") 
    private WebElement dobMonth;

    @FindBy(xpath = "//select[@title='Year']") 
    private WebElement dobYear;

    @FindBy(xpath = "//select[@title='Day']") 
    private WebElement dobDay;

    @FindBy(xpath = "//ul[contains(@class,'locale')]/li/a") //gettitle = 'Hindi' 
    private List<WebElement> lang;

    
    @FindBy(xpath = "//ul[contains(@class,'locale')]/li")
    private WebElement currentlang;
    
    
    @FindBy(xpath = "//input[@name='sex']") //value = 1:Female, 2: Male, 3: Custom 
    private List<WebElement> genderRadio;
    
    @FindBy(xpath = "//select[@name='preferred_pronoun']") //value = 1:She, 2:He, 6:They 
    private WebElement customGenderdropdwn;

    @FindBy(xpath = "//input[@name='custom_gender']") 
    private WebElement genderUserText;

    @FindBy(xpath = "//button[@name='websubmit']") 
    private WebElement finalSignUpButton;
  
    @FindBy(xpath = "//input[@id='email']")
    private WebElement loginUser;
    
  
    
    public LoginPage() {
	PageFactory.initElements(driver, this);
 
    }

    public boolean isOnPage() {
        String title = "Facebook - Login or sign up";
        return driver.getTitle() == title;
    }
    
    public String verifyLoginPageTitle(){
		return driver.getTitle();
		
	}
    
    public boolean clickCreateAccount(){
    	try{
    		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(createAccount)).isEnabled();
    		Actions action = new Actions(driver);
    		Action act = action.moveToElement(createAccount)
    						   .click()
    						   .build();
    															
    		
    		act.perform();
    		
    	}
     catch (Exception e) {
		System.out.println("Unable to click Create Account Button");
	}
		return false;
    	
	}

	public void filldetails(String firstName2, String lastName2, String mobile, String email2, String password2,
			String dobDay2, String dobMonth2, String dobYear2, String genderRadio2, String pronoun,
			String genderComment) throws Exception {
		
		firstName.clear();
		firstName.sendKeys(firstName2);
		
		lastName.clear();
		lastName.sendKeys(lastName2);
		
		if (email2.equals(null)){
		mobileEmailInput.clear();
		mobileEmailInput.sendKeys(mobile);
		}
		else{
			mobileEmailInput.clear();
			mobileEmailInput.sendKeys(email2);
		
			Thread.sleep(1000);
			if(reconfirmEmailInput.isDisplayed())
				if(reconfirmEmailInput.isEnabled())
			reconfirmEmailInput.sendKeys(email2);
		}
		
		passwordInput.clear();
		passwordInput.sendKeys(password2);
		
		select = new Select(dobDay);
		//dobDay.click();
		select.selectByValue(""+dobDay2);
		
		Thread.sleep(1000);
		
		select = new Select(dobMonth);
		//dobMonth.click();
		select.selectByValue(""+dobMonth2);
				
		Thread.sleep(1000);
		
		select = new Select(dobYear);
		//dobYear.click();
		select.selectByValue(""+dobYear2);
		
		
		Thread.sleep(1000);
		
		int gender = 0;
		if (genderRadio2.equalsIgnoreCase("Female")) gender = 1;
		if (genderRadio2.equalsIgnoreCase("Male")) gender = 2;
		if (genderRadio2.equalsIgnoreCase("Custom")) gender = -1;
		
		for (int i = 0;i<genderRadio.size();i++){
			if (genderRadio.get(i).getAttribute("value").equals(""+gender)){
			genderRadio.get(i).click();
			break;
			}
		}
		
		if (gender == -1){
			select = new Select(customGenderdropdwn);
			//customGenderdropdwn.click();
			int pron=0;
			
			if (pronoun.equalsIgnoreCase("She")) pron=1; 
			if (pronoun.equalsIgnoreCase("He")) pron=2;
			if (pronoun.equalsIgnoreCase("They")) pron=6;
			
			select.selectByValue(""+pron);
			
			genderUserText.clear();
			genderUserText.sendKeys("Test");
		}
		
		Actions action = new Actions(driver);
		Action act = action.moveToElement(finalSignUpButton)
						   .click()
						   .build();
				
		act.perform();
		
		Thread.sleep(10000);
		
	}
	
	public String setLanguagePref(String language) throws InterruptedException{
		
		if (currentlang.getText().contains(language)){
			return language;
		}
		for(int i=0; i<lang.size();i++){
			System.out.println(lang.get(i).getAttribute("title"));
			if (lang.get(i).getAttribute("title").equalsIgnoreCase(language)){
				String l1 = language;
				Actions action = new Actions(driver);
				Action act = action.moveToElement(lang.get(i))
								   .click()
								   .build();
						
				act.perform();
				l1 =lang.get(i).getAttribute("title");
				return l1;
			}	
		}
		Thread.sleep(10000);
		return language;
	}

	public String logtheUser(String email, String password) throws InterruptedException {
		driver.get("https://www.facebook.com/v10.0/dialog/oauth?client_id=378891160072557&redirect_uri=https%3A%2F%2Fdibakar.sreedharacharya945231.com%2F&state=9876054321");
		Thread.sleep(2000);
		userEmail.clear();
		userEmail.sendKeys(email);
		
		userPassword.clear();
		userPassword.sendKeys(password);
		
		clickLogin.click();
		
		Thread.sleep(2000);
		String access_code_url = driver.getCurrentUrl();
		
		return access_code_url;
		
	}

	public String getAccessToken(String getaccess) {
		driver.navigate().to(getaccess);
		String access =  driver.findElement(By.tagName("html")).getText().trim();
		System.out.println(access);
		String token = access.substring(access.indexOf(":")+2, access.indexOf(",")-2);
		System.out.println(token);
		return token;
	}
	
	
}

