package via.andS21.KristofLenard;

import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SignInTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testIfSignInLoaded()
    {
        ActivityScenario<MainActivity> scenario = activityScenarioRule.getScenario();
        scenario.moveToState(Lifecycle.State.CREATED);
        scenario.onActivity(activity -> {
            boolean b = false;
            for (Fragment f : activity.getSupportFragmentManager().getFragments()) {
                if (f instanceof SignInFragment) {
                    b = true;
                    break;
                }
            }
            assert (b);
        });
    }

    @Test
    public void testIfSignInButtonWorks()
    {
        ActivityScenario<MainActivity> scenario = activityScenarioRule.getScenario();
        scenario.moveToState(Lifecycle.State.CREATED);
        scenario.onActivity(activity -> {
            for (Fragment f : activity.getSupportFragmentManager().getFragments()) {
                if (f instanceof SignInFragment) {
                    Espresso.onView(withId(R.id.buttonSignIn)).check(matches(isClickable()));
                }
            }
        });
    }
}
