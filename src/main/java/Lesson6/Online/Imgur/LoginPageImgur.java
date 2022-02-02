package Lesson6.Online.Imgur;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

public class LoginPageImgur extends BasePageImgur {

    public LoginPageImgur(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "username")
    public WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(name = "submit")
    public WebElement buttonSignIn;

    public LoginPageImgur fillLogin(String username) {
        usernameInput.sendKeys(username);
        return this;
    }

    public LoginPageImgur fillPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public LoginPageImgur clickButtonSignInLogin() {
        buttonSignIn.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[@class='Dropdown NavbarUserMenu']/div[@class='Dropdown-title']/span[1]")));
        return this;
    }



}
