package via.andS21.KristofLenard;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import via.andS21.KristofLenard.Model.User;
import via.andS21.KristofLenard.Model.UserSingleton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignInFragment extends Fragment {

    private SignInViewModel signInViewModel;
    private SharedPreferences preferences;

    public SignInFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SignIn.
     */
    public static SignInFragment newInstance() {
        SignInFragment fragment = new SignInFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signInViewModel = new SignInViewModel();
        preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.sign_in_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.buttonSignIn);
        EditText username = view.findViewById(R.id.editTextTextPersonName);
        EditText password = view.findViewById(R.id.editTextTextPersonName2);
        User temp = new User();
        button.setOnClickListener((View.OnClickListener) v -> {
            temp.setUsername(username.getText().toString());
            temp.setPassword(password.getText().toString());
            signInViewModel.getUser().setValue(temp);
            boolean b = signInViewModel.SignIn();
            if (b) {
                signInViewModel.OnSignIn();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username", temp.getUsername());
                editor.putString("password", temp.getPassword());
                editor.apply();
                BottomNavigationView bottomNavigationView = (BottomNavigationView)
                        view.getRootView().findViewById(R.id.bottomNavigationView);
                bottomNavigationView.setVisibility(View.VISIBLE); //Turns on Navigation view
                UserSingleton.getInstance(temp);
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container_view, new NewsFragment()).commit();
            }
        });
    }
}