package via.andS21.KristofLenard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private Fragment newsFragment;
    private Fragment votingFragment;
    private Fragment voterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsFragment = new NewsFragment();
        votingFragment = new VotingFragment();
        voterFragment = new VoterFragment();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationListener);
        bottomNavigationView.setVisibility(View.INVISIBLE); //To not see navigation bar on Sign In menu
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_view, new SignInFragment()).commit();
    }

    //Bottom Navigation Bar Listener
    private final BottomNavigationView.OnNavigationItemSelectedListener navigationListener = item -> {
        Fragment selectedFragment = null;
        int itemId = item.getItemId();

        if (itemId == R.id.item_news) {
            selectedFragment = newsFragment;
        } else if (itemId == R.id.item_voting) {
            selectedFragment = votingFragment;
        } else if (itemId == R.id.item_voter) {
            selectedFragment = voterFragment;
        }

        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_view, selectedFragment).commit();
        }
        return true;
    };

    public Fragment getNewsFragment()
    {
        return newsFragment;
    }
}