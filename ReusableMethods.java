

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
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

public class ReusableMethods {

	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	ExtentTest logger;

	@BeforeClass
	public void CreateReport() {

		String fileName = new SimpleDateFormat("'XeroReport_'YYYY.MM.dd.HH.mm'.html'").format(new Date());
		String path = "/Users/visusri/Documents/JavaSelenium/Xero_in_MultiBrowser/Xero_reports/" + fileName;
		report = new ExtentReports(path);
		test = report.startTest("ExtentDemo");
		test.log(LogStatus.PASS, " Report Log starting ...");

	}
	@BeforeMethod
	public void InitializeDriver(String browser) {


		if(browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("FireFox")){
			System.setProperty("webdriver.gecko.driver","/Users/visusri/Downloads/geckodriver");
			driver = new FirefoxDriver();
		}
		driver.get("https://www.xero.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		if(driver.getTitle().equals("Accounting Software - Do Beautiful Business | Xero US"))
		{
			test.log(LogStatus.PASS, browser+" Navigated to the specified URL");
		}
		else
		{
			test.log(LogStatus.FAIL, browser+" Failed to open url");
		}

		//		} else if(browser.equalsIgnoreCase("FireFox")){
		//			System.setProperty("webdriver.gecko.driver","/Users/visusri/Downloads/geckodriver");
		//			driver = new FirefoxDriver();
		//			driver.get("https://www.xero.com/");
		//			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//			if(driver.getTitle().equals("Accounting Software - Do Beautiful Business | Xero US"))
		//			 {
		//			 test.log(LogStatus.PASS, "@FireFox Navigated to the specified URL");
		//			 }
		//			 else
		//			 {
		//			 test.log(LogStatus.FAIL, "@FireFox Failed to open url");
		//			 }
		//		}
		//		 else if(browser.equalsIgnoreCase("Safari")){
		//				System.setProperty("webdriver.safari.driver","/Applications/Safari.app/Contents/MacOS/Safari");
		//				driver = new SafariDriver();
		//			}

	}

	//@AfterMethod
	public void CloseBrowser() {
		driver.quit();
	}

	public void TC1_A_Login(String browser) {

		driver.findElement(By.partialLinkText("Login")).click();
		System.out.println("Login Link Clicked...");
		test.log(LogStatus.INFO, browser +"Login Link Clicked");

		WebElement username = driver.findElement(By.id("email"));
		username.sendKeys("bvinayasri@gmail.com");
		if(username.isEnabled())
		{
			test.log(LogStatus.PASS,"username entered Successfully");
		}
		else {
			test.log(LogStatus.FAIL,"username not entered ");
		}
		System.out.println("Email Id Entered...");

		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("vinayasri@X1");
		if(password.isEnabled())
		{
			test.log(LogStatus.PASS,"Password entered Successfully");
		}
		else {
			test.log(LogStatus.FAIL,"password not entered ");
		}
		System.out.println("Password Entered...");

		WebElement loginBtn = driver.findElement(By.id("submitButton"));

		if(loginBtn.isEnabled())
		{
			loginBtn.click();
			test.log(LogStatus.PASS,"loginButton Clicked");
		}
		else {
			test.log(LogStatus.FAIL,"loginButton not clicked ");
		}

		System.out.println("Log In Clicked...");

		String actualTitle = "My Xero | Home";
		Assert.assertEquals(driver.getTitle(), actualTitle);
		System.out.println("You are In Home Page...");

		if(driver.getTitle().equals("My Xero | Home"))
		{
			test.log(LogStatus.PASS, browser +"Navigated to HOME page");
		}
		else
		{
			System.out.println("You are NOT In Home Page...");
			test.log(LogStatus.FAIL, browser +" Failed to Login");
		}

		test.log(LogStatus.PASS, "TC_1A Passed");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.className("username")).click();
		driver.findElement(By.linkText("Logout")).click();
	}

	public void TC1_B_InvalidLogin(String browser) {

		//		driver.findElement(By.partialLinkText("Login")).click();
		//		System.out.println("Login Link Clicked...");
		//			test.log(LogStatus.INFO, browser +"Login Link Clicked");

		WebElement username = driver.findElement(By.id("email"));
		//		username.clear();
		username.sendKeys("bvinayasri@gmail.com");
		if(username.isEnabled())
		{
			test.log(LogStatus.PASS,"username entered Successfully");
		}
		else {
			test.log(LogStatus.FAIL,"username not entered ");
		}
		System.out.println("Email Id Entered...");

		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("vinayasri");
		if(password.isEnabled())
		{
			test.log(LogStatus.PASS,"Invalid Password entered");
		}
		else {
			test.log(LogStatus.FAIL,"password not entered ");
		}
		System.out.println("Invalid Password Entered...");

		WebElement loginBtn = driver.findElement(By.id("submitButton"));
		loginBtn.click();
		//		if(loginBtn.isEnabled())
		//		 {
		//		 test.log(LogStatus.INFO,"loginButton Clicked");
		//		 }
		System.out.println("Log In Clicked...");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


		driver.findElement(By.xpath("//p[contains(text(),'Your email or password is incorrect')]")).click();
		//		WebElement errormsg =driver.findElement(By.xpath("//p[contains(text(),'Your email or password is incorrect')]"));
		//		 if(errormsg.isDisplayed()){
		test.log(LogStatus.INFO, "Error message displaying 'Your email or password is incorrect'");
		test.log(LogStatus.PASS, "TC_1C Passed");
		//		 }
	} 

	public void TC1_C_invalidUserName(String browser) {

		WebElement username = driver.findElement(By.id("email"));
		//	username.clear();
		username.sendKeys("vinaya@abc.com");
		if(username.isEnabled())
		{
			test.log(LogStatus.PASS,"Invalid username entered");
		}
		else {
			test.log(LogStatus.FAIL,"username not entered ");
		}
		System.out.println("Invalid Email Id Entered...");

		WebElement password = driver.findElement(By.id("password"));

		if(password.isEnabled())
		{
			password.sendKeys("vinayasri@X1");
			test.log(LogStatus.INFO,"Password entered");
		}

		System.out.println("Password Entered...");

		WebElement loginBtn = driver.findElement(By.id("submitButton"));
		loginBtn.click();
		//	if(loginBtn.isEnabled())
		//	 {
		//	 test.log(LogStatus.INFO,"loginButton Clicked");
		//	 }
		System.out.println("Log In Clicked...");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


		driver.findElement(By.xpath("//p[contains(text(),'Your email or password is incorrect')]")).click();
		//	WebElement errormsg =driver.findElement(By.xpath("//p[contains(text(),'Your email or password is incorrect')]"));
		//	 if(errormsg.isDisplayed()){
		test.log(LogStatus.INFO, "Error message displaying 'Your email or password is incorrect'");
		test.log(LogStatus.PASS, "TC_1C Passed");
		//	 }
	} 
	public void TC1_D_ForgotPassword(String browser){

		driver.findElement(By.linkText("Forgot your password?")).click();
		String actualTitle = "Forgotten Password";
		Assert.assertEquals(driver.getTitle(), actualTitle);
		if(driver.getTitle().equals("Forgotten Password"))
		{
			test.log(LogStatus.PASS, browser +"Navigated to Forgotten Password page");
		}
		else
		{
			System.out.println("You are NOT In Forgotten Password Page...");
			test.log(LogStatus.FAIL, browser +" Failed to navigate");
		}

		driver.findElement(By.id("UserName")).sendKeys("bvinayasri@gmail.com");
		test.log(LogStatus.PASS, "Email id entered");
		driver.findElement(By.id("UserName")).click();
		test.log(LogStatus.PASS, "link sent to reset thepassword- clicked");

		test.log(LogStatus.PASS, "TC_1D Passed");

	}

	@AfterClass
	public void CloseReport() {
		report.endTest(test);
		report.flush();

	}
}
