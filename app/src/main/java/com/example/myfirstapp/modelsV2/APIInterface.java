package com.example.myfirstapp.modelsV2;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
    Object objectReturn = null;

    /**
     * @param username username of the user to get
     * @return return an object of User
     */
    @GET("user_details")
    Call<JsonElement> getUserInterface(@Query("username") String username);

    /**
     *
     * @param user JSON format of user to insert
     * @return
     */
    @POST("user_details")
    Call<User> insertUserInterface(@Body User user);

    /**
     *
     * @param table     table where to update
     * @param username     search key to be used to search the column for
     * @param newValues     JSON String of new values/value
     * @return              returns boolean value if query succeeded
     */
    @PATCH("{table}")
    Call<JsonElement> updateUserColumnInterface(@Path("table")String table,
                               @Query("username") String username,
                               @Body String newValues);


    @PATCH("contact_details")
    Call<ContactDetails> updateContactDetailsInterface(@Query("username") String username, @Body String newValues);

    @GET("travel_plan")
    Call<TravelPlan> getTravelPlanInterface(@Query("title") String title);

    @POST("travel_plan")
    Call<TravelPlan> insertTravelPlanInterface(@Body TravelPlan travelPlan);

    @Deprecated
    @GET("{table}?{column}=eq.{searchKey}")
    Call<Object> existsInterface(@Path("table")String table,@Path("column")String column,@Path("searchKey")String searchKey);

    @GET("review")
    Call<Review> getReviewInterface(@Query("author") String author);

    @POST("review")
    Call<Review> insertReviewInterface(@Body Review review);

    @POST("contact_details")
    Call<ContactDetails> insertContactDetailsInterface(@Body ContactDetails contactDetails);

    @GET("contact_details")
    Call<ContactDetails> getContactDetailsInterface(@Query("username") String username);

//    @GET("travel_plan")
//    Call<TravelPlan[]> getListOfTravelPlanInterface(@Query("title") String title);

    @GET("travel_plan")
    Call<JsonElement> getListOfTravelPlanInterface(@Query("author") String author);
}
