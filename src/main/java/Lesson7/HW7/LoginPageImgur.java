package Lesson7.HW7;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    @Step("Ввод логина в поле 'Username or Email'")
    public LoginPageImgur fillLogin(String username) {
        usernameInput.sendKeys(username);
        return this;
    }

    @Step("Ввод пароля в поле 'Password'")
    public LoginPageImgur fillPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Клик на кнопку 'SignIn' страницы авторизации")
    public LoginPageImgur clickButtonSignInLogin() {
        buttonSignIn.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//div[@class='Dropdown NavbarUserMenu']/div[@class='Dropdown-title']/span[1]")));
        return this;
    }

}