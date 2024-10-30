package assurance.contrat.services.impl;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthenticationTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/contrat_war_exploded/login");
    }

    @Test
    public void testValidLogin() {
        // Create a wait instance
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the email field to be visible
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.login__submit")));

        // Perform login action
        emailField.sendKeys("kholoud.sanak@gmail.com");
        passwordField.sendKeys("kholoud.sanak@gmail.com");
        loginButton.click();

        // Wait and check for successful redirect to "home" page
        WebElement homePageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("userWelcomeMessage"))
        );

        // Verify that the user is redirected to the home page and that the welcome message appears
        assertTrue(homePageElement.isDisplayed());
        assertEquals("Welcome, valid@example.com!", homePageElement.getText());
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
