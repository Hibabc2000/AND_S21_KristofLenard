package via.andS21.KristofLenard.Persistence;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebClient {
    /**
     * @author Kristóf Lénárd
     * @version 1.0
     * This class is responsible for implementing the Retrofit library, enabling connections to the webservice.
     */

    //URL for connection - empty since I don't actually have an API
    private static final String BASE_URL = "http://www.noactualwebsiteexists.com/KristofLenard/";

    private static final Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static final OkHttpClient.Builder httpClient
            = new OkHttpClient.Builder();

    private static final HttpLoggingInterceptor logging
            = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static String jwt = ""; //json web token for authentication

    private static VoterAPI voterAPI = createService(VoterAPI.class);
    private static VoteAPI voteAPI = createService(VoteAPI.class);
    private static NewsAPI newsAPI = createService(NewsAPI.class);
    private static UserAPI userAPI = createService(UserAPI.class);

    public static <S> S createService(Class<S> serviceClass) {
        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging);
            retrofitBuilder.client(httpClient.build());
            retrofit = retrofitBuilder.build();
        }
        return retrofit.create(serviceClass);
    }

    public static <S> S createService(Class<S> serviceClass, final String token) {
        if (token != null) {
            httpClient.interceptors().clear();
            httpClient.addInterceptor(chain -> {
                Request original = chain.request();
                Request.Builder builder1 = original.newBuilder()
                        .header("Authorization", token);
                Request request = builder1.build();
                return chain.proceed(request);
            });
            httpClient.addInterceptor(logging);
            retrofitBuilder.client(httpClient.build());
            retrofit = retrofitBuilder.build();
        }
        return retrofit.create(serviceClass);
    }

    public static VoterAPI getVoterAPI() {
        return voterAPI;
    }

    public static VoteAPI getVoteAPI() {
        return voteAPI;
    }

    public static NewsAPI getNewsAPI() {
        return newsAPI;
    }

    public static UserAPI getUserAPI() {
        return userAPI;
    }

    public static boolean token(String auth) {
        Call<String> tokenCall = getUserAPI().getToken(auth);
        final boolean[] isSuccess = {false};
        tokenCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null) {
                    jwt = response.body();
                }
                voterAPI = createService(VoterAPI.class, jwt);
                userAPI = createService(UserAPI.class, jwt);
                newsAPI = createService(NewsAPI.class, jwt);
                voteAPI = createService(VoteAPI.class, jwt);
                isSuccess[0] = true;
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                isSuccess[0] = false;
            }
        });
        return isSuccess[0];
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        final BitmapDrawable[] drawable = new BitmapDrawable[1];
        Thread thread = new Thread(() -> {
            try {
                Bitmap x;

                HttpURLConnection connection = (HttpURLConnection) new URL(url.trim()).openConnection();
                connection.connect();
                InputStream input = connection.getInputStream();

                x = BitmapFactory.decodeStream(input);
                drawable[0] = new BitmapDrawable(Resources.getSystem(), x);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return drawable[0];
    }
}
