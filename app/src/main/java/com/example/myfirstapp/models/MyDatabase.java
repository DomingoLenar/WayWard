package com.example.myfirstapp.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import androidx.annotation.Nullable;


public class MyDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "users.db";
    private static final String TABLE_NAME = "users";
    private static final String  COLUMN_ID = "user_id";
    private static final String  COLUMN_EMAIL = "user_email";
    private static final String  COLUMN_USERNAME = "user_username";
    private static final String  COLUMN_PASSWORD = "user_password";
    private static final String  COLUMN_FIRSTNAME = "user_firstname";
    private static final String  COLUMN_LASTNAME = "user_lastname";
    private static final String  COLUMN_PHONE = "user_phone";
    public MyDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_FIRSTNAME + " TEXT, " +
                COLUMN_LASTNAME + " TEXT, " +
                COLUMN_PHONE + " TEXT" +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

//    public boolean createAccount(User user_model) {
//        boolean exist = checkEmail(user_model.getEmail());
//        if (exist) {
//            return false;
//        }
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues cv = new ContentValues();
//        cv.put(COLUMN_EMAIL, user_model.getEmail());
//        cv.put(COLUMN_USERNAME, user_model.getUsername());
//        cv.put(COLUMN_PASSWORD, user_model.getPassword());
//        cv.put(COLUMN_FIRSTNAME, user_model.getFirstName());
//        cv.put(COLUMN_LASTNAME, user_model.getLastName());
//
//        cv.put(COLUMN_PHONE, user_model.getUsername());
//
//        if (db.insert(TABLE_NAME, null, cv) == -1) {
//            return false;
//        }
//
//        return true;
//    }

    /**
     *
     * @param email
     * @return false if email does not exist in database, otherwise true
     */
    public boolean checkEmail(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        try (Cursor cursor = MyDatabase.rawQuery("SELECT * FROM users WHERE user_email = ?", new String[]{email})) {
            if (cursor.getCount() > 0) {
                return true;
            } else {
                return false;
            }
        }
    }
    public boolean checkUsernamePassword(User userModel){
        String username = userModel.getUsername();
        String password = userModel.getPassword();

        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        try (Cursor cursor = MyDatabase.rawQuery("SELECT * FROM users WHERE user_username = ? AND user_password = ?", new String[]{username, password})) {
            if (cursor.getCount() > 0) {
                return true;
            } else {
                return false;
            }
        }
    }
}
