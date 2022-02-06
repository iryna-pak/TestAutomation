package Lesson5.HW5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

public class EvrasiaTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
    private final static String Evrasia_URL = "https://evrasia.in.ua/ru";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        driver.get(Evrasia_URL);
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Тест заказа 'Салата со Снежным крабом'")
    void evrasiaOrderSnowCrabSaladTest() throws InterruptedException {
        Thread.sleep(20000);    // Задержка для закрытия popup
        if (driver.findElements(By.xpath("//div[@class='popup-main__close-btn']")).size() > 0) {
            driver.findElements(By.xpath("//div[@class='popup-main__close-btn']")).get(0).click();
        }
        driver.findElement(By.xpath("//div[@class='navbar__burger menu-toggle js-popover-menu-toggle']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Заберу сам')]")).click();
        driver.findElement(By.xpath("//img[@alt='Салаты и Закуски']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='item'][contains(.," +
                "'Салат со Снежным крабом')]/a")));
        driver.findElement(By.xpath("//div[@class='item'][contains(.,'Салат со Снежным крабом')]/a")).click();
        driver.findElement(By.xpath("//*[@id='togo']/div/button")).click();
        driver.findElement(By.xpath("//div[@class='cart__toggle']")).click();
        assertThat(driver.findElement(By.xpath("//a[contains(text(),'Оформить заказ')]")), hasText("ОФОРМИТЬ ЗАКАЗ"));
        Assertions.assertTrue(driver.getCurrentUrl().contentEquals("https://evrasia.in.ua/ru/togo/Salad-with-Snow-Crab.html"));
        Thread.sleep(5000);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
