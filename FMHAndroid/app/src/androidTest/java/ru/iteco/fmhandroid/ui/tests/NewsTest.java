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
import ru.iteco.fmhandroid.ui.data.NewsDataHelper;
import ru.iteco.fmhandroid.ui.screen.AuthorizationScreen;
import ru.iteco.fmhandroid.ui.screen.CreatingNewsScreen;
import ru.iteco.fmhandroid.ui.screen.EditingNewsScreen;
import ru.iteco.fmhandroid.ui.screen.MainScreen;
import ru.iteco.fmhandroid.ui.screen.NewsControlPanelScreen;
import ru.iteco.fmhandroid.ui.screen.NewsScreen;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsTest {

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
    NewsControlPanelScreen controlPanelScreen = new NewsControlPanelScreen();
    CreatingNewsScreen creatingNewsScreen = new CreatingNewsScreen();
    EditingNewsScreen editScreen = new EditingNewsScreen();

    @Test
    @Description("Создание новости")
    public void creatingNewsTest() {
        mainScreen.goToNewsScreenFromMainMenu();
        newsScreen.checkNewsScreen();
        newsScreen.goToNewsControlPanel();
        controlPanelScreen.checkControlPanelScreen();
        controlPanelScreen.goToCreatingNewsScreen();
        creatingNewsScreen.checkCreatingNewsScreen();
        creatingNewsScreen.fillInTheNewsFieldsForHolidayCategory(NewsDataHelper.getFirstNewsData(0));
        creatingNewsScreen.saveNews();
        controlPanelScreen.waitNewsToLoad();
        controlPanelScreen.findCreatedNews(NewsDataHelper.getFirstNewsData(0));

    }

    @Test
    @Description("Отмена создания новости")
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
    @Description("Удаление новости")
    public void deleteNews() {
        mainScreen.goToNewsScreenFromMainMenu();
        newsScreen.checkNewsScreen();
        newsScreen.goToNewsControlPanel();
        controlPanelScreen.checkControlPanelScreen();
        controlPanelScreen.goToCreatingNewsScreen();
        creatingNewsScreen.checkCreatingNewsScreen();
        creatingNewsScreen.fillInTheNewsFieldsForHolidayCategory(NewsDataHelper.getDataForNewsToDelete(0));
        creatingNewsScreen.saveNews();
        controlPanelScreen.waitNewsToLoad();
        controlPanelScreen.findCreatedNews(NewsDataHelper.getDataForNewsToDelete(0));
        controlPanelScreen.deleteNews(NewsDataHelper.getDataForNewsToDelete(0));
        controlPanelScreen.waitNewsToLoad();
        controlPanelScreen.checkNewsDoesNotExist(NewsDataHelper.getDataForNewsToDelete(0));

    }

    @Test
    @Description("Редактирование заголовка новости с сохранением")
    public void editTitleInNewsAndSave() {
        mainScreen.goToNewsScreenFromMainMenu();
        newsScreen.checkNewsScreen();
        newsScreen.goToNewsControlPanel();
        controlPanelScreen.checkControlPanelScreen();
        controlPanelScreen.goToCreatingNewsScreen();
        creatingNewsScreen.checkCreatingNewsScreen();
        creatingNewsScreen.fillInTheNewsFieldsForHolidayCategory(NewsDataHelper.getDataForNewsEditing(0));
        creatingNewsScreen.saveNews();
        controlPanelScreen.waitScreenToLoad();
        controlPanelScreen.checkControlPanelScreen();
        controlPanelScreen.waitNewsToLoad();
        controlPanelScreen.findCreatedNews(NewsDataHelper.getDataForNewsEditing(0));
        controlPanelScreen.goToEditNews(NewsDataHelper.getDataForNewsEditing(0));
        editScreen.checkEditNewsScreen();
        editScreen.editTitle(NewsDataHelper.getNewsDataForCancelCreation(0));
        controlPanelScreen.checkControlPanelScreen();
        controlPanelScreen.findCreatedNews(NewsDataHelper.getNewsDataForCancelCreation(0));
        controlPanelScreen.deleteNews(NewsDataHelper.getNewsDataForCancelCreation(0));
    }

    @Test
    @Description("Создание новости с датой из прошлого")
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
