package com.practice.hooks;

import com.practice.config.ConfigLoader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class ApiHooks {
    private static String contentType;
    private static String loginApiPath;

    @Before("@api")
    public void setUp() {
        ConfigLoader configLoader = ConfigLoader.getInstance();
        RestAssured.baseURI = configLoader.getProperty("url");
        contentType = configLoader.getProperty("content.type");
        loginApiPath = configLoader.getProperty("authenticate.path");
    }

    @After("@api")
    public void tearDown() {
        // Clean up resources if needed
        // For example, close database connections or clear test data
    }

    public static String getBaseUrl() {
        return RestAssured.baseURI;
    }

    public static String getContentType() {
        return contentType;
    }

    public static String getLoginApiPath() {
        return loginApiPath;
    }
}