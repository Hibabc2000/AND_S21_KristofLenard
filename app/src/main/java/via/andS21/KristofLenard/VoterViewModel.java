package via.andS21.KristofLenard;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import via.andS21.KristofLenard.Model.User;
import via.andS21.KristofLenard.Model.UserSingleton;
import via.andS21.KristofLenard.Model.Voter;
import via.andS21.KristofLenard.Persistence.LocalPersistence;
import via.andS21.KristofLenard.Persistence.WebClient;

public class VoterViewModel extends ViewModel {

    private MutableLiveData<Voter> voterMutableLiveData;
    private LocalPersistence persistence;

    public VoterViewModel()
    {
        Voter voter = new Voter();
        voterMutableLiveData = new MutableLiveData<>();
        voterMutableLiveData.setValue(voter);
        persistence = new LocalPersistence();
        setVoterToStored();
    }

    public MutableLiveData<Voter> getVoterMutableLiveData() {
        return voterMutableLiveData;
    }

    public void persistVoter()
    {
        Call<Boolean> eligibilityCall = WebClient.getVoterAPI().checkEligibility(voterMutableLiveData.getValue());
        eligibilityCall.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.body() != null) {
                    if (response.body()) {
                        UserSingleton.setVoter(voterMutableLiveData.getValue());
                        Call<User> updateCall = WebClient.getUserAPI().updateUser(UserSingleton.getUser());
                        updateCall.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {

                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

    public void setVoterToStored()
    {
        Call<User> userCall = WebClient.getUserAPI().getUser(UserSingleton.getUser().getUsername());
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body() != null) {
                    voterMutableLiveData.setValue(response.body().getVoter());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}