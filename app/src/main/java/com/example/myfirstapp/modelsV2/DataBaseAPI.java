package com.example.myfirstapp.modelsV2;

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

    /**
     * Fetches the object of username in the database using the username as a search key
     * @param username      username to look for
     * @param retrofit      object of retrofit that can be created using createClient()
     * @param userCallback  object of UserCallback to to return the query from the method
     */
    public void getUser(String username, Retrofit retrofit, UserCallback userCallback){
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        apiInterface.getUserInterface(username).enqueue(new Callback<User>() {

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
                t.printStackTrace();
                userCallback.onError("Failed to fetch user");
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
                t.printStackTrace();
            }
        };

        apiInterface.insertUserInterface(user).enqueue(callback);

    }

    /**
     *
     * @param retrofit  Object of retrofit, can be created using createClient() method
     * @param column    Column of where to search at
     * @param searchKey search key to be used to search the column for
     * @param newValues JSON string of the new values
     */
    public void updateUserColumn(Retrofit retrofit, String column, String searchKey, String newValues){
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

            }
        };
        apiInterface.updateColumnInterface("user_details",column,searchKey,newValues);
    }





    public interface UserCallback {
        void onUserReceived(User user);
        void onUserReceived(String string);

        void onError(String errorMessage);
    }

}
