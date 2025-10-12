package com.example.framework.steps;

import com.example.framework.config.ConfigLoader;
import com.example.framework.pages.HomePage;
import com.example.framework.pages.LoginPage;
import io.cucumber.java.en.*;

public class NavigationSteps {
    private final LoginPage loginPage = new LoginPage();
    private final HomePage homePage = new HomePage();

    @Given("I am logged in")
    public void i_am_logged_in() {
        loginPage.open(ConfigLoader.get("base.url"));
        loginPage.login("standard_user", "secret_sauce");
    }

    @When("I open the burger menu")
    public void open_burger() {
        homePage.openMenu();
    }

    @Then("I can logout from menu")
    public void can_logout() {
        homePage.logout();
    }
}
