package com.owasp_juice_shop.springbootselenium.tests;

import com.owasp_juice_shop.springbootselenium.annotations.LazyAutowired;
import com.owasp_juice_shop.springbootselenium.steps.HomeSteps;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Description;

@Execution(ExecutionMode.CONCURRENT)
public class HomePageTest extends BaseTest {
    @LazyAutowired
    HomeSteps homeSteps;

    @Test
    public void homePageTest() {
        System.out.println("Driver of invalidUserNameInvalidPassword test: " + applicationContext
            .getBean(WebDriver.class));

        homeSteps
            .givenIAmAtHomePage()
            .thenIVerifyIAmAtHomePage();
    }

    @Test
    @Description("For testing parallel test execution.")
    public void homePageTest1() {
        System.out.println("Driver of invalidUserNameInvalidPassword test: " + applicationContext
                .getBean(WebDriver.class));

        homeSteps
                .givenIAmAtHomePage()
                .thenIVerifyIAmAtHomePage();
    }

    @Test
    @Description("For testing parallel test execution.")
    public void homePageTest2() {
        System.out.println("Driver of invalidUserNameInvalidPassword test: " + applicationContext
                .getBean(WebDriver.class));

        homeSteps
                .givenIAmAtHomePage()
                .thenIVerifyIAmAtHomePage();
    }
}
