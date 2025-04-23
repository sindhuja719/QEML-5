package com.practice.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebDriverUtils {
    public static WebElement findWebElement(WebDriver driver,By locator){
        return driver.findElement(locator);
    }
    public static void click(WebElement element){
        element.click();
    }
    public static void sendKeys(WebElement element,String keys){
        element.sendKeys(keys);
    }
    public static String getTitle(WebDriver driver){
        return driver.getTitle();
    }
    public static String getText(WebElement element){ return element.getText();}
    public static String getCurrentUrl(WebDriver driver){
        return driver.getCurrentUrl();
    }
}
