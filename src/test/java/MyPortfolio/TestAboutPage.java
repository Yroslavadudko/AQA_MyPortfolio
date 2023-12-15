package MyPortfolio;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class TestAboutPage extends BaseTest {
  String mainWindowHandle = null;
  Set<String> allWindowHandles = null;


    //Checking the navigation menu/About
  @Test(groups = "allTests")
  public void testAboutPage() {

    WebElement aboutLink = driver.findElement(By.xpath("//nav[@class='navbar']//a[text()='about']"));
    aboutLink.click();
    //Find the "my about" element using JavaScript
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String aboutHeader = (String) js.executeScript
            ("return document.querySelector('section.about-section.active h1[hidden]').textContent");
    String expectedAboutHeaderText = "my about";
    assertEquals(aboutHeader, expectedAboutHeaderText, "The page title does not match the expected text");
    //Additional checks and actions
    Assert.assertTrue(aboutLink.isDisplayed());
    assertEquals(aboutLink.getText(), "About");
    //Click on the body of the page to lose focus on "Projects"
    js.executeScript("document.body.click()");
    //Click in the "My resume"
    mainWindowHandle = driver.getWindowHandle();
    WebElement resumeOpen = driver.findElement(By.xpath("//button[text()='My resume']"));
    resumeOpen.click();
    //Switch to the new window
    allWindowHandles = driver.getWindowHandles();
    for (String windowHandle : allWindowHandles) {
      driver.switchTo().window(windowHandle);
    }
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    wait.until(ExpectedConditions
            .visibilityOfElementLocated(By.xpath("//a[contains(text(),'about')]")));
    driver.close();
    driver.switchTo().window(mainWindowHandle);
    //Find the "skills" section
    WebElement skillsSection = driver.
            findElement(By.xpath("//div[@class='skill-section']//h1[@class='heading']"));
    String expectedSkillsHeaderText = "skills";
    String actualSkillsHeaderText = skillsSection.getText();
    assertEquals(actualSkillsHeaderText, expectedSkillsHeaderText,
            "The page title does not match the expected text");
    //Click in the "Certificate QA"
    mainWindowHandle = driver.getWindowHandle();
    WebElement certificateQaOpen = driver.findElement(By.xpath("//button[text()='Certificate QA Eng.']"));
    certificateQaOpen.click();
    allWindowHandles = driver.getWindowHandles();
    for (String windowHandle : allWindowHandles) {
      driver.switchTo().window(windowHandle);
    }
    driver.close();
    driver.switchTo().window(mainWindowHandle);
    //Click in the "Certificate HTML/CSS"
    mainWindowHandle = driver.getWindowHandle();
    WebElement certificateHtmlOpen = driver.findElement(By.xpath("//button[text()='Certificate HTML/CSS.']"));
    certificateHtmlOpen.click();
    allWindowHandles = driver.getWindowHandles();
    for (String windowHandle : allWindowHandles) {
      driver.switchTo().window(windowHandle);
    }
    driver.close();
    driver.switchTo().window(mainWindowHandle);
    //Find the "education and experience" section
    WebElement educationAndExperienceSection = driver.
            findElement(By.xpath("//div[@class='timeline']//h1[@class='heading']"));
    String expectedEducationAndExperienceHeaderText = "education and experience";
    String actualEducationAndExperienceHeaderText = educationAndExperienceSection.getText();
    assertEquals(actualEducationAndExperienceHeaderText, expectedEducationAndExperienceHeaderText,
            "The page title does not match the expected text");

    Assert.assertTrue(aboutLink.isDisplayed());
    assertEquals(aboutLink.getText(), "about");
  }

}
