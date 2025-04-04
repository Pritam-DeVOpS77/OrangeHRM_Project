package extentReport;

import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportGeneratorUtil {
	
	static ExtentReports reports;
	static ExtentTest test;
	
	public static ExtentReports getReports() {
		
		if(reports == null) {
			
			reports = new ExtentReports();
			
			Date d = new Date();
			
			System.out.println(d.toString().replaceAll(":", "_"));
			//String reportsFolder = d.toString().replaceAll("", "")+"//screenShot";
			
			String reportFolderPath = System.getProperty("user.dir")+"//reports//"+d.toString().replaceAll(":", "_");
			
			ExtentSparkReporter htmlreport = new ExtentSparkReporter(reportFolderPath);
			
			//setting up the environment configuration
			reports.setSystemInfo("Laptop", "Lenovo");
			reports.setSystemInfo("OS Version", "Windows 11");
			reports.setSystemInfo("RAM", " 16 GB");
			reports.setSystemInfo("Processor", " Intel core i5");
			
			//Configuration of report for look and feel
			htmlreport.config().setDocumentTitle("Login Automation Report");
			htmlreport.config().setReportName("Prod_Regression : OrangeHRM");
			htmlreport.config().setTheme(Theme.DARK);
			htmlreport.config().setTimeStampFormat("dd-mm-yyyy");
			htmlreport.config().setEncoding("utf-8");
			
			reports.attachReporter(htmlreport);
			
		}
	
		return reports;
	}


}
