package com.cste.nstu.suvro.telemedicine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;



public class Database extends SQLiteOpenHelper {


    public static final String KEY_NAME="user_name";
    public static final String KEY_EMAIL="user_email";
    public static final String KEY_GENDER="gender";
    public static final String KEY_AGE="age";
    public static final String KEY_SKYPE="skypeName";
    public static final String KEY_PHONE="user_phoneNo";

    private static final String DB_NAME = "telemedicine";
    private static final int DB_VERSION = 1;

    private Context mContext;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.mContext = context;
    }

    public Cursor query(String sqlStatement){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(sqlStatement,null);
        return cursor;
    }

    public void copyDatabase() {
        File dbPath = mContext.getDatabasePath(DB_NAME);
        if (!dbPath.exists()){
            if (dbPath.getParentFile().mkdir()){
                try {
                    InputStream in = mContext.getAssets().open(DB_NAME);
                    FileOutputStream out = new FileOutputStream(dbPath);
                    byte[] buffer = new byte[in.available()];
                    int len = in.read(buffer);
                    out.write(buffer,0,len);
                    in.close();
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Failed to copy db.");
                }
            } else System.out.println("Failed to create db folder.");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public long insertUser(User user)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_EMAIL,user.getEmail());
        values.put(KEY_GENDER, user.getEmail());
        values.put(KEY_AGE, user.getAge());
        values.put(KEY_SKYPE, user.getSkypeName());
        values.put(KEY_PHONE,user.getMobile());



        long inserted=db.insertOrThrow("users",null, values);
        db.close();
        return inserted;
    }
}


