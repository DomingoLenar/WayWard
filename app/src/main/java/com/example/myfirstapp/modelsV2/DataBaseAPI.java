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
                            .header("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZhdW9rbXJ6cXBvd3pkaXFxeHhnIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTcwMTU4NzI1NiwiZXhwIjoyMDE3MTYzMjU2fQ.IPP4_Zgysjp--4AwxDwkHC33G-oTW04SQE4OUWnoTQA")
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

    public void getUser(String username, Retrofit retrofit, UserCallback userCallback){
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        apiInterface.getUserInterface(username).enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                userCallback.onUserReceived(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                userCallback.onError("Failed to fetch user");
            }
        });
    }

    public void insertUser(User user,Retrofit retrofit ,UserCallback userCallback){
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        String jsonBody="{\n";
        jsonBody += '"'+"username"+'"'+':'+'"'+user.getUsername()+'"'+",\n";
        jsonBody += '"'+"password"+'"'+':'+'"'+user.getPassword()+'"'+",\n";
        jsonBody += '"'+"first_name"+'"'+':'+'"'+user.getFirst_name()+'"'+",\n";
        jsonBody += '"'+"middle_name"+'"'+':'+'"'+user.getMiddle_name()+'"'+",\n";
        jsonBody += '"'+"last_name"+'"'+':'+'"'+user.getLast_name()+'"'+"\n";
        jsonBody +="}";
        apiInterface.insertUserInterface(jsonBody).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()) {
                    userCallback.onUserReceived(response.body());
                }else{
                    // Handle unsuccessful response
                    if (response.code() == 204) {
                        // HTTP status code 204 means No Content (intentionally empty response)
                        // Handle the case where the response is intentionally empty
                    } else {
                        System.err.println("Unsuccessful response: " + response.code());
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }





    public interface UserCallback {
        void onUserReceived(User user);
        void onUserReceived(String string);

        void onError(String errorMessage);
    }

}
