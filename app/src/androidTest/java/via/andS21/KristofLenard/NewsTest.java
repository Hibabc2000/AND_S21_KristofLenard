package via.andS21.KristofLenard;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.lifecycle.Lifecycle;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class NewsTest {

    @Test
    public void checkIfLoaded()
    {
        FragmentScenario<NewsFragment> scenario = FragmentScenario.launch(NewsFragment.class);
        scenario.moveToState(Lifecycle.State.RESUMED);
        scenario.onFragment(newsFragment -> {
            Espresso.onView(withId(R.id.news_rv)).check(matches(isEnabled()));
        });
    }
}
