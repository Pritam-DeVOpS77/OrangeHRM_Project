package pageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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
	By leftMenuBar = By.xpath("//i[@class='bi-chevron-left']");

	// clicking on Admin
	By AdminLink = By.linkText("Admin");

	// Search by Username field by sending Admin
	By searchByUserName = By.xpath("//input[@class='oxd-input--active']");

	// Search Button
	By SearchButton = By.linkText(" Search ");

	// searching by user role for Admin
	By byUserRoleDropDown = By.linkText("-- Select --");

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

	// Clicking on left Menu bar
	public void leftMenu() {
		WebElement leftMenu = driver.findElement(leftMenuBar);
		Select select = new Select(leftMenu);
		List<WebElement> options = select.getOptions();
		System.out.println("The total entries are : " + options.size());
		System.out.println(options);
	}

	// Clicking on Admin option
	public void adminOption() {
		driver.findElement(AdminLink).click();
	}

	// Search by Username
	public void userNameFieldSearch(String userName) {
		driver.findElement(searchByUserName).sendKeys(userName);
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
		WebElement userRole = driver.findElement(byUserRoleDropDown);
		Select select = new Select(userRole);
		select.selectByIndex(0);
	}

	// search user status
	public void status() {
		WebElement userRole = driver.findElement(byStatusDropDown);
		Select select = new Select(userRole);
		select.selectByIndex(0);
	}
}
