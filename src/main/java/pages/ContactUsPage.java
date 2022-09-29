package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

import static java.lang.String.format;
import static utils.ui.UiBaseTest.logStep;

public class ContactUsPage extends BasePage {

    @FindBy(id = "id_contact")
    public static WebElement subjectHeadingDropdown;
    @FindBy(id = "email")
    public static WebElement emailInput;
    @FindBy(id = "id_order")
    public static WebElement orderReferenceInput;
    @FindBy(id = "message")
    public static WebElement messageTextArea;
    @FindBy(id = "submitMessage")
    public static WebElement submitMessageButton;
    @FindBy(xpath = ".//*[contains(text(),'Your message has been successfully sent to our team.')]")
    public static WebElement messageIsSentAlert;
    @FindBy(id = "fileUpload")
    public static WebElement fileUpload;

    public ContactUsPage() {
        PageFactory.initElements(driver, this);
    }

    public ContactUsPage selectSubject() {
        logStep("Select first subject from drop-down");
        new Select(subjectHeadingDropdown).selectByIndex(1);

        return this;
    }

    public ContactUsPage enterEmail(String email) {
        logStep(format("Enter email %s to email input field", email));
        emailInput.sendKeys(email);

        return this;
    }

    public ContactUsPage enterOrderReference(String orderReference) {
        logStep(format("Enter Order Reference %s to Order Reference input field", orderReference));
        orderReferenceInput.sendKeys(orderReference);

        return this;
    }

    public ContactUsPage enterMessage(String message) {
        logStep(format("Enter Message {%s} to message input field", message));
        messageTextArea.sendKeys(message);

        return this;
    }

    public ContactUsPage uploadFile() {
        logStep("Upload dummy file");
        fileUpload.sendKeys(new File("src/test/resources/dummy.txt").getAbsolutePath());

        return this;
    }

    public ContactUsPage fillInTheForm(String email, String orderReference, String message) {
        return this.selectSubject()
                .enterEmail(email)
                .enterOrderReference(orderReference)
                .enterMessage(message)
                .uploadFile();
    }

    public ContactUsPage submitTheForm() {
        logStep("Submit the Contact Us form");
        submitMessageButton.click();

        return this;
    }

    public void verifyMessageIsSent() {
        logStep("Verify if message was sent");
        messageIsSentAlert.isDisplayed();
    }
}
