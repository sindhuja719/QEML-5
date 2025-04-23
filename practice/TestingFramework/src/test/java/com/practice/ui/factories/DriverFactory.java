package com.practice.ui.factories;

import com.practice.exceptions.DriverNotFoundException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
    private static volatile DriverFactory factory;
    private static final ThreadLocal<WebDriver> local = new ThreadLocal<>();
    private DriverFactory() {}

    private void setDriver(BrowserType browserType) {
        switch ((browserType)) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                local.set(new ChromeDriver());
                break;
            case SAFARI:
                WebDriverManager.safaridriver().setup();
                local.set(new SafariDriver());
                break;
            default:
                throw new DriverNotFoundException("Invalid Browser Name: " + browserType);
        }
    }

    public WebDriver getDriver() {
        return local.get();
    }

    public static DriverFactory getInstance(BrowserType browserType) {
        if (factory == null) {
            synchronized (DriverFactory.class) {
                if (factory == null) {
                    factory = new DriverFactory();
                }
            }
        }
        if (local.get() == null) {
            factory.setDriver(browserType);
        }
        return factory;
    }

    public void quitDriver() {
        WebDriver driver = local.get();
        if(driver != null) {
            driver.quit();
            local.remove();
        }
    }
}
