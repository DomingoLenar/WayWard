package com.example.myfirstapp.models.UserTasks;

import com.example.myfirstapp.models.User;

public interface UserCallback {
    void onTaskComplete(User result);
    void onTaskComplete(String response);
}
