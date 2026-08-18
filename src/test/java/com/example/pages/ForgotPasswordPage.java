package com.example.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/** Класс страницы восстановления пароля **/
public class ForgotPasswordPage {

    /** Форма восстановления пароля */
    private final SelenideElement forgotPasswordForm = $(".mira-forgot-password-form");

    /** Поле ввода логина или email */
    private final SelenideElement loginOrEmailInput = $("input[name='loginOrEmail']");

    /** Кнопка отправки */
    private final SelenideElement submitBtn = $(".mira-page-forgot-password-button");

    /** Поле cообщения об ошибке */
    private final SelenideElement alertMsg = $(".alert");

    /** Поле cообщения об успехе */
    private final SelenideElement successMsg = $(".success");

    /** Элемент перехода на страницу авторизации */
    private final SelenideElement authLink = $(".mira-page-forgot-password-link");

    @Step("Ввод логина или email")
    public ForgotPasswordPage inputLoginOrEmail(String text) {
        loginOrEmailInput.shouldBe(visible).sendKeys(text);
        return this;
    }

    @Step("Нажатие кнопки отправки")
    public ForgotPasswordPage submitBtnClick() {
        submitBtn.shouldBe(visible).click();
        return this;
    }

    @Step("Нажатие на переход к авторизации")
    public AuthPage authLinkClick() {
        authLink.shouldBe(visible).click();
        return new AuthPage();
    }

    @Step("Получение сообщения об ошибке")
    public String getAlertText() {return alertMsg.shouldBe(visible).getText();}

    @Step("Получение сообщения об успехе")
    public String getSuccessText() {return successMsg.shouldBe(visible).getText();}

    @Step("Проверка показа формы восстановления пароля")
    public boolean isForgotPasswordFormShown(){return forgotPasswordForm.is(visible);}
}