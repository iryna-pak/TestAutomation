package Lesson5.HW5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

public class ImgurTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
    private final static String Imgur_URL = "https://imgur.com";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        driver.get(Imgur_URL);
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Тест входа зарегестрированного пользователя")
    void imgur_SignIn_AthorizedUserTest() {
        // По Вашему совету автоматическое прохождение кукки
        if (driver.findElements(By.xpath("//button[contains(text(),'AGREE')]")).size() > 0) {
            driver.findElements(By.xpath("//button[contains(text(),'AGREE')]")).get(0).click();
        }

        driver.findElement(By.xpath("//a[contains(text(),'Sign in')]")).click();
        driver.findElement(By.id("username")).sendKeys("iryna.pak@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Zalupa123");
        driver.findElement(By.name("submit")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Dropdown NavbarUserMenu']" +
                "/div[@class='Dropdown-title']/span[1]")));
        assertThat(driver.findElement(By.xpath("//div[@class='Dropdown NavbarUserMenu']/div[@class='Dropdown-title']" +
                "/span[1]")), hasText("irynapak"));
    }

    @Test
    @DisplayName("Тест перехода в раздел Posts авторизованного пользователя")
    void goToPostsAuthorizedUserTest() {
        if (driver.findElements(By.xpath("//button[contains(text(),'AGREE')]")).size() > 0) {
            driver.findElements(By.xpath("//button[contains(text(),'AGREE')]")).get(0).click();
        }
        driver.findElement(By.xpath("//a[contains(text(),'Sign in')]")).click();
        driver.findElement(By.id("username")).sendKeys("iryna.pak@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Zalupa123");
        driver.findElement(By.name("submit")).click();
        assertThat(driver.findElement(By.xpath("//div[@class='Dropdown NavbarUserMenu']/div[@class='Dropdown-title']" +
                "/span[1]")), hasText("irynapak"));
        driver.findElement(By.xpath("//div[@class='Dropdown NavbarUserMenu']/div[@class='Dropdown-title']/span[2]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Posts')]")).click();
        Assertions.assertTrue(driver.getCurrentUrl().contentEquals("https://imgur.com/user/irynapak/posts"));
    }

    @Test
    @DisplayName("Тест выхода из сайта авторизованного пользователя")
    void imgur_SignUp_AthorizedUserTest() {
        if (driver.findElements(By.xpath("//button[contains(text(),'AGREE')]")).size() > 0) {
            driver.findElements(By.xpath("//button[contains(text(),'AGREE')]")).get(0).click();
        }
        driver.findElement(By.xpath("//a[contains(text(),'Sign in')]")).click();
        driver.findElement(By.id("username")).sendKeys("iryna.pak@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Zalupa123");
        driver.findElement(By.name("submit")).click();
        assertThat(driver.findElement(By.xpath("//div[@class='Dropdown NavbarUserMenu']/div[@class='Dropdown-title']" +
                "/span[1]")), hasText("irynapak"));
        driver.findElement(By.xpath("//div[@class='Dropdown NavbarUserMenu']/div[@class='Dropdown-title']/span[2]")).click();
        driver.findElement(By.xpath("//span[.='Sign Out']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id]//h1")));
        assertThat(driver.findElement(By.xpath("//div[@id]//h1")), hasText("You have been signed out"));
    }

    @Test
    @DisplayName("Тест перехода между окнами https://evrasia.in.ua/ru и https://imgur.com/")
    void switchBetweenWindowsTest() {
        ((JavascriptExecutor)driver).executeScript("window.open()");
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get("https://evrasia.in.ua/ru");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='footer__social-caption']")));
        Assertions.assertTrue(driver.getCurrentUrl().contentEquals("https://evrasia.in.ua/ru"));
        driver.switchTo().window(tabs.get(0));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Sign in')]")));
        Assertions.assertTrue(driver.getCurrentUrl().contentEquals("https://imgur.com/"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
