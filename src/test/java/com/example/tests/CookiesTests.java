package com.example.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.example.helpers.DataProviderHelper;
import com.example.helpers.ElementUtils;
import com.example.pages.AuthPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static com.codeborne.selenide.Selenide.open;

@Feature("Обработка и хранение сессий")
public class CookiesTests extends BaseTest{
    private AuthPage authPage;

    @BeforeMethod
    public void initBeforeMethod() {
        authPage = new AuthPage();
        open("/");
    }

    @Story("Проверка обработки и сохранения сессии")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "validAuthData", dataProviderClass = DataProviderHelper.class,
            description = "Cookies Test")
    public void cookiesTest(String login, String password) {
        // Вводим данные, нажимаем кнопку входа
        authPage.inputLogin(login)
                .inputPassword(password)
                .enterBtnClick();

        // Проверяем переход на новую страницу
        Assert.assertTrue(ElementUtils.isTitleCorrect("Главная страница"),
                "Главная страница не открылась!");

        // Сохраняем все куки в Set
        Set<Cookie> testCookies = WebDriverRunner.getWebDriver().manage().getCookies();

        // Закрываем браузер и WebDriver
        Selenide.closeWebDriver();

        // Открываем новую страницу и новый WebDriver
        open("/");

        // Проверяем открытие формы авторизации
        Assert.assertTrue(ElementUtils.isTitleCorrect("Авторизация"),
                "Форма авторизации не открылась!");

        // Восстанавливаем сохранённые куки в новом драйвере
        for (Cookie cookie : testCookies) {
            WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        }

        // Обновляем страницу
        open("/");

        // Проверяем, что мы снова главной странице (сессия восстановлена)
        Assert.assertTrue(ElementUtils.isTitleCorrect("Главная страница"),
                "Сессия не восстановилась после перезапуска браузера!");

        // Чистим куки, для успешной работы последующих тестов
        Selenide.clearBrowserCookies();
    }
}