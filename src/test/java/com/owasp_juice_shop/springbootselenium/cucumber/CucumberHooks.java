package com.owasp_juice_shop.springbootselenium.cucumber;

import com.owasp_juice_shop.springbootselenium.utils.ScreenshotUtil;
import com.owasp_juice_shop.springbootselenium.annotations.LazyAutowired;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.springframework.context.ApplicationContext;

public class CucumberHooks {

    @LazyAutowired
    private ScreenshotUtil screenshotUtil;

    @LazyAutowired
    private ApplicationContext applicationContext;

    @AfterStep
    public void afterStep(Scenario scenario){
        if(scenario.isFailed()){
            scenario.attach(this.screenshotUtil.getScreenshot(), "image/png", scenario.getName());
        }
    }

    @After
    public void afterScenario(){
        this.applicationContext.getBean(WebDriver.class).quit();
    }

}
