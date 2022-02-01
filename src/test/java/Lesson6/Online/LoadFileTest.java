package Lesson6.Online;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoadFileTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        driver.get("https://www.google.ru/imghp");
        //driver.manage().window().maximize();
    }
/*
    @Test
    void loadPicTest() throws InterruptedException {
        driver.findElement(By.xpath("//div[@aria-label='Поиск по картинке']")).click();
        driver.findElement(By.xpath("//a[.='Загрузить изображение']")).click();
///Users/spartalex/Desktop/Снимок экрана 2022-01-23 в 14.03.03.png
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys("/Users/spartalex/Desktop/Снимок экрана 2022-01-23 в 14.03.03.png");
        Thread.sleep(5000);
    }

 */

    @AfterEach
    void tearDown() {
        driver.quit();
    }

}
