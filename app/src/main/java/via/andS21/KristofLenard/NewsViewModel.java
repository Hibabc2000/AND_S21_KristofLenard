package via.andS21.KristofLenard;

import android.content.Intent;
import android.net.Uri;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import via.andS21.KristofLenard.Model.User;
import via.andS21.KristofLenard.Persistence.LocalPersistence;
import via.andS21.KristofLenard.Persistence.WebClient;

public class NewsViewModel extends ViewModel {

    private MutableLiveData<User> userLiveData;
    private MutableLiveData<List<String>> newsTitle;
    private MutableLiveData<List<String>> newsImageURLs;
    private LocalPersistence persistence;

    public NewsViewModel() {
        userLiveData = new MutableLiveData<>();
        persistence = new LocalPersistence();
        List<String> newsTitleList = new ArrayList<String>();
        newsTitle = new MutableLiveData<>();
        newsTitle.setValue(newsTitleList);
        List<String> newsImageURLList = new ArrayList<String>();
        newsImageURLs = new MutableLiveData<>();
        newsImageURLs.setValue(newsImageURLList);
    }

    public MutableLiveData<User> getUser() {
        return userLiveData;
    }

    public MutableLiveData<List<String>> getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(MutableLiveData<List<String>> newsTitle) {
        this.newsTitle = newsTitle;
    }

    public MutableLiveData<List<String>> getNewsImageURLs() {
        return newsImageURLs;
    }

    public void setNewsImageURLs(MutableLiveData<List<String>> newsImageURLs) {
        this.newsImageURLs = newsImageURLs;
    }

    public void getDataFromAPI(String filter) {
        Call<List<String>> titleCall = WebClient.getNewsAPI().getNewsTitles(filter);
        titleCall.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                newsTitle.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });
        Call<List<String>> imageCall = WebClient.getNewsAPI().getNewsImages(filter);
        imageCall.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                newsImageURLs.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });
    }
}