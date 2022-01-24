package Lesson3.HomeWork3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/*
Перенесла сценарий из Selenium IDE в код.
Сценарий заказа определённого салата.
 */

public class Evrasia {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://evrasia.in.ua/ru");
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//div[@class='navbar__burger menu-toggle js-popover-menu-toggle']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Заберу сам')]")).click();
        driver.findElement(By.xpath("//img[@alt='Салаты и Закуски']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='item'][contains(.,'Салат со Снежным крабом')]/a")));
        driver.findElement(By.xpath("//div[@class='item'][contains(.,'Салат со Снежным крабом')]/a")).click();
        driver.findElement(By.xpath("//*[@id='togo']/div/button")).click();

        Thread.sleep(5000);
        driver.quit();
    }
}
