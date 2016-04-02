package com.cste.nstu.suvro.telemedicine;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by Roaim on 29-Mar-16.
 */

public class Database extends SQLiteOpenHelper {
    private static final String DB_NAME = "telemedicine";
    private static final int DB_VERSION = 1;

    private Context mContext;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.mContext = context;
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
}


