

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
public class CrossBrowser{

	WebDriver driver;
	ExtentTest test;
	@Test
	(dataProvider = "getData")	
	public void TestCases(@Optional String browser){
		
//		if(browser.equalsIgnoreCase("Chrome")) {
//			System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
//			driver = new ChromeDriver();
//			
//		} else if(browser.equalsIgnoreCase("FireFox")){
//			System.setProperty("webdriver.gecko.driver","/Users/visusri/Downloads/geckodriver");
//			driver = new FirefoxDriver();
//			
//		}
//		 else if(browser.equalsIgnoreCase("Safari")){
//				System.setProperty("webdriver.safari.driver","/Applications/Safari.app/Contents/MacOS/Safari");
//				driver = new SafariDriver();
//			}
//		driver.get("https://www.xero.com/");
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		ReusableMethods obj = new ReusableMethods();
		obj.CreateReport();
		obj.InitializeDriver(browser);
		
		obj.TC1_A_Login(browser);
		obj.TC1_B_InvalidLogin(browser);
		obj.TC1_C_invalidUserName(browser);
		obj.TC1_D_ForgotPassword(browser);
		obj.CloseBrowser();
		obj.CloseReport();
		

	}
	@DataProvider
	public Object[][] getData(){
		return new Object[][] {
			{"chrome"},
			{"firefox"},
			
		};
	}
	

}
