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
import ru.iteco.fmhandroid.ui.data.NewsDataHelper;
import ru.iteco.fmhandroid.ui.screen.AuthorizationScreen;
import ru.iteco.fmhandroid.ui.screen.CreatingNewsScreen;
import ru.iteco.fmhandroid.ui.screen.FilterNewsScreen;
import ru.iteco.fmhandroid.ui.screen.MainScreen;
import ru.iteco.fmhandroid.ui.screen.NewsControlPanelScreen;
import ru.iteco.fmhandroid.ui.screen.NewsScreen;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class SortAndFilterNewsTest {

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
    }


    MainScreen mainScreen = new MainScreen();
    AuthorizationScreen authorizationScreen = new AuthorizationScreen();
    NewsScreen newsScreen = new NewsScreen();
    NewsControlPanelScreen controlPanelScreen = new NewsControlPanelScreen();
    FilterNewsScreen filterNews = new FilterNewsScreen();
    CreatingNewsScreen creatingNews = new CreatingNewsScreen();

    @Test
    public void sortNews() {
        mainScreen.goToNewsScreenFromMainMenu();
        newsScreen.checkNewsScreen();
        newsScreen.goToNewsControlPanel();
        controlPanelScreen.checkControlPanelScreen();
        controlPanelScreen.goToCreatingNewsScreen();
        creatingNews.checkCreatingNewsScreen();
        creatingNews.fillInTheNewsFieldsForHolidayCategory(NewsDataHelper.getFirstNewsData(0));
        creatingNews.saveNews();
        mainScreen.goToNewsScreenFromMainMenu();
        newsScreen.checkNewsBeforeSort(NewsDataHelper.getFirstNewsData(0));
        newsScreen.sortNews();
        newsScreen.checkNewsAfterSort(NewsDataHelper.getFirstNewsData(0));
    }

    @Test
    public void filterNewsWithDatesAndInactiveStatus() {
        mainScreen.goToNewsScreenFromMainMenu();
        newsScreen.checkNewsScreen();
        newsScreen.goToNewsControlPanel();
        controlPanelScreen.checkControlPanelScreen();
        controlPanelScreen.goToFilterNewsScreen();
        filterNews.checkFilterNewsScreen();
        filterNews.fillInFilteringFields(NewsDataHelper.getFilterInfoWithDatesAndEmptyCategory());
        controlPanelScreen.checkFirstNewsAfterFilter();
        controlPanelScreen.checkLastNewsAfterFilter();
    }

    @Test
    public void filterWithNonExistingCategory() {
        mainScreen.goToNewsScreenFromMainMenu();
        newsScreen.checkNewsScreen();
        newsScreen.goToNewsControlPanel();
        controlPanelScreen.checkControlPanelScreen();
        controlPanelScreen.goToFilterNewsScreen();
        filterNews.checkFilterNewsScreen();
        filterNews.fillInFilteringFields(NewsDataHelper.getFilterInfoWithNonExistingCategory());
        filterNews.wrongCategoryMessageDisplayed();

    }

}
