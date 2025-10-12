package com.example.framework.steps;

import com.example.framework.driver.DriverManager;
import com.example.framework.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.*;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SearchSteps {
    private final WebDriver driver = DriverManager.getDriver();

    @Given("user opens DuckDuckGo home page")
    public void open_ddg() {
        driver.get("https://duckduckgo.com");
    }

    @When("user searches for keyword from json {string}")
    public void search_from_json(String key) {
        JsonNode json = JsonUtils.readJsonResource("data/searchData.json");
        String query = json.get(key).asText();
        driver.findElement(By.id("searchbox_input")).sendKeys(query + Keys.ENTER);
    }

    @Then("results page should contain {string}")
    public void results_should_contain(String term) {
        String title = driver.getTitle().toLowerCase();
        Assertions.assertThat(title).contains(term.toLowerCase());
    }
}
