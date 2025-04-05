package pageObjectModel;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PageObjectTestClass {
	
	WebDriver driver;
	String URL ="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	
	@BeforeTest
	public void tearUp() {
	    driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);		
	}
	
	@Test(priority=1)
	public void loginTest() throws InterruptedException  {
		
		//object of page object class
		PageObjectClass test = new PageObjectClass(driver);
		
		//login with valid credentials
		test.userNameField("Admin");
		test.pwdField("admin123");
		test.submitButton();
		
		//left Menu bar
		//Thread.sleep(2000);
		//test.leftMenu();
		
		//clicking on admin option
		Thread.sleep(2000);
		test.adminOption();
		
		//search by entering Admin in search field
		Thread.sleep(2000);
		test.userNameFieldSearch("Admin");
		Thread.sleep(2000);
		test.clickOnSearch();
		Thread.sleep(2000);
		
		//refresh Page
		test.refreshpage();
		
		//searching by user Role
		test.userRole();
		Thread.sleep(1000);
		test.clickOnSearch();
		Thread.sleep(2000);
		
		//refresh Page
	    test.refreshpage();
		
		//searching by status
	    test.status();
	    Thread.sleep(1000);
	    test.clickOnSearch();
		Thread.sleep(2000);	
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
