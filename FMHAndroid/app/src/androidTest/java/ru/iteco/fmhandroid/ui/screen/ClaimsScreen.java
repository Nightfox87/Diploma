package ru.iteco.fmhandroid.ui.screen;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
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
import ru.iteco.fmhandroid.ui.data.ClaimsDataHelper;
import ru.iteco.fmhandroid.ui.utils.CustomViewAction;

public class ClaimsScreen {

    public ViewInteraction claimsTitle = onView(withId(R.id.claims_screen_title));
    public ViewInteraction claimList = onView(withId(R.id.claim_list_recycler_view));
    public ViewInteraction addClaimButton = onView(withId(R.id.add_new_claim_material_button));
    public ViewInteraction filterButton = onView(withId(R.id.filters_material_button));
    public ViewInteraction openCheckbox = onView(withId(R.id.item_filter_open));
    public ViewInteraction inProgressCheckbox = onView(withId(R.id.item_filter_in_progress));
    public ViewInteraction cancelledCheckbox = onView(withId(R.id.item_filter_cancelled));
    public ViewInteraction okButton = onView(withId(R.id.claim_list_filter_ok_material_button));

    @Step("Проверка экрана Заявки")
    public void checkClaimsScreen() {
        claimsTitle.check(matches(isDisplayed()));
        claimsTitle.check(matches(withText("Claims")));
    }

    @Step("Переход на экран создания заявки")
    public void goToCreatingClaim() {
        addClaimButton.perform(click());
    }

    @Step("Ожидание загрузки экрана")
    public void waitToLoadScreen() {
        onView(isRoot()).perform(CustomViewAction.waitDisplayed(R.id.claims_screen_title, 2000));
    }

    @Step("Ожидание загрузки списка")
    public void waitToLoadList() {
        onView(isRoot()).perform(CustomViewAction.waitDisplayed(R.id.description_material_text_view, 5000));
    }

    @Step("Поиск созданной заявки")
    public void findCreatedClaim(ClaimsDataHelper.ClaimsInfo info) {
        String claimToFind = info.getTextField();
        claimList.perform(RecyclerViewActions.scrollTo(hasDescendant(withText(claimToFind)))).check(matches(isDisplayed()));
        claimList.perform(RecyclerViewActions.scrollTo(hasDescendant(withText(claimToFind)))).perform(click());
    }

    @Step("Фильтрация заявок со статусом В процессе")
    public void filterInProgressClaims() {
        filterButton.perform(click());
        openCheckbox.perform(click());
        okButton.perform(click());
    }

    @Step("Фильтрация отмененных заявок")
    public void filterCancelledClaims() {
        filterButton.perform(click());
        openCheckbox.perform(click());
        inProgressCheckbox.perform(click());
        cancelledCheckbox.perform(click());
        okButton.perform(click());
    }

    @Step("Проверка первых трех отмененных заявок из списка")
    public void checkFirstThreeCancelledClaimsAfterFilter() {
        ClaimInfoScreen claimInfoScreen = new ClaimInfoScreen();
        claimList.perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        claimInfoScreen.waitToLoadScreen();
        claimInfoScreen.status.check(matches(withText("Canceled")));
        claimInfoScreen.commentsList.perform(swipeUp());
        claimInfoScreen.goBackButton.perform(click());
        waitToLoadScreen();
        waitToLoadList();
        claimList.perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        claimInfoScreen.waitToLoadScreen();
        claimInfoScreen.status.check(matches(withText("Canceled")));
        claimInfoScreen.goBackButton.perform(click());
        waitToLoadScreen();
        waitToLoadList();
        claimList.perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        claimInfoScreen.waitToLoadScreen();
        claimInfoScreen.status.check(matches(withText("Canceled")));
        claimInfoScreen.goBackButton.perform(click());
    }

}
