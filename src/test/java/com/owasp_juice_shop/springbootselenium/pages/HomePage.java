package com.owasp_juice_shop.springbootselenium.pages;

import com.owasp_juice_shop.springbootselenium.annotations.LazyComponent;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@LazyComponent
public class HomePage extends BasePage {

    @Autowired
    LoginPage loginPage;

    @Value("${application.url}")
    private String baseURL;

    // Web Elements By Using Page Factory
    @FindBy(how = How.ID, using = "navbarAccount")
    private WebElement accountButton;

    @FindBy(how = How.ID, using = "navbarLoginButton")
    private WebElement loginButton;

    @FindBy(how = How.CSS, using = "a.cc-btn.cc-dismiss")
    private WebElement dismissCookieButton;

    @FindBy(how = How.CSS, using = "button[aria-label='Close Welcome Banner']")
    private WebElement dismissWelcomePopupButton;

    By homePageLogo = By.cssSelector("h1");

    // Page Methods
    @SneakyThrows
    public HomePage goToHomePage() {
        driver.get(baseURL);
        Thread.sleep(2000);
        dismissPopups();
        return this;
    }

    // Dismiss Popups
    public HomePage dismissPopups() {
        if (isElementDisplayed(dismissWelcomePopupButton)) {
            click(dismissWelcomePopupButton);
        }

        if (isElementDisplayed(dismissCookieButton)) {
            click(dismissCookieButton);
        }

        return this;
    }

    private boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;  // Element not found or not visible
        }
    }

    @SneakyThrows
    public HomePage goToLoginPageViaAccount() {
        click(accountButton);

        Thread.sleep(1000);

        // Click the Login button
        click(loginButton);

        return this;
    }

    @Override
    public boolean isAt() {
        return this.wait.until((d) -> this.accountButton.isDisplayed());
    }

    public HomePage verifyThatIAmAtHomePage() {
        Assertions.assertTrue(driver.findElement(homePageLogo).isDisplayed());
        return this;
    }
}
