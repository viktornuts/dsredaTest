package tests;

import apiClasses.RootVarClass;
import apiClasses.VariablesData;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import pages.StartPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class DSredaTests extends TestBase {

    public String token = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJWaWt0b3JudXRzQEdtYWlsLmNvbSIsIm5iZiI6MTY2MjQ4MTMwMCwicm9sZXMiOlsiYjJiLnVzZXIiLCJjb3Vyc2VzLnVzZXIiLCJldmVudHMudXNlciIsImV4cGVydHMudXNlciIsInJiby51c2VyIiwic3VydmV5LnVzZXIiLCJ0cmFqZWN0LnVzZXIiLCJ1bmktYmxvZy51c2VyIiwidW5pLWNvbW1lbnRzLnVzZXIiLCJ1bmktZGVsaXZlcnkgLnVzZXIiLCJ1bmktbG1zLnN0dWRlbnQiLCJ1bmktcGF5bWVudHMudXNlciIsInVuaS1yYXRpbmdzLnVzZXIiLCJ1bmktc29jaWFsLnVzZXIiLCJ1bmktc3RvcmFnZS51c2VyIiwidW5pLXZpZGVvLnVzZXIiXSwiaXNzIjoiaHR0cHM6XC9cL3Nzby5kYXNyZWRhLnJ1IiwiaWQiOjE5MTM1MzgsImV4cCI6MTY2MzM0NTMwMCwiaWF0IjoxNjYyNDgxMzAwLCJqdGkiOiI2YjQwOGU5My02ODU1LTQ2MzQtODk0ZS1lNmIwOWFlNWI2Y2YifQ.R5Se-OztIno2HBmFoHCMudo8z1mwjFzeSbTS0eUgqxrB384hSkhItReFIVev-7BxQ97x-JY0uhNasEC8V18QrKthrqFaYk9IBuCG2BwHf_rowf-zF_xIUM_AKoKOizwSnkH9t7MqptB3hprzUHabFkK-gROcsQnprEMvSlEY1solNKiEPHtaNpGWks-uHx01BJzV9vk6fB9vej9_wLPUeWk29WTzPZB-n6Ylh-UMmihmEnEsB1Gfx0nT-2_6dj6_LB_Q9MAM_L_qYLIojNO0t-YSpUYza--611juFqN_7LITlMug3fKixv-p8YRCoPCdroPjlbaOHDGhqIypL6LDUA";
    StartPage startPage = new StartPage();
    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    @DisplayName("Проверка дашборда главной страницы")
    void checkDashboardTest() {
        step("Открываем сайт https://dasreda.ru/", () -> {
            open("https://dasreda.ru/");
        });

        step("check dashboard", () -> {
            $(".sc-1ngb3td.cqdeDj").shouldBe(visible).shouldHave(text("Персональные данные: какие изменения учесть компаниям с 1 сентября"));
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
