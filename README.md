# Selenium + Cucumber BDD (JUnit 5) – Java Framework

Production-ready test automation framework using **Java 11+**, **Selenium 4**, **Cucumber 7** with the **JUnit Platform**, **WebDriverManager**, **Extent Reports**, **Log4j2**, **AssertJ**, **Apache POI**, and utilities for email/DB/JSON/Excel.

## Quick start
```bash
# Clone & run (default env=dev, headless Chrome)
mvn -q -Pdev clean test

# Change environment
mvn -q -Pstaging clean test

# Override at runtime
mvn -q -Pdev clean test -Denv=dev -Dbrowser=firefox -Dheadless=false

# Run by tag (JUnit Platform filter)
mvn -q -Pdev -D"cucumber.filter.tags=@smoke" test
```

### Reports & Artifacts
* **Cucumber HTML**: `target/cucumber-report.html`
* **Extent Spark HTML**: `target/extent-report/Spark.html`
* **JUnit XML**: `target/cucumber-junit.xml`
* **JSON**: `target/cucumber.json`
* **Logs**: `target/logs/`
* **Screenshots**: `target/screenshots/` (automatically captured on failures)

### Environments
* Config files are under `src/test/resources/config/*.properties`. Use `-Pdev|-Pstaging|-Pprod` or `-Denv=dev`.

### Browsers
* Chrome/Firefox/Edge via WebDriverManager. Set `-Dbrowser=chrome|firefox|edge` and `-Dheadless=true|false`.
* Remote/Grid: set `remote.enabled=true` and `remote.url=http://localhost:4444/wd/hub` in the env properties.

### Parallel execution
* Enabled by default with 4 threads. Configure in `src/test/resources/junit-platform.properties`.

### Sample apps used
* **SauceDemo** for auth and e-commerce
* **DemoQA** for forms
* **DuckDuckGo** for search

---

## Project structure
```
java-selenium-cucumber-bdd-junit
├── pom.xml
├── src
│   ├── main/java/com/example/framework
│   │   ├── config
│   │   ├── driver
│   │   ├── pages
│   │   └── utils
│   └── test
│       ├── java/com/example/framework/{hooks,steps,runners}
│       └── resources/{features,config,data}
└── README.md
```

## CI/CD
* **Jenkins**: `Jenkinsfile` pipeline with JUnit/HTML report publishing
* **GitHub Actions**: `.github/workflows/ci.yml`
* **Docker**: `Dockerfile` to run tests in container (headless)

---

## Notes
* This project uses Cucumber on the JUnit 5 Platform with the `cucumber-junit-platform-engine` runner.
* Extent Reports provided via `extentreports-cucumber7-adapter` and configured by `extent.properties`.
