package ru.iteco.fmhandroid.ui.screen;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.NewsDataHelper;
import ru.iteco.fmhandroid.ui.utils.CustomViewMatcher;

public class NewsScreen {
    public ViewInteraction newsTitle = onView(withId(R.id.news_screen_title));
    public ViewInteraction editNewsButton = onView(withId(R.id.edit_news_material_button));
    public ViewInteraction sortButton = onView(withId(R.id.sort_news_material_button));
    public ViewInteraction newsList = onView(withId(R.id.news_list_recycler_view));

    public void checkNewsScreen() {
        newsTitle.check(matches(isDisplayed()));
        newsTitle.check(matches(withText("News")));
    }

    public void goToNewsControlPanel() {
        editNewsButton.perform(click());
    }

    public void sortNews() {
        sortButton.perform(click());
    }

    public void checkNewsBeforeSort(NewsDataHelper.NewsInfo info) {
        String firstNews = info.getTextField();
        String lastNewsDate = "01.01.2012";
        String lastNewsDescription = "Тестовое описание 01.01.2012В06:44";
        int itemCount = CustomViewMatcher.getCountFromRecyclerView(R.id.news_list_recycler_view);
        newsList.check(matches(CustomViewMatcher.atPosition(0, hasDescendant(withText(firstNews)))));
        newsList.perform(RecyclerViewActions.actionOnItemAtPosition(itemCount - 1, click()));
        newsList.check(matches(CustomViewMatcher.atPosition(itemCount - 1, hasDescendant(withText(lastNewsDate)))));
        newsList.check(matches(CustomViewMatcher.atPosition(itemCount - 1, hasDescendant(withText(lastNewsDescription)))));
    }

    public void checkNewsAfterSort(NewsDataHelper.NewsInfo info) {
        String firstNewsDate = "01.01.2012";
        String firstNewsDescription = "Тестовое описание 01.01.2012В06:44";
        String lastNews = info.getTextField();
        int itemCount = CustomViewMatcher.getCountFromRecyclerView(R.id.news_list_recycler_view);
        newsList.perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        newsList.check(matches(CustomViewMatcher.atPosition(0, hasDescendant(withText(firstNewsDate)))));
        newsList.check(matches(CustomViewMatcher.atPosition(0, hasDescendant(withText(firstNewsDescription)))));
        newsList.perform(RecyclerViewActions.actionOnItemAtPosition(itemCount - 1, click()));
        newsList.check(matches(CustomViewMatcher.atPosition(itemCount - 1, hasDescendant(withText(lastNews)))));
    }

}
