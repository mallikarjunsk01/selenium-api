package com.example.framework.hooks;

import com.example.framework.config.ConfigLoader;
import com.example.framework.driver.DriverManager;
import com.example.framework.driver.WebDriverFactory;
import com.example.framework.utils.ScreenshotUtils;
import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.nio.file.Files;
import java.nio.file.Path;

public class Hooks {
    private static final Logger log = LogManager.getLogger(Hooks.class);

    @BeforeAll
    public static void beforeAll() {
        log.info("Starting test suite...");
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        WebDriver driver = WebDriverFactory.createInstance();
        DriverManager.setDriver(driver);
        log.info("Starting scenario: {}", scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                Path shot = ScreenshotUtils.capture(scenario.getName());
                if (shot != null && Files.exists(shot)) {
                    byte[] bytes = Files.readAllBytes(shot);
                    scenario.attach(bytes, "image/png", "Failure Screenshot");
                }
            }
        } catch (Exception e) {
            log.error("Failed to attach screenshot", e);
        } finally {
            DriverManager.unload();
            log.info("Finished scenario: {}", scenario.getName());
        }
    }

    @AfterAll
    public static void afterAll() {
        log.info("Test suite finished.");
    }
}
