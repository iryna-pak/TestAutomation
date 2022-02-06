package Lesson5.HW5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestingcourseTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
    private final static String Testingcourse_URL = "http://testingcourse.ru/docs";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        driver.get(Testingcourse_URL);
        driver.manage().window().maximize();
    }

    @Test
    void testingcourseCookieTest() throws InterruptedException {
        Cookie cookie = new Cookie("DW0b99b027724d9fff9b6347d01f4e2033", "dXNlcg%3D%3D%7C0%7CEHoi4DIGs%2B2u" +
                "ZA8dR2i6GqJq%2FFasxBbLACks08eY%2BVc%3D");
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
        //assertThat(driver.findElement(By.xpath("//span[.='Выйти']")), hasText("Выйти"));
        //Assertions.assertTrue(driver.getCurrentUrl().contentEquals("http://testingcourse.ru/docs/start"));
        Thread.sleep(20000);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
