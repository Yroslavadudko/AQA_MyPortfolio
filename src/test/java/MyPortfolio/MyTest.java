package MyPortfolio;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class MyTest {
  private WebDriver driver;

  @BeforeMethod
  public void setUp() {
    driver = new SafariDriver();
  }

  @Test
  public void testMykolaDudkoPage(){
    driver.get("https://mykoladudko.netlify.app");

    //Title check
    WebElement homeLink = driver.findElement(By.xpath("//nav[@class='navbar']//a[text()='home']"));
    homeLink.click();
    String expectedPageTitle = "Mykola Dudko - Quality Assurance Engineer";
    assertEquals(driver.getTitle(), expectedPageTitle, "The page title does not match the expected text");

    //Checking the navigation menu/Home
    WebElement headerElement = driver.findElement(By.tagName("h1"));
    String expectedHeaderText = "hello, i'm Mykola";
    String actualHeaderText = headerElement.getText();
    assertEquals(actualHeaderText, expectedHeaderText, "The page title does not match the expected text");

    //Checking the navigation menu/Projects
    WebElement projectsLink = driver.findElement(By.xpath("//nav[@class='navbar']//a[text()='projects']"));
    projectsLink.click();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions
            .visibilityOfElementLocated(By.xpath("//nav[@class='navbar']//a[text()='projects']")));
    WebElement projectsHeader = driver
            .findElement(By.xpath(
                    "//section[@class='project-section active']//h1[@class='project-heading']"));
    String expectedProjectsHeaderText = "some of my projects";
    String actualProjectsHeaderText = projectsHeader.getText();
    assertEquals(actualProjectsHeaderText,expectedProjectsHeaderText,
            "The page title does not match the expected text");

    //Checking the navigation menu/About
    WebElement aboutLink = driver.findElement(By.xpath("//nav[@class='navbar']//a[text()='about']"));
    aboutLink.click();
        //Find the "my about" element using JavaScript
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String aboutHeader = (String) js.executeScript
            ("return document.querySelector('section.about-section.active h1[hidden]').textContent");
    String expectedAboutHeaderText = "my about";
    assertEquals(aboutHeader, expectedAboutHeaderText, "The page title does not match the expected text");
        //Click on the body of the page to lose focus on "Projects"
    js.executeScript("document.body.click()");
        //Click in the "My resume"
    String mainWindowHandle = driver.getWindowHandle();
    WebElement resumeOpen = driver.findElement(By.xpath("//button[text()='My resume']"));
    resumeOpen.click();
    Set<String> allWindowHandles = driver.getWindowHandles();
    for (String windowHandle : allWindowHandles){
      driver.switchTo().window(windowHandle);
    }
    driver.close();
    driver.switchTo().window(mainWindowHandle);
        //Find the "skills" section
    WebElement skillsSection = driver.
            findElement(By.xpath("//div[@class='skill-section']//h1[@class='heading']"));
    String expectedSkillsHeaderText = "skills";
    String actualSkillsHeaderText = skillsSection.getText();
    assertEquals(actualSkillsHeaderText, expectedSkillsHeaderText,
            "The page title does not match the expected text");
        //Find the "education and experience" section
    WebElement educationAndExperienceSection = driver.
            findElement(By.xpath("//div[@class='timeline']//h1[@class='heading']"));
    String expectedEducationAndExperienceHeaderText = "education and experience";
    String actualEducationAndExperienceHeaderText = educationAndExperienceSection.getText();
    assertEquals(actualEducationAndExperienceHeaderText, expectedEducationAndExperienceHeaderText,
            "The page title does not match the expected text");

    //Checking the navigation menu/Contact
    WebElement contactLink = driver.findElement(By.xpath("//nav[@class='navbar']//a[text()='contact']"));
    contactLink.click();
        //Find the "contact" element using JavaScript
    String contactHeader = (String) js.executeScript
            ("return document.querySelector('section.contact-section h1').textContent");
    String expectedContactHeaderText = "Contact Information";
    assertEquals(contactHeader, expectedContactHeaderText, "The page title does not match the expected text");
        //Click on the body of the page to lose focus on "my about"
    js.executeScript("document.body.click()");
    //Find the contact form
    WebElement contactForm = driver.findElement(By.cssSelector("form.contact-form"));
        //Find the name, email, and message elements within the form
    WebElement nameInput = driver.findElement(By.id("name"));
    WebElement emailInput = driver.findElement(By.id("email"));
    WebElement messageInput = driver.findElement(By.id("msg"));
        //Find in the form fields
    nameInput.sendKeys("Myk");
    emailInput.sendKeys("myk@myk.mk");
    messageInput.sendKeys("Hello, this is a AQA test");
        //Find and click the submit button
    WebElement submitButton = contactForm.findElement(By.cssSelector("button.form-submit-btn"));
    submitButton.click();









    Assert.assertTrue(homeLink.isDisplayed());
    Assert.assertTrue(projectsLink.isDisplayed());
    Assert.assertTrue(aboutLink.isDisplayed());
    Assert.assertTrue(contactLink.isDisplayed());

    assertEquals(homeLink.getText(), "home");
    assertEquals(projectsLink.getText(), "projects");
    assertEquals(aboutLink.getText(), "about");
    assertEquals(contactLink.getText(), "contact");


  }

  @AfterMethod
  public void tearDown(){
    if (driver != null){
      driver.quit();
    }
  }
}
