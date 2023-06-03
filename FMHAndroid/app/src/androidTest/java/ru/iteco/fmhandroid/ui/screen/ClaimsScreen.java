package ru.iteco.fmhandroid.ui.screen;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.CustomViewMatcher;

public class ClaimsScreen {

    public ViewInteraction claimsTitle = onView(withId(R.id.claims_screen_title));
    public ViewInteraction claimList = onView(withId(R.id.claim_list_recycler_view));
    public ViewInteraction addClaimButton = onView(withId(R.id.add_new_claim_material_button));
    public ViewInteraction filterButton = onView(withId(R.id.filters_material_button));
    public ViewInteraction openCheckbox = onView(withId(R.id.item_filter_open));
    public ViewInteraction inProgressCheckbox = onView(withId(R.id.item_filter_in_progress));
    public ViewInteraction cancelledCheckbox = onView(withId(R.id.item_filter_cancelled));
    public ViewInteraction okButton = onView(withId(R.id.claim_list_filter_ok_material_button));

    public void checkClaimsScreen() {
        claimsTitle.check(matches(isDisplayed()));
        claimsTitle.check(matches(withText("Claims")));
    }

    public void goToCreatingClaim() {
        addClaimButton.perform(click());
    }

    public void findCreatedClaimInLastPosition() {
        int itemCount = CustomViewMatcher.getCountFromRecyclerView(R.id.claim_list_recycler_view);
        claimList.perform(RecyclerViewActions.actionOnItemAtPosition(itemCount - 1, click()));
    }

    public void filterInProgressClaims() {
        filterButton.perform(click());
        openCheckbox.perform(click());
        okButton.perform(click());
    }

    public void filterCancelledClaims() {
        filterButton.perform(click());
        openCheckbox.perform(click());
        inProgressCheckbox.perform(click());
        cancelledCheckbox.perform(click());
        okButton.perform(click());
    }

    public void checkFirstThreeCancelledClaimsAfterFilter() {
        ClaimInfoScreen claimInfoScreen = new ClaimInfoScreen();
        claimList.perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        claimInfoScreen.status.check(matches(withText("Canceled")));
        claimInfoScreen.commentsList.perform(swipeUp());
        claimInfoScreen.goBackButton.perform(click());
        claimList.perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        claimInfoScreen.status.check(matches(withText("Canceled")));
        claimInfoScreen.goBackButton.perform(click());
        claimList.perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        claimInfoScreen.status.check(matches(withText("Canceled")));
        claimInfoScreen.goBackButton.perform(click());
    }


}
