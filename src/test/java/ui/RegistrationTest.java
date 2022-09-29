package ui;

import org.testng.annotations.Test;
import pages.MainPage;
import utils.ui.UiBaseTest;

import static helpers.RandomDataGenerator.randomEmail;

public final class RegistrationTest extends UiBaseTest {

    @Test
    public void successFullRegistrationTest() {
        new MainPage()
                .goToSignInPage()
                .createAccount(randomEmail())
                .fillInPersonalInformation()
                .fillInAddress()
                .clickRegisterButton()
                .verifyMyAccountIsLoaded();
    }

    @Test
    public void successLightRegistrationTest() {
        new MainPage()
                .goToSignInPage()
                .createAccount(randomEmail())
                .fillInOnlyRequiredFields()
                .clickRegisterButton()
                .verifyMyAccountIsLoaded();
    }
}
