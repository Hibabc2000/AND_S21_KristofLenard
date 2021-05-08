package via.andS21.KristofLenard.Persistence;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebClient {
    /**
     * @author Kristóf Lénárd
     * @version 1.0
     * This class is responsible for implementing the Retrofit library, enabling connections to the webservice.
     */

    //URL for connection - empty since I don't actually have an API
    private static final String BASE_URL = "";

    private static final Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static final OkHttpClient.Builder httpClient
            = new OkHttpClient.Builder();

    private static final HttpLoggingInterceptor logging
            = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static final String jwt = ""; //json web token for authentication

    private static final VoterAPI voterAPI = createService(VoterAPI.class, jwt);
    private static final VoteAPI voteAPI = createService(VoteAPI.class, jwt);
    private static final NewsAPI newsAPI = createService(NewsAPI.class, jwt);
    private static final UserAPI userAPI = createService(UserAPI.class, jwt);

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
            httpClient.addInterceptor( chain -> {
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

    public static VoterAPI getVoterAPI()
    {
        return voterAPI;
    }
    public static VoteAPI getVoteAPI()
    {
        return voteAPI;
    }
    public static NewsAPI getNewsAPI()
    {
        return newsAPI;
    }
    public static UserAPI getUserAPI()
    {
        return userAPI;
    }
}
