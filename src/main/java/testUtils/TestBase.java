package testUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestBase {
	public static Properties prop;
	public static WebDriver driver = null;
	public static String language;
	public static String pswd;
	
			
	public static void initialization(){
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("C:/softWare/eclipse_workspace/MavenFacebookAutom/src/main/java/propertyfiles/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String browserName = prop.getProperty("browser");
		pswd = prop.getProperty("passworD");
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver_win32\\chromedriver.exe");	
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		language = prop.getProperty("language");
		
		
	}	

		
	
	
	
	

}
