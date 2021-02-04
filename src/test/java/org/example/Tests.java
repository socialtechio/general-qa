package org.example;

import com.codeborne.selenide.Configuration;
import org.example.config.BaseConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static io.restassured.RestAssured.given;

public class Tests {

    private Logger log = LogManager.getLogger(Tests.class);
    private static BaseConfig conf = ConfigFactory.create(BaseConfig.class);

    @BeforeClass
    public void setup() {
        log.info("Setup Driver");
        WebDriverManager.chromedriver().setup();
        Configuration.pageLoadTimeout = conf.pageLoadTimeout();
        Configuration.timeout = conf.waitTimeout();
        Configuration.startMaximized = true;
    }

    @Test
    public void landingAccessibilityByAPI() {
        given().get(conf.baseUrl());
        /* API test here */
    }

    @Test
    public void landingTestUI() {
        open(conf.baseUrl());
        /* UI test here */
    }


    @AfterClass
    public void teardown() {
        log.info("Close Driver");
        closeWebDriver();
    }
}
