package com.cste.nstu.suvro.telemedicine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Database extends SQLiteOpenHelper {

    private static String DB_PATH ="/data/data/"+BuildConfig.APPLICATION_ID+"/databases/";
    private static final String DB_NAME = "telemedicine";
    private static final int DB_VERSION = 1;
    public SQLiteDatabase myDataBase;
     static Context mycontext;

// User Table
    public static final String KEY_NAME="user_name";
    public static final String KEY_EMAIL="user_email";
    public static final String KEY_GENDER="gender";
    public static final String KEY_AGE="age";
    public static final String KEY_SKYPE="skypeName";
    public static final String KEY_PHONE="user_phoneNo";


    // Doctor Table
    public static final String KEY_DOCTOR_ID="doctor_id";
    public static final String KEY_DOCTOR="doctor_name";
    public static final String KEY_DESIGNATION="designation";
    public static final String KEY_QUALIFICATION="qualification";
    public static final String KEY_SPECIALIST="specialist";
    public static final String KEY_LOCATION="location";
    public static final String KEY_NUMBER="doctor_phoneNo";

    private static final String[] DOCTORS = { KEY_DOCTOR_ID, KEY_DOCTOR, KEY_DESIGNATION,KEY_QUALIFICATION,KEY_SPECIALIST,KEY_LOCATION,KEY_NUMBER };



    // Medicine Table
    public static final String KEY_MEDICINE="med_name";
    public static final String KEY_GENERIC="gen_name";
    public static final String KEY_INDICATION="indication";
    public static final String KEY_DOSAGE="dosage";
    public static final String KEY_CONTRAINDICATION="contraindication";
    public static final String KEY_ACTION="action";
    public static final String KEY_SIDE_EFFECT="side_effect";
    public static final String KEY_COMPANY="com_name";
    public static final String KEY_PRICE="price";
    private int doctor_id;
    private int _id;


    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.mycontext = context;

        boolean dbexist = checkdatabase();
        if (dbexist) {
            System.out.println("Database exists");
            opendatabase();
        } else {
            System.out.println("Database doesn't exist");
            try {
                createdatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void createdatabase() throws IOException {
        boolean dbexist = checkdatabase();
        if(dbexist) {
            System.out.println(" Database exists.");
        } else {
            this.getReadableDatabase();
            try {
                copydatabase();
            } catch(IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkdatabase() {

        boolean checkdb = false;
        try {
            String myPath = DB_PATH + DB_NAME;
            File dbfile = new File(myPath);
            checkdb = dbfile.exists();
        } catch(SQLiteException e) {
            System.out.println("Database doesn't exist");
        }
        return checkdb;
    }


    public Cursor query(String sqlStatement){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(sqlStatement,null);
        return cursor;
    }

/*    public void copyDatabase() {
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
    }*/

    private void copydatabase() throws IOException {
        //Open your local db as the input stream
        InputStream myinput = mycontext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outfilename = getDatabasePath();

        // if the path doesn't exist first, create it
        File f = new File(mycontext.getApplicationInfo().dataDir + DB_PATH);
        if (!f.exists())
            f.mkdir();


        //Open the empty db as the output stream
        OutputStream myoutput = new FileOutputStream(outfilename);

        // transfer byte to inputfile to outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myinput.read(buffer))>0) {
            myoutput.write(buffer,0,length);
        }

        //Close the streams
        myoutput.flush();
        myoutput.close();
        myinput.close();
    }

    private static String getDatabasePath() {
        return mycontext.getApplicationInfo().dataDir + DB_PATH
                + DB_NAME;
    }

    //delete database
    public void db_delete()
    {
        File file = new File(DB_PATH + DB_NAME);
        if(file.exists())
        {
            file.delete();
            System.out.println("delete database file.");
        }
    }

    public void opendatabase() throws SQLException {
        //Open the database
        String mypath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);

        SQLiteDatabase db = this.getReadableDatabase();
    }

    public synchronized void closeDataBase()throws SQLException
    {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
        {
            Log.v("Database Upgrade", "Database version higher than old.");
            db_delete();
        }
    }

  /*  public void createDoctor(Doctor doctor) {
        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();

        long insert = db.insert("users", null,values);
        db.close();
    }*/


    public long insertUser(User user)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_GENDER, user.getEmail());
        values.put(KEY_AGE, user.getAge());
        values.put(KEY_SKYPE, user.getSkypeName());
        values.put(KEY_PHONE, user.getMobile());



        long inserted=db.insertOrThrow("users",null, values);
        db.close();
        return inserted;
    }



    //******Start DoctorList


    // Getting single doctor
    public Doctor getDoctorDetail() {
        this.doctor_id = doctor_id;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM doctors", null);
        if (cursor != null && cursor.moveToFirst()){
            Doctor doc = new Doctor(cursor.getInt(0),cursor.getString(1), cursor.getString(2),cursor.getString(3), cursor.getString(4),cursor.getString(5), cursor.getString(6));
            // return doctor
            doc.setDoctor_id(Integer.parseInt(cursor.getString(0)));
            doc.setDoctor_name(cursor.getString(1));
            doc.setDesignation(cursor.getString(2));
            doc.setQualification(cursor.getString(3));
            doc.setSpecialist(cursor.getString(4));
            doc.setLocation(cursor.getString(5));
            doc.setNumber(cursor.getString(6));

            cursor.close();
            db.close();

            return doc;

        }
        return null;
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new LinkedList<Doctor>();

        // select doctor query
        String query = "SELECT  * FROM " + "doctors";

        // get reference of the DoctorDB database

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        Doctor doctor = null;
        if (cursor.moveToFirst()) {
            do {
                doctor = new Doctor();
                doctor.setDoctor_id(Integer.parseInt(cursor.getString(0)));
                doctor.setDoctor_name(cursor.getString(1));
                doctor.setDesignation(cursor.getString(2));
                doctor.setQualification(cursor.getString(3));
                doctor.setSpecialist(cursor.getString(4));
                doctor.setLocation(cursor.getString(5));
                doctor.setNumber(cursor.getString(6));


//                // Add doctor to doctors
              doctors.add(doctor);
            } while (cursor.moveToNext());
        }

        return doctors;
    }

    //End DoctorList*******

    //******Start GenericList


    // Getting single generic
    public Doctor getGenericDetail() {
        this._id = _id;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM generics", null);
        if (cursor != null && cursor.moveToFirst()){
            Doctor doc = new Doctor(cursor.getInt(0),cursor.getString(1), cursor.getString(2),cursor.getString(3), cursor.getString(4),cursor.getString(5), cursor.getString(6));
            // return doctor
            doc.setDoctor_id(Integer.parseInt(cursor.getString(0)));
            doc.setDoctor_name(cursor.getString(1));
            doc.setDesignation(cursor.getString(2));
            doc.setQualification(cursor.getString(3));
            doc.setSpecialist(cursor.getString(4));
            doc.setLocation(cursor.getString(5));
            doc.setNumber(cursor.getString(6));

            cursor.close();
            db.close();

            return doc;

        }
        return null;
    }

    public List<Doctor> getAllGenerics() {
        List<Doctor> doctors = new LinkedList<Doctor>();

        // select doctor query
        String query = "SELECT  * FROM " + "doctors";

        // get reference of the DoctorDB database

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        Doctor doctor = null;
        if (cursor.moveToFirst()) {
            do {
                doctor = new Doctor();
                doctor.setDoctor_id(Integer.parseInt(cursor.getString(0)));
                doctor.setDoctor_name(cursor.getString(1));
                doctor.setDesignation(cursor.getString(2));
                doctor.setQualification(cursor.getString(3));
                doctor.setSpecialist(cursor.getString(4));
                doctor.setLocation(cursor.getString(5));
                doctor.setNumber(cursor.getString(6));


//                // Add book to books
                doctors.add(doctor);
            } while (cursor.moveToNext());
        }

        return doctors;
    }

    //End GenericList*******

   //Start Search medicine
  /*   public ArrayList<Medicine> searchMedicine(String keyword)
    {
        ArrayList<Medicine> md=new ArrayList<Medicine>();
        SQLiteDatabase database=this.getReadableDatabase();
      //  Cursor cr=database.query("medicines", null,KEY_MEDICINE +" LIKE '%"+ Keyword +"%'",null,null,null,null);
        Cursor cr=database.rawQuery(
                "SELECT med_name FROM medicines WHERE med_name LIKE '%"+ keyword + "%';", null);

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
        database.close();
        return md;
    }
    public ArrayList<Medicine> getAllInformation()
    {
        ArrayList<Medicine> allinformation=new ArrayList<Medicine>();
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor c=database.query("medicines", null, null, null, null, null, null);
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
        database.close();
        return allinformation;
    }

    public String getMedName(long l) throws SQLException {
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
    public String getGeneric(long l) throws SQLException{
        // TODO Auto-generated method stub
        String[] columns=new String[]{ KEY_MEDICINE,KEY_GENERIC,KEY_INDICATION,KEY_DOSAGE,KEY_CONTRAINDICATION,KEY_SIDE_EFFECT,KEY_ACTION,KEY_PRICE,KEY_COMPANY};
        SQLiteDatabase OurDatabase=this.getReadableDatabase();
        Cursor c=OurDatabase.query("generics", columns,KEY_GENERIC+"="+l,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            String generic=c.getString(2);
            return generic;
        }
        c.close();
        OurDatabase.close();
        return null;
    }
    public String getIndication(long l) throws SQLException{
        // TODO Auto-generated method stub
        String[] columns=new String[]{ KEY_MEDICINE,KEY_GENERIC,KEY_INDICATION,KEY_DOSAGE,KEY_CONTRAINDICATION,KEY_SIDE_EFFECT,KEY_ACTION,KEY_PRICE,KEY_COMPANY};
        SQLiteDatabase OurDatabase=this.getReadableDatabase();
        Cursor c=OurDatabase.query("generics", columns,KEY_GENERIC+"="+l,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            String ind=c.getString(3);
            return ind;
        }
        c.close();
        OurDatabase.close();
        return null;
    }
    public String getDosage(long l) throws SQLException{
        // TODO Auto-generated method stub
        String[] columns=new String[]{ KEY_MEDICINE,KEY_GENERIC,KEY_INDICATION,KEY_DOSAGE,KEY_CONTRAINDICATION,KEY_SIDE_EFFECT,KEY_ACTION,KEY_PRICE,KEY_COMPANY};
        SQLiteDatabase OurDatabase=this.getReadableDatabase();
        Cursor c=OurDatabase.query("generics", columns,KEY_DOSAGE+"="+l,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            String dos=c.getString(4);
            return dos;
        }
        c.close();
        OurDatabase.close();
        return null;
    }
    public String getContraindication(long l) throws SQLException{
        // TODO Auto-generated method stub
        String[] columns=new String[]{ KEY_MEDICINE,KEY_GENERIC,KEY_INDICATION,KEY_DOSAGE,KEY_CONTRAINDICATION,KEY_SIDE_EFFECT,KEY_ACTION,KEY_PRICE,KEY_COMPANY};
        SQLiteDatabase OurDatabase=this.getReadableDatabase();
        Cursor c=OurDatabase.query("generics", columns,KEY_CONTRAINDICATION+"="+l,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            String con=c.getString(5);
            return con;
        }
        c.close();
        OurDatabase.close();
        return null;
    }

    public String getSideEffect(long l) throws SQLException{
        // TODO Auto-generated method stub
        String[] columns=new String[]{ KEY_MEDICINE,KEY_GENERIC,KEY_INDICATION,KEY_DOSAGE,KEY_CONTRAINDICATION,KEY_SIDE_EFFECT,KEY_ACTION,KEY_PRICE,KEY_COMPANY};
        SQLiteDatabase OurDatabase=this.getReadableDatabase();
        Cursor c=OurDatabase.query("generics", columns,KEY_SIDE_EFFECT+"="+l,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            String side=c.getString(5);
            return side;
        }
        c.close();
        OurDatabase.close();
        return null;
    }
    public String getAction(long l) throws SQLException{
        // TODO Auto-generated method stub
        String[] columns=new String[]{ KEY_MEDICINE,KEY_GENERIC,KEY_INDICATION,KEY_DOSAGE,KEY_CONTRAINDICATION,KEY_SIDE_EFFECT,KEY_ACTION,KEY_PRICE,KEY_COMPANY};
        SQLiteDatabase OurDatabase=this.getReadableDatabase();
        Cursor c=OurDatabase.query("generics", columns,KEY_ACTION+"="+l,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            String act=c.getString(5);
            return act;
        }
        c.close();
        OurDatabase.close();
        return null;
    }

    public String getPrice(long l) throws SQLException{
        // TODO Auto-generated method stub
        String[] columns=new String[]{ KEY_MEDICINE,KEY_GENERIC,KEY_INDICATION,KEY_DOSAGE,KEY_CONTRAINDICATION,KEY_SIDE_EFFECT,KEY_ACTION,KEY_PRICE,KEY_COMPANY};
        SQLiteDatabase OurDatabase=this.getReadableDatabase();
        Cursor c=OurDatabase.query("generics", columns,KEY_PRICE+"="+l,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            String price=c.getString(5);
            return price;
        }
        c.close();
        OurDatabase.close();
        return null;
    }

    public String getCompany(long l) throws SQLException{
        // TODO Auto-generated method stub
        String[] columns=new String[]{ KEY_MEDICINE,KEY_GENERIC,KEY_INDICATION,KEY_DOSAGE,KEY_CONTRAINDICATION,KEY_SIDE_EFFECT,KEY_ACTION,KEY_PRICE,KEY_COMPANY};
        SQLiteDatabase OurDatabase=this.getReadableDatabase();
        Cursor c=OurDatabase.query("company", columns,KEY_COMPANY+"="+l,null,null,null,null);
        if(c!=null)
        {
            c.moveToFirst();
            String company=c.getString(5);
            return company;
        }
        c.close();
        OurDatabase.close();
        return null;
    }*/
    //close search
}


