package via.andS21.KristofLenard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.jetbrains.annotations.NotNull;

import via.andS21.KristofLenard.Model.User;
import via.andS21.KristofLenard.Model.UserSingleton;
import via.andS21.KristofLenard.Model.VoteType;
import via.andS21.KristofLenard.Model.Voter;

public class VotingFragment extends Fragment {

    private RecyclerView recyclerView;
    private VotingViewModel viewModel;

    public VotingFragment() {
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
        viewModel = new VotingViewModel();
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.voting_fragment, container, false);
        VoteType voteType = viewModel.getVoteType(); //not used right now, would be used if we had multiple layouts for voting
        Voter voter = UserSingleton.getUser().getVoter();
        if (voter != null) {
            viewModel.getVoter().setValue(voter);
        }
        recyclerView = view.findViewById(R.id.candidates_rv);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        VotingTypeListAdapter adapter = new VotingTypeListAdapter(voteType);
        recyclerView.setAdapter(adapter);

        Button button = view.findViewById(R.id.voteButton);
        button.setOnClickListener(v -> {

            DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
                if (which == DialogInterface.BUTTON_POSITIVE)
                {
                    adapter.voteForCurrentCandidate();
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            String message = "Are you sure? Currently selected: " + adapter.getCurrent();
            builder.setMessage(message).setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}