package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testUtils.TestBase;

public class SignUpPage extends TestBase{

	@FindBy(xpath = "//span[@class='fwb']")
    private WebElement checkMailTest;
	
	public SignUpPage() {
		PageFactory.initElements(driver, this);
 
		
    }
	
	public static boolean  isOnPage() {
        String urlConfirmText = "confirmemail.php";
        if (driver.getCurrentUrl().contains(urlConfirmText)){
        	return true;
        }
		return false;
    }
	
	public String validateSignUpPage(){
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(checkMailTest));
		return checkMailTest.getText();
	}
}
