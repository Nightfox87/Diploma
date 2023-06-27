package ru.iteco.fmhandroid.ui.screen;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.ClaimsDataHelper;

public class EditingClaimsScreen {

    public ViewInteraction screenTitle = onView(withId(R.id.custom_app_bar_title_text_view));
    public ViewInteraction screenSubTitle = onView(withId(R.id.custom_app_bar_sub_title_text_view));
    public ViewInteraction descriptionField = onView(withId(R.id.description_edit_text));
    public ViewInteraction saveButton = onView(withId(R.id.save_button));

    @Step("Проверка экрана редактирования заявки")
    public void checkEditingClaimScreen() {
        screenTitle.check(matches(isDisplayed()));
        screenTitle.check(matches(withText("Editing")));
        screenSubTitle.check(matches(isDisplayed()));
        screenSubTitle.check(matches(withText("Claims")));
    }

    @Step("Редактирование описания заявки")
    public void changeClaimDescription(ClaimsDataHelper.ClaimsInfo info) {
        descriptionField.perform(replaceText(info.getDate()));
        saveButton.perform(click());
    }
}
