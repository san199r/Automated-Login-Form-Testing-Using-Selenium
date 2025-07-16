package tests;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

public class LoginTests {
    WebDriver driver;
    LoginPage loginPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testValidLogin() {
        loginPage.login("tomsmith", "SuperSecretPassword!");
        Assert.assertTrue(loginPage.getFlashMessage().contains("You logged into a secure area!"));
    }

    @Test
    public void testInvalidLogin() {
        loginPage.login("wrongusername", "wrongpassword");
        Assert.assertTrue(loginPage.getFlashMessage().contains("Your username is invalid!"));
    }

    @Test
    public void testEmptyFields() {
        loginPage.login("", "");
        Assert.assertTrue(loginPage.getFlashMessage().contains("Your username is invalid!"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
