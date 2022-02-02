package Lesson6.Online.Imgur;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

public class BasePageImgur {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
    List<String> tabs;

    public BasePageImgur(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public BasePageImgur getUrlWebSite (String url){
        driver.get(url);
        return this;
    }

    public BasePageImgur getWindowHandles (){
        tabs = new ArrayList<>(driver.getWindowHandles());
        return this;
    }

    public BasePageImgur openNewWindow(){
        ((JavascriptExecutor) driver).executeScript("window.open()");
        return this;
    }

    public BasePageImgur switchToWindow (int tabNumbers){
        driver.switchTo().window(tabs.get(tabNumbers));
        return this;
    }

    public BasePageImgur checkSuccesSwitchToEvrasia (){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='footer__social-caption']")));
        assertTrue(driver.getCurrentUrl().contentEquals("https://evrasia.in.ua/ru"));
        return this;
    }

    public BasePageImgur checkSuccesSwitchEvrasiaToImgur (){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Sign in')]")));
        assertTrue(driver.getCurrentUrl().contentEquals("https://imgur.com/"));
        return this;
    }

}
