package Lesson6.Online;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

public class MoviePage extends BasePage {
    public MoviePage(WebDriver driver) {
        super(driver);
    }

    private final static String LIKE_BUTTON_LOCATOR_BY_XPATH =
            "//section[@data-test='PAGE-SECTION TITLE-SECTION']//button[@data-test='BUTTON FAVORITE']";
    @FindBy(xpath = LIKE_BUTTON_LOCATOR_BY_XPATH)
    private WebElement likeButton;

    public MoviePage likeFilm() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LIKE_BUTTON_LOCATOR_BY_XPATH)));
        likeButton.click();
        return this;
    }

    private final static String ADDED_TO_FAV_XPATH_LOCATOR = "//div[.='Добавлено в избранное']";
    @FindBy(xpath = ADDED_TO_FAV_XPATH_LOCATOR)
    private WebElement addedToFavouritesElement;

    public MoviePage checkFilmAddedToFavourites() {
        webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(ADDED_TO_FAV_XPATH_LOCATOR)));
        assertThat(addedToFavouritesElement, isDisplayed()); // Проверка, что элемент показывается
        return this;
    }
}

