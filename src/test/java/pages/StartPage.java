package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;


public class StartPage {

    SelenideElement
            registrationButton = $("[data-qa='Header-registration']");

    public StartPage clickRegistrationButton() {
        registrationButton.click();
        return this;
    }

}
