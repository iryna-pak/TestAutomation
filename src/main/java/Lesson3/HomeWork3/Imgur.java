package Lesson3.HomeWork3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/*
Перенесла сценарий из Selenium IDE в код - Авторизация на сайте http://automationpractice.com.
Так как http://automationpractice.com пятисотит сценарий воспроизвела на сайте https://imgur.com.

Сценарии входа и выхода зарегестрированного пользователя
 */

public class Imgur {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://imgur.com");
        driver.findElement(By.xpath("//a[contains(text(),'Sign in')]")).click();

        Thread.sleep(5000); // всплывает кукки, соглашаюсь вручную

        driver.findElement(By.id("username")).sendKeys("iryna.pak@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Zalupa123");
        driver.findElement(By.name("submit")).click();

        driver.findElement(By.xpath("//span[@class='UserAvatar NavbarAvatar-navbar NavbarAvatar']")).click();
        driver.findElement(By.xpath("//span[.='Sign Out']")).click();

        Thread.sleep(5000);
        driver.quit();
    }
}
