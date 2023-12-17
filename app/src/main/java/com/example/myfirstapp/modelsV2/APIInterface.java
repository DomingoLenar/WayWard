package com.example.myfirstapp.modelsV2;

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
    Call<User> getUserInterface(@Query("username") String username);

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
     * @param columnSearch  which column to search at
     * @param searchKey     search key to be used to search the column for
     * @param newValues     JSON String of new values/value
     * @return              returns boolean value if query succeeded
     */
    @PATCH("{table}?{columnSearch}=.eq{searchKey}")
    Call<User> updateColumnInterface(@Path("table")String table,
                               @Path("columnSearch")String columnSearch,
                               @Path("searchKey")String searchKey,
                               @Body String newValues);


    @GET("travel_plan?title=eq.{title}")
    Call<TravelPlan> getTravelPlanInterface(@Path("title") String title);

    @POST("travel_plan")
    Call<TravelPlan> insertTravelPlanInterface(@Body TravelPlan travelPlan);


    @GET("{table}?{column}=eq.{searchKey}")
    Call<Object> existsInterface(@Path("table")String table,@Path("column")String column,@Path("searchKey")String searchKey);

    @GET("review?author=eq.{author}")
    Call<Review> getReviewInterface(@Path("author") String author);

    @POST("review")
    Call<Review> insertReviewInterface(@Body Review review);

    @POST("contact_details")
    Call<ContactDetails> insertContactDetailsInterface(@Body ContactDetails contactDetails);

    @GET("contact_details?username=eq.{username}")
    Call<ContactDetails> getContactDetailsInterface(@Path("username") String username);

}
