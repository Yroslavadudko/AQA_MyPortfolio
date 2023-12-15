package Base;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;
import utils.LocalDriver;

import java.io.ByteArrayInputStream;
import java.sql.DriverManager;
import java.time.Duration;
import java.util.List;




public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = LocalDriver.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.get("https://mykoladudko.netlify.app");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            if (!result.isSuccess()) {
                Allure.addAttachment(result.getMethod().getMethodName(),
                        new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            }
        }
        finally {
            LocalDriver.closeDriver();
        }
    }
}