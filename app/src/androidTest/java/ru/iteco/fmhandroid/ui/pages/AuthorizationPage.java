package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import ru.iteco.fmhandroid.R;

public class AuthorizationPage {

    // Локаторы элементов
    private final int loginFieldId = R.id.login_text_input_layout;
    private final int passwordFieldId = R.id.password_text_input_layout;
    private final int signInButtonId = R.id.enter_button;
    //private final int errorMessageId = R.id.error_message;

    public void enterLogin(String login) {
        onView(withId(loginFieldId))
                .perform(typeText(login));
    }

    public void enterPassword(String password) {
        onView(withId(passwordFieldId))
                .perform(typeText(password));
    }

    public void clickSignInButton() {
        onView(withId(signInButtonId))
                .perform(click());
    }

    public void waitForAuthorizationScreen() {
        // Ожидаем появления основных элементов экрана авторизации
        try {
            // Проверяем, что поле логина отображается
            onView(withId(loginFieldId)).check(matches(isDisplayed()));

            // Можно добавить проверку и других элементов для надежности
//            onView(withId(passwordFieldId)).check(matches(isDisplayed()));
//            onView(withId(signInButtonId)).check(matches(isDisplayed()));
        } catch (Exception e) {
            // Если элементы не найдены сразу, ждем и проверяем снова
            waitForPageLoaded();
            onView(withId(loginFieldId)).check(matches(isDisplayed()));
        }
    }

//    // Проверки
//    public void checkLoginFieldHasText(String expectedText) {
//        onView(withId(loginFieldId))
//                .check(matches(withText(expectedText)));
//    }
//
//    public void checkPasswordFieldIsFilled() {
//        onView(withId(passwordFieldId))
//                .check(matches(withText("••••••••"))); // Проверка на маскированные символы
//    }

//    public void checkErrorMessageIsDisplayed(String errorText) {
//        onView(withId(errorMessageId))
//                .check(matches(withText(errorText)));
//    }

    public void waitForPageLoaded() {
        // Ожидание загрузки страницы
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isPageLoaded() {
        try {
            onView(withId(loginFieldId)).check(matches(isDisplayed()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

//    public void checkErrorMessageIsDisplayed() {
//        // Проверяем сообщение об ошибке
//        onView(withId(errorMessageId))
//                .check(matches(isDisplayed()));
//
//        // Или проверяем текст ошибки
//        // onView(withId(errorMessageId))
//        //     .check(matches(withText("Wrong login or password")));
//    }
}

//-------------------------
//вариант, уже запушен
// package ru.iteco.fmhandroid.ui.pages;
//
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
//import static androidx.test.espresso.action.ViewActions.replaceText;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.withHint;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static androidx.test.espresso.matcher.ViewMatchers.withParent;
//import static org.hamcrest.Matchers.allOf;
//import static ru.iteco.fmhandroid.ui.res.Constants.TEXT_LOGIN;
//import static ru.iteco.fmhandroid.ui.res.Constants.TEXT_PASSWORD;
//
//import androidx.test.espresso.ViewInteraction;
//
//import io.qameta.allure.Allure;
//import ru.iteco.fmhandroid.R;
//import ru.iteco.fmhandroid.ui.help.Utils;
//
//
//public class AuthorizationPage {
//
//    private final int ID_LOGIN_TEXT_INPUT_LAYOUT = R.id.login_text_input_layout;
//    private final int ID_PASSWORD_TEXT_INPUT_LAYOUT = R.id.password_text_input_layout;
//    private final int ID_ENTER_BUTTON = R.id.enter_button;
//
//
//    private final ViewInteraction loginField = onView(allOf(withHint(TEXT_LOGIN),
//            withParent(withParent(withId(ID_LOGIN_TEXT_INPUT_LAYOUT)))));
//
//    private final ViewInteraction passwordField = onView(allOf(withHint(TEXT_PASSWORD),
//            withParent(withParent(withId(ID_PASSWORD_TEXT_INPUT_LAYOUT)))));
//
//    private final ViewInteraction enterButton = onView(withId(ID_ENTER_BUTTON));
//
//
//    public void enterLoginAndPassword(String login, String password) {
//
//        Allure.step("Ввести Логин");
//        loginField.perform(replaceText(login), closeSoftKeyboard());
//
//        Allure.step("Ввести Пароль");
//        passwordField.perform(replaceText(password), closeSoftKeyboard());
//
//        Allure.step("Нажать кнопку Войти");
//        enterButton.check(matches(isDisplayed())).perform(click());
//    }
//
//    public void verifyRegistrationPage() {
//
//        Allure.step("Проверка видимости полей Логин, Пароль и кнопки Войти");
//        Utils.waitFor(ID_ENTER_BUTTON);
//        loginField.check(matches(isDisplayed()));
//        passwordField.check(matches(isDisplayed()));
//        enterButton.check(matches(isDisplayed()));
//    }
//}