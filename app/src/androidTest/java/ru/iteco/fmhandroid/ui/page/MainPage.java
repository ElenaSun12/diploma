package ru.iteco.fmhandroid.ui.page;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static ru.iteco.fmhandroid.ui.res.Constants.ALL_NEWS;
import static ru.iteco.fmhandroid.ui.res.Constants.EXIT;
import static ru.iteco.fmhandroid.ui.res.Constants.OUR_MISSION;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.help.Utils;


public class MainPage {

    private final int ID_AUTHORIZATION_IMAGE_BUTTON = R.id.authorization_image_button;
    private final int ID_ANDROID_TITLE = android.R.id.title;
    private final int ID_EXPAND_MATERIAL_BUTTON = R.id.expand_material_button;
    private final int ID_ALL_NEWS_TEXT_VIEW = R.id.all_news_text_view;
    private final int ID_NEWS_LIST_RECYCLER_VIEW = R.id.news_list_recycler_view;
    private final int ID_NEWS_ITEM_DESCRIPTION_TEXT_VIEW = R.id.news_item_description_text_view;
    private final int ID_NEWS_ITEM_MATERIAL_CARD_VIEW = R.id.news_item_material_card_view;
    private final int ID_ABOUT_BACK_IMAGE_BUTTON = R.id.about_back_image_button;

    private final ViewInteraction backAboutPage = onView(withId(ID_ABOUT_BACK_IMAGE_BUTTON));
    private final ViewInteraction authorizationImageButton = onView(withId(ID_AUTHORIZATION_IMAGE_BUTTON));
    private final ViewInteraction logOutButton = onView(allOf(withId(ID_ANDROID_TITLE), withText(EXIT)));
    private final ViewInteraction materialButton = onView(withId(ID_EXPAND_MATERIAL_BUTTON));
    private final ViewInteraction textAllNews = onView(withText(ALL_NEWS));
    private final ViewInteraction allNews = onView(withId(ID_ALL_NEWS_TEXT_VIEW));
    private final ViewInteraction buttonMission = onView(withContentDescription(OUR_MISSION));
    private final ViewInteraction buttonToExpandNewsCard = onView(withId(ID_NEWS_LIST_RECYCLER_VIEW));
    private final ViewInteraction itemDescriptionText = onView(
            allOf(withId(ID_NEWS_ITEM_DESCRIPTION_TEXT_VIEW),
                    withParent(withParent(withId(ID_NEWS_ITEM_MATERIAL_CARD_VIEW))),
                    isDisplayed()));


    public void clickBackAboutPage() {
        Allure.step("Нажать кнопку назад на странице о приложении");
        backAboutPage.check(matches(isDisplayed())).perform(click());
    }


    public void authorizationImageButtonVisible() {
        Allure.step("Кнопка авторизированого пользователя отображается");
        Utils.waitFor(ID_AUTHORIZATION_IMAGE_BUTTON);
        authorizationImageButton.check(matches(isDisplayed()));
    }

    public void clickAuthorizationImageButton() {
        Allure.step("Нажать профиль пользователя");
        Utils.waitFor(ID_AUTHORIZATION_IMAGE_BUTTON);
        authorizationImageButton.check(matches(isDisplayed())).perform(click());
    }


    public void clickLogOutButton() {
        Allure.step("Нажать кнопку выйти");
        Utils.waitFor(ID_ANDROID_TITLE);
        logOutButton.perform(click());
    }

    public void clickMaterialButton() {
        Allure.step("Нажать кнопку Развернуть/свернуть панель новостей");
        materialButton.check(matches(isDisplayed())).perform(click());
    }

    public void checkTextAllNewsVisible() {
        Allure.step("Проверить что текст ВСЕ НОВОСТИ отображается");
        textAllNews.check(matches(isDisplayed()));
    }

    public void checkTextAllNewsNotVisible() {
        Allure.step("Проверить что текст ВСЕ НОВОСТИ Не отображается");
        textAllNews.check(matches(not(isDisplayed())));
    }

    public void clickAllNews() {
        Allure.step("Нажать на ВСЕ НОВОСТИ");
        allNews.check(matches(isDisplayed())).perform(click());
    }

    public void clickButtonMission() {
        Allure.step("Нажать кнопку тематические цитаты");
        buttonMission.check(matches(isDisplayed())).perform(click());
    }


    public void clickButtonToExpandNewsCard() {
        Allure.step("Нажать раскрыть карточку новость");
        buttonToExpandNewsCard
                .check(matches(isDisplayed()))
                .check(matches(hasMinimumChildCount(1))) // проверка на наличие хотя бы одного элемента в списке
                .perform(actionOnItemAtPosition(0, click()));
    }

    public void checkItemDescriptionText() {
        Allure.step("Проверить тест описания");
        itemDescriptionText.check(matches(isDisplayed()));
    }

}