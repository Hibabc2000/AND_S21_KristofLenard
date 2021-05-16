package via.andS21.KristofLenard.Persistence;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface NewsAPI {
    @Headers({ "Content-Type: plain/text;charset=UTF-8"})
    @GET("news/titles")
    Call<List<String>> getNewsTitles(@Body String filter);

    @Headers({ "Content-Type: plain/text;charset=UTF-8"})
    @GET("news/imageURLs")
    Call<List<String>> getNewsImages(@Body String filter);
}
