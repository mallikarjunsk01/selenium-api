package com.example.framework.steps;

import com.example.framework.config.ConfigLoader;
import com.example.framework.pages.HomePage;
import com.example.framework.pages.LoginPage;
import io.cucumber.java.en.*;
import org.assertj.core.api.Assertions;

public class LoginSteps {
    private final LoginPage loginPage = new LoginPage();
    private final HomePage homePage = new HomePage();

    @Given("user navigates to login page")
    public void user_navigates_to_login_page() {
        loginPage.open(ConfigLoader.get("base.url"));
    }

    @When("user logs in with username {string} and password {string}")
    public void user_logs_in_with_username_and_password(String user, String pass) {
        loginPage.login(user, pass);
    }

    @Then("home page should be displayed")
    public void home_page_should_be_displayed() {
        Assertions.assertThat(homePage.getTitle()).containsIgnoringCase("products");
    }

    @Then("login error should be shown")
    public void login_error_should_be_shown() {
        Assertions.assertThat(loginPage.isErrorVisible()).isTrue();
    }

    @And("user logs out")
    public void user_logs_out() {
        homePage.logout();
    }
}
