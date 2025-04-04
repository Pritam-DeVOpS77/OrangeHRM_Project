import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import excelUtility.ReadDataFromExcelFile;
import reuseableBase.BaseUtil;

public class LoginTestCaseWithBase extends BaseUtil {
	
	@Test(dataProvider= "getLoginData", dataProviderClass=ReadDataFromExcelFile.class) 
	//dataProviderClass is created just because that class we have created as separate class
	public void login( String username ,String password) throws IOException, InterruptedException 
	{
		launchBrowser("Chrome");
		launchURL("applicationURL");
		validateTitleOfThePage("OrangeHRM");
		isElementVisible("username_xpath");
		EnterData("username_xpath", username);
		EnterData("password_xpath", password);
		ClickAction("submitButton_xpath");
		Thread.sleep(2000);
		takingScreenShot();
		ClickAction("adminButton_linkText");
		Thread.sleep(2000);
		isElementVisible("userManagement_linkText");
		takingScreenShot();
	}

	@AfterMethod
	public void closeDrivers() {
		System.out.println("After Method");
		baseDriver.close();
		test.log(Status.PASS, MarkupHelper.createLabel("OrangeHRM : Pass", ExtentColor.CYAN));
		reports.flush();
	}
}
