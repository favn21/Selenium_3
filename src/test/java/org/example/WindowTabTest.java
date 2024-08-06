package org.example;
import org.example.api.WebDriverConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class WindowTabTest {
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
    public void testWindowTabs() {
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


    }
}
