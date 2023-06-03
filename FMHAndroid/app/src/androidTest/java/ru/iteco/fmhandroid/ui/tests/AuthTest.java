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
import ru.iteco.fmhandroid.ui.screen.AuthorizationScreen;
import ru.iteco.fmhandroid.ui.screen.MainScreen;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AuthTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);

    @Before
    public void setUp() throws InterruptedException {
        Thread.sleep(5000);
        try {
            authorizationScreen.checkAuthScreenHeader();
        } catch (NoMatchingViewException e) {
            mainScreen.logout();
        }
    }

    MainScreen mainScreen = new MainScreen();
    AuthorizationScreen authorizationScreen = new AuthorizationScreen();

    @Test
    public void authWithCorrectDataAndLogout() throws InterruptedException {
        authorizationScreen.login(AuthDataHelper.getCorrectAuthInfo());
        Thread.sleep(1000);
        mainScreen.appNameVisible();
        mainScreen.logout();
        authorizationScreen.checkAuthScreenHeader();
    }

    @Test
    public void authWithIncorrectLogin() {
        authorizationScreen.login(AuthDataHelper.getIncorrectLoginInfo());
        authorizationScreen.wrongLoginOrPasswordToastVisible();
    }

    @Test
    public void authWithEmptyLogin() {
        authorizationScreen.login(AuthDataHelper.getInfoWithEmptyLogin());
        authorizationScreen.emptyLoginOrPasswordToastVisible();
    }

    @Test
    public void authWithSpacesBeforeLogin() {
        authorizationScreen.login(AuthDataHelper.getInfoWithSpacesBeforeLogin());
        authorizationScreen.wrongLoginOrPasswordToastVisible();
    }

    @Test
    public void authWithSpacesBeforePassword() {
        authorizationScreen.login(AuthDataHelper.getInfoWithSpacesBeforePassword());
        authorizationScreen.wrongLoginOrPasswordToastVisible();
    }

    @Test
    public void authWithSpaceAfterLogin() {
        authorizationScreen.login(AuthDataHelper.getInfoWithSpaceAfterLogin());
        authorizationScreen.wrongLoginOrPasswordToastVisible();
    }

    @Test
    public void authWithSpacesAfterPassword() {
        authorizationScreen.login(AuthDataHelper.getInfoWithSpacesAfterPassword());
        authorizationScreen.wrongLoginOrPasswordToastVisible();
    }


}
