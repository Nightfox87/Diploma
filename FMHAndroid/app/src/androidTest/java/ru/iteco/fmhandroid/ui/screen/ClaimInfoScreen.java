package ru.iteco.fmhandroid.ui.screen;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
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
import ru.iteco.fmhandroid.ui.utils.CustomViewAction;

public class ClaimInfoScreen {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);

    private View decorView;
    public ViewInteraction titleLabel = onView(withId(R.id.title_label_text_view));
    public ViewInteraction title = onView(withId(R.id.title_text_view));
    public ViewInteraction status = onView(withId(R.id.status_label_text_view));
    public ViewInteraction description = onView(withId(R.id.description_text_view));
    public ViewInteraction planDate = onView(withId(R.id.plane_date_text_view));
    public ViewInteraction addComment = onView(withId(R.id.add_comment_image_button));
    public ViewInteraction commentText = onView(withId(R.id.comment_description_text_view));
    public ViewInteraction editClaimButton = onView(withId(R.id.edit_processing_image_button));
    public ViewInteraction changeStatusButton = onView(withId(R.id.status_processing_image_button));
    public ViewInteraction throwOffButton = onView(withText("Throw off")).inRoot(withDecorView(not(decorView)));
    public ViewInteraction toExecuteButton = onView(withText("To execute")).inRoot(withDecorView(not(decorView)));
    public ViewInteraction cancelButton = onView(withText("Cancel")).inRoot(withDecorView(not(decorView)));
    public ViewInteraction goBackButton = onView(withId(R.id.close_image_button));
    public ViewInteraction commentsList = onView(withId(R.id.claim_comments_list_recycler_view));
    public ViewInteraction commentAfterStatusChange = onView(withId(R.id.editText));
    public ViewInteraction okButton = onView(withText("OK")).inRoot(withDecorView(not(decorView)));

    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<AppActivity>() {
            @Override
            public void perform(AppActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
    }

    @Step("Ожидание загрузки экрана")
    public void waitToLoadScreen() {
        onView(isRoot()).perform(CustomViewAction.waitDisplayed(R.id.status_label_text_view, 1000));
    }

    @Step("Проверка экрана информации о заявке")
    public void checkClaimInfoScreen() {
        titleLabel.check(matches(isDisplayed()));
    }

    @Step("Проверка заявки")
    public void checkClaim(ClaimsDataHelper.ClaimsInfo info) {
        title.check(matches(isDisplayed()));
        title.check(matches(withText(info.getTextField())));
        description.check(matches(isDisplayed()));
        description.check(matches(withText(info.getTextField())));
        planDate.check(matches(isDisplayed()));
        planDate.check(matches(withText(info.getDate())));
    }

    @Step("Выполнение заявки и проверка статуса")
    public void toExecuteClaimAndCheckStatus(ClaimsDataHelper.CommentsInfo info) {
        changeStatusButton.perform(click());
        toExecuteButton.perform(click());
        commentAfterStatusChange.perform(replaceText(info.getCommentText()));
        okButton.perform(click());
        status.check(matches(withText("Executed")));
    }

    @Step("Сброс заявки и проверка статуса")
    public void throwOffClaimAndCheckStatus(ClaimsDataHelper.CommentsInfo info) {
        changeStatusButton.perform(click());
        throwOffButton.perform(click());
        commentAfterStatusChange.perform(replaceText(info.getCommentText()));
        okButton.perform(click());
        status.check(matches(withText("Open")));
    }

    @Step("Переход на экран редактирования заявки")
    public void goToEditingClaimScreen() {
        editClaimButton.perform(click());
    }

    @Step("Проверка описания после редактирования")
    public void checkDescriptionAfterEdit(ClaimsDataHelper.ClaimsInfo info) {
        description.check(matches(withText(info.getDate())));
    }

    @Step("Отмена заявки и проверка статуса")
    public void cancelClaimAndCheckStatus() {
        changeStatusButton.perform(click());
        cancelButton.perform(click());
        status.check(matches(withText("Canceled")));
    }

    @Step("Переход к экрану добавления комментария")
    public void goToAddComment() {
        addComment.perform(click());
    }

    @Step("Проверка комментария")
    public void checkComment(ClaimsDataHelper.CommentsInfo info) {
        commentText.check(matches(withText(info.getCommentText())));
    }
}
