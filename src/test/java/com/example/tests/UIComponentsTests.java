package com.example.tests;

import com.codeborne.selenide.Selenide;
import com.example.helpers.ElementUtils;
import com.example.pages.AuthPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

@Feature("UI-компоненты")
public class UIComponentsTests extends BaseTest{
    private AuthPage authPage;

    @BeforeMethod
    public void initBeforeMethod() {
        authPage = new AuthPage();
        open("/");
    }

    @Story("Проверка работы скрытого режима поля для ввода пароля")
    @Test(description = "Show Password Test")
    public void showPasswordTest() {
        // Вводим данные, нажимаем кнопку показа пароля
        authPage.inputPassword("password")
                .showPasswordBtnClick();

        // Проверяем, что поле перешло в открытый вид
        Assert.assertTrue(authPage.isPasswordShown(), "Поле для ввода пароля не перешло в открытый тип!");
    }

    @Story("Тестирование клавиатурной доступности")
    @Test(description = "Keyboard Test")
    public void keyboardTest() {
        // Нажимаем на поле для ввода логина
        authPage.loginInputClick();
        // Вводим данные логина и нажимаем Tab
        Selenide.switchTo().activeElement().sendKeys("fominaelena", Keys.TAB);

        // Проверяем, что фокус перешел на поле для ввода пароля
        Assert.assertTrue(authPage.isPasswordFocused(), "Фокус не перешел на поле для ввода пароля!");

        // Вводим данные пароля и нажимаем Enter
        Selenide.switchTo().activeElement().sendKeys("1P73BP4Z", Keys.ENTER);

        // Проверяем переход на новую страницу
        Assert.assertTrue(ElementUtils.isTitleCorrect("Главная страница"),
                "Главная страница не открылась!");

        // Чистим куки, для успешной работы последующих тестов
        Selenide.clearBrowserCookies();
    }
}