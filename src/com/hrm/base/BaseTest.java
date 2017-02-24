package com.hrm.base;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.hrm.pages.DashboardPage;
import com.hrm.pages.LoginPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import generics.TestListener;
import generics.Utility;

@Listeners(TestListener.class)
public abstract class BaseTest implements AutomationConstants{
	
	public WebDriver driver;
	public ExtentTest eTest;
	public String reportFile;
	
	public static Logger log;
	public static String url;
	public static String un;
	public static String pw;
	public static long iTimeout;
	public static long eTimeout;
	public static ExtentReports eReport;
	
	public boolean loginRequired=true;
	public boolean logoutRequired=true;
	
	public BaseTest() {
		log=Logger.getLogger(this.getClass());
	}
	
	@BeforeSuite
	public void initReport()
	{
		log.info("Initializing ExtentReport");
		String now=Utility.getFormatedDateTime();
		reportFile=REPORT_PATH+now+".html";
		eReport=new ExtentReports(reportFile);
	}
	
	@AfterSuite
	public void publishReport()
	{
		log.info("Publishing ExtentReport:"+reportFile);
		eReport.flush();
	}
	
	@BeforeTest
	public void initGlobalVar(){
		log.info("Initialize Global Variables");
		url=Utility.getPropertyValue(CONFIG_PATH,"URL");
		un=Utility.getPropertyValue(CONFIG_PATH,"UN");
		pw=Utility.getPropertyValue(CONFIG_PATH,"PW");
		iTimeout=Long.parseLong(Utility.getPropertyValue(CONFIG_PATH,"IMPLICIT"));
		eTimeout=Long.parseLong(Utility.getPropertyValue(CONFIG_PATH,"EXPLICIT"));
	}
	
	@Parameters({"browser"})
	@BeforeClass
	public void initApplication(@Optional("chrome")String browser){
		log.info("Opening Browser:"+browser);
		if(browser.equals("chrome")){
			System.setProperty(CHROME_KEY,CHROME_VALUE);
			driver=new ChromeDriver();
		}
		else{
			System.setProperty(GECKO_KEY,GECKO_VALUE);
			driver=new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(iTimeout,TimeUnit.SECONDS);
	}

	@AfterClass
	public void closeApplication(){
		log.info("Closing Browser");
		driver.quit();
	}
	
	@BeforeMethod
	public void preCondition(Method method){
		driver.get(url);
		if(loginRequired){
			log.info("Auto login");
			new LoginPage(driver).login(un,pw);
		}
		eTest=eReport.startTest(method.getName());
		log.info("Started executing test:"+method.getName());
	}
	
	@AfterMethod
	public void postCondition(ITestResult testNGTestResult){
		if(logoutRequired){
			log.info("Auto logout");
			new DashboardPage(driver).logout();
		}
		
		if(testNGTestResult.getStatus()==ITestResult.FAILURE)
		{
			eTest.log(LogStatus.FAIL,"Check log for details");
			log.error("Test is FAILED");
		}
		else
		{
			eTest.log(LogStatus.PASS,"Script executed successfully");
			log.info("Test is PASSED");
		}
		eReport.endTest(eTest);
	}
}





