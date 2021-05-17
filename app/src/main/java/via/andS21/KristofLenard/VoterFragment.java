package via.andS21.KristofLenard;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import via.andS21.KristofLenard.Model.User;
import via.andS21.KristofLenard.Model.UserSingleton;
import via.andS21.KristofLenard.Model.Voter;

public class VoterFragment extends Fragment {

    private VoterViewModel viewModel;

    public VoterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Voter.
     */
    public static VoterFragment newInstance() {
        VoterFragment fragment = new VoterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new VoterViewModel();
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.voter_fragment, container, false);

        Voter voter = new Voter();

        Spinner countrySpinner = view.findViewById(R.id.countrySpinner);
        ArrayAdapter<CharSequence> countriesAdapter = ArrayAdapter.createFromResource(getContext(), R.array.countries, android.R.layout.simple_spinner_dropdown_item);
        countriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(countriesAdapter);

        Spinner authMethodSpinner = view.findViewById(R.id.authMethodSpinner);
        ArrayAdapter<CharSequence> authMethodAdapter = ArrayAdapter.createFromResource(getContext(), R.array.authMethods, android.R.layout.simple_spinner_dropdown_item);
        authMethodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        authMethodSpinner.setAdapter(authMethodAdapter);

        Button button = view.findViewById(R.id.confirmVoter);
        button.setOnClickListener(v -> {
            EditText name = view.findViewById(R.id.editTextName);
            voter.setFullName(name.getText().toString());
            EditText date = view.findViewById(R.id.editTextDateOfBirth);
            voter.setDateOfBirth(date.getText().toString());
            voter.setCountry(countrySpinner.getSelectedItem().toString());
            voter.setAuthMethod(authMethodSpinner.getSelectedItem().toString());
            EditText authCode = view.findViewById(R.id.editTextAuthCode);
            voter.setAuthCode(authCode.getText().toString());
            viewModel.getVoterMutableLiveData().setValue(voter);
            UserSingleton.getUser().setVoter(viewModel.getVoterMutableLiveData().getValue());
            viewModel.persistVoter();
        });

        viewModel.getVoterMutableLiveData().observe(getViewLifecycleOwner(), voter1 -> {
            EditText name = view.findViewById(R.id.editTextName);
            name.setText(voter1.getFullName());

            EditText date = view.findViewById(R.id.editTextDateOfBirth);
            date.setText(voter1.getDateOfBirth());

            ArrayList<String> countries = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.countries)));

            ArrayList<String> authMethods = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.authMethods)));

            if(!countries.contains(voter1.getCountry()))
            {
                countries.add(voter1.getCountry());
                countriesAdapter.notifyDataSetChanged();
            }
            countrySpinner.setSelection(countries.indexOf(voter1.getCountry()));

            if(!authMethods.contains(voter1.getAuthMethod()))
            {
                authMethods.add(voter1.getCountry());
                authMethodAdapter.notifyDataSetChanged();
            }
            authMethodSpinner.setSelection(countries.indexOf(voter1.getCountry()));

            EditText authCode = view.findViewById(R.id.editTextAuthCode);
            voter1.setAuthCode(authCode.getText().toString());
        });

        EditText date = view.findViewById(R.id.editTextDateOfBirth);
        date.setOnClickListener(v -> {
            DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view1, int year, int month, int dayOfMonth) {
                    Calendar calendar = Calendar.getInstance();
                    String myFormat = "dd/mm/yyyy";
                    SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, getResources().getConfiguration().getLocales().get(0));
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    date.setText(dateFormat.format(calendar.getTime()));
                }
            }, 1970, 1, 1);
            dialog.show();
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
