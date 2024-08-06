package org.example;

import org.example.api.WebDriverConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JavaScriptAlertTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        driver = WebDriverConfig.createDriver();
        wait = WebDriverConfig.createWait(driver);
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testJavaScriptAlerts() {
        driver.get("http://the-internet.herokuapp.com/javascript_alerts");

        WebElement jsAlertButton = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
        jsAlertButton.click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.getText().equals("I am a JS Alert");
        alert.accept();

        WebElement resultText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));
        resultText.getText().equals("You successfully clicked an alert");

        WebElement jsConfirmButton = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
        jsConfirmButton.click();

        alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();

        resultText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));
        resultText.getText().equals("You clicked: Cancel");

        WebElement jsPromptButton = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
        jsPromptButton.click();

        alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys("Hello World");
        alert.accept();

        resultText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));
        resultText.getText().equals("You entered: Hello World");

    }
}
