package via.andS21.KristofLenard.Persistence;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import via.andS21.KristofLenard.Model.*;

public interface UserAPI
{
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("user")
    Call<User> getUser(@Body String username);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("user")
    Call<User> createUser(@Body User user);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @DELETE("user")
    Call<User> delUser(@Body String username);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @PATCH("user")
    Call<User> updateUser(@Body User user);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("user/token")
    Call<String> getToken(@Header("Authorization") String auth);
}
