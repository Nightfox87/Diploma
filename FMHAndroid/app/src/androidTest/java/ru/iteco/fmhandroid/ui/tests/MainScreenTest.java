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
import ru.iteco.fmhandroid.ui.screen.ClaimsScreen;
import ru.iteco.fmhandroid.ui.screen.CreatingClaimsScreen;
import ru.iteco.fmhandroid.ui.screen.MainScreen;
import ru.iteco.fmhandroid.ui.screen.NewsScreen;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainScreenTest {

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
    NewsScreen newsScreen = new NewsScreen();
    ClaimsScreen claimsScreen = new ClaimsScreen();
    CreatingClaimsScreen creatingClaimsScreen = new CreatingClaimsScreen();

    @Test
    public void addNewClaimFromMainScreen() {
        mainScreen.goToCreatingClaims();
        creatingClaimsScreen.checkCreatingClaimsScreen();
    }

    @Test
    public void goToNewsScreenFromMainScreen() {
        mainScreen.goToNewsScreen();
        newsScreen.checkNewsScreen();
    }

    @Test
    public void goToClaimsScreenFromMainScreen() {
        mainScreen.goToClaimsScreen();
        claimsScreen.checkClaimsScreen();
    }
}
