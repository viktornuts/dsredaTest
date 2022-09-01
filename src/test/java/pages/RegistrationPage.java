package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;


public class RegistrationPage {

    SelenideElement
            alertFirstName = $$("[class='sc-fi2xrp ZYWKH']").get(0),
            alertEmail = $$("[class='sc-fi2xrp ZYWKH']").get(1),
            firstNameField = $("[data-qa='firstName_field']"),
            emailField = $("[data-qa='email_field']");

    public RegistrationPage clickFirstNameField() {
        firstNameField.click();
        return this;
    }

    public RegistrationPage clickEmailField() {
        emailField.click();
        return this;
    }

    public RegistrationPage checkAlertEmail() {
        alertEmail.shouldBe(Condition.visible).shouldHave(Condition.text("Поле обязательно к заполнению"));
        return this;
    }

    public RegistrationPage checkAlertFirstName() {
        alertFirstName.shouldBe(Condition.visible).shouldHave(Condition.text("Поле обязательно к заполнению"));
        return this;
    }


}