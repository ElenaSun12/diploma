package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.TestData;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class PositiveAuthorizationTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private AuthorizationSteps authSteps;

    @Before
    public void setUp() {
        authSteps = new AuthorizationSteps();

        // Ждем запуска приложения
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void successfulLoginWithValidCredentials() {
        // Сначала убедимся, что мы разлогинены
        try {
            // Попробуем разлогиниться (если залогинены)
            onView(withId(R.id.authorization_image_button)).perform(click());
            onView(withText("Выйти")).perform(click());
            Thread.sleep(2000); // Ждем возврата на экран логина
        } catch (Exception e) {
            // Игнорируем - возможно, уже разлогинены
        }

        // Теперь выполняем вход
        authSteps.login(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);

        // Проверяем успешность
        authSteps.verifyLoginSuccessful();
    }
//падает, хотя авторизуется и кнопки видно. Ищет поле логина после этого?
//    @Test
//    public void successfulLoginWithValidCredentials() {
//        // Выполняем вход
//        authSteps.login(TestData
//                .VALID_LOGIN, TestData
//                .VALID_PASSWORD);
//
//        // Проверяем успешность
//        authSteps.verifyLoginSuccessful();
//    }
}


//    @Test
//    public void loginWithInvalidPasswordShouldFail() {
//        // Пытаемся войти с неверным паролем
//        authSteps.login(TestData.VALID_LOGIN, "wrong_password");
//
//        // Проверяем, что вход не удался
//        authSteps.verifyLoginFailed();
//    }
//}

//--------------------------
// Первый рабочий! простой тест
//import androidx.test.ext.junit.rules.ActivityScenarioRule;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//import androidx.test.filters.LargeTest;
//
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import ru.iteco.fmhandroid.R;
//import ru.iteco.fmhandroid.ui.AppActivity;
//import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
//import ru.iteco.fmhandroid.ui.utils.ViewHelper;
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
//
//@LargeTest
//@RunWith(AndroidJUnit4.class)
//public class PositiveAuthorizationTest {
//
//    @Rule
//    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
//            new ActivityScenarioRule<>(AppActivity.class);
//
//    private AuthorizationPage authPage;
//
//    @Before
//    public void setUp() {
//        authPage = new AuthorizationPage();
//    }
//
//    @Test
//    public void testWaitForLoginField() {
//        // Шаг 1: Ждем загрузку экрана авторизации
//        onView(isRoot()).perform(ViewHelper.waitForId(R.id.login_text_input_layout, 10000));
//
//        // Шаг 2: Проверяем, что поле логина видно
//        authPage.enterLogin("test");
//    }
//
//    @Test
//    public void testCompleteAuthorization() {
//        // 1. Ждем экран авторизации
//        authPage.waitForAuthorizationScreen();
//
//        // 2. Проверяем фрагмент авторизации
//        authPage.checkAuthorizationFragmentIsVisible();
//
//        // 3. Вводим логин
//        authPage.enterLogin("login2");
//
//        // 4. Вводим пароль
//        authPage.enterPassword("password2");
//
//        // 5. Нажимаем кнопку
//        authPage.clickSignInButton();
//
//        // Пауза для наблюдения
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}

// Проверка 1: Поле логина заполнено

//    @Test
//    public void loginWithEmptyFieldsShouldShowError() {
//        // Шаг 1: Оставляем пустые поля
//        // Шаг 2: Нажимаем кнопку Войти
//        authPage.clickSignInButton();
//
//        // Проверка: Появилось сообщение об ошибке
//        authPage.checkErrorMessageIsDisplayed("Логин и пароль не могут быть пустыми");
//    }
//}
//---------------------------
//вариант, уже запушен
//import static ru.iteco.fmhandroid.ui.res.Constants.DEFAULT_LOGIN;
//import static ru.iteco.fmhandroid.ui.res.Constants.DEFAULT_PASSWORD;
//import static ru.iteco.fmhandroid.ui.res.Constants.TEXT_AUTHORIZATION;
//
//import androidx.test.ext.junit.rules.ActivityScenarioRule;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//import androidx.test.filters.LargeTest;
//
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import io.qameta.allure.Description;
//import ru.iteco.fmhandroid.ui.AppActivity;
//import ru.iteco.fmhandroid.ui.help.Utils;
//import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
//import ru.iteco.fmhandroid.ui.pages.MainPage;

//@LargeTest
//@RunWith(AndroidJUnit4.class)
//public class PositiveAuthorizationTest {
//
//    AuthorizationPage authorizationPage = new AuthorizationPage();
//    MainPage mainPage = new MainPage();
//    Utils utils = new Utils();
//
//    @Rule
//    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
//            new ActivityScenarioRule<>(AppActivity.class);
//
////    @Rule
// //   public AllureJunit4 allure = new AllureJunit4();
//
//    @Before
//    @Description("Выполняется перед каждым тестом")
//    public void setUp() {
//        try {
//            authorizationPage.verifyRegistrationPage();
//        } catch (Exception e) {
//            utils.logOut();
//        }
//    }
//
//    @Test
//    @Description("Проверка успешной авторизации пользователя")
//    public void successfulAuthorizationTest() {
//        authorizationPage.enterLoginAndPassword(DEFAULT_LOGIN, DEFAULT_PASSWORD);
//        mainPage.authorizationImageButtonVisible();
//    }
//
//    @Test
//    @Description("Проверка успешной авторизации и выхода из учётной записи")
//    public void successfulLoginAndLogOutTest() {
//        authorizationPage.enterLoginAndPassword(DEFAULT_LOGIN, DEFAULT_PASSWORD);
//        mainPage.clickAuthorizationImageButton();
//        mainPage.clickLogOutButton();
//        utils.checkText(TEXT_AUTHORIZATION);
//    }
//}