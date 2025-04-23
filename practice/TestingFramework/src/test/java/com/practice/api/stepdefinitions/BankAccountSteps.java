package com.practice.api.stepdefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.practice.api.utils.ApiUtils;
import com.practice.api.utils.AuthenticationUtil;
import com.practice.api.models.BankAccount;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static org.junit.Assert.assertEquals;

public class BankAccountSteps {
    private Response response;
    private static ApiUtils apiUtils;

    @Given("User is at the Base URL")
    public void setBaseUrl() throws JsonProcessingException {
        String accessToken = AuthenticationUtil.getToken("admin", "admin");
        apiUtils = new ApiUtils(accessToken);
    }


    @When("I send a GET request to endpoint {string}")
    public void iSendAGETRequestToEndpoint(String endpoint) {
        response = apiUtils.get(endpoint);

    }

    @Then("User checks if they Receive Status Code {int}")
    public void verifySuccessStatusCode(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }


    @Then("the response content type should be {string}")
    public void theResponseContentTypeShouldBe(String contentType) {
        String actualContentType = response.getContentType();
        assertEquals(contentType, actualContentType);
    }

    @When("User enters valid {string} and {string} at endpoint {string}")
    public void userEntersValidAndAtEndpoint(String name, String balance, String endpoint) {
        BankAccount bankAccount = new BankAccount.BankAccountBuilder()
                .setName(name)
                .setBalance(Double.parseDouble(balance))
                .build();

        response = apiUtils.post(endpoint, bankAccount);
    }

    @When("User enters empty fields at endpoint {string}")
    public void userEntersInvalidIdNameAndBalanceAtEndpoint(String endpoint) {
        BankAccount bankAccount = new BankAccount.BankAccountBuilder()
                .build();

        response = apiUtils.put(endpoint, bankAccount);
    }


    @When("I send get request to this particular endpoint {string}")
    public void iSendGetRequestToThisParticularEndpoint(String endpoint) {
        response = apiUtils.get(endpoint);
    }

    @When("I send a Patch request to this endpoint {string} where name is changed to {string} for id {string}")
    public void iSendAPatchRequestToThisEndpointWhereNameIsChangedTo(String endpoint, String name, String id) {
        BankAccount bankAccount = new BankAccount.BankAccountBuilder()
                .setId(Integer.parseInt(id))
                .setName(name)
                .build();

        response = apiUtils.patch(endpoint, bankAccount);
    }

    @When("I send a Put request to this endpoint {string} where name is changed to {string} and balance to {string} for id {string}")
    public void iSendAPutRequestToThisEndpointWhereNameIsChangedToAndBalanceToFor(String endpoint, String name, String balance, String id) {
        BankAccount bankAccount = new BankAccount.BankAccountBuilder()
                .setId(Integer.parseInt(id))
                .setName(name)
                .setBalance(Double.parseDouble(balance))
                .build();

        response = apiUtils.put(endpoint, bankAccount);
    }

    @When("I send a Delete request to this endpoint {string}")
    public void iSendADeleteRequestToThisEndpoint(String endpoint) {
        response = apiUtils.delete(endpoint);
    }

    @Then("User checks if the Status Code is {int}")
    public void userChecksIfTheStatusCodeIs(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }
}
