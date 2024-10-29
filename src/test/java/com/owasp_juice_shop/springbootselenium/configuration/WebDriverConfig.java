package com.owasp_juice_shop.springbootselenium.configuration;

import com.owasp_juice_shop.springbootselenium.annotations.LazyConfiguration;
import com.owasp_juice_shop.springbootselenium.annotations.WebdriverScopeBean;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import io.github.bonigarcia.wdm.WebDriverManager;

@Profile("!grid")
@LazyConfiguration
public class WebDriverConfig {

    @WebdriverScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "firefox")
    @Primary
    public WebDriver firefoxDriver() {
        WebDriverManager.firefoxdriver().setup();  // Automatically sets up the FirefoxDriver
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        Proxy proxy = new Proxy();
        proxy.setAutodetect(false);
        proxy.setNoProxy("no_proxy-var");
        firefoxOptions.setCapability("proxy", proxy);
        return new FirefoxDriver(firefoxOptions);
    }

    @WebdriverScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "edge")
    @Primary
    public WebDriver edgeDriver() {
        WebDriverManager.edgedriver().setup();  // Automatically sets up the EdgeDriver
        return new EdgeDriver();
    }

    @WebdriverScopeBean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "browser", havingValue = "chrome")
    @Primary
    public WebDriver chromeDriver() {
        WebDriverManager.chromedriver().setup();  // Automatically sets up the ChromeDriver

        // Setup ChromeOptions if you want to pass additional configurations
        ChromeOptions chromeOptions = new ChromeOptions();

        // Initialize the ChromeDriver with the specified options
        WebDriver driver = new ChromeDriver(chromeOptions);

        // Set window size to 1080p (1920x1080)
        driver.manage().window().setSize(new Dimension(1920, 1080));

        return driver;
    }
}
