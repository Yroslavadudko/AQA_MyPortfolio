package utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.domsnapshot.model.StringIndex;
import org.openqa.selenium.firefox.FirefoxDriver;
import static java.lang.System.*;

public class LocalDriver {
    private static WebDriver driver;

    private LocalDriver() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            String browserName = System.getProperty("browserName");
            if(browserName != null && browserName.equalsIgnoreCase("firefox")){
                driver = new FirefoxDriver();
                System.out.println("FireFox browser set");
            }else {
                driver = new ChromeDriver();
                System.out.println("Chrome browser set");
            }
        }

        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
