package com.example.myfirstapp.controllers;

import android.widget.Toast;

import com.example.myfirstapp.modelsV2.DataBaseAPI;
import com.example.myfirstapp.modelsV2.User;
import com.example.myfirstapp.modelsV2.UserRequest;
import com.example.myfirstapp.views.ProfileSettingsActivity;

import retrofit2.Retrofit;

public class ProfileSettingsController {
    ProfileSettingsActivity profileSettingsActivity;
    DataBaseAPI dataBaseAPI;
    User userModel;
    public ProfileSettingsController(ProfileSettingsActivity profileSettingsActivity) {
        this.profileSettingsActivity = profileSettingsActivity;
        this.dataBaseAPI = new DataBaseAPI();
    }

    public void updateAccountDetails(String username, String new_password, String new_username) {
        if (new_username.equals("") || new_password.equals("")) {
            Toast.makeText(profileSettingsActivity.getApplicationContext(), "Please enter the required input field", Toast.LENGTH_SHORT).show();
        }
        userModel = new User(new_username, new_password);
        Retrofit retrofit = dataBaseAPI.getClient();
        String hashedPassword = hashUserPassword(new_password);
        UserRequest userRequestModel = new UserRequest(new_username, hashedPassword);
//        String updatedUserJson = createJsonForUserUpdate(new_username, hashedPassword);

        DataBaseAPI.UserCallback userCallback = new DataBaseAPI.UserCallback() {
            @Override
            public void onUserReceived(User user) {
                Toast.makeText(profileSettingsActivity.getApplicationContext(), "Username and password updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(profileSettingsActivity.getApplicationContext(), "An error occur updating username and password.", Toast.LENGTH_SHORT).show();

            }
        };
        dataBaseAPI.updateUserColumn(retrofit, username, userModel, userCallback);
    }
    private String hashUserPassword(String password) {
        // Get the hashed password using the User model's hashPassword method
        // Assuming you have an instance of the User model
        return userModel.hashPassword(password);
    }
    private String createJsonForUserUpdate(String username, String hashedPassword) {
        // Create a JSON string of the new values to be updated in the user details
        // Modify this method according to your JSON structure
        // For example: {"username": "newUsername", "password": "newPasswordHash"}
        return "{\n\"username\":\"" + username + "\", \"password\":\"" + hashedPassword + "\"\n}";
    }
}

