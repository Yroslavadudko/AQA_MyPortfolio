package MyPortfolio;

import Base.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static utils.AllureAttachmentUtils.attachScreenshot;

public class TestHomePage extends BaseTest {
    @Test(groups = "allTests")
    public void testHomePage() {
        openHomePage();
        verifyTitle();
        verifyBodyText();
        verifyHomeLink();
    }
    @Step("Open the home page")
    private void openHomePage() {

        WebElement homeLink = driver.findElement(By.xpath("//a[contains(text(),'home')]"));
        homeLink.click();
    }
    @Step("Verify the Title")
    private void verifyTitle() {

        String expectedPageTitle = "Mykola Dudko - Quality Assurance Engineer";
        assertEquals(driver.getTitle(), expectedPageTitle, "The page title does not match the expected text");
    }
    @Step("Verify the body text")
    private void verifyBodyText() {

        WebElement bodyElement = driver.findElement(By.xpath("//body/section[1]/h1[1]"));
        String expectedBodyText = "hello, i'm\nmykola";
        String actualBodyText = bodyElement.getText().toLowerCase().trim();
        assertEquals(actualBodyText, expectedBodyText, "The body text does not match the expected text");
    }
    @Step("Verify the Home link")
    private void verifyHomeLink() {

        try {
            WebElement homelink = driver.findElement(By.xpath("//a[contains(text(),'home')]"));
            Assert.assertTrue(homelink.isDisplayed());
            assertEquals(homelink.getText(), "Home");
        } catch (AssertionError e) {
            attachScreenshot(driver);
            throw e;
        }
    }
}
