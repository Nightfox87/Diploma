package ru.iteco.fmhandroid.ui.tests;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.AuthDataHelper;
import ru.iteco.fmhandroid.ui.data.ClaimsDataHelper;
import ru.iteco.fmhandroid.ui.screen.AuthorizationScreen;
import ru.iteco.fmhandroid.ui.screen.ClaimInfoScreen;
import ru.iteco.fmhandroid.ui.screen.ClaimsScreen;
import ru.iteco.fmhandroid.ui.screen.CreatingClaimsScreen;
import ru.iteco.fmhandroid.ui.screen.MainScreen;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class ClaimsTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() throws InterruptedException {
        Thread.sleep(8000);
        try {
            authorizationScreen.checkAuthScreenHeader();
        } catch (NoMatchingViewException e) {
            mainScreen.logout();
        }
        authorizationScreen.login(AuthDataHelper.getCorrectAuthInfo());
        Thread.sleep(3000);
        mainScreen.appNameVisible();
        mainScreen.goToClaimsScreen();
        claimsScreen.checkClaimsScreen();
    }

    MainScreen mainScreen = new MainScreen();
    AuthorizationScreen authorizationScreen = new AuthorizationScreen();
    ClaimsScreen claimsScreen = new ClaimsScreen();
    CreatingClaimsScreen creatingClaimsScreen = new CreatingClaimsScreen();
    ClaimInfoScreen claimInfoScreen = new ClaimInfoScreen();

    @Test
    public void creatingClaimTest() {
        claimsScreen.goToCreatingClaim();
        creatingClaimsScreen.checkCreatingClaimsScreen();
        creatingClaimsScreen.fillInCreatingClaimFields(ClaimsDataHelper.getInfoForClaimCreation(8));
        creatingClaimsScreen.saveClaim();
        claimsScreen.checkClaimsScreen();
        claimsScreen.filterInProgressClaims();
        claimsScreen.findCreatedClaimInLastPosition(ClaimsDataHelper.getInfoForClaimCreation(8));
        claimInfoScreen.checkClaimInfoScreen();
        claimInfoScreen.checkClaim(ClaimsDataHelper.getInfoForClaimCreation(8));
    }

    @Test
    public void filterAndCheckCancelledClaims() throws InterruptedException {
        claimsScreen.filterCancelledClaims();
        Thread.sleep(1000);
        claimsScreen.checkFirstThreeCancelledClaimsAfterFilter();
    }

    @Test
    public void createClaimWithPastDate() {
        claimsScreen.goToCreatingClaim();
        creatingClaimsScreen.checkCreatingClaimsScreen();
        creatingClaimsScreen.fillInCreatingClaimFields(ClaimsDataHelper.getInfoForClaimCreation(-5));
        creatingClaimsScreen.saveClaim();
        creatingClaimsScreen.wrongDateMessage();
    }

    @Test
    public void createClaimWithNonExistingExecutor() {
        claimsScreen.goToCreatingClaim();
        creatingClaimsScreen.checkCreatingClaimsScreen();
        creatingClaimsScreen.fillInCreatingClaimFields(ClaimsDataHelper.getInfoForClaimCreation(0));
        creatingClaimsScreen.setExecutorName(ClaimsDataHelper.getInfoForClaimCreation(0));
        creatingClaimsScreen.saveClaim();
        creatingClaimsScreen.wrongExecutorMessage();
    }

    @Test
    public void cancelCreationOfClaim() {
        claimsScreen.goToCreatingClaim();
        creatingClaimsScreen.checkCreatingClaimsScreen();
        creatingClaimsScreen.cancelCreatingClaim();
        claimsScreen.checkClaimsScreen();
    }


}
