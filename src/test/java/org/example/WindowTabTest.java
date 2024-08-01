package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class WindowTabTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Desktop\\driver\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("http://the-internet.herokuapp.com/windows");

            WebElement clickHereButton = driver.findElement(By.linkText("Click Here"));
            clickHereButton.click();

            String originalWindow = driver.getWindowHandle();

            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            ArrayList<String> windows = new ArrayList<>(driver.getWindowHandles());
            windows.remove(originalWindow);
            driver.switchTo().window(windows.get(0));

            WebElement newPageText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h3")));
            newPageText.getText().equals("New Page");

            driver.close();

            driver.switchTo().window(originalWindow);

            WebElement originalPageText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h3")));
            originalPageText.getText().equals("Opening a new window");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
