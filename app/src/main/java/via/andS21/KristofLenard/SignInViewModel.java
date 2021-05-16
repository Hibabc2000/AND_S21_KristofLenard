package via.andS21.KristofLenard;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import via.andS21.KristofLenard.Model.User;
import via.andS21.KristofLenard.Persistence.LocalPersistence;
import via.andS21.KristofLenard.Persistence.WebClient;

public class SignInViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<User> userLiveData;
    private LocalPersistence persistence;

    public SignInViewModel() {
        userLiveData = new MutableLiveData<>();
        persistence = new LocalPersistence();
    }

    public MutableLiveData<User> getUser() {
        return userLiveData;
    }

    public boolean SignIn()
    {
        String s = userLiveData.getValue().getUsername() + ":" + userLiveData.getValue().getPassword();
        return WebClient.token(Objects.requireNonNull(s));
    }

    public void OnSignIn()
    {
        Call<User> userCall = WebClient.getUserAPI().getUser(userLiveData.getValue().getUsername());
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                userLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}