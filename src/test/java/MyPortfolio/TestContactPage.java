package MyPortfolio;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestContactPage extends BaseTest {

    @Test(groups = "allTests")
    public void testContactPage() {
        WebElement contactLink = driver.findElement(By.xpath("//nav[@class='navbar']//a[text()='contact']"));
        contactLink.click();

        // Find the "contact" element using JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String contactHeader = (String) js.executeScript("return document.querySelector('section.contact-section h1').textContent");
        String expectedContactHeaderText = "Contact Information";
        assertEquals(contactHeader, expectedContactHeaderText, "The page title does not match the expected text");

        // Click on the body of the page to lose focus on "my about"
        js.executeScript("document.body.click()");

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

        // Find and click the submit button
        WebElement submitButton = contactForm.findElement(By.cssSelector("button.form-submit-btn"));
        submitButton.click();

        // After submitting, wait for the "Back to Our Site" button to appear
        WebElement backToOurSiteButton = driver.findElement(By.xpath("//a[@id='back-link']"));
        backToOurSiteButton.click();

        // Find the contact link again after returning to the main page
        contactLink = driver.findElement(By.xpath("//button[contains(text(),'contact')]"));

        Assert.assertTrue(contactLink.isDisplayed());
        assertEquals(contactLink.getText(), "Contact");
    }
}


