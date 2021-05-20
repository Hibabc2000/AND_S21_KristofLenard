package via.andS21.KristofLenard;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import via.andS21.KristofLenard.Model.VoteType;
import via.andS21.KristofLenard.Persistence.WebClient;

public class VotingTypeListAdapter extends RecyclerView.Adapter<VotingTypeListAdapter.VoteViewHolder> {

    private VotingViewModel viewModel;
    private int selectedPosition = -1; // no default

    public VotingTypeListAdapter(VoteType voteType, VotingViewModel model)
    {
        viewModel = model;
        //voteType not used, would be a switch to decide which sub-layout to call if we had more
    }

    @NonNull
    @Override
    public VotingTypeListAdapter.VoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.voting_type_list_element, parent, false);
        return new VotingTypeListAdapter.VoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VotingTypeListAdapter.VoteViewHolder holder, int position) {
        holder.checkBox.setChecked(position == selectedPosition);
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            selectedPosition = holder.getAdapterPosition();
            notifyDataSetChanged();
        });
        if(viewModel.getCandidateImageURLsLiveData().getValue().size() >= position) {
            Drawable d = WebClient.LoadImageFromWebOperations(viewModel.getCandidateImageURLsLiveData().getValue().get(position));
            holder.candidateImage.setImageDrawable(d);
        }
        if(viewModel.getCandidateNameLiveData().getValue().size() >= position) {
            holder.candidateName.setText(viewModel.getCandidateNameLiveData().getValue().get(position));
        }
    }

    @Override
    public int getItemCount() {
        return viewModel.getCandidateNameLiveData().getValue().size();
    }

    public String getCurrent()
    {
        return viewModel.getCandidateNameLiveData().getValue().get(selectedPosition);
    }

    public void voteForCurrentCandidate()
    {
        viewModel.voteForCandidate("", viewModel.getCandidateNameLiveData().getValue().get(selectedPosition));
    }

    public static class VoteViewHolder extends RecyclerView.ViewHolder {
        private final TextView candidateName;
        private final ImageView candidateImage;
        private final CheckBox checkBox;

        VoteViewHolder(View itemView){
            super(itemView);
            candidateName = itemView.findViewById(R.id.candidateName);
            candidateImage = itemView.findViewById(R.id.candidateImage);
            checkBox = itemView.findViewById(R.id.voteCheckbox);
        }
    }
}
