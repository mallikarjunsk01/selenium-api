package com.example.framework.steps;

import com.example.framework.driver.DriverManager;
import io.cucumber.java.en.*;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormSteps {
    private WebDriver driver = DriverManager.getDriver();

    @Given("user opens DemoQA practice form")
    public void open_form() {
        driver.get("https://demoqa.com/automation-practice-form");
    }

    @When("user fills the form with first name {string}, last name {string}, email {string}")
    public void fill_form(String fn, String ln, String email) {
        driver.findElement(By.id("firstName")).sendKeys(fn);
        driver.findElement(By.id("lastName")).sendKeys(ln);
        driver.findElement(By.id("userEmail")).sendKeys(email);
    }

    @And("submits the form")
    public void submit_form() {
        WebElement submit = driver.findElement(By.id("submit"));
        ((org.openqa.selenium.JavascriptExecutor)driver).executeScript("arguments[0].click();", submit);
    }

    @Then("a submission modal should appear")
    public void verify_modal() {
        WebElement modal = driver.findElement(By.id("example-modal-sizes-title-lg"));
        Assertions.assertThat(modal.isDisplayed()).isTrue();
    }
}
