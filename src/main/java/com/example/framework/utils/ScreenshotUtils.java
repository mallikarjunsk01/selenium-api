package com.example.framework.utils;

import com.example.framework.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {
    private static final Logger log = LogManager.getLogger(ScreenshotUtils.class);

    public static Path capture(String scenarioName) {
        try {
            File src = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            String ts = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
            Path folder = Paths.get("target", "screenshots");
            Files.createDirectories(folder);
            Path dest = folder.resolve(scenarioName.replaceAll("[^a-zA-Z0-9_-]", "_") + "_" + ts + ".png");
            Files.copy(src.toPath(), dest);
            return dest;
        } catch (IOException e) {
            log.error("Failed to capture screenshot", e);
            return null;
        }
    }
}
