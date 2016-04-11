package com.cste.nstu.suvro.telemedicine;

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

public class SqlLiteDbHelper extends SQLiteOpenHelper {

	// User Table
	public static final String KEY_ID="user_id";
	public static final String KEY_NAME="user_name";
	public static final String KEY_EMAIL="user_email";
	public static final String KEY_GENDER="gender";
	public static final String KEY_AGE="age";
	public static final String KEY_SKYPE="skypeName";
	public static final String KEY_PHONE="user_phoneNo";

	// Doctor Table
	public static final String KEY_DOCID="doctor_id";
	public static final String KEY_DOCNAME="doctor_name";
	public static final String KEY_DESIGNATION="designation";
	public static final String KEY_QUALIFICATION="qualification";
	public static final String KEY_SPECIALIST="specialist";
	public static final String KEY_LOCATION	="location";
	public static final String KEY_DOCPHONE="doctor_phoneNo";

    // Generic,Medicine,Company Table
	public static final String KEY_GENID="gen_id";
    public static final String KEY_GENERIC="gen_name";
    public static final String KEY_INDICATION="indication";
    public static final String KEY_DOSAGE="dosage";
    public static final String KEY_CONTRAINDICATION="contraindication";
    public static final String KEY_ACTION="action";
    public static final String KEY_SIDE_EFFECT="side_effect";
    public static final String KEY_PRICE="price";

	public static final String KEY_MEDID="med_id";
	public static final String KEY_MEDICINE="med_name";

	public static final String KEY_COMPANY="com_name";
	private static final String DB_NAME = "medicine.db";
	private static final int DB_VERSION = 1;
	private static Context mycontext;
//	private static String DB_PATH = "/data/data/com.cste.nstu.suvro.telemedicine/databases/";
	private static String DB_PATH = "/data/data/" + BuildConfig.APPLICATION_ID + "/databases/";
	public SQLiteDatabase myDataBase;



	public SqlLiteDbHelper(Context context) {
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

	private static String getDatabasePath() {
		return mycontext.getApplicationInfo().dataDir + DB_PATH
				+ DB_NAME;
	}

	public void createdatabase() throws IOException {
		boolean dbexist = checkdatabase();
		if (dbexist) {
			System.out.println(" Database exists.");
		} else {
			this.getReadableDatabase();
			try {
				copydatabase();
			} catch (IOException e) {
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
		} catch (SQLiteException e) {
			System.out.println("Database doesn't exist");
		}
		return checkdb;
	}

	private void copydatabase() throws IOException {
		//Open your local db as the input stream
		InputStream myinput = mycontext.getAssets().open(DB_NAME);

		// Path to the just created empty db
		// String outfilename = getDatabasePath();
		String outfilename = DB_PATH + DB_NAME;

		// if the path doesn't exist first, create it
		File f = new File(mycontext.getApplicationInfo().dataDir + DB_PATH);
		if (!f.exists())
			f.mkdir();


		//Open the empty db as the output stream
		OutputStream myoutput = new FileOutputStream(outfilename);

		// transfer byte to inputfile to outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myinput.read(buffer)) > 0) {
			myoutput.write(buffer, 0, length);
		}

		//Close the streams
		myoutput.flush();
		myoutput.close();
		myinput.close();
	}

	//delete database
	public void db_delete() {
		File file = new File(DB_PATH + DB_NAME);
		if (file.exists()) {
			file.delete();
			System.out.println("delete database file.");
		}
	}
	//Open Database

	public void opendatabase() throws SQLException {
		//Open the database
		String mypath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);

		SQLiteDatabase db = this.getReadableDatabase();
	}

	public synchronized void closeDataBase() throws SQLException {
		if (myDataBase != null)
			myDataBase.close();
		super.close();
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (newVersion > oldVersion) {
			Log.v("Database Upgrade", "Database version higher than old.");
			db_delete();
		}
	}
	public Cursor query(String sqlStatement){
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database.rawQuery(sqlStatement,null);
		return cursor;
	}

}