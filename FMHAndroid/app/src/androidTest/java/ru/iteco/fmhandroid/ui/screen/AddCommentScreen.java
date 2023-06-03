package ru.iteco.fmhandroid.ui.screen;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.ClaimsDataHelper;

public class AddCommentScreen {

    public ViewInteraction commentField = onView(withId(R.id.comment_edit_text));
    public ViewInteraction saveButton = onView(withId(R.id.save_button));

    public void checkAddCommentScreen() {
        commentField.check(matches(isDisplayed()));
    }

    public void addCommentAndSave(ClaimsDataHelper.CommentsInfo info) {
        commentField.perform(replaceText(info.getCommentText()));
        saveButton.perform(click());
    }

}
