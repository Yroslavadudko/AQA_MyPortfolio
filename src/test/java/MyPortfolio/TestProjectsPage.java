package MyPortfolio;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class TestProjectsPage extends BaseTest {
    //Checking the navigation menu/Projects
    @Test(groups = "allTests")
    public void testProjectsPage() {

        WebElement projectsLink = driver.findElement(By.xpath("//nav[@class='navbar']//a[text()='projects']"));
        projectsLink.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//nav[@class='navbar']//a[text()='projects']")));
        WebElement projectsHeader = driver
                .findElement(By.xpath(
                        "//section[@class='project-section active']//h1[@class='project-heading']"));
        String expectedProjectsHeaderText = "Some Of My Projects";
        String actualProjectsHeaderText = projectsHeader.getText();
        assertEquals(actualProjectsHeaderText, expectedProjectsHeaderText,
                "The page title does not match the expected text");

        Assert.assertTrue(projectsLink.isDisplayed());
        assertEquals(projectsLink.getText(), "Projects");
    }
}
