package com.example.tests;

import com.codeborne.selenide.Selenide;
import com.example.helpers.DataProviderHelper;
import com.example.helpers.ElementUtils;
import com.example.pages.AuthPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.open;

@Feature("Авторизация пользователя")
public class AuthTests extends BaseTest{
    private AuthPage authPage;

    @BeforeMethod
    public void initBeforeMethod() {
        authPage = new AuthPage();
        open("/");
    }

    @Story("Проверка корректности обработки неверных данных")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "invalidAuthData", dataProviderClass = DataProviderHelper.class,
            description = "Invalid Auth Test")
    public void invalidAuthTest(String login, String password) {
        SoftAssert softAssert = new SoftAssert();
        // Вводим данные, нажимаем кнопку входа
        authPage.inputLogin(login)
                .inputPassword(password)
                .enterBtnClick();

        // Проверяем текст уведомления
        String alertMsg = Selenide.confirm();
        softAssert.assertTrue(alertMsg.contains("Неверные данные для авторизации"),
                "Некорркетный текст о неверных данных авторизации!\n" +
                "Ожидалось - 'Неверные данные для авторизации'\n" +
                "Полученный текст - " + alertMsg);
        // Проверяем, что форма перешла в невалидный вид
        softAssert.assertTrue(authPage.isFormInvalid(), "Форма авторизации не перешла в невалидный вид!");
        softAssert.assertAll();
    }

    @Story("Проверка корректного входа в систему")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "validAuthData", dataProviderClass = DataProviderHelper.class,
            description = "Valid Auth Test")
    public void validAuthTest(String login, String password) {
        // Вводим данные, нажимаем кнопку входа
        authPage.inputLogin(login)
                .inputPassword(password)
                .enterBtnClick();

        // Проверяем переход на новую страницу
        Assert.assertTrue(ElementUtils.isTitleCorrect("Главная страница"),
                "Главная страница не открылась!");

        // Чистим куки, для успешной работы последующих тестов
        Selenide.clearBrowserCookies();
    }
}