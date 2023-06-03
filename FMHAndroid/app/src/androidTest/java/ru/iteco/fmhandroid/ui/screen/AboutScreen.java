package ru.iteco.fmhandroid.ui.screen;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Intent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.Intents;

import ru.iteco.fmhandroid.R;

public class AboutScreen {

    public ViewInteraction version = onView(withId(R.id.about_version_title_text_view));
    public ViewInteraction backButton = onView(withId(R.id.about_back_image_button));
    public ViewInteraction privacyPolicyLink = onView(withId(R.id.about_privacy_policy_value_text_view));
    public ViewInteraction termsOfUseLink = onView(withId(R.id.about_terms_of_use_value_text_view));


    public void checkAboutScreen() {
        version.check(matches(isDisplayed()));
    }

    public void goBack() {
        backButton.perform(click());
    }

    public void goToPrivacyPolicy() {
        Intents.init();
        privacyPolicyLink.perform(click());
        intended(hasData("https://vhospice.org/#/privacy-policy/"));
        intended(hasAction(Intent.ACTION_VIEW));
        Intents.release();

    }

    public void goToTermsOfUse() {
        Intents.init();
        termsOfUseLink.perform(click());
        intended(hasData("https://vhospice.org/#/terms-of-use"));
        intended(hasAction(Intent.ACTION_VIEW));
        Intents.release();
    }
}
