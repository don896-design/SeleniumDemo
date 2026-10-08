package base;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import page.SauceInventoryPage;
import page.SaucedemoLoginPage;

public class Saucedemobase {

    public static WebDriver driver;
    public static ExtentSparkReporter reporter;
    public static ExtentReports extent;
    public static ExtentTest test;
    public SauceInventoryPage inventoryPage ;

    @BeforeTest
    public void setUpReport() {
        // Configure HTML Report location & metadata
        reporter = new ExtentSparkReporter("./Reports/myreport1.html");
        reporter.config().setDocumentTitle("Automationreport");
        reporter.config().setReportName("Functional test");
        reporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("hostname", "localhost");
        extent.setSystemInfo("os", "windows10");
        extent.setSystemInfo("testername", "abc");
        extent.setSystemInfo("Browser Name", "chrome");
    }

    @BeforeMethod
    public void setUp(Method method) {
        // Create an entry in Extent Report for the running test method
        test = extent.createTest(method.getName());

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");

        // Auto-login before each test method starts
        SaucedemoLoginPage loginPage = new SaucedemoLoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
         inventoryPage = new SauceInventoryPage(driver);
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        // Log pass/fail status into Extent Report
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Case Failed: " + result.getName());
            test.log(Status.FAIL, "Failure Details: " + result.getThrowable());
            String screenshotpath=screenshotMethod(driver,result.getName());
            test.addScreenCaptureFromPath(screenshotpath);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Case Passed: " + result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Case Skipped: " + result.getName());
        }

        if (driver != null) {
            driver.quit();
        }
    }
    public static String screenshotMethod(WebDriver driver, String screenshotname) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destination = "./Screenshot/" + screenshotname + ".jpeg";
      //File finaldestination=new File(destination);
        //FileHandler.copy(src, finaldestination);
        FileHandler.copy(src, new File(destination));
        return destination;
    }

    @AfterTest
    public void flushReport() {
        // Write all logs to HTML report file
        extent.flush();
    }
}