//package ru.testsAppHospis;
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.action.ViewActions.replaceText;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;
//
//import androidx.test.espresso.ViewInteraction;
//
//import ru.iteco.fmhandroid.R;
//
//public class AuthPage {
//
//    public void checkAuthScreenIsDisplayed() {
//        onView(withText("Авторизация")).check(matches(isDisplayed()));
//    }
//
//    public void enterLogin(String login) {
//        onView(withId(R.id.login)).perform(replaceText(login));
//    }
//
//    public void enterPassword(String password) {
//        onView(withId(R.id.password)).perform(replaceText(password));
//    }
//
//    public void clickLoginButton() {
//        onView(withId(R.id.enter_button)).perform(click());
//    }
//}