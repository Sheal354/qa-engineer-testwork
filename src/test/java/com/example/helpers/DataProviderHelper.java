package com.example.helpers;

import org.testng.annotations.DataProvider;

/** Класс с данными DataProvider */
public class DataProviderHelper {
    /**
     * {login, password}
     * @return массив с тестовыми данными для тестирования обработки неверных данных при авторизации
     */
    @DataProvider()
    public Object[][] invalidAuthData() {
        return new Object[][]{
                {"", ""},
                {"login", "password"},
        };
    }

    /**
     * {login, password}
     * @return массив с тестовыми данными для тестирования успешной авторизации
     */
    @DataProvider()
    public Object[][] validAuthData() {
        return new Object[][]{
                {"fominaelena", "1P73BP4Z"}
        };
    }

    /**
     * {login/email}
     * @return массив с тестовыми данными для тестирования обработки неверных данных при восстановлении пароля
     */
    @DataProvider()
    public Object[][] invalidForgotPasswordData() {
        return new Object[][]{
                {""}, {"login"}
        };
    }

    /**
     * {login/email}
     * @return массив с тестовыми данными для тестирования успешного восстановления пароля
     */
    @DataProvider()
    public Object[][] validForgotPasswordData() {
        return new Object[][]{
                {"fominaelena"}
        };
    }
}