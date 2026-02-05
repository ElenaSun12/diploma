package ru.iteco.fmhandroid.ui.steps;


import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.Espresso.onView;

public class AuthorizationSteps {
    private final AuthorizationPage authPage = new AuthorizationPage();
    private final MainPage mainPage = new MainPage();

    public void login(String login, String password) {
        authPage.waitForAuthorizationScreen();
        authPage.enterLogin(login);
        authPage.enterPassword(password);
        authPage.clickSignInButton();
    }

    public void verifyLoginSuccessful() {
        // Ждем загрузку главного экрана
        mainPage.waitForMainScreen();

        // Проверяем ключевые элементы главного экрана:

        // 1. Меню пользователя (иконка в правом верхнем углу)
        mainPage.checkbuttonMenuIsDisplayed();

        // 2. Заголовок главной страницы
        mainPage.checkTrademarkIsDisplayed();

        // 3. Основные разделы (Новости, Заявки и т.д.)
        mainPage.checkLogoutButtonIsDisplayed();
    }

//    public void verifyLoginFailed() {
//        // Проверяем, что остались на экране авторизации
//        authPage.checkAuthorizationScreenIsStillDisplayed();
//
//        // Или проверяем сообщение об ошибке
//        authPage.checkErrorMessageIsDisplayed();
//    }
}