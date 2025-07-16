Automated Login Form Testing Using SeleniumThis project automates the testing of a login form on the demo website: https://the-internet.herokuapp.com/login. It includes test cases to simulate different login scenarios and validates the results using Selenium WebDriver in Java.Project RequirementsThe project implements the following test cases:Test Case 1: Valid LoginEnters a valid username (tomsmith) and password (SuperSecretPassword!).Asserts that the user is successfully logged in and redirected to the "Secure Area" page.Test Case 2: Invalid LoginEnters an invalid username (wrongusername) and password (wrongpassword).Asserts that an error message ("Your username is invalid!") is displayed.Test Case 3: Empty FieldsLeaves both username and password fields empty.Asserts that an error message ("This field is required") is displayed.Tools & TechnologiesSelenium WebDriver (Java)JUnit 4Maven (for dependency management)ChromeDriver (or any other browser driver)OpenCSV (for optional data-driven testing)Project Setup (IntelliJ IDEA)Follow these steps to set up and run the project in IntelliJ IDEA:1. Clone the Repository (if applicable)If your project is in a Git repository, clone it to your local machine:git clone <your-repository-url>
cd automated-login-form-testing
If you have the project as a local folder, simply open it in IntelliJ IDEA.2. Open Project in IntelliJ IDEALaunch IntelliJ IDEA.Select Open from the welcome screen or File > Open... from the menu bar.Navigate to the root directory of this project and click Open.IntelliJ IDEA should automatically detect the pom.xml file and import the Maven project. If prompted, allow IntelliJ to import Maven dependencies.3. Verify Maven DependenciesIntelliJ IDEA uses the pom.xml file to manage project dependencies. Ensure that all dependencies are correctly downloaded and configured.Your pom.xml includes the following essential dependencies:<dependencies>
    <!-- Selenium WebDriver -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.20.0</version>
    </dependency>

    <!-- JUnit -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>

    <!-- OpenCSV for reading CSV files (if implementing data-driven tests) -->
    <dependency>
        <groupId>com.opencsv</groupId>
        <artifactId>opencsv</artifactId>
        <version>5.9</version>
    </dependency>
</dependencies>
If you encounter any issues with dependencies, you can manually re-import them:In IntelliJ IDEA, go to Maven tab (usually on the right sidebar).Click on the Reimport All Maven Projects button (a circular arrow icon).4. Download ChromeDriverSelenium WebDriver requires a browser-specific driver to interact with the browser. For Chrome, you'll need ChromeDriver.Check your Chrome browser version:Open Chrome.Go to Settings (three dots menu) > About Chrome. Note down your Chrome version.Download the compatible ChromeDriver:Go to the ChromeDriver Downloads page.Find the ChromeDriver version that matches your Chrome browser version.Download the appropriate zip file for your operating system.Extract and Place ChromeDriver:Extract the chromedriver.exe (Windows) or chromedriver (macOS/Linux) executable from the downloaded zip file.Recommended: Place the chromedriver executable in a well-known location, e.g., inside a drivers folder within your project root, or in a directory that is already in your system's PATH.Example of placing in project root:automated-login-form-testing/
├── src/
├── pom.xml
├── drivers/
│   └── chromedriver.exe  <-- Place it here
└── README.md
5. Configure ChromeDriver Path in TestsIn your Java test code, you'll need to specify the path to the ChromeDriver executable. Locate the test class (e.g., LoginTest.java) and ensure the webdriver.chrome.driver system property is set correctly.Example (adjust the path as needed):import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
// ... other imports

public class LoginTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe"); // Adjust this path!
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximize browser window
        driver.get("https://the-internet.herokuapp.com/login"); // Navigate to the login page
    }

    // ... your test methods

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
Important: If you placed chromedriver.exe in a drivers folder within your project root, the path drivers/chromedriver.exe should work. If you placed it elsewhere, update the path accordingly.6. Run the TestsYou can run the JUnit tests directly from IntelliJ IDEA:Run All Tests:Navigate to the src/test/java directory in the Project explorer.Right-click on the test package (e.g., com.login.tests) or the test class (LoginTest.java).Select Run 'All Tests' or Run 'LoginTest'.Run Individual Test Method:Open the LoginTest.java file.Click the green play icon next to the @Test method you want to run.Select Run 'testValidLogin()' (or the name of your test method).IntelliJ IDEA will open a Chrome browser instance, execute the tests, and display the test results in the Run tool window.Bonus: Data-Driven Testing (Optional)The project is set up to easily integrate data-driven testing using OpenCSV.Create a CSV File: Create a .csv file (e.g., testdata.csv) in your src/test/resources folder (you might need to create this folder).Example testdata.csv:username,password,expectedMessage,testCase
tomsmith,SuperSecretPassword!,You logged into a secure area!,Valid
wronguser,wrongpass,Your username is invalid!,Invalid
,,This field is required,Empty
Implement CSV Reading Logic: In your test class, use OpenCSV to read data from this file and parameterize your tests.Example (conceptual, requires implementation):import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
// ... other imports

public class LoginTest {
    // ... setUp and tearDown

    // Example of reading data (this would be integrated into your test methods)
    public List<String[]> readTestData(String filePath) throws IOException, CsvException {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            return reader.readAll();
        }
    }

    // You would then iterate over this data to run your tests
    // For example, using JUnit's Parameterized runner or custom loops
}
Code Quality and Best PracticesPage Object Model (POM): For larger projects, consider implementing the Page Object Model design pattern to improve test maintainability and readability.Explicit Waits: Use WebDriverWait and ExpectedConditions for explicit waits to handle dynamic elements and improve test reliability.Assertions: Use JUnit's Assert class for clear and concise assertions.Error Handling: Implement robust error handling where necessary.TroubleshootingWebDriverException: unknown error: cannot find Chrome binary: Ensure Chrome browser is installed and its path is correctly configured in your system's environment variables or directly in your test code.SessionNotCreatedException: session not created: This version of ChromeDriver only supports Chrome version XX: Your ChromeDriver version does not match your Chrome browser version. Download the correct ChromeDriver as described in Step 4.Maven Build Issues: Check your pom.xml for any syntax errors. Ensure you have a stable internet connection for Maven to download dependencies.IntelliJ IDEA Indexing: Sometimes, IntelliJ IDEA might take time to index the project. Wait for it to complete before running tests.Feel free to contribute to this project or suggest improvements!
