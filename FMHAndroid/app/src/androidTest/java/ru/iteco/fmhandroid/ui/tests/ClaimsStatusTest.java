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
import ru.iteco.fmhandroid.ui.data.ClaimsDataHelper;
import ru.iteco.fmhandroid.ui.screen.AuthorizationScreen;
import ru.iteco.fmhandroid.ui.screen.ClaimInfoScreen;
import ru.iteco.fmhandroid.ui.screen.ClaimsScreen;
import ru.iteco.fmhandroid.ui.screen.CreatingClaimsScreen;
import ru.iteco.fmhandroid.ui.screen.EditingClaimsScreen;
import ru.iteco.fmhandroid.ui.screen.MainScreen;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ClaimsStatusTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() throws InterruptedException {
        Thread.sleep(7000);
        try {
            authorizationScreen.checkAuthScreenHeader();
        } catch (NoMatchingViewException e) {
            mainScreen.logout();
        }
        authorizationScreen.login(AuthDataHelper.getCorrectAuthInfo());
        Thread.sleep(1000);
        mainScreen.appNameVisible();
        mainScreen.goToClaimsScreen();
        claimsScreen.checkClaimsScreen();
        claimsScreen.goToCreatingClaim();
        creatingClaimsScreen.checkCreatingClaimsScreen();
    }

    MainScreen mainScreen = new MainScreen();
    AuthorizationScreen authorizationScreen = new AuthorizationScreen();
    ClaimsScreen claimsScreen = new ClaimsScreen();
    CreatingClaimsScreen creatingClaimsScreen = new CreatingClaimsScreen();
    ClaimInfoScreen claimInfoScreen = new ClaimInfoScreen();
    EditingClaimsScreen editClaim = new EditingClaimsScreen();

    @Test
    public void changeClaimStatusToExecuted() {
        creatingClaimsScreen.fillInCreatingClaimFields(ClaimsDataHelper.getInfoForExecutedStatus(8));
        creatingClaimsScreen.saveClaim();
        claimsScreen.checkClaimsScreen();
        claimsScreen.filterInProgressClaims();
        claimsScreen.findCreatedClaimInLastPosition();
        claimInfoScreen.checkClaimInfoScreen();
        claimInfoScreen.checkClaim(ClaimsDataHelper.getInfoForExecutedStatus(8));
        claimInfoScreen.toExecuteClaimAndCheckStatus(ClaimsDataHelper.getFirstComment());
    }

    @Test
    public void editClaimWithOpenStatus() {
        creatingClaimsScreen.fillInCreatingClaimFields(ClaimsDataHelper.getInfoForClaimEditing(8));
        creatingClaimsScreen.saveClaim();
        claimsScreen.checkClaimsScreen();
        claimsScreen.filterInProgressClaims();
        claimsScreen.findCreatedClaimInLastPosition();
        claimInfoScreen.checkClaimInfoScreen();
        claimInfoScreen.checkClaim(ClaimsDataHelper.getInfoForClaimEditing(8));
        claimInfoScreen.throwOffClaimAndCheckStatus(ClaimsDataHelper.getSecondComment());
        claimInfoScreen.goToEditingClaimScreen();
        editClaim.checkEditingClaimScreen();
        editClaim.changeClaimDescription(ClaimsDataHelper.getInfoForClaimEditing(0));
        claimInfoScreen.checkDescriptionAfterEdit(ClaimsDataHelper.getInfoForClaimEditing(0));
    }

    @Test
    public void changeClaimStatusToCancelled() {
        creatingClaimsScreen.fillInCreatingClaimFields(ClaimsDataHelper.getInfoForCancelledStatus(8));
        creatingClaimsScreen.saveClaim();
        claimsScreen.checkClaimsScreen();
        claimsScreen.filterInProgressClaims();
        claimsScreen.findCreatedClaimInLastPosition();
        claimInfoScreen.checkClaimInfoScreen();
        claimInfoScreen.checkClaim(ClaimsDataHelper.getInfoForCancelledStatus(8));
        claimInfoScreen.throwOffClaimAndCheckStatus(ClaimsDataHelper.getThirdComment());
        claimInfoScreen.cancelClaimAndCheckStatus();
    }

}
