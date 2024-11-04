package com.selenium.seleniumdemotest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void init() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver); // Instantiate LoginPage here
        driver.get("http://localhost:9898/login"); // Open the login page
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void testSuccessfulLogin() {
        loginPage.enterUsername("user");
        loginPage.enterPassword("password");
        loginPage.clickLogin();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement dashboardWelcome = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        assertEquals("Welcome to Dashboard", dashboardWelcome.getText());
    }

    @Test
    public void testFailedLogin() {
        driver.get("http://localhost:9898/login");
        driver.findElement(By.id("username")).sendKeys("invalidUsername");
        driver.findElement(By.id("password")).sendKeys("invalidPassword");
        driver.findElement(By.tagName("button")).click();

        // Wait for the error message to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.error")));

        // Assert the actual error message
        assertEquals("Invalid credentials", errorMessage.getText());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
