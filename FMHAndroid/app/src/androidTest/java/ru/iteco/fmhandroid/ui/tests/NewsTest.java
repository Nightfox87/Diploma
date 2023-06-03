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
import ru.iteco.fmhandroid.ui.data.NewsDataHelper;
import ru.iteco.fmhandroid.ui.screen.AuthorizationScreen;
import ru.iteco.fmhandroid.ui.screen.CreatingNewsScreen;
import ru.iteco.fmhandroid.ui.screen.EditingNewsScreen;
import ru.iteco.fmhandroid.ui.screen.MainScreen;
import ru.iteco.fmhandroid.ui.screen.NewsControlPanelScreen;
import ru.iteco.fmhandroid.ui.screen.NewsScreen;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NewsTest {

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
    NewsControlPanelScreen controlPanelScreen = new NewsControlPanelScreen();
    CreatingNewsScreen creatingNewsScreen = new CreatingNewsScreen();
    EditingNewsScreen editScreen = new EditingNewsScreen();

    @Test
    public void creatingNewsTest() throws InterruptedException {
        mainScreen.goToNewsScreenFromMainMenu();
        newsScreen.checkNewsScreen();
        newsScreen.goToNewsControlPanel();
        controlPanelScreen.checkControlPanelScreen();
        controlPanelScreen.goToCreatingNewsScreen();
        creatingNewsScreen.checkCreatingNewsScreen();
        creatingNewsScreen.fillInTheNewsFieldsForHolidayCategory(NewsDataHelper.getFirstNewsData(0));
        creatingNewsScreen.saveNews();
        Thread.sleep(1000);
        controlPanelScreen.findCreatedNews(NewsDataHelper.getFirstNewsData(0));

    }

    @Test
    public void cancelCreationOfNews() {
        mainScreen.goToNewsScreenFromMainMenu();
        newsScreen.checkNewsScreen();
        newsScreen.goToNewsControlPanel();
        controlPanelScreen.checkControlPanelScreen();
        controlPanelScreen.goToCreatingNewsScreen();
        creatingNewsScreen.checkCreatingNewsScreen();
        creatingNewsScreen.fillInTheNewsFieldsForHolidayCategory(NewsDataHelper.getNewsDataForCancelCreation(0));
        creatingNewsScreen.cancelNews();
        controlPanelScreen.checkControlPanelScreen();
    }

    @Test
    public void deleteNews() throws InterruptedException {
        mainScreen.goToNewsScreenFromMainMenu();
        newsScreen.checkNewsScreen();
        newsScreen.goToNewsControlPanel();
        controlPanelScreen.checkControlPanelScreen();
        controlPanelScreen.goToCreatingNewsScreen();
        creatingNewsScreen.checkCreatingNewsScreen();
        creatingNewsScreen.fillInTheNewsFieldsForHolidayCategory(NewsDataHelper.getDataForNewsToDelete(0));
        creatingNewsScreen.saveNews();
        Thread.sleep(1000);
        controlPanelScreen.findCreatedNews(NewsDataHelper.getDataForNewsToDelete(0));
        controlPanelScreen.deleteNews(NewsDataHelper.getDataForNewsToDelete(0));
        Thread.sleep(2000);
        controlPanelScreen.checkNewsDoesNotExist(NewsDataHelper.getDataForNewsToDelete(0));

    }

    @Test
    public void editTitleInNewsAndSave() throws InterruptedException {
        mainScreen.goToNewsScreenFromMainMenu();
        newsScreen.checkNewsScreen();
        newsScreen.goToNewsControlPanel();
        controlPanelScreen.checkControlPanelScreen();
        controlPanelScreen.goToCreatingNewsScreen();
        creatingNewsScreen.checkCreatingNewsScreen();
        creatingNewsScreen.fillInTheNewsFieldsForHolidayCategory(NewsDataHelper.getDataForNewsEditing(0));
        creatingNewsScreen.saveNews();
        Thread.sleep(1000);
        controlPanelScreen.findCreatedNews(NewsDataHelper.getDataForNewsEditing(0));
        controlPanelScreen.goToEditNews(NewsDataHelper.getDataForNewsEditing(0));
        editScreen.checkEditNewsScreen();
        editScreen.editTitle(NewsDataHelper.getNewsDataForCancelCreation(0));
        controlPanelScreen.checkControlPanelScreen();
        controlPanelScreen.findCreatedNews(NewsDataHelper.getNewsDataForCancelCreation(0));
        controlPanelScreen.deleteNews(NewsDataHelper.getNewsDataForCancelCreation(0));
    }

    @Test
    public void createNewsWithPastDate() {
        mainScreen.goToNewsScreenFromMainMenu();
        newsScreen.checkNewsScreen();
        newsScreen.goToNewsControlPanel();
        controlPanelScreen.checkControlPanelScreen();
        controlPanelScreen.goToCreatingNewsScreen();
        creatingNewsScreen.checkCreatingNewsScreen();
        creatingNewsScreen.fillInTheNewsFieldsForHolidayCategory(NewsDataHelper.getDataForNewsWithPastDate(-5));
        creatingNewsScreen.saveNews();
        creatingNewsScreen.wrongDateMessage();
    }

}
