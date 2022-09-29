package ui;

import org.testng.annotations.Test;
import pages.MainPage;
import utils.ui.UiBaseTest;

import static helpers.RandomDataGenerator.randomAlphaString;

public final class SearchTest extends UiBaseTest {

    @Test
    public void successSearchTest() {
        new MainPage()
                .doSearch("dress")
                .verifySearchResultsAreFound();
    }

    @Test
    public void noResultsFoundTest(){
        new MainPage()
                .doSearch(randomAlphaString(10))
                .verifyNoResultsMessageIsShown();
    }
}
