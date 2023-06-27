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

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.NewsDataHelper;

public class FilterNewsScreen {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);

    public ViewInteraction filterNewsTitle = onView(withId(R.id.filter_news_title_text_view));
    public ViewInteraction category = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public ViewInteraction startDate = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
    public ViewInteraction endDate = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));
    public ViewInteraction activeCheckbox = onView(withId(R.id.filter_news_active_material_check_box));
    public ViewInteraction filterButton = onView(withId(R.id.filter_button));
    private View decorView;

    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<AppActivity>() {
            @Override
            public void perform(AppActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
    }

    @Step("Проверка экрана фильтрации новостей")
    public void checkFilterNewsScreen() {
        filterNewsTitle.check(matches(isDisplayed()));
        filterNewsTitle.check(matches(withText("Filter news")));
    }

    @Step("Заполнение полей для фильтрации новостей")
    public void fillInFilteringFields(NewsDataHelper.FilterInfo info) {
        category.perform(click());
        category.perform(replaceText(info.getCategory()));
        startDate.perform(replaceText(info.getStartDate()));
        endDate.perform(replaceText(info.getEndDate()));
        activeCheckbox.perform(click());
        filterButton.perform(click());
    }

    @Step("Сообщение о неверной категории")
    public void wrongCategoryMessageDisplayed() {
        onView(withText("Wrong category")).inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }
}
