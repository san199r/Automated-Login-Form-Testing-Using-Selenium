package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.ScreenshotUtil;

import static org.junit.Assert.*;

public class LoginCsvDrivenTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @Test
    public void validLoginTest() {
        try {
            driver.findElement(By.id("username")).sendKeys("tomsmith");
            driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
            driver.findElement(By.cssSelector("button.radius")).click();

            WebElement message = driver.findElement(By.id("flash"));
            assertTrue(message.getText().contains("You logged into a secure area!"));

        } catch (Throwable t) {
            ScreenshotUtil.captureScreenshot(driver, "validLoginTest");
            throw t; // rethrow to fail the test
        }
    }

    @After
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
