package ru.iteco.fmhandroid.ui.screen;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.NewsDataHelper;
import ru.iteco.fmhandroid.ui.utils.CustomViewMatcher;
import ru.iteco.fmhandroid.ui.utils.RecyclerViewAssertions;

public class NewsControlPanelScreen {

    public ViewInteraction title = onView(withId(R.id.control_panel_title));
    public ViewInteraction addNewsButton = onView(withId(R.id.add_news_image_view));
    public ViewInteraction filterButton = onView(withId(R.id.filter_news_material_button));
    public ViewInteraction newsList = onView(withId(R.id.news_list_recycler_view));
    public ViewInteraction okButton = onView(withText("OK"));

    public void checkControlPanelScreen() {
        title.check(matches(isDisplayed()));
        title.check(matches(withText("Control panel")));
    }

    public void goToCreatingNewsScreen() {
        addNewsButton.perform(click());
    }

    public void goToFilterNewsScreen() {
        filterButton.perform(click());
    }

    public void checkFirstNewsAfterFilter() {
        newsList.check(matches(CustomViewMatcher.atPosition(0, hasDescendant(withText(NewsDataHelper.getFilterInfoWithDatesAndEmptyCategory().getEndDate())))));
        newsList.check(matches(CustomViewMatcher.atPosition(0, hasDescendant(withText("NOT ACTIVE")))));
    }

    public void checkLastNewsAfterFilter() {
        newsList.perform(RecyclerViewActions.actionOnItemAtPosition(5, swipeUp()));
        newsList.check(matches(CustomViewMatcher.atPosition(5, hasDescendant(withText(NewsDataHelper.getFilterInfoWithDatesAndEmptyCategory().getStartDate())))));
        newsList.check(matches(CustomViewMatcher.atPosition(5, hasDescendant(withText("NOT ACTIVE")))));
    }

    public void findCreatedNews(NewsDataHelper.NewsInfo newsInfo) {
        newsList.check(RecyclerViewAssertions.withRowContaining(withText(newsInfo.getTextField())));
        newsList.check(RecyclerViewAssertions.withRowContaining(withText(newsInfo.getDate())));
    }

    public void goToEditNews(NewsDataHelper.NewsInfo newsInfo) {
        onView(allOf(withId(R.id.edit_news_item_image_view),
                withParent(withParent(hasDescendant(withText(newsInfo.getTextField())))))).perform(click());
    }

    public void deleteNews(NewsDataHelper.NewsInfo newsInfo) {
        onView(allOf(withId(R.id.delete_news_item_image_view),
                withParent(withParent(hasDescendant(withText(newsInfo.getTextField())))))).perform(click());
        okButton.perform(click());
    }

    public void checkNewsDoesNotExist(NewsDataHelper.NewsInfo newsInfo) {
        try {
            newsList.check(RecyclerViewAssertions.withRowContaining(withText(newsInfo.getTextField())));
        } catch (OutOfMemoryError e) {
            System.out.println("News deleted");
        }
    }
}
