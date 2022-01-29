package Lesson5.Online;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

public class DragAndDropTest {
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
        driver.get("https://crossbrowsertesting.github.io/drag-and-drop.html");
        driver.manage().window().maximize();
    }

    @Test
    void dragAndDropTest() throws InterruptedException {
        actions.clickAndHold(driver.findElement(By.id("draggable")))    // Кликанье с зажатием
                .dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droppable")))    // Указывание первого зажатого элемента с переносом на второй элемент
                .build()    // Завершили
                .perform(); // Выполнить
        Thread.sleep(5000);
        assertThat(driver.findElement(By.id("droppable")), hasText("Dropped!")); //Первый аргумент который хотим проверить. второй элемент должен иметь указанный текст
        // Проверка на то, что айди перемещённого элемента стало таким же как и у того элемента на который переместили
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

}
