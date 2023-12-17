package com.example.myfirstapp.modelsV2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {
    Object objectReturn = null;

    /**
     * @param username username of the user to get
     * @return return an object of User
     */
    @GET("user_details?username=eq.{username}&select=*")
    Call<User> getUserInterface(@Path("username") String username);

    /**
     *
     * @param user JSON format of user to insert
     * @return
     */
    @POST("user_details")
    Call<String> insertUserInterface(@Body String user);

    /**
     *
     * @param table     table where to update
     * @param columnSearch  which column to search at
     * @param searchKey     search key to be used to search the column for
     * @param newValues     JSON String of new values/value
     * @return              returns boolean value if query succeeded
     */
    @PATCH("{table}?{columnSearch}=.eq{searchKey}")
    Call<Boolean> updateColumnInterface(@Path("table")String table,
                               @Path("columnSearch")String columnSearch,
                               @Path("searchKey")String searchKey,
                               @Body String newValues);


    @GET("{table}?{column}=eq.{searchKey}")
    Call<Boolean> existsInterface(@Path("table")String table,@Path("column")String column,@Path("searchKey")String searchKey);

}
