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
import ru.iteco.fmhandroid.ui.screen.AuthorizationScreen;
import ru.iteco.fmhandroid.ui.screen.MainScreen;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);

    @Before
    public void setUp() {
        try {
            authorizationScreen.waitForScreenHeader();
        } catch (PerformException e) {
            mainScreen.logout();
        }
    }

    MainScreen mainScreen = new MainScreen();
    AuthorizationScreen authorizationScreen = new AuthorizationScreen();

    @Test
    @Description("Успешная авторизация в приложение")
    public void authWithCorrectDataAndLogout() {
        authorizationScreen.login(AuthDataHelper.getCorrectAuthInfo());
        mainScreen.waitForAppName();
        mainScreen.logout();
        authorizationScreen.checkAuthScreenHeader();
    }

    @Test
    @Description("Авторизация в приложение с некорректным логином")
    public void authWithIncorrectLogin() {
        authorizationScreen.login(AuthDataHelper.getIncorrectLoginInfo());
        authorizationScreen.wrongLoginOrPasswordToastVisible();
    }

    @Test
    @Description("Авторизация с пустым полем логин")
    public void authWithEmptyLogin() {
        authorizationScreen.login(AuthDataHelper.getInfoWithEmptyLogin());
        authorizationScreen.emptyLoginOrPasswordToastVisible();
    }

    @Test
    @Description("Авторизация при вводе пробелов перед логином")
    public void authWithSpacesBeforeLogin() {
        authorizationScreen.login(AuthDataHelper.getInfoWithSpacesBeforeLogin());
        authorizationScreen.wrongLoginOrPasswordToastVisible();
    }

    @Test
    @Description("Авторизация при вводе пробелов перед паролем")
    public void authWithSpacesBeforePassword() {
        authorizationScreen.login(AuthDataHelper.getInfoWithSpacesBeforePassword());
        authorizationScreen.wrongLoginOrPasswordToastVisible();
    }

    @Test
    @Description("Авторизация при вводе пробела после логина")
    public void authWithSpaceAfterLogin() {
        authorizationScreen.login(AuthDataHelper.getInfoWithSpaceAfterLogin());
        authorizationScreen.wrongLoginOrPasswordToastVisible();
    }

    @Test
    @Description("Авторизация при вводе пробелов после пароля")
    public void authWithSpacesAfterPassword() {
        authorizationScreen.login(AuthDataHelper.getInfoWithSpacesAfterPassword());
        authorizationScreen.wrongLoginOrPasswordToastVisible();
    }


}
