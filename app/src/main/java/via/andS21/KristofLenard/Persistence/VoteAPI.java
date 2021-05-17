package via.andS21.KristofLenard.Persistence;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface VoteAPI {

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("vote/candidates")
    Call<List<String>> getCandidates(@Body String filter);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("vote/candidates/images")
    Call<List<String>> getCandidateImages(@Body String filter);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("vote")
    Call<String> vote(@Header("Election") String vote, @Body String votedCandidateName);
}
