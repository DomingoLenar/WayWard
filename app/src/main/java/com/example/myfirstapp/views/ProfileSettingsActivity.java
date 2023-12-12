package com.example.myfirstapp.views;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myfirstapp.R;

public class ProfileSettingsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilesettings);

        TextView textView_editProfileDetails = findViewById(R.id.textView_editProfileDetails);

        TextView textView_username = findViewById(R.id.textView_username);
        EditText editText_username = findViewById(R.id.editText_username);

        TextView textView_firstName = findViewById(R.id.textView_firstName);
        EditText editText_firstName = findViewById(R.id.editText_firstName);

        TextView textView_middleName = findViewById(R.id.textView_middleName);
        EditText editText_middleName = findViewById(R.id.editText_middleName);

        TextView textView_lastName = findViewById(R.id.textView_lastName);
        EditText editText_lastName = findViewById(R.id.editText_lastName);

        Button button_edit = findViewById(R.id.button_edit);

    }
}
