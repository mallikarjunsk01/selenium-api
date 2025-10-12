package com.example.framework.driver;

import com.example.framework.config.ConfigLoader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;

public class WebDriverFactory {

    public static WebDriver createInstance() {
        String browser = ConfigLoader.get("browser");
        boolean headless = ConfigLoader.getBoolean("headless", false);
        int implicitWait = ConfigLoader.getInt("implicit.wait.seconds", 0);
        String windowSize = ConfigLoader.get("window.size");
        boolean remote = ConfigLoader.getBoolean("remote.enabled", false);
        String remoteUrl = ConfigLoader.get("remote.url");

        WebDriver driver;
        try {
            if (remote) {
                MutableCapabilities caps = getOptions(browser, headless, windowSize);
                driver = new RemoteWebDriver(new URL(remoteUrl), caps);
            } else {
                switch (browser.toLowerCase()) {
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        FirefoxOptions fopts = new FirefoxOptions();
                        if (headless) fopts.addArguments("-headless");
                        if (windowSize != null && !windowSize.isEmpty()) fopts.addArguments("--width="+windowSize.split("x")[0], "--height="+windowSize.split("x")[1]);
                        driver = new FirefoxDriver(fopts);
                        break;
                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        EdgeOptions eopts = new EdgeOptions();
                        if (headless) eopts.addArguments("--headless=new");
                        if (windowSize != null && !windowSize.isEmpty()) eopts.addArguments("--window-size="+windowSize);
                        driver = new EdgeDriver(eopts);
                        break;
                    case "chrome":
                    default:
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions copts = new ChromeOptions();
                        if (headless) copts.addArguments("--headless=new");
                        if (windowSize != null && !windowSize.isEmpty()) copts.addArguments("--window-size="+windowSize);
                        copts.addArguments("--disable-gpu", "--no-sandbox");
                        driver = new ChromeDriver(copts);
                        break;
                }
            }
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Long.parseLong(ConfigLoader.get("page.load.timeout.seconds"))));
            if (implicitWait > 0) {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
            }
            if (!ConfigLoader.getBoolean("start.maximized", true) && windowSize == null) {
                // do nothing
            } else {
                driver.manage().window().maximize();
            }
            return driver;
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver", e);
        }
    }

    private static MutableCapabilities getOptions(String browser, boolean headless, String windowSize) {
        switch (browser.toLowerCase()) {
            case "firefox":
                FirefoxOptions fopts = new FirefoxOptions();
                if (headless) fopts.addArguments("-headless");
                return fopts;
            case "edge":
                EdgeOptions eopts = new EdgeOptions();
                if (headless) eopts.addArguments("--headless=new");
                if (windowSize != null && !windowSize.isEmpty()) eopts.addArguments("--window-size="+windowSize);
                return eopts;
            case "chrome":
            default:
                ChromeOptions copts = new ChromeOptions();
                if (headless) copts.addArguments("--headless=new");
                if (windowSize != null && !windowSize.isEmpty()) copts.addArguments("--window-size="+windowSize);
                copts.addArguments("--disable-gpu", "--no-sandbox");
                return copts;
        }
    }
}
