package com.example.myfirstapp.modelsV2;

import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Handler;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Headers;


public class DataBaseAPI {
    private Retrofit retrofit = null;

    @Headers("")
    public Retrofit getClient(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(chain ->{
                    Request originalRequest = chain.request();
                    Request newRequest = originalRequest.newBuilder()
                            .header("apikey","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZhdW9rbXJ6cXBvd3pkaXFxeHhnIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTcwMTU4NzI1NiwiZXhwIjoyMDE3MTYzMjU2fQ.IPP4_Zgysjp--4AwxDwkHC33G-oTW04SQE4OUWnoTQA")
                            .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZhdW9rbXJ6cXBvd3pkaXFxeHhnIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTcwMTU4NzI1NiwiZXhwIjoyMDE3MTYzMjU2fQ.IPP4_Zgysjp--4AwxDwkHC33G-oTW04SQE4OUWnoTQA")
                            .method(originalRequest.method(), originalRequest.body())
                            .build();
                    return chain.proceed(newRequest);
                })
                .build();


        retrofit = new Retrofit.Builder()
                .baseUrl("https://fauokmrzqpowzdiqqxxg.supabase.co/rest/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();



        return retrofit;
    }
    //START USER OPERATIONS

    /**
     * Fetches the object of username in the database using the username as a search key
     * @param       user to look for
     * @param retrofit      object of retrofit that can be created using createClient()
     * @param userCallback  object of UserCallback to return the query from the method
     */
    public void getUser(User user, Retrofit retrofit, UserCallback userCallback){
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        apiInterface.getUserInterface("eq." + user.getUsername()).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (!response.isSuccessful()) {
                    Log.e("getUser", "Unsuccessful response: " + response.code());
                    userCallback.onError("Failed to fetch user");
                    return;
                }

                Gson gson = new Gson();
                JsonElement responseBody = response.body();
                if (responseBody.isJsonObject()) {
                    User user = gson.fromJson(responseBody, User.class);
                    userCallback.onUserReceived(user);
                } else if (responseBody.isJsonArray()) {
                    Type userListType = new TypeToken<List<User>>(){}.getType();
                    List<User> userList = gson.fromJson(responseBody, userListType);
                    if (!userList.isEmpty()) {
                        userCallback.onUserReceived(userList.get(0)); // returns an object of User model to the interface
                    }
                } else {
                    Log.e("getUser", "Unexpected JSON structure");
                    userCallback.onError("Unexpected JSON structure");
                }

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e("Unable to fetch user", t.getMessage());
                userCallback.onError("Failed to fetch user");
                t.printStackTrace();
            }
        });

    }

    /**
     *
     * @param user          object of user to be inserted into the database
     * @param retrofit      object of retrofit to interact with the database, can be created using
     *                      createClient()
     * @param userCallback  object of UserCallback to get the return of the query
     */
    public void insertUser(User user,Retrofit retrofit ,UserCallback userCallback){
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        Callback<User> callback = new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    System.out.println(response.code());
                }else{
                    userCallback.onUserReceived(response.body());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                userCallback.onError(t.getMessage());
                t.printStackTrace();
            }
        };

        apiInterface.insertUserInterface(user).enqueue(callback);

    }

    /**
     *
     * @param retrofit  Object of retrofit, can be created using createClient() method
     * @param username search key to be used to search the column for
     * @param newValues JSON string of the new values
     */
    public void updateUserColumn(Retrofit retrofit, String username, String newValues){
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        Callback<User> callback = new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    System.out.println(response.code());
                }else{
                    response.body();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        };
        apiInterface.updateUserColumnInterface("user_details","eq."+username,newValues).enqueue(callback);
    }

    //END USER OPERATIONS

    //START TRAVEL PLAN OPERATIONS

    public void getTravelPlan(Retrofit retrofit, String title, TravelPlanCallback travelPlanCallback){
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        Callback<TravelPlan> callback = new Callback<TravelPlan>() {
            @Override
            public void onResponse(Call<TravelPlan> call, Response<TravelPlan> response) {
                if(!response.isSuccessful()){
                    System.out.println(response.code());
                }else{
                    travelPlanCallback.onReceived(response.body());
                }
            }

            @Override
            public void onFailure(Call<TravelPlan> call, Throwable t) {
                travelPlanCallback.onError(t.getMessage());
                t.printStackTrace();
            }
        };

        apiInterface.getTravelPlanInterface("eq."+title).enqueue(callback);
    }

    /**
     * Returns a list of TravelPlan to the callback given to it
     * @param retrofit              Object of Retrofit that can be created using createClient()
     * @param titleLetter           Title of travel plans to search for, format {P*}
     * @param travelPlanCallback    object of TravelPlanListCallback where to return the data to
     */
    public void getListOfTravelPlan(Retrofit retrofit, String titleLetter, TravelPlanListCallback travelPlanCallback){
        APIInterface apiInterface = retrofit.create(APIInterface.class);

        Callback<TravelPlan[]> callback = new Callback<TravelPlan[]>() {
            @Override
            public void onResponse(Call<TravelPlan[]> call, Response<TravelPlan[]> response) {
                if(!response.isSuccessful()){
                    Log.e("Callback Response: ", String.valueOf(response.code()));
                }else{
                    travelPlanCallback.onReceived(response.body());
                }
            }

            @Override
            public void onFailure(Call<TravelPlan[]> call, Throwable t) {
                travelPlanCallback.onError(t.getMessage());
            }
        };

        apiInterface.getListOfTravelPlanInterface("like(any)."+titleLetter).enqueue(callback);
    }

    public void insertTravelPlan(Retrofit retrofit, TravelPlan travelPlan, TravelPlanCallback travelPlanCallback){
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        Callback<TravelPlan> callback = new Callback<TravelPlan>() {
            @Override
            public void onResponse(Call<TravelPlan> call, Response<TravelPlan> response) {
                if(!response.isSuccessful()){
                    System.out.println(response.code());
                }else{
                    travelPlanCallback.onReceived(response.body());
                }
            }

            @Override
            public void onFailure(Call<TravelPlan> call, Throwable t) {
                travelPlanCallback.onError(t.getMessage());
            }
        };
        apiInterface.insertTravelPlanInterface(travelPlan).enqueue(callback);
    }

    //END TRAVEL PLAN OPERATIONS

    //START REVIEW OPERATIONS

    public void getReview(Retrofit retrofit,String author, ReviewCallback reviewCallback){
        APIInterface apiInterface = retrofit.create(APIInterface.class);

        Callback<Review> callback = new Callback<Review>() {
            @Override
            public void onResponse(Call<Review> call, Response<Review> response) {
                if(!response.isSuccessful()){
                    System.out.println(response.code());
                }else{
                    reviewCallback.onReceived(response.body());
                }
            }

            @Override
            public void onFailure(Call<Review> call, Throwable t) {
                reviewCallback.onError(t.getMessage());
            }
        };

        apiInterface.getReviewInterface("eq."+author).enqueue(callback);
    }

    public void insertReview(Retrofit retrofit, Review review, ReviewCallback reviewCallback){
        APIInterface apiInterface = retrofit.create(APIInterface.class);

        Callback<Review> callback = new Callback<Review>() {
            @Override
            public void onResponse(Call<Review> call, Response<Review> response) {
                if(!response.isSuccessful()){
                    System.out.println(response.code());
                }else{
                    reviewCallback.onReceived(response.body());
                }
            }

            @Override
            public void onFailure(Call<Review> call, Throwable t) {
                reviewCallback.onError(t.getMessage());
            }
        };
        apiInterface.insertReviewInterface(review).enqueue(callback);
    }

    //END REVIEW OPERATIONS

    //START CONTACT DETAILS OPERATIONS

    public void getContactDetails(Retrofit retrofit, String username, ContactDetailsCallback contactDetailsCallback){
        APIInterface apiInterface = retrofit.create(APIInterface.class);

        Callback<ContactDetails> callback = new Callback<ContactDetails>() {
            @Override
            public void onResponse(Call<ContactDetails> call, Response<ContactDetails> response) {
                if(!response.isSuccessful()){
                    System.out.println(response.code());
                }else{
                    contactDetailsCallback.onReceived(response.body());
                }
            }

            @Override
            public void onFailure(Call<ContactDetails> call, Throwable t) {
                contactDetailsCallback.onError(t.getMessage());
            }
        };

        apiInterface.getContactDetailsInterface("eq."+username).enqueue(callback);
    }

    /**
     * Method that updates contact details and returns the new object of contact details to given callback
     * @param retrofit                  Object of retrofit that can be created using createClient()
     * @param username                  username of the Contact details to update
     * @param newValues                 JSON String of the new values
     * @param contactDetailsCallback    Callback where to give contact details
     */
    public void updateContactDetails(Retrofit retrofit, String username, String newValues, ContactDetailsCallback contactDetailsCallback){
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        Callback<ContactDetails> callback = new Callback<ContactDetails>() {
            @Override
            public void onResponse(Call<ContactDetails> call, Response<ContactDetails> response) {
                if(!response.isSuccessful()){
                    Log.e("Response Code: ", String.valueOf(response.code()));
                }else{
                    contactDetailsCallback.onReceived(response.body());
                }
            }

            @Override
            public void onFailure(Call<ContactDetails> call, Throwable t) {
                Log.e("Update CD Query",t.getMessage(),t);
                contactDetailsCallback.onError(t.getMessage());
            }
        };

        apiInterface.updateContactDetailsInterface("eq."+username, newValues).enqueue(callback);
    }

    public void insertContactDetails(Retrofit retrofit, ContactDetails contactDetails, ContactDetailsCallback contactDetailsCallback){
        APIInterface apiInterface = retrofit.create(APIInterface.class);

        Callback<ContactDetails> callback = new Callback<ContactDetails>() {
            @Override
            public void onResponse(Call<ContactDetails> call, Response<ContactDetails> response) {
                if(!response.isSuccessful()){
                    System.out.println(response.code());
                }else{
                    contactDetailsCallback.onReceived(response.body());
                }
            }

            @Override
            public void onFailure(Call<ContactDetails> call, Throwable t) {
                contactDetailsCallback.onError(t.getMessage());
            }
        };

        apiInterface.insertContactDetailsInterface(contactDetails).enqueue(callback);
    }

    //END CONTACT DETAILS OPERATIONS

    public interface TravelPlanListCallback{
        void onReceived(TravelPlan[] travelPlans);
        void onError(String errorMessage);
    }
    public interface ContactDetailsCallback{
        void onReceived(ContactDetails contactDetails);
        void onError(String errorMessage);
    }
    public interface ReviewCallback{
        void onReceived(Review review);
        void onError(String errorMessage);
    }

    public interface TravelPlanCallback{
        void onReceived(TravelPlan travelPlan);
        void onError(String errorMessage);
    }

    public interface UserCallback {
        void onUserReceived(User user);

        void onError(String errorMessage);
    }

}
