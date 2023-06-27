package ru.iteco.fmhandroid.ui.tests;

import androidx.test.espresso.PerformException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
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
@RunWith(AllureAndroidJUnit4.class)
public class ClaimsStatusTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        try {
            authorizationScreen.waitForScreenHeader();
        } catch (PerformException e) {
            mainScreen.logout();
        }
        authorizationScreen.login(AuthDataHelper.getCorrectAuthInfo());
        mainScreen.waitForAppName();
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
    @Description("Изменение статуса заявки с В работе на Выполнена")
    public void changeClaimStatusToExecuted() {
        creatingClaimsScreen.fillInCreatingClaimFields(ClaimsDataHelper.getInfoForExecutedStatus(0));
        creatingClaimsScreen.saveClaim();
        claimsScreen.waitToLoadScreen();
        claimsScreen.checkClaimsScreen();
        claimsScreen.waitToLoadList();
        claimsScreen.filterInProgressClaims();
        claimsScreen.findCreatedClaim(ClaimsDataHelper.getInfoForExecutedStatus(0));
        claimInfoScreen.checkClaimInfoScreen();
        claimInfoScreen.checkClaim(ClaimsDataHelper.getInfoForExecutedStatus(0));
        claimInfoScreen.toExecuteClaimAndCheckStatus(ClaimsDataHelper.getFirstComment());
    }

    @Test
    @Description("Редактирование заявки со статусом Открыта")
    public void editClaimWithOpenStatus() {
        creatingClaimsScreen.fillInCreatingClaimFields(ClaimsDataHelper.getInfoForClaimEditing(0));
        claimsScreen.waitToLoadScreen();
        claimsScreen.checkClaimsScreen();
        claimsScreen.waitToLoadList();
        claimsScreen.filterInProgressClaims();
        claimsScreen.findCreatedClaim(ClaimsDataHelper.getInfoForClaimEditing(0));
        claimInfoScreen.checkClaimInfoScreen();
        claimInfoScreen.checkClaim(ClaimsDataHelper.getInfoForClaimEditing(0));
        claimInfoScreen.throwOffClaimAndCheckStatus(ClaimsDataHelper.getSecondComment());
        claimInfoScreen.goToEditingClaimScreen();
        editClaim.checkEditingClaimScreen();
        editClaim.changeClaimDescription(ClaimsDataHelper.getInfoForClaimEditing(0));
        claimInfoScreen.checkDescriptionAfterEdit(ClaimsDataHelper.getInfoForClaimEditing(0));
    }

    @Test
    @Description("Изменение статуса заявки с Открыта на Отменена")
    public void changeClaimStatusToCancelled() {
        creatingClaimsScreen.fillInCreatingClaimFields(ClaimsDataHelper.getInfoForCancelledStatus(0));
        creatingClaimsScreen.saveClaim();
        claimsScreen.waitToLoadScreen();
        claimsScreen.checkClaimsScreen();
        claimsScreen.waitToLoadList();
        claimsScreen.filterInProgressClaims();
        claimsScreen.findCreatedClaim(ClaimsDataHelper.getInfoForCancelledStatus(0));
        claimInfoScreen.checkClaimInfoScreen();
        claimInfoScreen.checkClaim(ClaimsDataHelper.getInfoForCancelledStatus(0));
        claimInfoScreen.throwOffClaimAndCheckStatus(ClaimsDataHelper.getThirdComment());
        claimInfoScreen.cancelClaimAndCheckStatus();
    }

}
