package com.practice.api.stepdefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.practice.api.utils.ApiUtils;
import com.practice.api.utils.AuthenticationUtil;
import com.practice.api.models.AuthenticationUser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static org.junit.Assert.assertEquals;

public class AuthenticationSteps {
    private Response response;
    private static ApiUtils apiUtils;

    @Given("User is at the base URL")
    public void setBaseUrl() throws JsonProcessingException {
        String accessToken = AuthenticationUtil.getToken("admin", "admin");
        apiUtils = new ApiUtils(accessToken);
    }

    @When("User enters valid Username {string}, valid Password {string} and RememberMe {string} at endpoint {string}")
    public void authenticateWithValidCredentials(String username, String password, String rememberMe, String endpoint) {
        AuthenticationUser user = new AuthenticationUser.Builder()
                .setUsername(username)
                .setPassword(password)
                .setRememberMe(Boolean.parseBoolean(rememberMe))
                .build();

        response = apiUtils.post(endpoint, user);
    }

    @When("User enters incorrect Username {string} or incorrect Password {string} and RememberMe {string} at endpoint {string}")
    public void authenticateWithInvalidCredentials(String username, String password, String rememberMe, String endpoint) {
        AuthenticationUser user = new AuthenticationUser.Builder()
                .setUsername(username)
                .setPassword(password)
                .setRememberMe(Boolean.parseBoolean(rememberMe))
                .build();

        response = apiUtils.post(endpoint, user);
    }

    @Then("User checks if they Receive correct Status Code {int}")
    public void verifySuccessStatusCode(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }


    @Then("User will get Status Code as {int}")
    public void userWillGetStatusCodeAs(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }
}