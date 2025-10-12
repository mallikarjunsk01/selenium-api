package com.example.framework.steps;

import com.example.framework.config.ConfigLoader;
import com.example.framework.pages.HomePage;
import com.example.framework.pages.LoginPage;
import com.example.framework.pages.ProductPage;
import io.cucumber.java.en.*;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import com.example.framework.driver.DriverManager;

public class EcommerceSteps {
    private final LoginPage loginPage = new LoginPage();
    private final HomePage homePage = new HomePage();
    private final ProductPage productPage = new ProductPage();

    @Given("user is on products page after login")
    public void user_on_products_page() {
        loginPage.open(ConfigLoader.get("base.url"));
        loginPage.login("standard_user", "secret_sauce");
        Assertions.assertThat(homePage.getTitle()).containsIgnoringCase("products");
    }

    @When("user adds a backpack to cart and checks out")
    public void add_and_checkout() {
        productPage.addBackpackToCart();
        productPage.goToCart();
        productPage.checkout("John","Doe","560001");
    }

    @Then("order confirmation should be displayed")
    public void order_confirmation() {
        String completed = DriverManager.getDriver().findElement(By.className("complete-header")).getText();
        Assertions.assertThat(completed).containsIgnoringCase("thank you");
    }
}
