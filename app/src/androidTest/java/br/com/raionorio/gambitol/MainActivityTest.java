package br.com.raionorio.gambitol;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void selectsAndMovesAPieceThroughTheBoard() {
        onView(withContentDescription(containsString("e2, peão das Brancas")))
                .perform(click());
        onView(withContentDescription(containsString("e4, vazia, movimento disponível")))
                .perform(click());

        onView(withText("Vez das Pretas"))
                .check(matches(withText("Vez das Pretas")));
    }
}
