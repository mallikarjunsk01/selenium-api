package com.example.framework.runners;

import org.junit.platform.suite.api.*;
import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.example.framework.steps,com.example.framework.hooks")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, summary, html:target/cucumber-report.html, json:target/cucumber.json, junit:target/cucumber-junit.xml, com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:")
public class RunCucumberTest {
}
