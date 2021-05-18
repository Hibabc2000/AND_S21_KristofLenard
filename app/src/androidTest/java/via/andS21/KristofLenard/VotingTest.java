package via.andS21.KristofLenard;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.lifecycle.Lifecycle;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class VotingTest {

    @Test
    public void checkIfLoaded()
    {
        FragmentScenario<VotingFragment> scenario = FragmentScenario.launch(VotingFragment.class);
        scenario.moveToState(Lifecycle.State.RESUMED);
        scenario.onFragment(votingFragment -> {
            Espresso.onView(withId(R.id.votingView)).check(matches(isEnabled()));
        });
    }
}
