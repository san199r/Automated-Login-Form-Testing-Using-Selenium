package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

public class LoginTests {
    WebDriver driver;
    LoginPage loginPage;

    @Before
    public void setUp() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.get("https://the-internet.herokuapp.com/login");
        this.loginPage = new LoginPage(this.driver);
    }

    @Test
    public void testValidLogin() {
        this.loginPage.login("tomsmith", "SuperSecretPassword!");
        Assert.assertTrue(this.loginPage.getFlashMessage().contains("You logged into a secure area!"));
    }

    @Test
    public void testInvalidLogin() {
        this.loginPage.login("wrongusername", "wrongpassword");
        Assert.assertTrue(this.loginPage.getFlashMessage().contains("Your username is invalid!"));
    }

    @Test
    public void testEmptyFields() {
        this.loginPage.login("", "");
        Assert.assertTrue(this.loginPage.getFlashMessage().contains("Your username is invalid!"));
    }

    @After
    public void tearDown() {
        if (this.driver != null) {
            this.driver.quit();
        }

    }
}
