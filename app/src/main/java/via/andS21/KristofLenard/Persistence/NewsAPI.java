package via.andS21.KristofLenard.Persistence;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface NewsAPI {
    @Headers({ "Content-Type: plain/text;charset=UTF-8"})
    @GET("news/titles/{filter}")
    Call<List<String>> getNewsTitles(@Path("filter") String filter);

    @Headers({ "Content-Type: plain/text;charset=UTF-8"})
    @GET("news/imageURLs/{filter}")
    Call<List<String>> getNewsImages(@Path("filter") String filter);
}
