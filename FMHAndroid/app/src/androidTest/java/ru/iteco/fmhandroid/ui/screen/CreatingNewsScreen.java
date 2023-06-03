package ru.iteco.fmhandroid.ui.screen;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.NewsDataHelper;

public class CreatingNewsScreen {
    private View decorView;
    public ViewInteraction creatingNewsTitle = onView(withId(R.id.custom_app_bar_title_text_view));
    public ViewInteraction creatingNewsSubTitle = onView(withId(R.id.custom_app_bar_sub_title_text_view));
    public ViewInteraction categoryField = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public ViewInteraction titleField = onView(withId(R.id.news_item_title_text_input_edit_text));
    public ViewInteraction dateField = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    public ViewInteraction timeField = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    public ViewInteraction descriptionField = onView(withId(R.id.news_item_description_text_input_edit_text));
    public ViewInteraction saveButton = onView(withId(R.id.save_button));
    public ViewInteraction okButton = onView(withText("OK"));
    public ViewInteraction holidayCategory = onView(withText("Праздник")).inRoot(withDecorView(not(decorView)));
    public ViewInteraction cancelButton = onView(withId(R.id.cancel_button));


    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);

    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<AppActivity>() {
            @Override
            public void perform(AppActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
    }

    public void checkCreatingNewsScreen() {
        creatingNewsTitle.check(matches(isDisplayed()));
        creatingNewsTitle.check(matches(withText("Creating")));
        creatingNewsSubTitle.check(matches(isDisplayed()));
        creatingNewsSubTitle.check(matches(withText("News")));
    }

    public void fillInTheNewsFieldsForHolidayCategory(NewsDataHelper.NewsInfo newsInfo) {
        categoryField.perform(click());
        holidayCategory.check(matches(isDisplayed())).perform(click());
        titleField.perform(replaceText(newsInfo.getTextField()));
        dateField.perform(replaceText(newsInfo.getDate()));
        timeField.perform(click());
        okButton.perform(click());
        descriptionField.perform(replaceText(newsInfo.getTextField()));
    }

    public void saveNews() {
        saveButton.perform(click());
    }

    public void cancelNews() {
        cancelButton.perform(click());
        okButton.perform(click());
    }

    public void wrongDateMessage() {
        onView(withText("Wrong date")).inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

}
