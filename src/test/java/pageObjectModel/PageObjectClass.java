package pageObjectModel;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectClass {

	WebDriver driver;

	public PageObjectClass(WebDriver driver) {
		this.driver = driver;
	}

	// Login locators
	By username_loc = By.xpath("//input[@name='username']");
	By password_loc = By.xpath("//input[@name='password']");
	By subButton_loc = By.xpath("//button[@type='submit']");

	// left Menu bar
	//By leftMenuBar = By.xpath("//select[@class='oxd-icon-button']");

	// clicking on Admin
	By AdminLink = By.linkText("Admin");

	// Search by Username field by sending Admin
	By searchByUserName = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/input[1]");

	// Search Button
	By SearchButton = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[2]/button[2]");

	// searching by user role for Admin
	By byUserRoleDropDown = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]");

	// searching by status for enabled/disabled
	By byStatusDropDown = By.xpath("//div[@class='oxd-select-text-input']");

	// Login Action
	public void userNameField(String user) {
		driver.findElement(username_loc).sendKeys(user);
	}

	public void pwdField(String pwd) {
		driver.findElement(password_loc).sendKeys(pwd);
	}

	public void submitButton() {
		driver.findElement(subButton_loc).click();
	}
/*
	// Clicking on left Menu bar
	public void leftMenu() {
		WebElement leftMenu = driver.findElement(leftMenuBar);
		Select select = new Select(leftMenu);
		List<WebElement> options = select.getOptions();
		System.out.println("The total entries are : " + options.size());
		System.out.println(options);
	}  */

	// Clicking on Admin option
	public void adminOption() {
		driver.findElement(AdminLink).click();
	}

	// Search by Username
	public void userNameFieldSearch(String userName) {
		//driver.findElement(searchByUserName).sendKeys(userName);
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			// Wait until the element is visible
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchByUserName));

			// Perform actions on the element
			element.sendKeys(userName);
			
			} catch (ElementNotInteractableException e) {
	            System.out.println("Exception is handled...");
			//driver.findElement(placeOrder).click();
		    }
	}
	
	//clicking on search
	public void clickOnSearch() {
		driver.findElement(SearchButton).click();
	}

	// Refresh page
	public void refreshpage() {
		driver.navigate().refresh();
	}

	// search user role
	public void userRole() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement userRole =wait.until(ExpectedConditions.visibilityOfElementLocated(byUserRoleDropDown));
		
		//WebElement userRole = driver.findElement(byUserRoleDropDown);
		
		Select dropdown = new Select(userRole);
		
		dropdown.selectByVisibleText("Admin");
		
		try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

	// search user status
	public void status() {
		WebElement userRole = driver.findElement(byStatusDropDown);
		Select select = new Select(userRole);
		select.selectByIndex(0);
		
		try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
}
