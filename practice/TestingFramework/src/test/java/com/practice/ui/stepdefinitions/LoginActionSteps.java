package com.practice.ui.stepdefinitions;

import com.practice.hooks.UIHooks;
import com.practice.ui.pages.HomePage;
import com.practice.ui.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginActionSteps {
    private final WebDriver driver = UIHooks.getDriver();
    private LoginPage loginPage;
    private HomePage homePage;

    @Given("User with valid Credentials navigates to Login Page")
    public void userNavigatesToLoginPage() {
        homePage = new HomePage(driver);
        homePage.clickSignIn();
    }


    @When("User enters valid Username {string}, valid Password {string} and RememberMe {string}")
    public void userEntersValidUsernameValidPasswordAndRememberMe(String username, String password, String rememberMe) {
        loginPage = new LoginPage(driver);
        loginPage.login(username, password, Boolean.valueOf(rememberMe));
    }

    @Then("Page Title After Successfully Login In will be {string}")
    public void pageTitleAfterSuccessfullyLoginInWillBe(String title) {
        String actual = loginPage.verifyTitle();
        Assert.assertEquals(actual, title);
    }

    @Given("User with invalid Credentials navigates to Login Page")
    public void userWithInvalidCredentialsNavigatesToLoginPage() {
        homePage = new HomePage(driver);
        homePage.clickSignIn();
    }

    @When("User enters invalid Username {string}, invalid Password {string} and RememberMe {string}")
    public void userEntersInvalidUsernameInvalidPasswordAndRememberMe(String username, String password, String rememberMe) {
        loginPage = new LoginPage(driver);
        loginPage.login(username, password, Boolean.valueOf(rememberMe));
    }

    @Then("Page will display message {string}")
    public void pageWillDisplayMessage(String message) {
        String actual = loginPage.invalidMessage();
        Assert.assertEquals(actual, message);
    }
}
