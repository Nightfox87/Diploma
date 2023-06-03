package ru.iteco.fmhandroid.ui.screen;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.NewsDataHelper;

public class EditingNewsScreen {

    public ViewInteraction editNewsScreenTitle = onView(withId(R.id.custom_app_bar_title_text_view));
    public ViewInteraction editNewsScreenSubTitle = onView(withId(R.id.custom_app_bar_sub_title_text_view));
    public ViewInteraction title = onView(withId(R.id.news_item_title_text_input_edit_text));
    public ViewInteraction saveButton = onView(withId(R.id.save_button));

    public void checkEditNewsScreen() {
        editNewsScreenTitle.check(matches(isDisplayed()));
        editNewsScreenTitle.check(matches(withText("Editing")));
        editNewsScreenSubTitle.check(matches(isDisplayed()));
        editNewsScreenSubTitle.check(matches(withText("News")));
    }

    public void editTitle(NewsDataHelper.NewsInfo info) {
        title.perform(replaceText(info.getTextField()));
        saveButton.perform(click());
    }
}
