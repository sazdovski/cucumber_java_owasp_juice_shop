package com.owasp_juice_shop.springbootselenium.pages;

import com.owasp_juice_shop.springbootselenium.annotations.LazyComponent;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.jupiter.api.Assertions.*;

@LazyComponent
public class LoginPage extends BasePage {

    // Web Elements by using Page Factory
    @FindBy(how = How.NAME, using = "email")
    public WebElement userName;

    @FindBy(how = How.NAME, using = "password")
    public WebElement password;

    @FindBy(how = How.CSS, using = "button[type='submit']")
    public WebElement submitButton;

    @FindBy(how = How.CSS, using = "button[aria-label='Show the shopping cart']")
    public WebElement shoppingCartButton;

    // Web Elements by using By Class
    By errorMessageBy = By.className("error");

    // Page Methods
    public LoginPage login(String userName, String password) {
        writeText(this.userName, userName);
        writeText(this.password, password);
        click(submitButton);
        return this;
    }

    public LoginPage verifyLoginErrorMessage(String expectedText) {
        assertEquals(expectedText, readText(errorMessageBy));
        return this;
    }

    public LoginPage verifyUnclickableLoginButton() {
        assertFalse(submitButton.isEnabled());
        return this;
    }

@SneakyThrows
    public LoginPage verifySuccessfulLogin() {
        Thread.sleep(2000);
        assertTrue(shoppingCartButton.isDisplayed());
        return this;
    }

    @Override
    public boolean isAt() {
        return this.wait.until((d) -> this.userName.isDisplayed());
    }
}
