package MyPortfolio;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedCondition.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MyTest {
  private WebDriver driver;

  @BeforeMethod
  public void setUp() {
    driver = new SafariDriver();
  }

  @Test
  public void testMykolaDudkoPage(){
    driver.get("https://mykoladudko.netlify.app");

    WebElement headerElement = driver.findElement(By.tagName("h1"));

    //Title check
    WebElement homeLink = driver.findElement(By.xpath("//nav[@class='navbar']//a[text()='home']"));
    homeLink.click();
    String expectedPageTitle = "Mykola Dudko - Quality Assurance Engineer";
    assertEquals(driver.getTitle(), expectedPageTitle, "The page title does not match the expected text");

    //Checking the navigation menu/Home
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
            .findElement(By.xpath("//section[@class='project-section active']//h1[@class='project-heading']"));
    String expectedProjectsHeaderText = "some of my projects";
    String actualProjectsHeaderText = projectsHeader.getText();
    assertEquals(actualProjectsHeaderText,expectedProjectsHeaderText, "The page title does not match the expected text");

    //Checking the navigation menu/About
    WebElement aboutLink = driver.findElement(By.xpath("//nav[@class='navbar']//a[text()='about']"));
    aboutLink.click();
      //Find the "my about" element using JavaScript
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String aboutHeader = (String) js.executeScript("return document.querySelector('section.about-section.active h1[hidden]').textContent");
    String expectedAboutHeaderText = "my about";
    assertEquals(aboutHeader, expectedAboutHeaderText, "The page title does not match the expected text");
        //Click on the body of the page to lose focus on "Projects"
    js.executeScript("document.body.click()");
        //Find the "skills" section
    WebElement skillsSection = driver.findElement(By.xpath("//div[@class='skill-section']//h1[@class='heading']"));
    String expectedSkillsHeaderText = "skills";
    String actualSkillsHeaderText = skillsSection.getText();
    assertEquals(actualSkillsHeaderText, expectedSkillsHeaderText, "The page title does not match the expected text");
        //Find the "education and experience" section
    WebElement educationAndExperienceSection = driver.findElement(By.xpath("//div[@class='timeline']//h1[@class='heading']"));
    String expectedEducationAndExperienceHeaderText = "education and experience";
    String actualEducationAndExperienceHeaderText = educationAndExperienceSection.getText();
    assertEquals(actualEducationAndExperienceHeaderText, expectedEducationAndExperienceHeaderText, "The page title does not match the expected text");

    //Checking the navigation menu/Contact
    WebElement contactLink = driver.findElement(By.xpath("//nav[@class='navbar']//a[text()='contact']"));
    contactLink.click();
        //Find the "contact" element using JavaScript
    String contactHeader = (String) js.executeScript("return document.querySelector('section.contact-section h1').textContent");
    String expectedContactHeaderText = "Contact Information";
    assertEquals(contactHeader, expectedContactHeaderText, "The page title does not match the expected text");
        //Click on the body of the page to lose focus on "my about"
    js.executeScript("document.body.click()");









    Assert.assertTrue(homeLink.isDisplayed());
    Assert.assertTrue(projectsLink.isDisplayed());
    Assert.assertTrue(aboutLink.isDisplayed());
    Assert.assertTrue(contactLink.isDisplayed());

    assertEquals("home", homeLink.getText());
    assertEquals("projects", projectsLink.getText());
    assertEquals("about", aboutLink.getText());
    assertEquals("contact", contactLink.getText());


  }

  @AfterMethod
  public void tearDown(){
    if (driver != null){
      driver.quit();
    }
  }
}
