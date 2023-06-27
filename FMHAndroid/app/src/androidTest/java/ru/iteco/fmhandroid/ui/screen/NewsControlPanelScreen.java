package ru.iteco.fmhandroid.ui.screen;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.NewsDataHelper;
import ru.iteco.fmhandroid.ui.utils.CustomViewAction;
import ru.iteco.fmhandroid.ui.utils.CustomViewMatcher;

public class NewsControlPanelScreen {

    public ViewInteraction title = onView(withId(R.id.control_panel_title));
    public ViewInteraction addNewsButton = onView(withId(R.id.add_news_image_view));
    public ViewInteraction filterButton = onView(withId(R.id.filter_news_material_button));
    public ViewInteraction newsList = onView(withId(R.id.news_list_recycler_view));
    public ViewInteraction okButton = onView(withText("OK"));

    @Step("Проверка экрана Панель управления новостями")
    public void checkControlPanelScreen() {
        title.check(matches(isDisplayed()));
        title.check(matches(withText("Control panel")));
    }

    @Step("Переход на экран создания новости")
    public void goToCreatingNewsScreen() {
        addNewsButton.perform(click());
    }

    @Step("Переход на экран фильтрации новостей")
    public void goToFilterNewsScreen() {
        filterButton.perform(click());
    }

    @Step("Проверка первой новости после фильтрации")
    public void checkFirstNewsAfterFilter() {
        newsList.check(matches(CustomViewMatcher.atPosition(0, hasDescendant(withText(NewsDataHelper.getFilterInfoWithDatesAndEmptyCategory().getEndDate())))));
        newsList.check(matches(CustomViewMatcher.atPosition(0, hasDescendant(withText("NOT ACTIVE")))));
    }

    @Step("Проверка последней новости после фильтрации")
    public void checkLastNewsAfterFilter() {
        newsList.perform(RecyclerViewActions.actionOnItemAtPosition(5, swipeUp()));
        newsList.check(matches(CustomViewMatcher.atPosition(5, hasDescendant(withText(NewsDataHelper.getFilterInfoWithDatesAndEmptyCategory().getStartDate())))));
        newsList.check(matches(CustomViewMatcher.atPosition(5, hasDescendant(withText("NOT ACTIVE")))));
    }

    @Step("Ожидание загрузки экрана")
    public void waitScreenToLoad() {
        onView(isRoot()).perform(CustomViewAction.waitDisplayed(R.id.control_panel_title, 1000)).check(matches(isDisplayed()));
    }

    @Step("Ожидание загрузки списка новостей")
    public void waitNewsToLoad() {
        onView(isRoot()).perform(CustomViewAction.waitDisplayed(R.id.news_item_title_text_view, 2000)).check(matches(isDisplayed()));
    }

    @Step("Поиск созданной новости")
    public void findCreatedNews(NewsDataHelper.NewsInfo newsInfo) {
        String newsToFind = newsInfo.getTextField();
        newsList.perform(RecyclerViewActions.scrollTo(hasDescendant(withText(newsToFind)))).check(matches(isDisplayed()));
    }

    @Step("Переход на экран редактирования новости")
    public void goToEditNews(NewsDataHelper.NewsInfo newsInfo) {
        onView(allOf(withId(R.id.edit_news_item_image_view),
                withParent(withParent(hasDescendant(withText(newsInfo.getTextField())))))).perform(click());
    }

    @Step("Удаление новости")
    public void deleteNews(NewsDataHelper.NewsInfo newsInfo) {
        onView(allOf(withId(R.id.delete_news_item_image_view),
                withParent(withParent(hasDescendant(withText(newsInfo.getTextField())))))).perform(click());
        okButton.perform(click());
    }

    @Step("Проверка, что новость не существует")
    public void checkNewsDoesNotExist(NewsDataHelper.NewsInfo newsInfo) {
        try {
            String newsToFind = newsInfo.getTextField();
            newsList.perform(RecyclerViewActions.scrollTo(hasDescendant(withText(newsToFind)))).check(matches(isDisplayed()));
        } catch (PerformException e) {
            return;
        }
    }
}
