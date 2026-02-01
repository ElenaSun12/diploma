package ru.iteco.fmhandroid.ui.test;

import static ru.iteco.fmhandroid.ui.res.Constants.DEFAULT_LOGIN;
import static ru.iteco.fmhandroid.ui.res.Constants.DEFAULT_PASSWORD;
import static ru.iteco.fmhandroid.ui.res.Constants.TEXT_AUTHORIZATION;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.AllureJunit4;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.help.Utils;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MainPage;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class PositiveAuthorizationTest {

    AuthorizationPage authorizationPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();
    Utils utils = new Utils();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

//    @Rule
 //   public AllureJunit4 allure = new AllureJunit4();

    @Before
    @Description("Выполняется перед каждым тестом")
    public void setUp() {
        try {
            authorizationPage.verifyRegistrationPage();
        } catch (Exception e) {
            utils.logOut();
        }
    }

    @Test
    @Description("Проверка успешной авторизации пользователя")
    public void successfulAuthorizationTest() {
        authorizationPage.enterLoginAndPassword(DEFAULT_LOGIN, DEFAULT_PASSWORD);
        mainPage.authorizationImageButtonVisible();
    }

    @Test
    @Description("Проверка успешной авторизации и выхода из учётной записи")
    public void successfulLoginAndLogOutTest() {
        authorizationPage.enterLoginAndPassword(DEFAULT_LOGIN, DEFAULT_PASSWORD);
        mainPage.clickAuthorizationImageButton();
        mainPage.clickLogOutButton();
        utils.checkText(TEXT_AUTHORIZATION);
    }
}