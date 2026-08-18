package com.example.tests;

import com.example.helpers.DataProviderHelper;
import com.example.helpers.ElementUtils;
import com.example.pages.AuthPage;
import com.example.pages.ForgotPasswordPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

@Feature("Форма восстановления пароля")
public class ForgotPasswordTests extends BaseTest {
    private ForgotPasswordPage forgotPasswordPage;

    @Story("Проверка работы перехода на форму восстановления пароля")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Links Test")
    public void linksTest() {
        AuthPage authPage = new AuthPage();
        // Открываем страницу авторизации
        open("/");
        // Переходим на страницу восстановления пароля
        forgotPasswordPage = authPage.forgotPasswordLinkClick();

        // Проверяем, что открылась форма восстановления пароля
        Assert.assertTrue(ElementUtils.isTitleCorrect("Восстановление пароля"),
                "Форма восстановления пароля не открылась!");
        Assert.assertTrue(forgotPasswordPage.isForgotPasswordFormShown(),
                "Форма восстановления пароля не отображается!");

        // Переходим на страницу авторизации
        authPage = forgotPasswordPage.authLinkClick();

        // Проверяем, что открылась форма авторизации
        Assert.assertTrue(ElementUtils.isTitleCorrect("Авторизация"),
                "Форма авторизации не открылась!");
        Assert.assertTrue(authPage.isAuthFormShown(),
                "Форма авторизации не отображается!");
    }

    @Story("Проверка обработки неверных данных в форме восстановления пароля")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "invalidForgotPasswordData", dataProviderClass = DataProviderHelper.class,
            description = "Invalid Forgot Password Test")
    public void invalidForgotPasswordTest(String data) {
        forgotPasswordPage = new ForgotPasswordPage();
        // Открываем страницу восстановления пароля
        open("Do?doaction=Go&type=remindpassword");

        // Вводим данные и отправляем
        forgotPasswordPage.inputLoginOrEmail(data)
                .submitBtnClick();

        Assert.assertEquals(forgotPasswordPage.getAlertText(),
                "Пользователь с таким именем не найден.",
                "Сообщение об ошибке некорректно!");
    }

    @Story("Проверка корректной работы формы восстановления пароля")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "validForgotPasswordData", dataProviderClass = DataProviderHelper.class,
            description = "Valid Forgot Password Test")
    public void validForgotPasswordTest(String data) {
        forgotPasswordPage = new ForgotPasswordPage();
        // Открываем страницу восстановления пароля
        open("Do?doaction=Go&type=remindpassword");

        // Вводим данные и отправляем
        forgotPasswordPage.inputLoginOrEmail(data)
                .submitBtnClick();

        Assert.assertEquals(forgotPasswordPage.getSuccessText(),
                "На ваш электронный адрес отправлена инструкция по восстановлению пароля.",
                "Сообщение об успехе некорректно!");
    }
}