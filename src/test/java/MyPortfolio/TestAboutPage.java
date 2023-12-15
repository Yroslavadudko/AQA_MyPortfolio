package MyPortfolio;

import org.openqa.selenium.JavascriptExecutor;
import utils.WindowUtils;
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

  @Test(groups = "allTests")
  public void testAboutPage() {
    JavascriptExecutor js = (JavascriptExecutor) driver;

    //Find the "my about"
    WebElement aboutLink = driver.findElement(By.xpath("//nav[@class='navbar']//a[text()='about']"));
    aboutLink.click();
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
//    WindowUtils.performActionsInNewWindow(driver,() -> {
//      WebElement resumeOpen = driver.findElement(By.xpath("//button[contains(text(),'My resume')]"));

//      String mainWindowHandle = driver.getWindowHandle();
//      String pdfLink = resumeOpen.getAttribute("href");
//      js.executeScript("window.open('" + pdfLink + "', '_blank');");
//    });

    //Switch to the new window
//    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//    wait.until(ExpectedConditions.numberOfWindowsToBe(2));

//    Set<String> allWindowHandles = driver.getWindowHandles();
//    for (String windowHandle : allWindowHandles){
//      String mainWindowHandle = null;
//      if (!windowHandle.equals(mainWindowHandle)) {
//        driver.switchTo().window(windowHandle);
//        break;
//      }

    //Find the "skills" section
    WebElement skillsSection = driver.
            findElement(By.xpath("//h1[contains(text(),'skills')]"));
    String expectedSkillsHeaderText = "Skills";
    String actualSkillsHeaderText = skillsSection.getText();
    assertEquals(actualSkillsHeaderText, expectedSkillsHeaderText,
            "The page title does not match the expected text");

    //Click in the "Certificate QA"
//    WindowUtils.performActionsInNewWindow(driver,() -> {
//      WebElement certificateQaOpen = driver.findElement(By.xpath("//button[text()='Certificate QA Eng.']"));
//      certificateQaOpen.click();});


    //Click in the "Certificate HTML/CSS"
//    mainWindowHandle = driver.getWindowHandle();
//    WebElement certificateHtmlOpen = driver.findElement(By.xpath("//button[text()='Certificate HTML/CSS.']"));
//    certificateHtmlOpen.click();
//    allWindowHandles = driver.getWindowHandles();
//    for (String windowHandle : allWindowHandles) {
//      driver.switchTo().window(windowHandle);
//    }
//    driver.close();
//    driver.switchTo().window(mainWindowHandle);


    //Find the "education and experience" section
    WebElement educationAndExperienceSection = driver.
            findElement(By.xpath("//h1[contains(text(),'education and experience')]"));
    String expectedEducationAndExperienceHeaderText = "Education And Experience";
    String actualEducationAndExperienceHeaderText = educationAndExperienceSection.getText();
    assertEquals(actualEducationAndExperienceHeaderText, expectedEducationAndExperienceHeaderText,
            "The page title does not match the expected text");

    Assert.assertTrue(aboutLink.isDisplayed());
    assertEquals(aboutLink.getText(), "About");
  }
}
