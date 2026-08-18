package com.example.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Epic;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@Epic("Страница авторизации")
public class BaseTest {
    @BeforeClass
    public void initBeforeClass() {
        Configuration.baseUrl = "https://qa.copy.mirapolis.ru/mira/";
//        Configuration.headless = true;
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
