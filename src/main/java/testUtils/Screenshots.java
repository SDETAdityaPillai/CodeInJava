package testUtils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import testUtils.TestBase;

public class Screenshots extends TestBase{
	
	public static void takeScreenshot(String fileName) throws IOException{
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, 
				new File("C:/softWare/eclipse_workspace/MavenFacebookAutom/src/main/java/screenshots/" + fileName +".png"));

	}

}
