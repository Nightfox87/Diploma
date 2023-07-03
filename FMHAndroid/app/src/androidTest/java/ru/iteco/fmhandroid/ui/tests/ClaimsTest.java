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
import io.qameta.allure.kotlin.Flaky;
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
    }

    MainScreen mainScreen = new MainScreen();
    AuthorizationScreen authorizationScreen = new AuthorizationScreen();
    ClaimsScreen claimsScreen = new ClaimsScreen();
    CreatingClaimsScreen creatingClaimsScreen = new CreatingClaimsScreen();
    ClaimInfoScreen claimInfoScreen = new ClaimInfoScreen();

    @Test
    @Flaky
    @Description("Создание заявки")
    public void creatingClaimTest() {
        claimsScreen.goToCreatingClaim();
        creatingClaimsScreen.checkCreatingClaimsScreen();
        creatingClaimsScreen.fillInCreatingClaimFields(ClaimsDataHelper.getInfoForClaimCreation(0));
        creatingClaimsScreen.saveClaim();
        claimsScreen.waitToLoadScreen();
        claimsScreen.checkClaimsScreen();
        claimsScreen.waitToLoadList();
        claimsScreen.filterInProgressClaims();
        claimsScreen.findCreatedClaim(ClaimsDataHelper.getInfoForClaimCreation(0));
        claimInfoScreen.checkClaimInfoScreen();
        claimInfoScreen.checkClaim(ClaimsDataHelper.getInfoForClaimCreation(0));
    }

    @Test
    @Description("Фильтрация и проверка отмененных заявок")
    public void filterAndCheckCancelledClaims() {
        claimsScreen.filterCancelledClaims();
        claimsScreen.waitToLoadScreen();
        claimsScreen.checkClaimsScreen();
        claimsScreen.waitToLoadList();
        claimsScreen.checkFirstThreeCancelledClaimsAfterFilter();
    }

    @Test
    @Description("Создание заявки с датой из прошлого")
    public void createClaimWithPastDate() {
        claimsScreen.goToCreatingClaim();
        creatingClaimsScreen.checkCreatingClaimsScreen();
        creatingClaimsScreen.fillInCreatingClaimFields(ClaimsDataHelper.getInfoForClaimCreation(-5));
        creatingClaimsScreen.saveClaim();
        creatingClaimsScreen.wrongDateMessage();
    }

    @Test
    @Description("Создание заявки с несуществующим исполнителем")
    public void createClaimWithNonExistingExecutor() {
        claimsScreen.goToCreatingClaim();
        creatingClaimsScreen.checkCreatingClaimsScreen();
        creatingClaimsScreen.fillInCreatingClaimFields(ClaimsDataHelper.getInfoForClaimCreation(0));
        creatingClaimsScreen.setExecutorName(ClaimsDataHelper.getInfoForClaimCreation(0));
        creatingClaimsScreen.saveClaim();
        creatingClaimsScreen.wrongExecutorMessage();
    }

    @Test
    @Description("Отмена создания заявки")
    public void cancelCreationOfClaim() {
        claimsScreen.goToCreatingClaim();
        creatingClaimsScreen.checkCreatingClaimsScreen();
        creatingClaimsScreen.cancelCreatingClaim();
        claimsScreen.checkClaimsScreen();
    }

}
