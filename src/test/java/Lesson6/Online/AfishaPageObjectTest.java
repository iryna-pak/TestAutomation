package Lesson6.Online;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AfishaPageObjectTest {
    WebDriver driver;
    private final static String AFISHA_URL = "https://afisha.ru";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        driver.get(AFISHA_URL);
    }

    @Test
    void likeRandomFilmTest() {
        new MainPage(driver)
                .clickLoginButton()
                .switchToLoginFrame()
                .fillLogin("aptemkaoas123@gmail.com")
                .fillPassword("123qwertY")
                .clickLoginButton()
                .clickFilmByName("Крик")
                .likeFilm()
                .checkFilmAddedToFavourites();
    }

    @AfterEach
    void killDriver() {
        driver.quit();
    }

}
