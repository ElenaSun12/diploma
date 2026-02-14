package ru.iteco.fmhandroid.ui.utils;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.Espresso.onView;

import android.view.View;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;

import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.UiController;
//import static androidx.test.espresso.ViewAction;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import org.hamcrest.Matcher;

import org.hamcrest.Matcher;

import java.util.concurrent.TimeoutException;

public class ViewHelper {

    // Ожидание появления элемента с определенным ID
    public static ViewAction waitForId(final int viewId, final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with id <" + viewId + "> during " + millis + " millis.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;
                final Matcher<View> viewMatcher = withId(viewId);

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        if (viewMatcher.matches(child)) {
                            return;
                        }
                    }
                    uiController.loopMainThreadForAtLeast(50);
                } while (System.currentTimeMillis() < endTime);

                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }

//    // Ожидание появления любого элемента из списка
//    public static ViewAction waitForAnyElement(final int[] viewIds, final long millis) {
//        return new ViewAction() {
//            @Override
//            public Matcher<View> getConstraints() {
//                return isRoot();
//            }
//
//            @Override
//            public String getDescription() {
//                return "wait for any element during " + millis + " millis.";
//            }
//
//            @Override
//            public void perform(UiController uiController, View view) {
//                uiController.loopMainThreadUntilIdle();
//                final long startTime = System.currentTimeMillis();
//                final long endTime = startTime + millis;
//
//                do {
//                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
//                        for (int viewId : viewIds) {
//                            if (withId(viewId).matches(child)) {
//                                return;
//                            }
//                        }
//                    }
//                    uiController.loopMainThreadForAtLeast(50);
//                } while (System.currentTimeMillis() < endTime);
//
//                throw new PerformException.Builder()
//                        .withActionDescription(this.getDescription())
//                        .withViewDescription(HumanReadables.describe(view))
//                        .withCause(new TimeoutException())
//                        .build();
//            }
//        };
//    }
    /**
     * Ожидает появления View с указанным ID в течение заданного времени
     */
    public static ViewAction waitForElement(int viewId, long timeout) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with id <" + viewId + "> during " + timeout + " millis.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + timeout;

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        if (child.getId() == viewId && child.isShown()) {
                            return;
                        }
                    }

                    uiController.loopMainThreadForAtLeast(50);
                } while (System.currentTimeMillis() < endTime);

                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }

    /**
     * Ожидает появления любого элемента из массива ID
     */
    public static ViewAction waitForAnyElement(int[] viewIds, long timeout) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for any element during " + timeout + " millis.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + timeout;

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        for (int id : viewIds) {
                            if (child.getId() == id && child.isShown()) {
                                return;
                            }
                        }
                    }

                    uiController.loopMainThreadForAtLeast(50);
                } while (System.currentTimeMillis() < endTime);

                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }
    //первоначальный метод public static ViewAction waitForElementGone/закомментила 14.02 и прописала тот, что написал Александр
//    public static ViewAction waitForElementGone(final int viewId, final long timeout) {
//        return new ViewAction() {
//            @Override
//            public Matcher<android.view.View> getConstraints() {
//                return isRoot();
//            }
//
//            @Override
//            public String getDescription() {
//                return "wait for element with id <" + viewId + "> to disappear during " + timeout + " ms";
//            }
//
//            @Override
//            public void perform(UiController uiController, android.view.View view) {
//                uiController.loopMainThreadUntilIdle();
//                final long startTime = System.currentTimeMillis();
//                final long endTime = startTime + timeout;
//
//                do {
//                    try {
//                        // Пытаемся найти элемент и проверить, отображен ли он
//                        onView(withId(viewId)).check(new ViewAssertion() {
//                            @Override
//                            public void check(android.view.View view, NoMatchingViewException noViewFoundException) {
//                                if (noViewFoundException != null) {
//                                    // Элемент не найден, значит, уже исчез - выходим из цикла
//                                    return;
//                                }
//                                if (view != null && view.isShown()) {
//                                    // Элемент виден, бросаем исключение, чтобы продолжить ожидание
//                                    throw new RuntimeException("Element still visible");
//                                }
//                            }
//                        });
//                        // Если элемент все еще виден, ждем
//                        uiController.loopMainThreadForAtLeast(100);
//                    } catch (RuntimeException e) {
//                        if (e.getMessage() != null && e.getMessage().equals("Element still visible")) {
//                            // Элемент все еще виден, продолжаем цикл
//                        } else {
//                            // Любое другое исключение (например, NoMatchingViewException) означает, что элемент исчез
//                            return;
//                        }
//                    }
//                } while (System.currentTimeMillis() < endTime);
//
//                // Если цикл завершился, а элемент так и не исчез
//                throw new RuntimeException("Element with id " + viewId + " did not disappear within " + timeout + " ms");
//            }
//        };
//    }

    public static ViewAction waitForElementGone(final int viewId, final long timeout) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();

            }

            @Override
            public String getDescription() {
                return "wait for element with id <" + viewId + "> to disappear during " + timeout + " ms";
            }

            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + timeout;

                do {
                    try {
                        onView(withId(viewId)).check(matches(isDisplayed()));
                        // Элемент всё ещё виден - ждём ещё
                        uiController.loopMainThreadForAtLeast(100);
                    } catch (Exception | AssertionError e) {
                        // Элемент не найден или не виден - ура, успех!
                        return;
                    }
                } while (System.currentTimeMillis() < endTime);

                throw new RuntimeException("Element still displayed after " + timeout + " ms");
            }
        };
    }
}