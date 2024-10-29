package com.owasp_juice_shop.springbootselenium.pages;

import com.owasp_juice_shop.springbootselenium.utils.LogUtil;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BasePage {
    @Autowired
    protected WebDriver driver;

    @Autowired
    protected WebDriverWait wait;

    @Autowired
    protected JavascriptExecutor javascriptExecutor;

    @Autowired
    protected LogUtil logUtil;

    @PostConstruct
    private void init() {
        PageFactory.initElements(this.driver, this);
    }

    public abstract boolean isAt();

    public <T> void waitElement(T elementAttr) {
        if (elementAttr.getClass().getName().contains("By")) {
            wait.until(ExpectedConditions.presenceOfElementLocated((By) elementAttr));
        } else {
            wait.until(ExpectedConditions.visibilityOf((WebElement) elementAttr));
        }
    }

    public <T> void waitElements(T elementAttr) {
        if (elementAttr.getClass().getName().contains("By")) {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) elementAttr));
        } else {
            wait.until(ExpectedConditions.visibilityOfAllElements((WebElement) elementAttr));
        }
    }

    //Click Method by using JAVA Generics (You can use both By or Web element)
    public <T> void click(T elementAttr) {
        waitElement(elementAttr);
        if (elementAttr.getClass().getName().contains("By")) {
            driver.findElement((By) elementAttr).click();
        } else {
            ((WebElement) elementAttr).click();
        }
    }

    public void jsClick(By by) {
        javascriptExecutor.executeScript("arguments[0].click();", wait.until(ExpectedConditions.visibilityOfElementLocated(by)));
    }

    public <T> void writeText(T elementAttr, String text) {
        waitElement(elementAttr);
        if (elementAttr.getClass().getName().contains("By")) {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) elementAttr));
            driver.findElement((By) elementAttr).sendKeys(text);
        } else {
            wait.until(ExpectedConditions.visibilityOf((WebElement) elementAttr));
            ((WebElement) elementAttr).sendKeys(text);
        }
    }

    @SneakyThrows
    public <T> String readText(T elementAttr) {
        Thread.sleep(2000);
        if (elementAttr.getClass().getName().contains("By")) {
            return driver.findElement((By) elementAttr).getText();
        } else {
            return ((WebElement) elementAttr).getText();
        }
    }


    public void handleOverlay(By overlayBy) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(overlayBy));
    }

    //Close popup if exists
    public void handlePopup(By by) {
        waitElements(by);
        List<WebElement> popup = driver.findElements(by);
        if (!popup.isEmpty()) {
            popup.get(0).click();
        }
    }
}
