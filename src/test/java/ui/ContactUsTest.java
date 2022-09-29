package ui;

import org.testng.annotations.Test;
import pages.MainPage;
import utils.ui.UiBaseTest;

import static helpers.RandomDataGenerator.randomAlphaString;
import static helpers.RandomDataGenerator.randomEmail;

public final class ContactUsTest extends UiBaseTest {

    @Test
    public void successMessageSendTest() {
        new MainPage()
                .goToContactUsPage()
                .fillInTheForm(randomEmail(), randomAlphaString(10), randomAlphaString(100))
                .submitTheForm()
                .verifyMessageIsSent();
    }
}
