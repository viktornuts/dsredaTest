package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import pages.StartPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class DSredaTests extends TestBase {

    StartPage startPage = new StartPage();
    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    @DisplayName("Проверка дашборда главной страницы")
    void checkDashboardTest() {
        step("Открываем сайт https://dasreda.ru/", () -> {
            open("https://dasreda.ru/");
        });

        step("check dashboard", () -> {
            $(".sc-jRQBWg.sc-1rd2md3.gjmuaX.fBFSxG").shouldBe(visible);
        });
    }

    @Test
    @DisplayName("Проверка текста раздела 'Открыть бизнес'")
    void checkOpenBusinessPage() {
        step("Открываем сайт https://dasreda.ru/", () -> {
            open("https://dasreda.ru/");
        });

        step("Переходим в раздел 'Открыть бизнес'", () -> {
            $("[data-qa='Nav-rbo']").click();
        });

        step("Проверяем отображение текста раздела 'Акции'", () -> {
            switchTo().window(1);
            $("#test-landing-header-text").shouldBe(visible).shouldHave(text("Заре\u00ADгис\u00ADтри\u00ADруйте\n" +
                    "бизнес легко и быстро"));

        });
    }

    @Test
    @DisplayName("PageObj Проерка текста алертов(подсказок) при регистрации")
    void tariffCheckTest() {
        open("https://dasreda.ru/");
        startPage.clickRegistrationButton();
        registrationPage.clickFirstNameField().clickEmailField().clickFirstNameField().checkAlertEmail().checkAlertFirstName();

    }

    @Test
    @DisplayName("Проверка работы калькулятора НДС (начислить НДС)")
    void checkNDSCalculator() {

        step("Открываем сайт https://dasreda.ru/", () -> {
            open("https://dasreda.ru/");
        });

        step("Нажимаем на кнопку 'База знаний'", () -> {
            $("[data-qa='Nav-learn']").click();
        });

        step("Выбираем 'Калькулятор НДС'", () -> {
            $("[data-qa='Nav-calculators']").click();
        });

        step("В поле Цена вводим значение 50 000", () -> {
            $(".sc-1pp9aek.cjkUqZ").setValue("50000");
        });

        step("Выбираем тип операции начислить НДС", () -> {
            $$(".sc-1u8oc1p.EtgpM").get(1).click();
        });

        step("Выбираем ставку НДС 20%", () -> {
            $$(".sc-1u8oc1p.EtgpM").get(4).click();
        });

        step("Проверяем сумму с НДС", () -> {
            $$(".sc-1gwk9xj.cbMykj").get(2).shouldHave(text("60 000 ₽"));
        });

    }

    @Test
    @DisplayName("Проверка разедла Видео, что в нем присутствует видео 'Как сэкономить время и деньги для своего дела'")
    void checkVideosPage() {
        step("Открываем сайт https://dasreda.ru/", () -> {
            open("https://dasreda.ru/");
        });

        step("Нажимаем на кнопку 'База знаний'", () -> {
            $("[data-qa='Nav-learn']").click();
        });

        step("Выбираем 'Калькулятор НДС'", () -> {
            $("[data-qa='Nav-learn-videos']").click();
        });

        step("Проверяем наличие видео 'Как сэкономить время и деньги для своего дела'", () -> {
            $$("[class='TitleLimit__root--2fOOr Content__card-title--1YmUW']").get(0).shouldHave(text("Как сэкономить время и деньги для своего дела"));
        });
    }

}
