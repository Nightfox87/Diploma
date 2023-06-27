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
import ru.iteco.fmhandroid.ui.screen.AboutScreen;
import ru.iteco.fmhandroid.ui.screen.AuthorizationScreen;
import ru.iteco.fmhandroid.ui.screen.MainScreen;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AboutScreenTest {

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
    }

    MainScreen mainScreen = new MainScreen();
    AuthorizationScreen authorizationScreen = new AuthorizationScreen();
    AboutScreen aboutScreen = new AboutScreen();

    @Test
    @Description("Переход к просмотру политики конфиденциальности")
    public void openPrivacyPolicy() {
        mainScreen.goToAboutScreenFromMainMenu();
        aboutScreen.checkAboutScreen();
        aboutScreen.goToPrivacyPolicy();
    }

    @Test
    @Description("Переход к просмотру пользовательского соглашения")
    public void openTermsOfUse() {
        mainScreen.goToAboutScreenFromMainMenu();
        aboutScreen.checkAboutScreen();
        aboutScreen.goToTermsOfUse();
    }

}
