package Lesson5.Online;

import org.junit.jupiter.api.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AfishaTest {
    /*
    Поля всех типов, которые понадобяться для написания всех тестов
     */
    WebDriver driver;               // Взаимодействие с selenium
    WebDriverWait webDriverWait;    // Для ожидания видимости элемента
    Actions actions;                // Для выполнения различных не очень часто встречающихся действий в браузере
    private final static String AFISHA_URL = "https://afisha.ru";   // Константа для всего класса, т.к. в классе проверки только на этом сайте.
    // Если же тесты в классе для разных сайтов, нужно в каждом тесте прописать driver.get("https://evrasia.in.ua/ru")

    /*
    Инициализация полей класса
     */

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();    // Зарегестрировали хромдрайвер
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        driver.get(AFISHA_URL);     // Инициализация для запуска перед каждым тестом
        //driver.manage().window().maximize(); Открытие браузера на весю ширину экрана
        //driver.manage().window().setSize(new Dimension(337, 665)); Открытие браузера в определённом разрешении
    }

    @Test
    void windowsTest() throws InterruptedException {
        ((JavascriptExecutor)driver).executeScript("window.open()");    // Открытие новой вкладки
        List<String> tabs = new ArrayList<>(driver.getWindowHandles()); // Сохраняем в список дискрипторы вкладок. Сохраняются в том же порядке, что вкладки открыты в браузере
        driver.switchTo().window(tabs.get(1));  // Достаём первую вкладку и переключаемся в неё
        driver.get("https://ya.ru");    // Открываем в новой вкладке сайт
        Thread.sleep(3000);
        driver.switchTo().window(tabs.get(0));
        Thread.sleep(3000);
        ((JavascriptExecutor)driver).executeScript("alert(\"asdfgsdaf\")"); // Создание alert - для примера работы с ним
        Thread.sleep(2000);
        driver.switchTo().alert().accept(); // Переключение в алерт и принятие (нажатие ок в алерте)
        Thread.sleep(2000);
    }

    @Test
    void hoverMenuTest() throws InterruptedException {  // Наведение мышкой называется hover
        // Удаление элемента с страницы с помощью кода на джава скрипте взятого с просторов интернета
        ((JavascriptExecutor)driver).executeScript("var badTableEval = document.evaluate (\n" + //Приведение драйвера к исполнению джава скрипта
                String.format("    \"%s\",\n", "//a[.='ВЫСТАВКИ']") +
                "    document.documentElement,\n" +
                "    null,\n" +
                "    XPathResult.FIRST_ORDERED_NODE_TYPE,\n" +
                "    null\n" +
                ");\n" +
                "\n" +
                "if (badTableEval  &&  badTableEval.singleNodeValue) {\n" +
                "    var badTable  = badTableEval.singleNodeValue;\n" +
                "    badTable.parentNode.removeChild (badTable);\n" +
                "}");
        Thread.sleep(5000);
        actions.moveToElement(driver.findElement(By.xpath("//a[.='КИНО']")))    // Перевод курсора мыши на элемент
                .build()    // Этим говорится, что завершили список команд выше
                .perform(); // Вызов метода для выполнения
        driver.findElement(By.xpath("//div[@DATA-TEST='SUGGEST']//a[.='IMAX кинотеатры']")).click();
        Assertions.assertTrue(driver.getCurrentUrl().contains("imax")); // Проверка, что если внутри строки Url есть слово imax, то сценарий выполнен успешно
    }


    @AfterEach
    void tearDown() {
        driver.quit();
    } // Закрытие браузера


}
