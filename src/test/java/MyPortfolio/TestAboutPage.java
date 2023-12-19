package MyPortfolio;

import io.qameta.allure.Step;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.*;
import Base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.Set;
import static org.testng.Assert.assertEquals;
import static utils.AllureAttachmentUtils.attachScreenshot;

@Setter
@Getter
public class TestAboutPage extends BaseTest {

  private JavascriptExecutor js;

  @Test(groups = "allTests")
  public void testAboutPage() {
    openAboutPage();
    openMyResume();
    verifySkillsSection();
    openCertificateQa();
    openCertificateHtml();
    verifyEducationSection();
    verifyAboutLink();
  }

  @Step("Open the Abuot page")
  private void openAboutPage() {
      //Find the "my about"
    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    WebElement aboutLink = wait.until(ExpectedConditions
            .visibilityOfElementLocated(By.xpath("//a[contains(text(),'about')]")));
    aboutLink.click();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String aboutHeader = (String) js.executeScript
              ("return document.querySelector('section.about-section.active h1[hidden]').textContent");
    String expectedAboutHeaderText = "my about";
    assertEquals(aboutHeader, expectedAboutHeaderText, "The page title does not match the expected text");
    //Additional checks and actions
    Assert.assertTrue(aboutLink.isDisplayed());
    assertEquals(aboutLink.getText(), "About");
    js.executeScript("document.body.click()");
  }

  @Step("Open My Resume")
  private void openMyResume() {
  //Click in the "My resume" link
    WebElement resumeLink = driver.findElement(By.xpath("//button[contains(text(),'My resume')]"));
    resumeLink.click();
    // Switch to the new window
    String mainWindowHandle = driver.getWindowHandle();
    // Wait for the new window to appear
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    wait.until(ExpectedConditions.numberOfWindowsToBe(2));
    // Switch to the new window
    Set<String> allWindowHandles = driver.getWindowHandles();
    String newWindowHandle = allWindowHandles.stream()
            .filter(handle -> !handle.equals(mainWindowHandle)).findFirst().orElse(null);
    driver.switchTo().window(newWindowHandle);
    // Close the new window
    driver.close();
    // Switch back to the main window
    driver.switchTo().window(mainWindowHandle);
  }
  @Step("Verify Skills section")
  private void verifySkillsSection() {
  //Find the "skills" section
    WebElement skillsSection = driver.
            findElement(By.xpath("//h1[contains(text(),'skills')]"));
    String expectedSkillsHeaderText = "Skills";
    String actualSkillsHeaderText = skillsSection.getText();
    assertEquals(actualSkillsHeaderText, expectedSkillsHeaderText,
            "The page title does not match the expected text");
  }

  @Step("Open the Cerificate QA")
  private void openCertificateQa() {
    // Click in the "Certificate QA" link
    WebElement qalLink = driver.findElement(By.xpath("//button[contains(text(),'Certificate QA Eng.')]"));
    qalLink.click();
    // Switch to the new window
    String mainWindowHandle = driver.getWindowHandle();
    // Wait for the new window to appear
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    wait.until(ExpectedConditions.numberOfWindowsToBe(2));
    // Switch to the new window
    Set<String> allWindowHandles = driver.getWindowHandles();
    String newWindowHandle = allWindowHandles.stream()
            .filter(handle -> !handle.equals(mainWindowHandle)).findFirst().orElse(null);
    driver.switchTo().window(newWindowHandle);
    // Close the new window
    driver.close();
    // Switch back to the main window
    driver.switchTo().window(mainWindowHandle);
  }

  @Step("Open the Certificate HTML")
  private void openCertificateHtml() {
    // Click on the "Certificate HTML" link
    WebElement htmlLink = driver.findElement(By.xpath("//button[contains(text(),'Certificate HTML/CSS.')]"));
    htmlLink.click();
    // Switch to the new window
    String mainWindowHandle = driver.getWindowHandle();
    // Wait for the new window to appear
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    wait.until(ExpectedConditions.numberOfWindowsToBe(2));
    // Switch to the new window
    Set<String> allWindowHandles = driver.getWindowHandles();
    String newWindowHandle = allWindowHandles.stream()
            .filter(handle -> !handle.equals(mainWindowHandle)).findFirst().orElse(null);
    driver.switchTo().window(newWindowHandle);
    // Close the new window
    driver.close();
    // Switch back to the main window
    driver.switchTo().window(mainWindowHandle);
  }

  @Step("Verify the Education section")
  private void verifyEducationSection() {
  //Find the "education and experience" section
    WebElement educationAndExperienceSection = driver.
            findElement(By.xpath("//h1[contains(text(),'education and experience')]"));
    String expectedEducationAndExperienceHeaderText = "Education And Experience";
    String actualEducationAndExperienceHeaderText = educationAndExperienceSection.getText();
    assertEquals(actualEducationAndExperienceHeaderText, expectedEducationAndExperienceHeaderText,
           "The page title does not match the expected text");
  }

  @Step("Verify the About Link")
  private void verifyAboutLink(){
    try {
      WebElement abuotLink = driver.findElement(By.xpath("//a[contains(text(),'about')]"));
      Assert.assertTrue(abuotLink.isDisplayed());
      assertEquals(abuotLink.getText(), "About");
    } catch (AssertionError e) {
      attachScreenshot(driver);
      throw e;
    }
  }
}
