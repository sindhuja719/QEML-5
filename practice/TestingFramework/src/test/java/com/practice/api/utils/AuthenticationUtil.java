package com.practice.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.hooks.ApiHooks;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthenticationUtil {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationUtil.class);

    public static String getToken(String username, String password) throws JsonProcessingException {
        RestAssured.defaultParser = Parser.JSON;

        String baseUrl = RestAssured.baseURI;
        String contentType = ApiHooks.getContentType();
        String loginApiPath = ApiHooks.getLoginApiPath();

        Map<String, String> login = new HashMap<>();
        login.put("username",username);
        login.put("password",password);

        logger.info("Base URL: {}", baseUrl);
        logger.info("Content Type: {}", contentType);
        logger.info("Login API Path: {}", loginApiPath);

        Response response = given()
                .contentType("application/json")
                .body(login)
                .when()
                .post("http://localhost:8080/api/authenticate");
        System.out.println(response.getStatusCode());

        logger.info("Authentication response status: " + response.getStatusLine());
        logger.info("Authentication response body: " + response.asString());

        ObjectMapper objectMapper = new ObjectMapper();
        Map res = objectMapper.readValue(response.asString(), Map.class);
        return res.get("id_token").toString();
    }
}