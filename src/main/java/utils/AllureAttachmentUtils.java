package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class AllureAttachmentUtils {
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] captureScreenshot(WebDriver driver){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
    public static void attachScreenshot(WebDriver driver){
        Allure.addAttachment("Page screenshot", "image/png", new ByteArrayInputStream(captureScreenshot(driver)).toString());
    }
}
