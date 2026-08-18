package com.example.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

/** Класс страницы авторизации**/
public class AuthPage {

    /** Форма авторизации */
    private final SelenideElement authForm = $("#login_form_panel");

    /** Поле ввода логина */
    private final SelenideElement loginInput = $("input[name='user']");

    /** Поле ввода пароля */
    private final SelenideElement passwordInput = $("input[name='password']");

    /** Кнопка показа пароля */
    private final SelenideElement showPasswordBtn = $("#show_password");

    /** Кнопка входа */
    private final SelenideElement enterBtn = $("#button_submit_login_form");

    /** Элемент перехода на страницу восстановления пароля */
    private final SelenideElement forgotPasswordLink = $(".mira-default-login-page-link");

    @Step("Ввод логина")
    public AuthPage inputLogin(String login) {
        loginInput.shouldBe(visible).sendKeys(login);
        return this;
    }

    @Step("Ввод пароля")
    public AuthPage inputPassword(String password) {
        passwordInput.shouldBe(visible).sendKeys(password);
        return this;
    }

    @Step("Нажатие кнопки показа пароля")
    public AuthPage showPasswordBtnClick() {
        showPasswordBtn.shouldBe(visible).click();
        return this;
    }

    @Step("Нажатие кнопки входа")
    public AuthPage enterBtnClick() {
        enterBtn.shouldBe(visible).click();
        return this;
    }

    @Step("Нажатие на переход к восстановлению пароля")
    public AuthPage forgotPasswordLinkClick() {
        forgotPasswordLink.shouldBe(visible).click();
        return this;
    }

    @Step("Нажатие на поле для ввода логина")
    public AuthPage loginInputClick() {
        loginInput.shouldBe(visible).click();
        return this;
    }

    @Step("Проверка показа формы авторизации")
    public boolean isAuthFormShown() {
        return authForm.is(visible);
    }

    @Step("Проверка перехода формы в невалидный вид")
    public boolean isFormInvalid() {return authForm.is(cssClass("invalid"));}

    @Step("Проверка перехода поля пароля в открытый вид")
    public boolean isPasswordShown() {return passwordInput.is(type("text"));}

    @Step("Проверка фокуса на поле для ввода пароля")
    public boolean isPasswordFocused() {return passwordInput.is(focused);}
}
