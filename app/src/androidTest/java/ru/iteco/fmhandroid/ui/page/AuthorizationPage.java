package ru.iteco.fmhandroid.ui.page;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.res.Constants.TEXT_LOGIN;
import static ru.iteco.fmhandroid.ui.res.Constants.TEXT_PASSWORD;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.help.Utils;


public class AuthorizationPage {

    private final int ID_LOGIN_TEXT_INPUT_LAYOUT = R.id.login_text_input_layout;
    private final int ID_PASSWORD_TEXT_INPUT_LAYOUT = R.id.password_text_input_layout;
    private final int ID_ENTER_BUTTON = R.id.enter_button;


    private final ViewInteraction loginField = onView(allOf(withHint(TEXT_LOGIN),
            withParent(withParent(withId(ID_LOGIN_TEXT_INPUT_LAYOUT)))));

    private final ViewInteraction passwordField = onView(allOf(withHint(TEXT_PASSWORD),
            withParent(withParent(withId(ID_PASSWORD_TEXT_INPUT_LAYOUT)))));

    private final ViewInteraction enterButton = onView(withId(ID_ENTER_BUTTON));


    public void enterLoginAndPassword(String login, String password) {

        Allure.step("Ввести Логин");
        loginField.perform(replaceText(login), closeSoftKeyboard());

        Allure.step("Ввести Пароль");
        passwordField.perform(replaceText(password), closeSoftKeyboard());

        Allure.step("Нажать кнопку Войти");
        enterButton.check(matches(isDisplayed())).perform(click());
    }

    public void verifyRegistrationPage() {

        Allure.step("Проверка видимости полей Логин, Пароль и кнопки Войти");
        Utils.waitFor(ID_ENTER_BUTTON);
        loginField.check(matches(isDisplayed()));
        passwordField.check(matches(isDisplayed()));
        enterButton.check(matches(isDisplayed()));
    }
}