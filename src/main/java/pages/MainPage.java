package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.String.format;
import static utils.ui.UiBaseTest.logStep;


public class MainPage extends BasePage {

    @FindBy(className = "search_query")
    private static WebElement searchInput;
    @FindBy(name = "submit_search")
    private static WebElement searchButton;
    @FindBy(id = "contact-link")
    private static WebElement contactUsLink;
    @FindBy(className = "login")
    private static WebElement signInLink;

    public MainPage() {
        driver.get(BASE_URL);
        logStep("Main Page is opened");
        PageFactory.initElements(driver, this);
    }

    public SearchResultsPage doSearch(String searchParam) {
        logStep(format("Searching for %s", searchParam));
        searchInput.isDisplayed();
        searchInput.sendKeys(searchParam);
        searchButton.click();

        return new SearchResultsPage();
    }

    public ContactUsPage goToContactUsPage() {
        logStep("Going to Contact Us page");
        contactUsLink.isDisplayed();
        contactUsLink.click();

        return new ContactUsPage();
    }

    public SignInPage goToSignInPage() {
        logStep("Going to Sign In page");
        signInLink.isDisplayed();
        signInLink.click();

        return new SignInPage();
    }
}
