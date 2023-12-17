package MyPortfolio;

import Base.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static utils.AllureAttachmentUtils.attachScreenshot;

public class TestProjectsPage extends BaseTest {

    //Checking the navigation menu/Projects
    @Test(groups = "allTests")
    public void testProjectsPage() {
        openProjectsPage();
        verifyBodyText();
        verifyProjectsLink();
    }

    @Step("Open the Projects page")
    private void openProjectsPage() {

        WebElement projectsLink = driver.findElement(By.xpath("//nav[@class='navbar']//a[text()='projects']"));
        projectsLink.click();
    }

    @Step("Verify the body text")
    private void verifyBodyText() {
        WebElement projectsHeader = driver
                .findElement(By.xpath(
                        "//section[@class='project-section active']//h1[@class='project-heading']"));
        String expectedProjectsHeaderText = "Some Of My Projects";
        String actualProjectsHeaderText = projectsHeader.getText();
        assertEquals(actualProjectsHeaderText, expectedProjectsHeaderText,
                "The page title does not match the expected text");
    }

    @Step("Verify the Projects link")
    private void verifyProjectsLink() {
        try {
            WebElement projectsLink = driver.findElement(By.xpath("//a[contains(text(),'projects')]"));
            Assert.assertTrue(projectsLink.isDisplayed());
            assertEquals(projectsLink.getText(), "Projects");
        } catch (AssertionError e) {
            attachScreenshot(driver);
            throw e;
        }
    }
}

