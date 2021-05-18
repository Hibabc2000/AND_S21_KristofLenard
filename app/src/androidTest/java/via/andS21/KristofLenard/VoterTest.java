package via.andS21.KristofLenard;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.lifecycle.Lifecycle;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class VoterTest {

    @Test
    public void checkIfLoaded()
    {
        FragmentScenario<VoterFragment> scenario = FragmentScenario.launch(VoterFragment.class);
        scenario.moveToState(Lifecycle.State.RESUMED);
        scenario.onFragment(voterFragment -> {
            Espresso.onView(withId(R.id.countrySpinner)).check(matches(isEnabled()));
        });
    }
}
