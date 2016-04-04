package com.cste.nstu.suvro.telemedicine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class Database extends SQLiteOpenHelper {


    public static final String KEY_NAME="user_name";
    public static final String KEY_EMAIL="user_email";
    public static final String KEY_GENDER="gender";
    public static final String KEY_AGE="age";
    public static final String KEY_SKYPE="skypeName";
    public static final String KEY_PHONE="user_phoneNo";


    public static final String KEY_MEDICINE="med_name";
    public static final String KEY_GENERIC="gen_name";
    public static final String KEY_INDICATION="indication";
    public static final String KEY_DOSAGE="dosage";
    public static final String KEY_CONTRAINDICATION="contraindication";
    public static final String KEY_ACTION="action";
    public static final String KEY_SIDE_EFFECT="side_effect";
    public static final String KEY_COMPANY="com_name";
    public static final String KEY_PRICE="price";


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


    public ArrayList<Medicine> searchMedicine(String keyword)
    {
        ArrayList<Medicine> md=new ArrayList<Medicine>();
        SQLiteDatabase db=this.getReadableDatabase();
        //Cursor cr=db.query(DATABASE_TABLE, null,KEY_GROUP +" LIKE '%"+ KeyWord +"%'",null,null,null,null);
        Cursor cr=db.rawQuery(
                "select * from medicines where name LIKE '%"+ keyword + "%';"
                , null);

        if(cr!=null && cr.getCount()>0)
        {
            cr.moveToFirst();
            for(int i=0;i<cr.getCount();i++)
            {

                String medName=cr.getString(cr.getColumnIndex(KEY_MEDICINE));
                String genName=cr.getString(cr.getColumnIndex(KEY_GENERIC));
                String ind=cr.getString(cr.getColumnIndex(KEY_INDICATION));
                String dos=cr.getString(cr.getColumnIndex(KEY_DOSAGE));
                String action= cr.getString(cr.getColumnIndex(KEY_ACTION));
                String contra=cr.getString(cr.getColumnIndex(KEY_CONTRAINDICATION));
                String side=cr.getString(cr.getColumnIndex(KEY_SIDE_EFFECT));
                String company=cr.getString(cr.getColumnIndex(KEY_COMPANY));
                String price=cr.getString(cr.getColumnIndex(KEY_PRICE));


                Medicine med=new Medicine(medName,genName,ind,dos,action,contra,side,company,price);
                md.add(med);
                cr.moveToNext();
            }
        }
        cr.close();
        db.close();
        return md;
    }
    public ArrayList<Medicine> getAllInformation()
    {
        ArrayList<Medicine> allinformation=new ArrayList<Medicine>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.query("medicines", null, null, null, null, null, null);
        if(c!=null && c.getCount()>0)
        {
            c.moveToFirst();
            for(int i=0;i<c.getCount();i++)
            {

                String medName=c.getString(c.getColumnIndex(KEY_MEDICINE));
                String genName=c.getString(c.getColumnIndex(KEY_GENERIC));
                String ind=c.getString(c.getColumnIndex(KEY_INDICATION));
                String dos=c.getString(c.getColumnIndex(KEY_DOSAGE));
                String action= c.getString(c.getColumnIndex(KEY_ACTION));
                String contra=c.getString(c.getColumnIndex(KEY_CONTRAINDICATION));
                String side=c.getString(c.getColumnIndex(KEY_SIDE_EFFECT));
                String company=c.getString(c.getColumnIndex(KEY_COMPANY));
                String price=c.getString(c.getColumnIndex(KEY_PRICE));
                Medicine med=new Medicine(medName,genName,ind,dos,action,contra,side,company,price);
                allinformation.add(med);
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        return allinformation;
    }

    public String getKeyMedicine(long l) throws SQLException {
        // TODO Auto-generated method stub
        String[] columns=new String[]{ KEY_MEDICINE,KEY_GENERIC,KEY_INDICATION,KEY_DOSAGE,KEY_CONTRAINDICATION,KEY_SIDE_EFFECT,KEY_ACTION,KEY_PRICE,KEY_COMPANY};
        SQLiteDatabase OurDatabase=this.getReadableDatabase();
        Cursor c=OurDatabase.query("medicines", columns,KEY_MEDICINE+"="+l,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            String name=c.getString(1);
            return name;
        }
        c.close();
        OurDatabase.close();
        return null;
    }
    public String getKeyGeneric(long l) throws SQLException{
        // TODO Auto-generated method stub
        String[] columns=new String[]{ KEY_MEDICINE,KEY_GENERIC,KEY_INDICATION,KEY_DOSAGE,KEY_CONTRAINDICATION,KEY_SIDE_EFFECT,KEY_ACTION,KEY_PRICE,KEY_COMPANY};
        SQLiteDatabase OurDatabase=this.getReadableDatabase();
        Cursor c=OurDatabase.query("generics", columns,KEY_GENERIC+"="+l,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            String group=c.getString(2);
            return group;
        }
        c.close();
        OurDatabase.close();
        return null;
    }
    public String getKeyIndication(long l) throws SQLException{
        // TODO Auto-generated method stub
        String[] columns=new String[]{ KEY_MEDICINE,KEY_GENERIC,KEY_INDICATION,KEY_DOSAGE,KEY_CONTRAINDICATION,KEY_SIDE_EFFECT,KEY_ACTION,KEY_PRICE,KEY_COMPANY};
        SQLiteDatabase OurDatabase=this.getReadableDatabase();
        Cursor c=OurDatabase.query("generics", columns,KEY_GENERIC+"="+l,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            String group=c.getString(3);
            return group;
        }
        c.close();
        OurDatabase.close();
        return null;
    }
    public String getKeyDosage(long l) throws SQLException{
        // TODO Auto-generated method stub
        String[] columns=new String[]{ KEY_MEDICINE,KEY_GENERIC,KEY_INDICATION,KEY_DOSAGE,KEY_CONTRAINDICATION,KEY_SIDE_EFFECT,KEY_ACTION,KEY_PRICE,KEY_COMPANY};
        SQLiteDatabase OurDatabase=this.getReadableDatabase();
        Cursor c=OurDatabase.query("generics", columns,KEY_DOSAGE+"="+l,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            String group=c.getString(4);
            return group;
        }
        c.close();
        OurDatabase.close();
        return null;
    }
    public String getKeyContraindication(long l) throws SQLException{
        // TODO Auto-generated method stub
        String[] columns=new String[]{ KEY_MEDICINE,KEY_GENERIC,KEY_INDICATION,KEY_DOSAGE,KEY_CONTRAINDICATION,KEY_SIDE_EFFECT,KEY_ACTION,KEY_PRICE,KEY_COMPANY};
        SQLiteDatabase OurDatabase=this.getReadableDatabase();
        Cursor c=OurDatabase.query("generics", columns,KEY_CONTRAINDICATION+"="+l,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            String group=c.getString(5);
            return group;
        }
        c.close();
        OurDatabase.close();
        return null;
    }

    public String getKeySideEffect(long l) throws SQLException{
        // TODO Auto-generated method stub
        String[] columns=new String[]{ KEY_MEDICINE,KEY_GENERIC,KEY_INDICATION,KEY_DOSAGE,KEY_CONTRAINDICATION,KEY_SIDE_EFFECT,KEY_ACTION,KEY_PRICE,KEY_COMPANY};
        SQLiteDatabase OurDatabase=this.getReadableDatabase();
        Cursor c=OurDatabase.query("generics", columns,KEY_SIDE_EFFECT+"="+l,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            String group=c.getString(5);
            return group;
        }
        c.close();
        OurDatabase.close();
        return null;
    }
    public String getKeyAction(long l) throws SQLException{
        // TODO Auto-generated method stub
        String[] columns=new String[]{ KEY_MEDICINE,KEY_GENERIC,KEY_INDICATION,KEY_DOSAGE,KEY_CONTRAINDICATION,KEY_SIDE_EFFECT,KEY_ACTION,KEY_PRICE,KEY_COMPANY};
        SQLiteDatabase OurDatabase=this.getReadableDatabase();
        Cursor c=OurDatabase.query("generics", columns,KEY_ACTION+"="+l,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            String group=c.getString(5);
            return group;
        }
        c.close();
        OurDatabase.close();
        return null;
    }

    public String getKeyPrice(long l) throws SQLException{
        // TODO Auto-generated method stub
        String[] columns=new String[]{ KEY_MEDICINE,KEY_GENERIC,KEY_INDICATION,KEY_DOSAGE,KEY_CONTRAINDICATION,KEY_SIDE_EFFECT,KEY_ACTION,KEY_PRICE,KEY_COMPANY};
        SQLiteDatabase OurDatabase=this.getReadableDatabase();
        Cursor c=OurDatabase.query("generics", columns,KEY_PRICE+"="+l,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            String group=c.getString(5);
            return group;
        }
        c.close();
        OurDatabase.close();
        return null;
    }

    public String getKeyCompany(long l) throws SQLException{
        // TODO Auto-generated method stub
        String[] columns=new String[]{ KEY_MEDICINE,KEY_GENERIC,KEY_INDICATION,KEY_DOSAGE,KEY_CONTRAINDICATION,KEY_SIDE_EFFECT,KEY_ACTION,KEY_PRICE,KEY_COMPANY};
        SQLiteDatabase OurDatabase=this.getReadableDatabase();
        Cursor c=OurDatabase.query("company", columns,KEY_COMPANY+"="+l,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            String group=c.getString(5);
            return group;
        }
        c.close();
        OurDatabase.close();
        return null;
    }
   /* public List<String> getAllLabels(){
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  name FROM " + "medicines";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

       *//* // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
*//*
        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }*/
}


