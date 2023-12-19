package com.example.myfirstapp.controllers;

import android.widget.Toast;

import com.example.myfirstapp.modelsV2.DataBaseAPI;
import com.example.myfirstapp.modelsV2.User;
import com.example.myfirstapp.views.ProfileSettingsActivity;

import retrofit2.Retrofit;

public class ProfileSettingsController {
    ProfileSettingsActivity profileSettingsActivity;
    DataBaseAPI dataBaseAPI;
    User userModel;
    public ProfileSettingsController(DataBaseAPI dataBaseAPI) {
        this.dataBaseAPI = dataBaseAPI;
    }

    public void updateAccountDetails(String username, String password, String new_username) {
        if (username.equals("") || password.equals("")) {
            Toast.makeText(profileSettingsActivity.getApplicationContext(), "Please enter the required input field", Toast.LENGTH_SHORT).show();
        }
        Retrofit retrofit = dataBaseAPI.getClient();
        String hashedPassword = hashUserPassword(password);
        String updatedUserJson = createJsonForUserUpdate(new_username, hashedPassword);
        dataBaseAPI.updateUserColumn(retrofit, username, updatedUserJson);
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

