package pages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import javax.annotation.Nullable;

import static helpers.Helper.getElementValue;
import static helpers.RandomDataGenerator.getRandomEnum;
import static helpers.RandomDataGenerator.randomAlphaString;
import static helpers.RandomDataGenerator.randomEmail;
import static helpers.RandomDataGenerator.randomFiveDigits;
import static helpers.RandomDataGenerator.randomIntFromTo;
import static helpers.RandomDataGenerator.randomPhoneNumber;
import static java.lang.String.format;
import static utils.ui.UiBaseTest.logStep;

public class RegistrationPage extends BasePage {

    //Personal Information area
    @FindBy(id = "customer_firstname")
    private static WebElement personalFirstNameInput;
    @FindBy(id = "customer_lastname")
    private static WebElement personalLastNameInput;
    @FindBy(id = "email")
    private static WebElement emailInput;
    @FindBy(id = "passwd")
    private static WebElement passwordInput;
    @FindBy(id = "days")
    private static WebElement daysDropDown;
    @FindBy(id = "months")
    private static WebElement monthDropDown;
    @FindBy(id = "years")
    private static WebElement yearDropDown;
    @FindBy(id = "id_gender1")
    private static WebElement msGenderRadio;
    @FindBy(id = "id_gender2")
    private static WebElement mrsGenderRadio;
    @FindBy(id = "uniform-newsletter")
    private static WebElement newsLetterCheckbox;
    @FindBy(id = "optin")
    private static WebElement specialOffersCheckbox;

    //Address area
    @FindBy(id = "firstname")
    private static WebElement addressFirstNameInput;
    @FindBy(id = "lastname")
    private static WebElement addressLastNameInput;
    @FindBy(id = "company")
    private static WebElement addressCompanyInput;
    @FindBy(id = "address1")
    private static WebElement addressInput;
    @FindBy(id = "address2")
    private static WebElement address2Input;
    @FindBy(id = "city")
    private static WebElement cityInput;
    @FindBy(id = "id_state")
    private static WebElement stateDropDown;
    @FindBy(id = "postcode")
    private static WebElement zipInput;
    @FindBy(id = "id_country")
    private static WebElement countryDropDown;
    @FindBy(id = "other")
    private static WebElement additionalInfoTextBox;
    @FindBy(id = "phone")
    private static WebElement homePhoneInput;
    @FindBy(id = "phone_mobile")
    private static WebElement mobilePhoneInput;
    @FindBy(id = "alias")
    private static WebElement addressAliasInput;

    @FindBy(id = "submitAccount")
    private static WebElement registerButton;

    @FindBy(xpath = ".//*[contains(@title,'Information')]")
    private static WebElement myPersonalInformationItem;


    public RegistrationPage() {
        PageFactory.initElements(driver, this);
    }

    @AllArgsConstructor
    public enum Gender {
        MR(msGenderRadio, "Mr"),
        MRS(mrsGenderRadio, "Mrs");

        @Getter
        private final WebElement locator;
        @Getter
        private final String name;
    }

    public RegistrationPage selectDayOfBirth(@Nullable Integer day) {
        Select days = new Select(daysDropDown);
        daysDropDown.isDisplayed();
        days.selectByIndex(day == null ? randomIntFromTo(1, days.getOptions().size()) - 1 : day);

        logStep(format("Selected day of birth %s", days.getFirstSelectedOption().getAccessibleName()));

        return this;
    }

    public RegistrationPage selectMonthOfBirth(@Nullable Integer month) {
        Select months = new Select(monthDropDown);
        monthDropDown.isDisplayed();
        months.selectByIndex(month == null ? randomIntFromTo(1, months.getOptions().size()) - 1 : month);
        logStep(format("Selected month of birth is %s", months.getFirstSelectedOption().getAccessibleName()));

        return this;
    }

    public RegistrationPage selectYearOfBirth(@Nullable Integer year) {
        Select years = new Select(yearDropDown);
        yearDropDown.isDisplayed();
        years.selectByIndex(year == null ? randomIntFromTo(2, years.getOptions().size()) - 1 : year);
        logStep(format("Selected year of birth is %s", years.getFirstSelectedOption().getAccessibleName()));

        return this;
    }


    public RegistrationPage selectDateOfBirth(@Nullable Integer day,
                                              @Nullable Integer month,
                                              @Nullable Integer year) {
        logStep("Select DOB");
        this.selectDayOfBirth(day)
                .selectMonthOfBirth(month)
                .selectYearOfBirth(year);

        return this;
    }

    public RegistrationPage selectDateOfBirth() {
        this.selectDateOfBirth(null, null, null);

        return this;
    }

    public RegistrationPage selectGender(@Nullable Gender gender) {
        if (gender == null) {
            gender = getRandomEnum(Gender.class);
        }
        logStep(format("Select gender %s", gender.getName()));
        gender.getLocator().click();

        return this;
    }

    public RegistrationPage selectGender() {
        this.selectGender(null);

        return this;
    }

    public RegistrationPage setFirstName(@Nullable String firstName) {
        firstName = firstName == null ? randomAlphaString(10) : firstName;
        personalFirstNameInput.sendKeys(firstName);

        logStep(format("Selected First Name is %s", firstName));

        return this;
    }

    public RegistrationPage setFirstName() {
        this.setFirstName(null);

        return this;
    }

    public RegistrationPage setLastName(@Nullable String lastName) {
        lastName = lastName == null ? randomAlphaString(10) : lastName;
        personalLastNameInput.sendKeys(lastName);

        logStep(format("Selected Last Name is %s", lastName));

        return this;
    }

    public RegistrationPage setLastName() {
        this.setLastName(null);

        return this;
    }

    public RegistrationPage setEmail(@Nullable String email) {
        email = email == null ? randomEmail() : email;
        emailInput.clear();
        emailInput.sendKeys(email);

        logStep(format("Selected Email is %s", email));

        return this;
    }

    public RegistrationPage setRandomEmail() {
        this.setEmail(null);

        return this;
    }

    public RegistrationPage setPassword(@Nullable String password) {
        password = password == null ? randomAlphaString(10) : password;
        passwordInput.sendKeys(password);

        logStep(format("Selected Password is %s", password));

        return this;
    }

    public RegistrationPage setPassword() {
        this.setPassword(null);

        return this;
    }

    public RegistrationPage selectNotificationCheckboxes(boolean selectNewsLetter, boolean selectSpecialOffers) {
        if (selectNewsLetter) {
            newsLetterCheckbox.click();
            newsLetterCheckbox.isSelected();
        }
        if (selectSpecialOffers) {
            specialOffersCheckbox.click();
            specialOffersCheckbox.isSelected();
        }

        return this;
    }

    public RegistrationPage setAddressFirstName(@Nullable String firstName, boolean updateAnyway) {
        firstName = firstName == null ? randomAlphaString(10) : firstName;
        String value = getElementValue(addressFirstNameInput);
        if (value.equals(getElementValue(addressFirstNameInput)) && !updateAnyway) {
            logStep("Address First name is the same as Personal Info First Name, don`t update the field");
            return this;
        }

        addressFirstNameInput.clear();
        addressFirstNameInput.sendKeys(firstName);

        logStep(format("Selected Address First Name is %s", firstName));

        return this;
    }

    public RegistrationPage setAddressFirstName() {
        this.setAddressFirstName(null, false);

        return this;
    }

    public RegistrationPage setAddressLastName(@Nullable String lastName, boolean updateAnyway) {
        lastName = lastName == null ? randomAlphaString(10) : lastName;
        String value = getElementValue(addressLastNameInput);
        if (value.equals(getElementValue(personalLastNameInput)) && !updateAnyway) {
            logStep("Address Last name is the same as Personal Info Last Name, don`t update the field");
            return this;
        }

        addressLastNameInput.clear();
        addressLastNameInput.sendKeys(lastName);

        logStep(format("Selected Address Last Name is %s", lastName));

        return this;
    }

    public RegistrationPage setAddressLastName() {
        this.setAddressLastName(null, false);

        return this;
    }

    public RegistrationPage setCompany(@Nullable String company) {
        company = company == null ? randomAlphaString(10) : company;
        addressCompanyInput.sendKeys(company);

        logStep(format("Selected Company is %s", company));

        return this;
    }

    public RegistrationPage setCompany() {
        this.setCompany(null);

        return this;
    }

    public RegistrationPage setAddress(@Nullable String address) {
        address = address == null ? randomAlphaString(10) : address;
        addressInput.sendKeys(address);

        logStep(format("Selected Address 1 is %s", address));

        return this;
    }

    public RegistrationPage setAddress() {
        this.setAddress(null);

        return this;
    }

    public RegistrationPage setAddress2(@Nullable String address) {
        address = address == null ? randomAlphaString(10) : address;
        address2Input.sendKeys(address);

        logStep(format("Selected Address 2 is %s", address));

        return this;
    }

    public RegistrationPage setAddress2() {
        this.setAddress2(null);

        return this;
    }

    public RegistrationPage setCity(@Nullable String city) {
        city = city == null ? randomAlphaString(10) : city;
        cityInput.sendKeys(city);

        logStep(format("Selected City is %s", city));

        return this;
    }

    public RegistrationPage setCity() {
        this.setCity(null);

        return this;
    }

    public RegistrationPage selectState() {
        Select states = new Select(stateDropDown);
        stateDropDown.isDisplayed();
        states.selectByIndex(randomIntFromTo(1, states.getOptions().size() - 1));

        logStep(format("Selected State is %s", states.getFirstSelectedOption().getAccessibleName()));

        return this;
    }

    public RegistrationPage setZip(@Nullable String zip) {
        zip = zip == null ? randomFiveDigits().toString() : zip;
        zipInput.sendKeys(zip);

        logStep(format("Selected ZIP Code is %s", zip));

        return this;
    }

    public RegistrationPage setZip() {
        this.setZip(null);

        return this;
    }

    public RegistrationPage selectUSCountry() {
        Select states = new Select(countryDropDown);
        states.selectByVisibleText("United States");

        logStep("United States Country is selected");

        return this;
    }

    public RegistrationPage setAdditionalInfo(@Nullable String text, int wordsCounter) {
        if (text == null) {
            text = randomAlphaString(10);
            StringBuilder textBuilder = new StringBuilder(text);
            for (int i = 0; i < wordsCounter; i++) {
                textBuilder.append(randomAlphaString(randomIntFromTo(1, 16))).append(" ");
            }
            text = textBuilder.toString();
        }
        additionalInfoTextBox.sendKeys(text);

        logStep(format("Additional Info message is %s", text));

        return this;
    }

    public RegistrationPage setAdditionalInfo() {
        this.setAdditionalInfo(null, 10);

        return this;
    }

    public RegistrationPage setHomePhone(@Nullable String homePhone) {
        homePhone = homePhone == null ? randomPhoneNumber() : homePhone;
        homePhoneInput.sendKeys(homePhone);

        logStep(format("Home Phone Number is %s", homePhone));

        return this;
    }

    public RegistrationPage setHomePhone() {
        this.setHomePhone(null);

        return this;
    }

    public RegistrationPage setMobilePhone(@Nullable String mobilePhone) {
        mobilePhone = mobilePhone == null ? randomPhoneNumber() : mobilePhone;
        mobilePhoneInput.sendKeys(mobilePhone);

        logStep(format("Mobile Phone Number is %s", mobilePhone));

        return this;
    }

    public RegistrationPage setMobilePhone() {
        this.setMobilePhone(null);

        return this;
    }

    public RegistrationPage setAddressAlias(@Nullable String alias) {
        alias = alias == null ? randomAlphaString(10) : alias;
        addressAliasInput.sendKeys(alias);

        logStep(format("Address Alias is %s", alias));

        return this;
    }

    public RegistrationPage setAddressAlias() {
        this.setAddressAlias(null);

        return this;
    }

    public RegistrationPage fillInPersonalInformation() {
        return this.selectGender()
                .setFirstName()
                .setLastName()
                .setPassword()
                .selectDateOfBirth()
                .selectNotificationCheckboxes(true, true);
    }

    public RegistrationPage fillInAddress() {
        return this.setAddressFirstName()
                .setAddressLastName()
                .setCompany()
                .setAddress()
                .setAddress2()
                .setCity()
                .selectState()
                .setZip()
                .selectUSCountry()
                .setAdditionalInfo()
                .setHomePhone()
                .setMobilePhone()
                .setAddressAlias();
    }

    public RegistrationPage fillInOnlyRequiredFields() {
        return this.setFirstName()
                .setLastName()
                .setPassword()
                .setAddress()
                .setCity()
                .selectState()
                .setZip()
                .selectUSCountry()
                .setMobilePhone()
                .setAddressAlias();
    }

    public RegistrationPage clickRegisterButton() {
        registerButton.click();

        return this;
    }

    public void verifyMyAccountIsLoaded() {
        myPersonalInformationItem.isDisplayed();
    }
}
