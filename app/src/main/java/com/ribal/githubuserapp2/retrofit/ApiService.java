package com.ribal.githubuserapp2.retrofit;



import com.ribal.githubuserapp2.model.DetailUser;
import com.ribal.githubuserapp2.model.Follower;
import com.ribal.githubuserapp2.model.Following;
import com.ribal.githubuserapp2.model.RequestGit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    Call<RequestGit> getSearchUser(
            @Query("q") String username
    );

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    Call<DetailUser> getDetailUser(
            @Path("username") String username
    );

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    //<list> soalnya modelnya dibungkus array karena data ne banyak
    Call<List<Follower>> getFollowerUser(
            @Path("username") String username
    );

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    Call<List<Following>> getFollowingUser(
            @Path("username") String username
    );
}
