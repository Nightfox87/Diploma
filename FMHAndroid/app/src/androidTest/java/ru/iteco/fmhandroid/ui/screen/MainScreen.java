package ru.iteco.fmhandroid.ui.screen;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class MainScreen {

    public ViewInteraction appName = onView(withId(R.id.trademark_image_view));
    public ViewInteraction profileButton = onView(withId(R.id.authorization_image_button));
    public ViewInteraction logoutButton = onView(withText("Log out"));
    public ViewInteraction mainMenuButton = onView(withId(R.id.main_menu_image_button));
    public ViewInteraction mainMenuNewsButton = onView(allOf(withId(android.R.id.title), withText("News")));
    public ViewInteraction mainMenuClaimsButton = onView(allOf(withId(android.R.id.title), withText("Claims")));
    public ViewInteraction mainMenuAboutButton = onView(allOf(withId(android.R.id.title), withText("About")));
    public ViewInteraction mainMenuMainButton = onView(allOf(withId(android.R.id.title), withText("Main")));
    public ViewInteraction addClaimButton = onView(withId(R.id.add_new_claim_material_button));
    public ViewInteraction allNewsButton = onView(withId(R.id.all_news_text_view));
    public ViewInteraction allClaimsButton = onView(withId(R.id.all_claims_text_view));
    public ViewInteraction loveIsAllButton = onView(withId(R.id.our_mission_image_button));

    public void logout() {
        profileButton.perform(click());
        logoutButton.perform(click());
    }

    public void appNameVisible() {
        appName.check(matches(isDisplayed()));
    }

    public void goToNewsScreenFromMainMenu() {
        mainMenuButton.perform(click());
        mainMenuNewsButton.perform(click());
    }

    public void goToClaimsScreenFromMainMenu() {
        mainMenuButton.perform(click());
        mainMenuClaimsButton.perform(click());
    }

    public void goToAboutScreenFromMainMenu() {
        mainMenuButton.perform(click());
        mainMenuAboutButton.perform(click());
    }

    public void goToMainScreenFromMainMenu() {
        mainMenuButton.perform(click());
        mainMenuMainButton.perform(click());
    }

    public void goToCreatingClaims() {
        addClaimButton.perform(click());
    }

    public void goToNewsScreen() {
        allNewsButton.perform(click());
    }

    public void goToClaimsScreen() {
        allClaimsButton.perform(click());
    }

    public void goToLoveIsAllScreen() {
        loveIsAllButton.perform(click());
    }
}
