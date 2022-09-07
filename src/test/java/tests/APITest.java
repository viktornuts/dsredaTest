package tests;

import apiClasses.RootVarClass;
import apiClasses.VariablesData;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class APITest {

    @Test
    @DisplayName("Проверка информации о подписке (API)")
    void checkSubscriptionInfo() {

        String token = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJWaWt0b3JudXRzQEdtYWlsLmNvbSIsIm5iZiI6MTY2MjQ4MTMwMCwicm9sZXMiOlsiYjJiLnVzZXIiLCJjb3Vyc2VzLnVzZXIiLCJldmVudHMudXNlciIsImV4cGVydHMudXNlciIsInJiby51c2VyIiwic3VydmV5LnVzZXIiLCJ0cmFqZWN0LnVzZXIiLCJ1bmktYmxvZy51c2VyIiwidW5pLWNvbW1lbnRzLnVzZXIiLCJ1bmktZGVsaXZlcnkgLnVzZXIiLCJ1bmktbG1zLnN0dWRlbnQiLCJ1bmktcGF5bWVudHMudXNlciIsInVuaS1yYXRpbmdzLnVzZXIiLCJ1bmktc29jaWFsLnVzZXIiLCJ1bmktc3RvcmFnZS51c2VyIiwidW5pLXZpZGVvLnVzZXIiXSwiaXNzIjoiaHR0cHM6XC9cL3Nzby5kYXNyZWRhLnJ1IiwiaWQiOjE5MTM1MzgsImV4cCI6MTY2MzM0NTMwMCwiaWF0IjoxNjYyNDgxMzAwLCJqdGkiOiI2YjQwOGU5My02ODU1LTQ2MzQtODk0ZS1lNmIwOWFlNWI2Y2YifQ.R5Se-OztIno2HBmFoHCMudo8z1mwjFzeSbTS0eUgqxrB384hSkhItReFIVev-7BxQ97x-JY0uhNasEC8V18QrKthrqFaYk9IBuCG2BwHf_rowf-zF_xIUM_AKoKOizwSnkH9t7MqptB3hprzUHabFkK-gROcsQnprEMvSlEY1solNKiEPHtaNpGWks-uHx01BJzV9vk6fB9vej9_wLPUeWk29WTzPZB-n6Ylh-UMmihmEnEsB1Gfx0nT-2_6dj6_LB_Q9MAM_L_qYLIojNO0t-YSpUYza--611juFqN_7LITlMug3fKixv-p8YRCoPCdroPjlbaOHDGhqIypL6LDUA";

        VariablesData variablesData = new VariablesData("GET","/v3/contacts?query[email]=Viktornuts@Gmail.com");
        RootVarClass rootVarClass = new RootVarClass("subscription", variablesData, "query subscription($method: String!, $url: String!) {\n" +
                "  subscription(method: $method, url: $url) {\n" +
                "    contactId\n" +
                "    href\n" +
                "    name\n" +
                "    email\n" +
                "    note\n" +
                "    activities\n" +
                "    __typename\n" +
                "  }\n" +
                "}\n" );

        given()
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .body(rootVarClass)
                .when()
                .post("https://apollo-server.dasreda.ru/graphql")
                .then().log().all()
                .statusCode(200)
                .body("data.subscription.contactId[0]", is("01IX"))
                .body("data.subscription.href[0]", is("https://api3.getresponse360.pl/v3/contacts/01IX"))
                .body("data.subscription.name[0]", is("Виктор  ―."))
                .body("data.subscription.email[0]", is("Viktornuts@gmail.com"))
                .body("data.subscription.__typename[0]", is("Subscription"));

    }

}
