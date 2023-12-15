package MyPortfolio;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestHomePage extends BaseTest {
    @Test(groups = "allTests")
    public void testHomePage() {
        // Title check
        WebElement homeLink = driver.findElement(By.cssSelector("body:nth-child(2) nav.navbar:nth-child(1) ul.link-group li.link.active:nth-child(1) > a:nth-child(1)"));
        homeLink.click();

        String expectedPageTitle = "Mykola Dudko - Quality Assurance Engineer";
        assertEquals(driver.getTitle(), expectedPageTitle, "The page title does not match the expected text");

        // Checking the navigation menu/Home
        WebElement bodyElement = driver.findElement(By.xpath("//body/section[1]/h1[1]"));
        String expectedBodyText = "hello, i'm\nmykola";
        String actualBodyText = bodyElement.getText().toLowerCase().trim();

        assertEquals(actualBodyText, expectedBodyText, "The body text does not match the expected text");

        Assert.assertTrue(homeLink.isDisplayed());
        assertEquals(homeLink.getText(), "Home");
    }

}
