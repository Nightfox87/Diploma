package ru.iteco.fmhandroid.ui.screen;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.NewsDataHelper;
import ru.iteco.fmhandroid.ui.utils.CustomViewAction;
import ru.iteco.fmhandroid.ui.utils.CustomViewMatcher;

public class NewsScreen {
    public ViewInteraction newsTitle = onView(withId(R.id.news_screen_title));
    public ViewInteraction editNewsButton = onView(withId(R.id.edit_news_material_button));
    public ViewInteraction sortButton = onView(withId(R.id.sort_news_material_button));
    public ViewInteraction newsList = onView(withId(R.id.news_list_recycler_view));

    @Step("Проверка экрана Новости")
    public void checkNewsScreen() {
        newsTitle.check(matches(isDisplayed()));
        newsTitle.check(matches(withText("News")));
    }
    @Step("Ожидание загрузки экрана")
    public void waitToLoadScreen(){
        onView(isRoot()).perform(CustomViewAction.waitDisplayed(R.id.news_screen_title, 1000));
    }

    @Step("Переход на экран Панель управления новостями")
    public void goToNewsControlPanel() {
        editNewsButton.perform(click());
    }

    @Step("Сортировка новостей")
    public void sortNews() {
        sortButton.perform(click());
    }

    @Step("Проверка новостей перед сортировкой")
    public void checkNewsBeforeSort(NewsDataHelper.NewsInfo info) {
        String firstNews = info.getTextField();
        String lastNewsDescription = "Тестовый заголовок 01.01.2012В06:44";
        int itemCount = CustomViewMatcher.getCountFromRecyclerView(R.id.news_list_recycler_view);
        newsList.perform(RecyclerViewActions.scrollTo(hasDescendant(withText(firstNews)))).check(matches(isDisplayed()));
        newsList.perform(RecyclerViewActions.actionOnItemAtPosition(itemCount - 1, click()));
        newsList.check(matches(CustomViewMatcher.atPosition(itemCount - 1, hasDescendant(withText(lastNewsDescription)))));
    }

    @Step("Проверка новостей после сортировки")
    public void checkNewsAfterSort(NewsDataHelper.NewsInfo info) {
        String firstNewsDescription = "Тестовый заголовок 01.01.2012В06:44";
        String lastNews = info.getTextField();
        int itemCount = CustomViewMatcher.getCountFromRecyclerView(R.id.news_list_recycler_view);
        newsList.perform(RecyclerViewActions.scrollTo(hasDescendant(withText(firstNewsDescription)))).check(matches(isDisplayed()));
        newsList.perform(RecyclerViewActions.actionOnItemAtPosition(itemCount - 1, click()));
        newsList.check(matches(CustomViewMatcher.atPosition(itemCount - 1, hasDescendant(withText(lastNews)))));
    }

}
