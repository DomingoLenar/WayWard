package com.example.myfirstapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.controllers.SignupController;
import com.example.myfirstapp.models.DataBase;
import com.example.myfirstapp.models.User;
import com.example.myfirstapp.models.UserTasks.UserCallback;

import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SignupActivity extends AppCompatActivity implements UserCallback {

    private ValueAnimator valueAnimator;
    private EditText usernameField, passwordField, emailField, fnameField, lNameField, phoneNoField;
    private TextView usernameLabel, passwordLabel, emailLabel, fNameLabel, lNameLabel, phoneNoLabel;
    private Button signUpButton;
    SignupController signupController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        signupController = new SignupController(this);




        initViews();

        valueAnimator = ValueAnimator.ofInt(0, -1000);

        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(
                new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                        int value = (int) animation.getAnimatedValue();

                        usernameField.setTranslationX(value);
                        passwordField.setTranslationX(value);
                        emailField.setTranslationX(value);
                        usernameLabel.setTranslationX(value);
                        passwordLabel.setTranslationX(value);
                        emailLabel.setTranslationX(value);

                        fnameField.setTranslationX(value);
                        lNameField.setTranslationX(value);
                        phoneNoField.setTranslationX(value);
                        fNameLabel.setTranslationX(value + 30);
                        lNameLabel.setTranslationX(value + 45);
                        phoneNoLabel.setTranslationX(value + 45);
                    }
                }
        );

    }

    public void SU_signUp(View view) {
        valueAnimator.start(); // start animation

        signUpButton.setText(R.string.sign_up);

        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    DataBase db = new DataBase();

                    Connection conn = createConnection(db.getUrl(), db.getUser(),db.getPassword());
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery("insert into user_details(user_id,username,password,first_name,middle_name,last_name) values (default,'" + usernameField.getText().toString()+ "','"
                            + passwordField.getText().toString() + "','" + fnameField.getText().toString() + "','" + null + "','" + lNameField.getText().toString()+ "')");
                    rs.next();
                }
                catch(PSQLException psqlException){
                    if("No results were returned by the query.".equals(psqlException.getMessage())){
                        System.out.println("No results from query, query success");

                    }else{
                        psqlException.printStackTrace();

                    }
                }
                catch(SQLException e){
                    e.printStackTrace();

                }
            }
        }).start();



//        signupController.submitAccountDetails(emailField.getText().toString(), usernameField.getText().toString(), passwordField.getText().toString(),
//                fnameField.getText().toString(), lNameField.getText().toString(), phoneNoField.getText().toString());

    }

    public Connection createConnection(String url, String user, String password){
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,user, password);
        }catch(ClassNotFoundException | SQLException e ){
            e.printStackTrace();
        }
        return connection;
    }

    private void initViews() {
        findViewById(R.id.SI_welcomeLabel);

        signUpButton = findViewById(R.id.SI_signInButton);
        usernameField = findViewById(R.id.SI_usernameField);
        passwordField = findViewById(R.id.SI_passwordField);
        emailField= findViewById(R.id.SU_emailField);
        fnameField = findViewById(R.id.SU_firstNameField);
        lNameField = findViewById(R.id.SU_lastnameField);
        phoneNoField = findViewById(R.id.SU_phoneField);

        emailLabel = findViewById(R.id.SU_emailLabel);
        passwordLabel = findViewById(R.id.SI_passwordLabel);
        usernameLabel = findViewById(R.id.SI_usernameLabel);
        fNameLabel = findViewById(R.id.SU_firstNameLabel);
        lNameLabel = findViewById(R.id.SU_lastNameLabel);
        phoneNoLabel = findViewById(R.id.SU_phoneLabel);

    }

    @Override
    public void onTaskComplete(User result) {

    }

    @Override
    public void onTaskComplete(String response) {

    }

    public void performBackgroundTask() {
        new YourAsyncTask().execute();
//        YourAsyncTask asyncTask = new YourAsyncTask();
//        Thread thread = new Thread(asyncTask);
//        thread.start();

    }

    private class YourAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            User user = new User(usernameField.getText().toString(), passwordField.getText().toString(), false);
            user.insertCurrentUser();
            return null;
        }
    }
}