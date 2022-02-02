package Lesson6.Online;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[.='Войти']")
    private WebElement loginButton;

    public LoginPage clickLoginButton() {
        loginButton.click();
        return new LoginPage(driver);
    }

    @FindBy(xpath = "//a[contains(@href, 'movie')]/ancestor::div[@data-test='ITEM']")
    private List<WebElement> filmsList;     // Сохраняет в список все фильмы

    // Пишем в метод ту страницу на которую попадаем в результате действия clickFilmByName
    public MoviePage clickFilmByName(String filmName) {
        filmsList.stream().filter(f -> f.getText().contains(filmName)).findFirst().get().click();
        return new MoviePage(driver);
    }

    // Нахождение сразу всех элементов по разным локаторам
    @FindAll({@FindBy(xpath = "//a[contains(@href, 'movie')]/ancestor::div[@data-test='ITEM']"),
            @FindBy(xpath = "//a[contains(@href, 'concert')]/ancestor::div[@data-test='ITEM']")})
    private List<WebElement> testList;

    // Поиск элемента внутри первого элемента указанного в локаторе
    // Пример: xpath = "//a[contains(@href, 'movie')]/ancestor::div[@data-test='ITEM']"
    @FindBys({
            @FindBy(xpath = "//a[contains(@href, 'movie')]"),
            @FindBy(xpath = "//ancestor::div[@data-test='ITEM']")
    })
    private WebElement test;
}

