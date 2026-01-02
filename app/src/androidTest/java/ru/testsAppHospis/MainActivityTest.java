//package ru.testsAppHospis;
//
//import androidx.test.espresso.Espresso;
//import androidx.test.espresso.action.ViewActions;
//import androidx.test.espresso.assertion.ViewAssertions;
//import androidx.test.espresso.matcher.ViewMatchers;
//import androidx.test.ext.junit.rules.ActivityScenarioRule;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//// Укажите здесь полный путь к вашей MainActivity
//import com.yourapp.MainActivity;  // ← ЗАМЕНИТЕ на ваш реальный путь!
//
//@RunWith(AndroidJUnit4.class)  // ← ОБЯЗАТЕЛЬНАЯ аннотация!
//public class MainActivityTest {
//
//    @Rule
//    public ActivityScenarioRule<MainActivity> activityRule =
//            new ActivityScenarioRule<>(MainActivity.class);
//
//    @Test  // ← ОБЯЗАТЕЛЬНО для каждого тестового метода!
//    public void testAppLaunch() {
//        Espresso.onView(ViewMatchers.withText("Hello World!"))
//                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
//    }
//}
//
//public class MainActivityTest {
//}