package org.example;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JavaScriptAlertTest {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Desktop\\driver\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
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

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
