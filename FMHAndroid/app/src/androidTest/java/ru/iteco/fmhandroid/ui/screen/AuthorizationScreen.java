package ru.iteco.fmhandroid.ui.screen;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.AuthDataHelper;
import ru.iteco.fmhandroid.ui.utils.CustomViewAction;

public class AuthorizationScreen {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(ru.iteco.fmhandroid.ui.AppActivity.class);

    public ViewInteraction loginField = onView(withId(R.id.login_text_input));
    public ViewInteraction passwordField = onView(withId(R.id.password_text_input));
    public ViewInteraction signInButton = onView(withId(R.id.enter_button));
    public ViewInteraction authHeader = onView(withId(R.id.header));
    private View decorView;

    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<AppActivity>() {
            @Override
            public void perform(AppActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
    }

    @Step("Ожидание видимости названия экрана Авторизации")
    public void waitForScreenHeader() {
        onView(isRoot()).perform(CustomViewAction.waitDisplayed(R.id.header, 8000));
    }

    @Step("Авторизация с валидными данными")
    public void login(AuthDataHelper.AuthInfo info) {
        loginField.perform(replaceText(info.getLogin()));
        passwordField.perform(replaceText(info.getPassword()));
        signInButton.perform(click());
    }

    @Step("Проверка экрана Авторизации")
    public void checkAuthScreenHeader() {
        authHeader.check(matches(isDisplayed()));
        authHeader.check(matches(withText("Authorization")));
    }

    @Step("Сообщение об ошибочном логине или пароле")
    public void wrongLoginOrPasswordToastVisible() {
        onView(withText("Wrong login or password")).inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Step("Сообщение о пустом логине или пароле")
    public void emptyLoginOrPasswordToastVisible() {
        onView(withText("Login and password cannot be empty")).inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

}