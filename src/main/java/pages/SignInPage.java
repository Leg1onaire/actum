package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.String.format;
import static utils.ui.UiBaseTest.logStep;

public class SignInPage extends BasePage {

    @FindBy(id = "email_create")
    public static WebElement emailCreateInput;
    @FindBy(id = "SubmitCreate")
    public static WebElement createAccountButton;

    public SignInPage() {
        PageFactory.initElements(driver, this);
    }

    public RegistrationPage createAccount(String email){
        logStep(format("Set email %s for registration", email));
        emailCreateInput.sendKeys(email);
        createAccountButton.click();

        return new RegistrationPage();
    }
}
