package ru.iteco.fmhandroid.ui.screen;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class LoveIsAllScreen {

    public ViewInteraction screenTitle = onView(withId(R.id.our_mission_title_text_view));

    public void checkLoveIsAllScreen() {
        screenTitle.check(matches(isDisplayed()));
        screenTitle.check(matches(withText("Love is all")));
    }
}
