package ru.iteco.fmhandroid.ui.steps;


import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.utils.ViewHelper;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.Espresso.onView;

public class AuthorizationSteps {
    private final AuthorizationPage authPage = new AuthorizationPage();
    private final MainPage mainPage = new MainPage();

    public void login(String login, String password) {
        // Сначала ждем экран авторизации
        authPage.waitForAuthorizationScreen();

        // Вводим данные
        authPage.enterLogin(login);
        authPage.enterPassword(password);

        // Нажимаем кнопку
        authPage.clickSignInButton();

        // ВАЖНО: Даем время на анимацию
        try {
            Thread.sleep(500); // небольшая пауза для начала анимации
        } catch (InterruptedException e) {
            // ignore
        }
    }

    public void verifyLoginSuccessful() {
        // СНАЧАЛА ждем исчезновения экрана авторизации с таймаутом
        ViewHelper.waitForElementGone(R.id.login_text_input_layout, 5000);

        // ПОТОМ ждем загрузку главной страницы
        mainPage.waitForMainScreen();

        // ПОТОМ проверяем элементы
        mainPage.checkbuttonMenuIsDisplayed();
        mainPage.checkTrademarkIsDisplayed();
    }

//    public void verifyLoginFailed() {
//        // Проверяем, что остались на экране авторизации
//        authPage.checkAuthorizationScreenIsStillDisplayed();
//
//        // Или проверяем сообщение об ошибке
//        authPage.checkErrorMessageIsDisplayed();
//    }
}