package com.example.simpleloginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SimpleLoginApp.db";
    private static final String TABLE_NAME = "users";
    private static final String COL_USERNAME = "username";
    private static final String COL_PASSWORD = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COL_USERNAME + " TEXT PRIMARY KEY, " +
                COL_PASSWORD + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method untuk menyimpan data pengguna baru
    public boolean registerUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USERNAME, username);
        values.put(COL_PASSWORD, password);

        long result = db.insert(TABLE_NAME, null, values);
        return result != -1; // Return true jika berhasil
    }

    // Method untuk mengecek keberadaan pengguna berdasarkan username dan password
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_USERNAME + "=? AND " + COL_PASSWORD + "=?",
                new String[]{username, password}
        );

        boolean exists = cursor.getCount() > 0; // True jika ada data
        cursor.close();
        return exists;
    }

    // Method untuk mengecek apakah username sudah ada
    public boolean checkUsernameExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_USERNAME + "=?",
                new String[]{username}
        );

        boolean exists = cursor.getCount() > 0; // True jika username sudah ada
        cursor.close();
        return exists;
    }

    // Method untuk menghapus semua data pengguna (opsional)
    public void clearUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
