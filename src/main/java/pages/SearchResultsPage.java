package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static utils.ui.UiBaseTest.logStep;


public class SearchResultsPage extends BasePage {

    @FindBy(className = "product_list")
    public static WebElement searchResultsList;
    @FindBy(xpath = ".//*[contains(text(),'No results were found for your search')]")
    public static WebElement noSearchResultsMessage;
    
    public SearchResultsPage (){
        PageFactory.initElements(driver, this);
    }

    public SearchResultsPage verifySearchResultsAreFound(){
        logStep("Verify if search results are displayed");
        searchResultsList.isDisplayed();

        return this;
    }

    public SearchResultsPage verifyNoResultsMessageIsShown(){
        logStep("Verify if message 'No Results was found' is shown");
        noSearchResultsMessage.isDisplayed();

        return this;
    }
}
