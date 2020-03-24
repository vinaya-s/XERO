package xero_parallelTesting;

import static org.testng.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browserlaunch { //extends ReusableMethods  {
	
	WebDriver driver;
	
	
	@BeforeMethod  
	@Parameters({"browser"})
	public void openBrowser(@Optional String browser) throws InterruptedException {

//		ReusableMethods obj =new ReusableMethods();
//		obj.CreateReport();
        
        if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver","/Users/visusri/Downloads/chromedriver");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			System.out.println("Opening Chrome...");	
//			logger.log(LogStatus.INFO, "Url Opened in Chrome Browser Successfully");
		
		}
		
		else if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver","/Users/visusri/Downloads/geckodriver"); // "/Applications/Firefox.app/Contents/MacOS/firefox");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Opening Firefox...");
//			logger.log(LogStatus.INFO, "Url Opened in Firefox Browser Successfully");

		}
//		else if (browser.equalsIgnoreCase("safari")) {
//			System.setProperty("webdriver.safari.driver", "/Applications/Safari.app/Contents/MacOS/Safari");
//			driver = new SafariDriver();
//			driver.manage().window().maximize();
//			
//			System.out.println("Opening Safari...");
////			logger.log(LogStatus.INFO, "Url Opened in Safari Browser Successfully");
//
//		}
		else
			System.out.println("no browser is setup");
	}
		
//		@Test
		public void LoginTest(){
			driver.get("https://www.xero.com/us/");
			
		driver.findElement(By.partialLinkText("Login")).click();
		System.out.println("Login Link Clicked...");
		WebElement username = driver.findElement(By.id("email"));
		username.sendKeys("bvinayasri@gmail.com");
		System.out.println("Email Id Entered...");
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("vinayasri@X1");
		System.out.println("Password Entered...");
		WebElement loginBtn = driver.findElement(By.id("submitButton"));
		loginBtn.click();
		System.out.println("Log In Clicked...");
		
		String actualTitle = "My Xero | Home";
		 Assert.assertEquals(driver.getTitle(), actualTitle);
		 System.out.println("You are In Home Page...");
		}
	
//		public void invalidlogin() {
//			driver.findElement(By.partialLinkText("Login")).click();
//			System.out.println("Login Link Clicked...");
//			WebElement username = driver.findElement(By.id("email"));
//			username.sendKeys("bvinayasri@gmail.com");
//			System.out.println("Email Id Entered...");
//			WebElement password = driver.findElement(By.id("password"));
//			password.sendKeys("vinayasri@X");
//			System.out.println("incorrect Password entered...");
//			WebElement loginBtn = driver.findElement(By.id("submitButton"));
//			loginBtn.click();
//			System.out.println("Log In Clicked...");
//		}
	
	@AfterTest
	public void teardown() {
		try{
			driver.wait(15000);
			 
			}
			 
			catch(Exception e)
			{}

		driver.close();
		driver.quit();
		
	}

}

//ReusableMethods obj =new ReusableMethods();
//obj.CloseReport();
//if(username.isDisplayed()) {
//logger.log(LogStatus.INFO, "username Element found and sending the keys"); }
//if(password.isDisplayed()) {
//	logger.log(LogStatus.INFO, "password box found and sending the keys"); }
///		if(loginBtn.isDisplayed()) {
//logger.log(LogStatus.INFO, "loginButton is found"); }

	
