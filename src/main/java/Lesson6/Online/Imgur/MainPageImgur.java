package Lesson6.Online.Imgur;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

public class MainPageImgur extends BasePageImgur {

    public MainPageImgur(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(text(),'Sign in')]")
    private WebElement buttonSignIn;

    public MainPageImgur clickButtonSignInMain() {
        buttonSignIn.click();
        return this;
    }

    public MainPageImgur checkSuccessSignOut() {
        assertThat(driver.findElement(By.xpath("//div[@id]//h1")), hasText("You have been signed out"));
        return this;
    }


}
