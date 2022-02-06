package Lesson7.HW7;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;


public class ImgurPageObjectTest {
    WebDriver driver;
    private final static String Imgur_URL = "https://imgur.com";
    private final static String Evrasia_URL = "https://evrasia.in.ua/ru";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriverAndCookie() {
        //driver = new ChromeDriver();
        driver = new EventFiringDecorator(new CustomLoggerNew()).decorate(new ChromeDriver());
        driver.get(Imgur_URL);
        driver.manage().window().maximize();
        if (driver.findElements(By.xpath("//button[contains(text(),'AGREE')]")).size() > 0) {
            driver.findElements(By.xpath("//button[contains(text(),'AGREE')]")).get(0).click();
        }
    }

    @Test
    @Feature("Управление доступом пользователя")
    @Description("Тест авторизации зарегестрированного пользователя")
    @DisplayName("Тест авторизации зарегестрированного пользователя")
    void imgurSignInAthorizedUserTest() {
        new MainPageImgur(driver)
                .clickButtonSignInMain();
        new LoginPageImgur(driver)
                .fillLogin("iryna.pak@gmail.com")
                .fillPassword("Zalupa123")
                .clickButtonSignInLogin();
        new UserWorkspacePageImgur(driver)
                .checkSuccessAuthorization();
    }

    @Test
    @Feature("Посты")
    @Description("Тест перехода в раздел Posts авторизованного пользователя")
    @DisplayName("Тест перехода в раздел Posts авторизованного пользователя")
    void goToPostsAuthorizedUserTest() {
        new MainPageImgur(driver)
                .clickButtonSignInMain();
        new LoginPageImgur(driver)
                .fillLogin("iryna.pak@gmail.com")
                .fillPassword("Zalupa123")
                .clickButtonSignInLogin();
        new UserWorkspacePageImgur(driver)
                .checkSuccessAuthorization()
                .clickButtonMenuUser()
                .clickButtonPosts()
                .checkSuccessAuthorizationPosts();
    }

    @Test
    @Feature("Управление доступом пользователя")
    @Description("Тест выхода из сайта авторизованного пользователя")
    @DisplayName("Тест выхода из сайта авторизованного пользователя")
    void imgurSignUpAthorizedUserTest() {
        new MainPageImgur(driver)
                .clickButtonSignInMain();
        new LoginPageImgur(driver)
                .fillLogin("iryna.pak@gmail.com")
                .fillPassword("Zalupa123")
                .clickButtonSignInLogin();
        new UserWorkspacePageImgur(driver)
                .checkSuccessAuthorization()
                .clickButtonMenuUser()
                .clickButtonSignOut();
        new MainPageImgur(driver)
                .checkSuccessSignOut();
    }

    @Test
    @Feature("Switch Windows")
    @Description("Тест перехода между окнами https://evrasia.in.ua/ru и https://imgur.com/")
    @DisplayName("Тест перехода между окнами https://evrasia.in.ua/ru и https://imgur.com/")
    void switchBetweenWindowsTest() {
        new BasePageImgur(driver)
                .openNewWindow()
                .getWindowHandles()
                .switchToWindow(1)
                .getUrlWebSite(Evrasia_URL)
                .checkSuccesSwitchToEvrasia()
                .switchToWindow(0)
                .checkSuccesSwitchEvrasiaToImgur();
    }

    @AfterEach
    void tearDown() {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry log: logEntries) {
            Allure.addAttachment("Лог браузера:", log.getMessage());
        }
       driver.quit();
    }

}
