package via.andS21.KristofLenard.Persistence;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface VoteAPI {

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("vote/candidates/{filter}")
    Call<List<String>> getCandidates(@Path("filter") String filter);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("vote/candidates/images/{filter}")
    Call<List<String>> getCandidateImages(@Path("filter") String filter);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("vote")
    Call<String> vote(@Header("Election") String vote, @Body String votedCandidateName);
}
