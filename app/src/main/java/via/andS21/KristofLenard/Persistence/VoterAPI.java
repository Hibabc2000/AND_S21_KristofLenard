package via.andS21.KristofLenard.Persistence;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import via.andS21.KristofLenard.Model.Voter;

public interface VoterAPI
{
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("voter/eligible")
    Call<Boolean> checkEligibility(@Body Voter voter);
}
