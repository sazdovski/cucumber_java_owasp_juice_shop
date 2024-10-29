package com.owasp_juice_shop.springbootselenium.steps;

import com.owasp_juice_shop.springbootselenium.annotations.LazyAutowired;
import com.owasp_juice_shop.springbootselenium.annotations.LazyComponent;
import com.owasp_juice_shop.springbootselenium.annotations.TakeScreenshot;
import com.owasp_juice_shop.springbootselenium.pages.HomePage;
import org.springframework.beans.factory.annotation.Value;

@LazyComponent
public class HomeSteps {
    @Value("${browser}")
    private String browser;

    @LazyAutowired
    HomePage homePage;

    public HomeSteps givenIAmAtHomePage() {
        homePage
            .goToHomePage();
        return this;
    }

    @TakeScreenshot
    public HomeSteps thenIVerifyIAmAtHomePage() {
            homePage
                .verifyThatIAmAtHomePage();
        return this;
    }
}
