package ru.iteco.fmhandroid.ui.tests;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.AuthDataHelper;
import ru.iteco.fmhandroid.ui.screen.AboutScreen;
import ru.iteco.fmhandroid.ui.screen.AuthorizationScreen;
import ru.iteco.fmhandroid.ui.screen.MainScreen;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AboutScreenTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() throws InterruptedException {
        Thread.sleep(5000);
        try {
            authorizationScreen.checkAuthScreenHeader();
        } catch (NoMatchingViewException e) {
            mainScreen.logout();
        }
        authorizationScreen.login(AuthDataHelper.getCorrectAuthInfo());
        Thread.sleep(1000);
        mainScreen.appNameVisible();
    }

    MainScreen mainScreen = new MainScreen();
    AuthorizationScreen authorizationScreen = new AuthorizationScreen();
    AboutScreen aboutScreen = new AboutScreen();

    @Test
    public void openPrivacyPolicy() {
        mainScreen.goToAboutScreenFromMainMenu();
        aboutScreen.checkAboutScreen();
        aboutScreen.goToPrivacyPolicy();
    }

    @Test
    public void openTermsOfUse() {
        mainScreen.goToAboutScreenFromMainMenu();
        aboutScreen.checkAboutScreen();
        aboutScreen.goToTermsOfUse();
    }

}
