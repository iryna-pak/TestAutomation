package Lesson5_Online;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Cookie;

import java.time.Duration;

public class DiaryTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
    private final static String DIARY_URL = "https://diary.ru";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        driver.get(DIARY_URL);
        //driver.manage().window().maximize();
        //driver.manage().window().setSize(new Dimension(337, 665));
    }

    @Test
    void diaryCookieTest() throws InterruptedException {
        Cookie cookie = new Cookie("_identity_", "83668234c30812b14c46bac1deda1a240770255504032650b424a7503" +
                "8c00b3aa%3A2%3A%7Bi%3A0%3Bs%3A10%3A%22_identity_%22%3Bi%3A1%3Bs%3A52%3A%22%5B3545507%2C%22E_QJqRaNdBre" +
                "pyeVN7uNXi5Dz9tjGpfX%22%2C2592000%5D%22%3B%7D");

        driver.manage().addCookie(cookie);
        //driver.manage().deleteAllCookies();
        driver.navigate().refresh();       // Обновление страницы для проверки - авторизовались или нет
        Thread.sleep(5000);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

}
