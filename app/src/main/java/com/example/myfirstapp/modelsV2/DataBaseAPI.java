package com.example.myfirstapp.modelsV2;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.HEAD;
import retrofit2.http.Headers;
import retrofit2.http.Query;

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
        Callback<User> callback = new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    Log.e("getUser", "Unsuccessful response: " + response.code());
                    System.out.println(response.code());
                }else{
                    userCallback.onUserReceived(response.body());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("getUser",t.getMessage());
                userCallback.onError("Failed to fetch user");
                t.printStackTrace();
            }
        };
        apiInterface.getUserInterface(user.getUsername()).enqueue(callback);
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
        apiInterface.updateUserColumnInterface("user_details",username,newValues).enqueue(callback);
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

        apiInterface.getTravelPlanInterface(title).enqueue(callback);
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

        apiInterface.getReviewInterface(author).enqueue(callback);
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

        apiInterface.getContactDetailsInterface(username).enqueue(callback);
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
