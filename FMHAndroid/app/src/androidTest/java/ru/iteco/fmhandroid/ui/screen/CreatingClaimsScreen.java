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
import ru.iteco.fmhandroid.ui.data.ClaimsDataHelper;

public class CreatingClaimsScreen {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);

    private View decorView;
    public ViewInteraction creatingClaimsTitle = onView(withId(R.id.custom_app_bar_title_text_view));
    public ViewInteraction creatingClaimsSubTitle = onView(withId(R.id.custom_app_bar_sub_title_text_view));
    public ViewInteraction title = onView(withId(R.id.title_edit_text));
    public ViewInteraction executor = onView(withId(R.id.executor_drop_menu_auto_complete_text_view));
    public ViewInteraction executorName = onView(withText("Ivanov Ivan Ivanovich")).inRoot(withDecorView(not(decorView)));
    public ViewInteraction date = onView(withId(R.id.date_in_plan_text_input_edit_text));
    public ViewInteraction time = onView(withId(R.id.time_in_plan_text_input_edit_text));
    public ViewInteraction description = onView(withId(R.id.description_edit_text));
    public ViewInteraction saveButton = onView(withId(R.id.save_button));
    public ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    public ViewInteraction okButton = onView(withText("OK"));

    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<AppActivity>() {
            @Override
            public void perform(AppActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
    }

    @Step("Проверка экрана создания заявки")
    public void checkCreatingClaimsScreen() {
        creatingClaimsTitle.check(matches(isDisplayed()));
        creatingClaimsTitle.check(matches(withText("Creating")));
        creatingClaimsSubTitle.check(matches(isDisplayed()));
        creatingClaimsSubTitle.check(matches(withText("Claims")));
    }

    @Step("Заполнение полей при создании заявки")
    public void fillInCreatingClaimFields(ClaimsDataHelper.ClaimsInfo info) {
        title.perform(replaceText(info.getTextField()));
        executor.perform(click());
        executorName.check(matches(isDisplayed())).perform(click());
        date.perform(replaceText(info.getDate()));
        time.perform(click());
        okButton.perform(click());
        description.perform(replaceText(info.getTextField()));
    }

    @Step("Сохранение заявки")
    public void saveClaim() {
        saveButton.perform(click());
    }

    @Step("Отмена создания заявки")
    public void cancelCreatingClaim() {
        cancelButton.perform(click());
        okButton.perform(click());
    }

    @Step("Заполнение поля Исполнитель")
    public void setExecutorName(ClaimsDataHelper.ClaimsInfo info) {
        executorName.perform(replaceText(info.getTextField()));
    }

    @Step("Сообщение о неверной дате")
    public void wrongDateMessage() {
        onView(withText("Wrong date")).inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Step("Сообщение о неверном исполнителе")
    public void wrongExecutorMessage() {
        onView(withText("Wrong executor")).inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }
}
