package MyPortfolio;

import Base.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;

import static org.testng.Assert.assertEquals;
import static utils.AllureAttachmentUtils.attachScreenshot;

public class TestContactPage extends BaseTest {

    @Test(groups = "allTests")
    public void testContactPage() {
        openContactPage();
        verifyContactForm();
        verifyContactLink();
        verifySinoptikInformer();
        verifyMyCity();
        verifyContactLink();
    }
    @Step("Open the contact page")
    private void openContactPage() {
        WebElement contactLink = driver.findElement(By.xpath("//nav[@class='navbar']//a[text()='contact']"));
        contactLink.click();

        // Find the "contact" element using JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String contactHeader = (String) js.executeScript("return document.querySelector('section.contact-section h1').textContent");
        String expectedContactHeaderText = "Contact Information";
        assertEquals(contactHeader, expectedContactHeaderText, "The page title does not match the expected text");

        // Click on the body of the page to lose focus on "my about"
        js.executeScript("document.body.click()");
    }

    @Step("Verify the Contact-Form")
    private void verifyContactForm(){

        // Find the contact form
        WebElement contactForm = driver.findElement(By.cssSelector("form.contact-form"));

        // Find the name, email, and message elements within the form
        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement emailInput = driver.findElement(By.id("email"));
        WebElement messageInput = driver.findElement(By.id("msg"));

        // Find in the form fields
        nameInput.sendKeys("Myk");
        emailInput.sendKeys("myk@myk.mk");
        messageInput.sendKeys("Hello, this is an AQA test");

        // Take a screenshot
        TakesScreenshot screenshot = (TakesScreenshot)  driver;
        byte[] screenshotBytes = screenshot.getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("Form Input", new ByteArrayInputStream(screenshotBytes));

        // Find and click the submit button
        WebElement submitButton = contactForm.findElement(By.cssSelector("button.form-submit-btn"));
        submitButton.click();

        // After submitting, wait for the "Back to Our Site" button to appear
        WebElement backToOurSiteButton = driver.findElement(By.xpath("//a[@id='back-link']"));
        backToOurSiteButton.click();
    }

    @Step("Verify Sinoptik Informer element")
    private void verifySinoptikInformer(){

        // Find the Sinoptik element
        WebElement sinoptikInformer = driver.findElement(By.xpath("//div[@id='SinoptikInformer']"));
        if (sinoptikInformer.isDisplayed()){
            System.out.println("The Sinoptik element is present on the page.");
        }else {
            System.out.println("Sinoptik element not found or not displayed.");
        }
    }

    @Step("Verify My City element")
    private void verifyMyCity(){

        // Find the My City element
        WebElement myCity = driver.findElement(By.xpath("//body/section[4]/iframe[1]"));
        if (myCity.isDisplayed()){
            System.out.println("The My City element is present on the page.");
        }else {
            System.out.println("My City element not found or not displayed.");
        }
    }

    @Step("Verify the Contact link")
    private void verifyContactLink(){

        // Find the contact link again after returning to the main page
        try {
            WebElement contactlink = driver.findElement(By.xpath("//button[contains(text(),'contact')]"));
            Assert.assertTrue(contactlink.isDisplayed());
            assertEquals(contactlink.getText(), "Contact");
        } catch (AssertionError e) {
            attachScreenshot(driver);
            throw e;
        }
    }
}




