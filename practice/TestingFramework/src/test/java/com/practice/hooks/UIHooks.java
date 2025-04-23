package com.practice.hooks;

import com.practice.config.ConfigLoader;
import com.practice.ui.factories.BrowserType;
import com.practice.ui.factories.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class UIHooks {
    private static WebDriver driver;
    ConfigLoader configLoader = ConfigLoader.getInstance();
    DriverFactory driverFactory;

    @Before("@ui")
    public void before() {
        BrowserType browserType = BrowserType.valueOf(configLoader.getProperty("browser").toUpperCase());
        driverFactory = DriverFactory.getInstance(browserType);
        driver = driverFactory.getDriver();
        driver.get(configLoader.getProperty("login.url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
    }

    @After("@ui")
    public void tearDown() {
        driverFactory.quitDriver();
        driver.quit();
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver is not initialized. Make sure setUp() is executed before calling getDriver().");
        }
        return driver;
    }
}
