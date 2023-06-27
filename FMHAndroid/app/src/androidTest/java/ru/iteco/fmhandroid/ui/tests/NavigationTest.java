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
import ru.iteco.fmhandroid.ui.screen.ClaimsScreen;
import ru.iteco.fmhandroid.ui.screen.LoveIsAllScreen;
import ru.iteco.fmhandroid.ui.screen.MainScreen;
import ru.iteco.fmhandroid.ui.screen.NewsScreen;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NavigationTest {

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
    NewsScreen newsScreen = new NewsScreen();
    ClaimsScreen claimsScreen = new ClaimsScreen();
    AboutScreen aboutScreen = new AboutScreen();
    LoveIsAllScreen loveIsAllScreen = new LoveIsAllScreen();

    @Test
    @Description("Переход между экранами приложения через выпадающее меню вверху экрана")
    public void navigationTest() {
        mainScreen.goToNewsScreenFromMainMenu();
        newsScreen.checkNewsScreen();
        mainScreen.goToClaimsScreenFromMainMenu();
        claimsScreen.checkClaimsScreen();
        mainScreen.goToAboutScreenFromMainMenu();
        aboutScreen.checkAboutScreen();
        aboutScreen.goBack();
        mainScreen.goToMainScreenFromMainMenu();
        mainScreen.waitForAppName();
    }

    @Test
    @Description("Переход на экран Цитаты")
    public void goToLoveIsAllScreen() {
        mainScreen.goToLoveIsAllScreen();
        loveIsAllScreen.checkLoveIsAllScreen();
    }

}
