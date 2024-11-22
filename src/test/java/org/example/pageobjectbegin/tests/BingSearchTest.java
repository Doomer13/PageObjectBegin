
package org.example.pageobjectbegin.tests;

import org.example.pageobjectbegin.pages.MainPage;
import org.example.pageobjectbegin.pages.ResultsPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BingSearchTest {

    private WebDriver driver;

    @BeforeEach
    public void searchResultTest() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com/");
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchResults() {

        String input = "Selenium";

        MainPage mp =new MainPage(driver);
        mp.sendText(input);

        ResultsPage rp = new ResultsPage(driver);
        rp.proverka(0);

        //проверка на соотвествие ВЕБ Страницы (ожидаемая, Актуальная)
        assertEquals("https://www.selenium.dev/", driver.getCurrentUrl());


    }

    @Test
    public void searchFieldTest () {

        String input = "Selenium";
        MainPage mp = new MainPage(driver);
        mp.sendText(input);

        ResultsPage rp = new ResultsPage(driver);

        assertEquals(input, rp.getTextFromSearchField(), "Текст не совпал");
    }
}