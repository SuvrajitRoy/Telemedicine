package com.cste.nstu.suvro.telemedicine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SqlLiteManger {


	private int doctor_id;
	private int user_id;
	private int gen_id;
	private int med_id;

	private SqlLiteDbHelper _dbHelper;



	private Context context;

	private SQLiteDatabase database;

	public SqlLiteManger(Context c) {
		context = c;
		_dbHelper = new SqlLiteDbHelper(context);
	}

	public SqlLiteManger open() throws SQLException {

		database = _dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		_dbHelper.close();
	}

	public void insert(String name, String version) {
		database.beginTransaction();
//		try {
//			ContentValues contentValue = new ContentValues();
//			contentValue.put(SqlLiteDbHelper.VERSION_NAME, name);
//			contentValue.put(SqlLiteDbHelper.VERSION_NO, version);
//			database.insert(SqlLiteDbHelper.TABLE_NAME, null, contentValue);
//			database.setTransactionSuccessful();
//		} finally {
//			database.endTransaction();
//		}
	}

	//****** Start User

	public long insertUser(User user) {

		SQLiteDatabase database = _dbHelper.getWritableDatabase();


		ContentValues values = new ContentValues();

		values.put(SqlLiteDbHelper.KEY_ID, user.getUser_id());
		values.put(SqlLiteDbHelper.KEY_NAME, user.getUser_name());
		values.put(SqlLiteDbHelper.KEY_EMAIL, user.getUser_email());
		values.put(SqlLiteDbHelper.KEY_GENDER, user.getGender());
		values.put(SqlLiteDbHelper.KEY_AGE, user.getAge());
		values.put(SqlLiteDbHelper.KEY_SKYPE, user.getSkypeName());
		values.put(SqlLiteDbHelper.KEY_PHONE, user.getUser_phoneNo());


		long inserted = database.insert("users", null, values);

		database.close();
		return inserted;
	}
	//****** End User


	//******Start DoctorList


	// Getting single doctor

	public Doctor getDoctorDetail(int doctor_id) {
		// get reference of the BookDB database
		SQLiteDatabase db = _dbHelper.getReadableDatabase();

		String[] COLUMNS = new String[] {SqlLiteDbHelper.KEY_DOCID, SqlLiteDbHelper.KEY_DOCNAME, SqlLiteDbHelper.KEY_DESIGNATION,
				SqlLiteDbHelper.KEY_QUALIFICATION,SqlLiteDbHelper.KEY_SPECIALIST,SqlLiteDbHelper.KEY_LOCATION,
		SqlLiteDbHelper.KEY_DOCPHONE};

		// get doctor query
		Cursor cursor = db.query("doctors", // a. table
				COLUMNS, " doctor_id = ?", new String[] { String.valueOf(doctor_id) }, null, null, null, null);


		// if results !=null, parse the first one
		if (cursor != null)
			cursor.moveToFirst();

		Doctor doc = new Doctor();
		doc.setDoctor_id(Integer.parseInt(cursor.getString(0)));
		doc.setDoctor_name(cursor.getString(1));
		doc.setDesignation(cursor.getString(2));
		doc.setQualification(cursor.getString(3));
		doc.setSpecialist(cursor.getString(4));
		doc.setLocation(cursor.getString(5));
		doc.setNumber(cursor.getString(6));

		return doc;
	}

	public List<Doctor> getAllDoctors() {
		List<Doctor> doctors = new LinkedList<Doctor>();

		// select doctor query
		//  String query = "SELECT  * FROM  doctors ORDER BY doctor_name" ;
		//  String query = "SELECT  * FROM " + "doctors";

		// get reference of the DoctorDB database

		SQLiteDatabase db = _dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT  * FROM  doctors ORDER BY doctor_name", null);

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

			db.close();
		}

		return doctors;
	}


	//End DoctorList*******

//******Start MedicineList


	// Getting single Medicine

	/*public Medicine getMedicineDetail(int med_id) {
		// get reference of the MedicineDB database
		SQLiteDatabase db = _dbHelper.getReadableDatabase();

		String[] COLUMNS = new String[] {SqlLiteDbHelper.KEY_DOCID, SqlLiteDbHelper.KEY_DOCNAME, SqlLiteDbHelper.KEY_DESIGNATION,
				SqlLiteDbHelper.KEY_QUALIFICATION,SqlLiteDbHelper.KEY_SPECIALIST,SqlLiteDbHelper.KEY_LOCATION,
				SqlLiteDbHelper.KEY_DOCPHONE};

		// get medicine query
		Cursor cursor = db.query("mediciness", // a. table
				COLUMNS, " med_id = ?", new String[] { String.valueOf(med_id) }, null, null, null, null);


		// if results !=null, parse the first one
		if (cursor != null)
			cursor.moveToFirst();

		Medicine med = new Medicine();
		med.setMed_id(Integer.parseInt(cursor.getString(0)));
		med.setMed_name(cursor.getString(1));
		med.setCom_name(cursor.getString(2));

		return med;
	}*/

	public List<Medicine> getAllMedicines() {
		List<Medicine> medicines = new LinkedList<Medicine>();

		// get reference of the MedicineDB database

		SQLiteDatabase db = _dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT  * FROM  medicines ORDER BY med_name", null);

		// parse all results
		Medicine medicine = null;
		if (cursor.moveToFirst()) {
			do {
				medicine = new Medicine();
				medicine.setMed_id(Integer.parseInt(cursor.getString(0)));
				medicine.setMed_name(cursor.getString(1));
				medicine.setCom_name(cursor.getString(2));


//                // Add medicine to medicines
				medicines.add(medicine);
			} while (cursor.moveToNext());

			db.close();
		}

		return medicines;
	}


	//End MedicineList*******



	//******Start GenericList/MedicineList


	// Getting single generic
	public Generic getGenericDetail(int gen_id) {
	//	this._id = _id;
		SQLiteDatabase db = _dbHelper.getReadableDatabase();

		String[] COLUMNS = new String[] {SqlLiteDbHelper.KEY_GENID,
				SqlLiteDbHelper.KEY_GENERIC, SqlLiteDbHelper.KEY_INDICATION,SqlLiteDbHelper.KEY_DOSAGE,SqlLiteDbHelper.KEY_CONTRAINDICATION,SqlLiteDbHelper.KEY_SIDE_EFFECT,
				SqlLiteDbHelper.KEY_SIDE_EFFECT,SqlLiteDbHelper.KEY_ACTION,SqlLiteDbHelper.KEY_PRICE};

		// get doctor query
		Cursor cursor = db.query("generics", // a. table
				COLUMNS, " gen_id = ?", new String[]{String.valueOf(gen_id)}, null, null, null, null);


		/*Cursor cursor = db.rawQuery("SELECT * FROM generics,medicines,companies " +
				"WHERE generics._id=medicines.generic_id AND companies._id=medicines.company_id", null);*/
		if (cursor != null && cursor.moveToFirst()){
			Generic gen = new Generic(cursor.getInt(0),cursor.getString(1), cursor.getString(2),cursor.getString(3), cursor.getString(4),cursor.getString(5), cursor.getString(6),cursor.getString(7));

	//		Generic gen = new Generic(cursor.getInt(0),cursor.getString(1), cursor.getString(2),cursor.getString(3), cursor.getString(4),cursor.getString(5), cursor.getString(6),cursor.getString(7),cursor.getInt(8),cursor.getString(9), cursor.getString(10));
			// return doctor
			gen.setGen_id(Integer.parseInt(cursor.getString(0)));
			gen.setGen_name(cursor.getString(1));
			gen.setIndication(cursor.getString(2));
			gen.setDosage(cursor.getString(3));
			gen.setContraindication(cursor.getString(4));
			gen.setSideEffect(cursor.getString(5));
			gen.setAction(cursor.getString(6));
			gen.setSize_price(cursor.getString(7));

			/*gen.setMed_id(Integer.parseInt(cursor.getString(8)));
			gen.setMed_name(cursor.getString(9));
			gen.setCom_name(cursor.getString(10));*/

			cursor.close();
			db.close();

			return gen;

		}
		return null;
	}

	public List<Generic> getAllGenerics() {
		List<Generic> generics = new LinkedList<Generic>();

		// get reference of the GenericDB database

		SQLiteDatabase db = _dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM generics ORDER BY gen_name", null);

		// parse all results
	//	Generic generic = null;
		if (cursor.moveToFirst()) {
			do {
				Generic generic = new Generic();
				generic.setGen_id(Integer.parseInt(cursor.getString(0)));
				generic.setGen_name(cursor.getString(1));
				generic.setIndication(cursor.getString(2));
				generic.setDosage(cursor.getString(3));
				generic.setContraindication(cursor.getString(4));
				generic.setSideEffect(cursor.getString(5));
				generic.setAction(cursor.getString(6));
				generic.setSize_price(cursor.getString(7));

				/*generic.setMed_id(Integer.parseInt(cursor.getString(8)));
				generic.setMed_name(cursor.getString(9));
				generic.setCom_name(cursor.getString(10));*/



               // Add generic to generics
				generics.add(generic);
			} while (cursor.moveToNext());

			db.close();

		}

		return generics;
	}


	//****Specific Medicine for Generic Name
	/*public List<Generic> getSpecificMed() {
		List<Generic> generics = new LinkedList<Generic>();

		// select medicine for generic query
		String query = "SELECT * FROM generics,medicines,companies " +
				"WHERE generics._id=medicines.generic_id AND companies._id=medicines.company_id ORDER BY med_name";

		// get reference of the GenericDB,MedicineDB database

		SQLiteDatabase db = _dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		// parse all results
		Generic generic = null;
		if (cursor.moveToFirst()) {
			do {
				generic = new Generic();
				generic.set_id(Integer.parseInt(cursor.getString(0)));
				generic.setGen_name(cursor.getString(1));
				generic.setIndication(cursor.getString(2));
				generic.setDosage(cursor.getString(3));
				generic.setContraindication(cursor.getString(4));
				generic.setSideEffect(cursor.getString(5));
				generic.setAction(cursor.getString(6));
				generic.setSize_price(cursor.getString(7));

				generic.setMed_id(Integer.parseInt(cursor.getString(8)));
				generic.setMed_name(cursor.getString(9));
				generic.setCom_name(cursor.getString(10));



//                // Add generic to generics
				generics.add(generic);
			} while (cursor.moveToNext());
		}

		return generics;
	}*/


}
   //******Start MedicineList