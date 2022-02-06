package Lesson7.HW7;


import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.io.ByteArrayInputStream;

public class CustomLoggerNew implements WebDriverListener {

    public void beforeClick(WebElement element) {
        //System.out.println("Перед кликом");
        Allure.step("Клик на элемент с текстом " + element.getText()/* + element.getTagName() */);
            }

    public void beforeQuit(WebDriver driver) {
        Allure.addAttachment("Скриншот страницы",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

}

