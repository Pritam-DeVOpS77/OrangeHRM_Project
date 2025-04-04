package reuseableBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import extentReport.ExtentReportGeneratorUtil;

public class BaseUtil {
	
	public WebDriver baseDriver;
	public ExtentReports reports;
	public ExtentTest test; 
	File file;
	FileInputStream fis;
	Properties prop;
	String propFilePath ="C:\\Users\\prita\\eclipse-workspace\\OrangeHRM\\src\\main\\java\\dataFile\\data.properties";
	String ScrFilePath ="C:\\Users\\prita\\eclipse-workspace\\OrangeHRM\\screenshots\\";
	Actions act; 
	
	@BeforeMethod 
	//BeforeMethod runs one in entire session before test Method
	public void init() {
		System.out.println("Before Method executed !");
		reports = ExtentReportGeneratorUtil.getReports();
		test = reports.createTest("LoginTestWithValidTestData");          
	}
	
	@BeforeTest
	public void beforeTest() throws IOException {
		prop = new Properties();
		fis = new FileInputStream(propFilePath);
		prop.load(fis);
        fis.close();
	}
    
	public void launchBrowser(String browserName) {
		if(browserName.equals("Chrome")) {
			baseDriver = new ChromeDriver();
			baseDriver.manage().window().maximize();
			baseDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		}else {
			baseDriver = new EdgeDriver();
			baseDriver.manage().window().maximize();
			baseDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		}	
	}
	
	public void launchURL(String URL) {
		baseDriver.get(prop.getProperty("applicationURL"));
		//baseDriver.get(URL);
	}
	
	public void validateTitleOfThePage(String ExpectedTitle) {
		String ActualTitleofThePage = baseDriver.getTitle();
		System.out.println(ActualTitleofThePage);
		Assert.assertEquals(ActualTitleofThePage, ExpectedTitle);
	}
	
	public void takingScreenShot() throws IOException {
		File srcFile =((TakesScreenshot)baseDriver).getScreenshotAs(OutputType.FILE);
		File Destination = new File(ScrFilePath+"test"+System.currentTimeMillis()+".png");
		try {
			FileHandler.copy(srcFile, Destination);
		}catch(IOException e) {
			System.out.println(" Exception is Handled ");
		}	
	}
	
    public By getLocators(String locatorKey) {
		
		By by = null;
		
		if(locatorKey.endsWith("_id"))
		{
			by = By.id(prop.getProperty(locatorKey));
			
		}else if(locatorKey.endsWith("_xpath"))
		{
			by = By.xpath(prop.getProperty(locatorKey));
			
		}else if(locatorKey.endsWith("_name"))
		{
			by = By.name(prop.getProperty(locatorKey));
			
		}else if(locatorKey.endsWith("_css"))
		{
			by = By.cssSelector(prop.getProperty(locatorKey));
			
		}else if(locatorKey.endsWith("_tagName"))
		{
			by = By.tagName(prop.getProperty(locatorKey));
			
		}else if(locatorKey.endsWith("_linkText"))
		{
			by = By.linkText(prop.getProperty(locatorKey));
		}
		
		return by;
	}
	
	public void EnterData(String locator, String textData) {
	     baseDriver.findElement(getLocators(locator)).sendKeys(textData);	
	}
	
	public void ClickAction(String locator) {
	     baseDriver.findElement(getLocators(locator)).click();
	}
	
	public void isElementVisible(String locator) {
		WebDriverWait wait = new WebDriverWait(baseDriver,Duration.ofSeconds(10));
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(getLocators(locator)));
			}catch(Exception e) 
		    {
				System.out.println(e.getLocalizedMessage());
			}
	}
	
	public void scrollAction() {
		JavascriptExecutor jse = (JavascriptExecutor)baseDriver;
		jse.executeScript("window.scrollBy(0,500);");
	}
	
	public void refreshPage() {
		baseDriver.navigate().refresh();
	}
	
	public void moveToElement(String locator) {
		act = new Actions(baseDriver);
		WebElement elementToVisit = baseDriver.findElement(getLocators(locator));
		act.moveToElement(elementToVisit).build().perform();
		System.out.println("Moved to specified WebElement!");	
	}
	
	public void tearDown() {
		baseDriver.close();
	}


}
