package via.andS21.KristofLenard.Persistence;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import via.andS21.KristofLenard.Model.*;

public interface UserAPI
{
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("user/{username}")
    Call<User> getUser(@Path("username")String username);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("user")
    Call<User> createUser(@Body User user);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @PATCH("user")
    Call<User> updateUser(@Body User user);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("user/token")
    Call<String> getToken(@Header("Authorization") String auth);
}
