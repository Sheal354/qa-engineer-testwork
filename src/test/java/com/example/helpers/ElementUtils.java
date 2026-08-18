package com.example.helpers;

import com.codeborne.selenide.Selenide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.title;

/** Класс для управления элементами страниц */
public class ElementUtils {
    private static final Logger logger = LoggerFactory.getLogger(ElementUtils.class);
    /** Метод для проверки заголовка страницы */
    public static boolean isTitleCorrect(String expected) {
        try {
            webdriver().shouldHave(title(expected));
            return true;
        } catch (Exception e) {
            logger.error("Некорректный заголовок страницы - {}", Selenide.title());
            return false;
        }
    }
}
