package via.andS21.KristofLenard;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import via.andS21.KristofLenard.Model.VoteType;
import via.andS21.KristofLenard.Model.Voter;
import via.andS21.KristofLenard.Persistence.WebClient;

public class VotingViewModel extends ViewModel {

    private MutableLiveData<Voter> voterLiveData;
    private MutableLiveData<List<String>> candidateNameLiveData;
    private MutableLiveData<List<String>> candidateImageURLsLiveData;
    private MutableLiveData<Integer> selectedCandidate;

    public VotingViewModel()
    {
        Voter voter = new Voter();
        voterLiveData = new MutableLiveData<>();
        voterLiveData.setValue(voter);
        ArrayList<String> candidateName = new ArrayList<>();
        ArrayList<String> candidateImageURLs = new ArrayList<>();
        candidateNameLiveData = new MutableLiveData<>();
        candidateNameLiveData.setValue(candidateName);
        candidateImageURLsLiveData = new MutableLiveData<>();
        candidateImageURLsLiveData.setValue(candidateImageURLs);
        selectedCandidate = new MutableLiveData<>();
        selectedCandidate.setValue(-1);
        getDataFromAPI(""); //no filtering currently - would need filter by election/vote if we had an API
    }

    public VoteType getVoteType()
    {
        return null;
    }

    public MutableLiveData<Voter> getVoter()
    {
        return voterLiveData;
    }

    public MutableLiveData<List<String>> getCandidateNameLiveData() {
        return candidateNameLiveData;
    }

    public MutableLiveData<List<String>> getCandidateImageURLsLiveData() {
        return candidateImageURLsLiveData;
    }

    public MutableLiveData<Integer> getSelectedCandidate() {
        return selectedCandidate;
    }

    public void getDataFromAPI(String filter) {
        Call<List<String>> titleCall = WebClient.getVoteAPI().getCandidates(filter);
        titleCall.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                candidateNameLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });
        Call<List<String>> imageCall = WebClient.getVoteAPI().getCandidateImages(filter);
        imageCall.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                candidateImageURLsLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });
    }

    public void voteForCandidate(String vote, String candidateName) {
        Call<String> voteCall = WebClient.getVoteAPI().vote(vote, candidateName);
        voteCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}