package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.registerIdlingResources;
import static androidx.test.espresso.Espresso.unregisterIdlingResources;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;

import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginEspressoTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private static class ViewAppearedIdlingResource implements IdlingResource {
        private final int viewId;
        private final ActivityScenarioRule<AppActivity> activityScenarioRule;
        private ResourceCallback resourceCallback;
        private boolean isIdle = false;

        public ViewAppearedIdlingResource(
                int viewId,
                ActivityScenarioRule<AppActivity> activityScenarioRule
        ) {
            this.viewId = viewId;
            this.activityScenarioRule = activityScenarioRule;
        }

        @Override
        public String getName() {
            return "ViewAppeared:" + viewId;
        }

        @Override
        public boolean isIdleNow() {
            if (isIdle) return true;

            boolean[] isViewVisible = {false};

            // ✅ Проверяем видимость НАПРЯМУЮ через Activity
            activityScenarioRule.getScenario().onActivity(activity -> {
                View view = activity.findViewById(viewId);
                isViewVisible[0] = view != null && view.isShown();
            });

            if (isViewVisible[0]) {
                isIdle = true;
                if (resourceCallback != null) {
                    resourceCallback.onTransitionToIdle();
                }
            }
            return isIdle;
        }

        @Override
        public void registerIdleTransitionCallback(ResourceCallback callback) {
            this.resourceCallback = callback;
        }
    }
//    // === ПРОСТОЙ IdlingResource для ожидания ПОЯВЛЕНИЯ элемента ===
//    private static class ViewAppearedIdlingResource implements IdlingResource {
//        private final int viewId;
//        private ResourceCallback resourceCallback;
//        private boolean isIdle = false;
//
//        public ViewAppearedIdlingResource(int viewId) {
//            this.viewId = viewId;
//        }
//
//        @Override
//        public String getName() {
//            return "ViewAppeared:" + viewId;
//        }
//
//        @Override
//        public boolean isIdleNow() {
//            if (isIdle) return true;
//
//            try {
//                // Ждём, пока элемент станет видимым
//                onView(withId(viewId)).check(matches(isDisplayed()));
//                isIdle = true;
//                if (resourceCallback != null) {
//                    resourceCallback.onTransitionToIdle();
//                }
//                return true;
//            } catch (Exception e) {
//                return false; // Продолжаем ждать
//            }
//        }
//
//        @Override
//        public void registerIdleTransitionCallback(ResourceCallback callback) {
//            this.resourceCallback = callback;
//        }
//    }

    // === ПРОСТОЙ IdlingResource для ожидания ИСЧЕЗНОВЕНИЯ элемента ===
    private static class ViewGoneIdlingResource implements IdlingResource {
        private final int viewId;
        private ResourceCallback resourceCallback;
        private boolean isIdle = false;

        public ViewGoneIdlingResource(int viewId) {
            this.viewId = viewId;
        }

        @Override
        public String getName() {
            return "ViewGone:" + viewId;
        }

        @Override
        public boolean isIdleNow() {
            if (isIdle) return true;

            try {
                // Пытаемся найти элемент — если НЕ найден, значит он исчез
                onView(withId(viewId)).check(matches(isDisplayed()));
                return false; // Элемент ещё виден — продолжаем ждать
            } catch (Exception e) {
                // Элемент не виден — считаем, что он исчез
                isIdle = true;
                if (resourceCallback != null) {
                    resourceCallback.onTransitionToIdle();
                }
                return true;
            }
        }

        @Override
        public void registerIdleTransitionCallback(ResourceCallback callback) {
            this.resourceCallback = callback;
        }
    }

    private IdlingResource loginScreenIdlingResource;

    @Before
    public void setUp() {
        // Передаем mActivityScenarioRule в конструктор
        loginScreenIdlingResource = new ViewAppearedIdlingResource(
                R.id.enter_button,
                mActivityScenarioRule // <-- КЛЮЧЕВОЕ ИЗМЕНЕНИЕ
        );
        registerIdlingResources(loginScreenIdlingResource);
    }

//    @Before
//    public void setUp() {
//        // === ЭТАП 1 → 2: Ждём ПОЯВЛЕНИЯ КНОПКИ SIGN IN (R.id.enter_button) ===
//        // Это надёжная точка — кнопка появляется ПОСЛЕДНЕЙ, когда экран входа полностью готов
//        loginScreenIdlingResource = new ViewAppearedIdlingResource(R.id.enter_button);
//        registerIdlingResources(loginScreenIdlingResource);
//    }

    @After
    public void tearDown() {
        if (loginScreenIdlingResource != null) {
            unregisterIdlingResources(loginScreenIdlingResource);
        }
    }

    @Test
    public void loginEspressoTest() {
        // === ЭТАП 2: Экран входа готов (кнопка SIGN IN видна) ===
        // Теперь проверяем и заполняем поля логина/пароля
        onView(
                allOf(
                        withClassName(is("android.widget.EditText")),
                        withParent(withId(R.id.login_text_input_layout))
                )
        ).check(matches(isDisplayed()))
                .perform(replaceText("login2"), closeSoftKeyboard());

        onView(
                allOf(
                        withClassName(is("android.widget.EditText")),
                        withParent(withId(R.id.password_text_input_layout))
                )
        ).check(matches(isDisplayed()))
                .perform(replaceText("password2"), closeSoftKeyboard());

        // Нажимаем кнопку входа
        onView(withId(R.id.enter_button)).perform(click());

        // === ЭТАП 2 → 3: Ждём ИСЧЕЗНОВЕНИЯ поля логина ===
        // Это критически важно — гарантирует, что экран входа закрыт
        IdlingResource goneResource = new ViewGoneIdlingResource(R.id.login_text_input_layout);
        try {
            registerIdlingResources(goneResource);

            // === ЭТАП 3: Проверяем появление кнопки-человечка (главный экран) ===
            onView(withId(R.id.authorization_image_button))
                    .check(matches(isDisplayed()));
        } finally {
            unregisterIdlingResources(goneResource);
        }
    }
}


//закоммментила 23.02, верх. Вариант с простым исправлениями (рефакторинг еспрессо вараинта). Поиск по логину
// package ru.iteco.fmhandroid.ui;
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.Espresso.registerIdlingResources;
//import static androidx.test.espresso.Espresso.unregisterIdlingResources;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
//import static androidx.test.espresso.action.ViewActions.replaceText;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
//import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static androidx.test.espresso.matcher.ViewMatchers.withParent;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;
//import static org.hamcrest.Matchers.allOf;
//import static org.hamcrest.Matchers.is;
//
//import android.app.Activity;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewParent;
//
//import androidx.test.espresso.IdlingResource;
//import androidx.test.espresso.UiController;
//import androidx.test.espresso.ViewAction;
//import androidx.test.espresso.ViewInteraction;
//import androidx.test.espresso.assertion.ViewAssertions;
//import androidx.test.espresso.intent.rule.IntentsTestRule;
//import androidx.test.ext.junit.rules.ActivityScenarioRule;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//import androidx.test.filters.LargeTest;
//
//import org.hamcrest.Description;
//import org.hamcrest.Matcher;
//import org.hamcrest.TypeSafeMatcher;
//import org.hamcrest.core.IsInstanceOf;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import ru.iteco.fmhandroid.R;
//
//@LargeTest
//@RunWith(AndroidJUnit4.class)
//public class LoginEspressoTest {
//
//    //    @Rule
////    public IntentsTestRule<AppActivity> mActivityScenarioRule =
////            new IntentsTestRule<>(AppActivity.class);
//    @Rule
//    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
//            new ActivityScenarioRule<>(AppActivity.class);
//
//    // Кастомный IdlingResource для ожидания ВИДИМОСТИ элемента (исправлено!)
//    // Кастомный IdlingResource для ожидания ВИДИМОСТИ элемента (исправлено!)
//    private static class ViewVisibilityIdlingResource implements IdlingResource {
//        private final int viewId;
//        private ResourceCallback resourceCallback;
//        private boolean isIdle = false;
//
//        public ViewVisibilityIdlingResource(int viewId) {
//            this.viewId = viewId;
//        }
//
//        @Override
//        public String getName() {
//            return ViewVisibilityIdlingResource.class.getName() + ":" + viewId;
//        }
//
//        @Override
//        public boolean isIdleNow() {
//            if (isIdle) {
//                System.out.println("ViewVisibilityIdlingResource: ALREADY IDLE for view ID " + viewId);
//                return true;
//            }
//
//            try {
//                // Проверяем ВИДИМОСТЬ элемента (ключевое исправление!)
//                onView(withId(viewId)).check(matches(isDisplayed()));
//
//                // Элемент стал видимым — переходим в состояние idle
//                isIdle = true;
//                System.out.println("ViewVisibilityIdlingResource: ELEMENT VISIBLE for view ID " + viewId);
//
//                if (resourceCallback != null) {
//                    resourceCallback.onTransitionToIdle();
//                }
//                return true;
//            } catch (Exception e) {
//                // Элемент ещё не видим — продолжаем ждать
//                System.out.println("ViewVisibilityIdlingResource: ELEMENT NOT VISIBLE yet for view ID " + viewId +
//                        " (" + e.getClass().getSimpleName() + ": " + e.getMessage() + ")");
//                return false;
//            }
//        }
//
//        @Override
//        public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
//            this.resourceCallback = resourceCallback;
//        }
//    }
//
//    // Вспомогательный метод для простого ожидания (оставлен для сравнения/резерва)
////    public static ViewAction waitFor(final long millis) {
////        return new ViewAction() {
////            @Override
////            public Matcher<View> getConstraints() {
////                return isRoot();
////            }
////
////            @Override
////            public String getDescription() {
////                return "Wait for " + millis + " milliseconds";
////            }
////
////            @Override
////            public void perform(UiController uiController, View view) {
////                uiController.loopMainThreadForAtLeast(millis);
////            }
////        };
////    }
//
//    private IdlingResource loginScreenIdlingResource;
//
//    @Before
//    public void setUp() {
//        // Регистрируем IdlingResource ДО выполнения теста
//        loginScreenIdlingResource = new ViewVisibilityIdlingResource(R.id.login_text_input_layout);
//        registerIdlingResources(loginScreenIdlingResource);
//    }
//
//    @After
//    public void tearDown() {
//        // Всегда отменяем регистрацию во избежание утечек
//        if (loginScreenIdlingResource != null) {
//            unregisterIdlingResources(loginScreenIdlingResource);
//        }
//    }
//
//    @Test
//    public void loginEspressoTest() {
//        // Espresso автоматически ждёт, пока loginScreenIdlingResource станет idle
//        // То есть пока появится и отобразится элемент R.id.login_text_input_layout
//
//        // Ищем EditText ВНУТРИ TextInputLayout через иерархию
//        onView(
//                allOf(
//                        withClassName(is("android.widget.EditText")),
//                        withParent(withId(R.id.login_text_input_layout))
//                )
//        ).check(matches(isDisplayed()))
//                .perform(replaceText("login2"), closeSoftKeyboard());
//
////                allOf(childAtPosition(
////                                childAtPosition(
////                                        withId(R.id.login_text_input_layout),
////                                        0),
////                                0),
////                        isDisplayed()));
////        textInputEditText.perform(replaceText("login2"), closeSoftKeyboard());
//
//        // То же для пароля
//        onView(
//                allOf(
//                        withClassName(is("android.widget.EditText")),
//                        withParent(withId(R.id.password_text_input_layout))
//                )
//        ).check(matches(isDisplayed()))
//                .perform(replaceText("password2"), closeSoftKeyboard());
////                allOf(childAtPosition(
////                                childAtPosition(
////                                        withId(R.id.password_text_input_layout),
////                                        0),
////                                0),
////                        isDisplayed()));
////        textInputEditText2.perform(replaceText("password2"), closeSoftKeyboard());
//
//        ViewInteraction button = onView(withText("SIGN IN"));
//        button.check(matches(isDisplayed()));
//
////                allOf(withId(R.id.enter_button), withText("SIGN IN"), withContentDescription("Save"),
////                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class))),
////                        isDisplayed()));
////        button.check(matches(isDisplayed()));
//
//        ViewInteraction materialButton = onView(withId(R.id.enter_button));
//        materialButton.check(matches(isDisplayed()));
//        materialButton.perform(click());
//
////                allOf(withId(R.id.enter_button), withText("Sign in"), withContentDescription("Save"),
////                        childAtPosition(
////                                childAtPosition(
////                                        withClassName(is("android.widget.RelativeLayout")),
////                                        1),
////                                2),
////                        isDisplayed();
////        materialButton.perform(click());
//
//        // Дополнительный IdlingResource для ожидания главного экрана после входа
//        IdlingResource mainScreenIdlingResource = new ViewVisibilityIdlingResource(R.id.authorization_image_button);
//        try {
//            registerIdlingResources(mainScreenIdlingResource);
//
//            ViewInteraction imageButton = onView(withId(R.id.authorization_image_button));
//            imageButton.check(matches(isDisplayed()));
//        } finally {
//            unregisterIdlingResources(mainScreenIdlingResource);
//        }
//
////                allOf(withId(R.id.authorization_image_button), withContentDescription("Authorization"),
////                        withParent(allOf(withId(R.id.container_custom_app_bar_include_on_fragment_main),
////                                withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
////                        isDisplayed()));
////        imageButton.check(matches(isDisplayed()));
//    }
//
////    private static Matcher<View> childAtPosition(
////            final Matcher<View> parentMatcher, final int position) {
////
////        return new TypeSafeMatcher<View>() {
////            @Override
////            public void describeTo(Description description) {
////                description.appendText("Child at position " + position + " in parent ");
////                parentCallback.describeTo(description);
////            }
////
////            @Override
////            public boolean matchesSafely(View view) {
////                ViewParent parent = view.getParent();
////                return parent instanceof ViewGroup && parentMatcher.matches(parent)
////                        && view.equals(((ViewGroup) parent).getChildAt(position));
////            }
////        };
////    }
//}