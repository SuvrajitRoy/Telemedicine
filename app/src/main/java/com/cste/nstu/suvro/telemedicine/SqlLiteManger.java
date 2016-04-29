package com.cste06.nstu.suvro.telemedicine;

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


	//****** Start User

	public long insertUser(User user) {

		SQLiteDatabase database = _dbHelper.getWritableDatabase();


		ContentValues values = new ContentValues();

	//	values.put(SqlLiteDbHelper.KEY_ID, user.getUser_id());
		values.put(SqlLiteDbHelper.KEY_NAME, user.getUser_name());
		values.put(SqlLiteDbHelper.KEY_EMAIL, user.getUser_email());
		values.put(SqlLiteDbHelper.KEY_PASSWORD, user.getPassword());
		values.put(SqlLiteDbHelper.KEY_GENDER, user.getGender());
		values.put(SqlLiteDbHelper.KEY_AGE, user.getAge());
		values.put(SqlLiteDbHelper.KEY_SKYPE, user.getSkypeName());
		values.put(SqlLiteDbHelper.KEY_PHONE, user.getUser_phoneNo());


		long inserted = database.insert("users", null , values);


		database.close();
		return inserted;
	}
	//****** End User


	//******Start DoctorList
	// Getting single doctor

	public Doctor getDoctorDetail(Integer doctor_id) {
		this.doctor_id = doctor_id;
		// get reference of the BookDB database
		SQLiteDatabase db = _dbHelper.getReadableDatabase();

		String[] COLUMNS = new String[]{SqlLiteDbHelper.KEY_DOCID, SqlLiteDbHelper.KEY_DOCNAME, SqlLiteDbHelper.KEY_DESIGNATION,
				SqlLiteDbHelper.KEY_QUALIFICATION, SqlLiteDbHelper.KEY_SPECIALIST, SqlLiteDbHelper.KEY_LOCATION,
				SqlLiteDbHelper.KEY_DOCPHONE};

		// get doctor query
		Cursor cursor = db.query("doctors", // a. table
				COLUMNS, " doctor_id = ?", new String[]{String.valueOf(doctor_id)}, null, null, null, null);


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

	// All doctorList
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

	public Medicine getMedicineDetail(int med_id) {
		// get reference of the MedicineDB database
		SQLiteDatabase db = _dbHelper.getReadableDatabase();

		String[] COLUMNS = new String[]{SqlLiteDbHelper.KEY_MEDID, SqlLiteDbHelper.KEY_MEDICINE, SqlLiteDbHelper.KEY_COMPANY, SqlLiteDbHelper.KEY_GENERIC};

		String whereClause = "med_id = ? AND  medicines.com_id = companies.com_id AND  medicines.gen_id = generics.gen_id";
		//String whereClause = "med_id = ? AND  medicines.com_id = companies.com_id AND  medicines.gen_id = generics.gen_id";

		// get medicine query
		Cursor cursor = db.query("medicines,companies,generics", // a. table
				COLUMNS, whereClause, new String[]{String.valueOf(med_id)}, null, null, null, null);

		//	Cursor cursor = db.query("medicines,companies", // a. table
		//			COLUMNS, " med_id = ?", new String[] { String.valueOf(med_id) }, null, null, null, null);


		// if results !=null, parse the first one
		if (cursor != null)
			cursor.moveToFirst();

		Medicine med = new Medicine();
		med.setMed_id(Integer.parseInt(cursor.getString(0)));
		med.setMed_name(cursor.getString(1));
		med.setCom_name(cursor.getString(2));
		med.setGen_name(cursor.getString(3));

		return med;
	}

	// All medicineList
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


	//******Start GenericList
	// Getting single generic
	public Generic getGenericDetail(Integer gen_id) {
		this.gen_id = gen_id;
		SQLiteDatabase db = _dbHelper.getReadableDatabase();

		String[] COLUMNS = new String[]{SqlLiteDbHelper.KEY_GENID, SqlLiteDbHelper.KEY_GENERIC, SqlLiteDbHelper.KEY_INDICATION,
				SqlLiteDbHelper.KEY_DOSAGE, SqlLiteDbHelper.KEY_CONTRAINDICATION, SqlLiteDbHelper.KEY_SIDE_EFFECT,
				 SqlLiteDbHelper.KEY_ACTION, SqlLiteDbHelper.KEY_PRICE};

		// get doctor query
		Cursor cursor = db.query("generics", // a. table
				COLUMNS, " gen_id = ?", new String[]{String.valueOf(gen_id)}, null, null, null, null);


		/*Cursor cursor = db.rawQuery("SELECT * FROM generics,medicines,companies " +
				"WHERE generics._id=medicines.generic_id AND companies._id=medicines.company_id", null);*/
//		if (cursor != null && cursor.moveToFirst()){
//			Generic gen = new Generic(cursor.getInt(0),cursor.getString(1), cursor.getString(2),cursor.getString(3), cursor.getString(4),cursor.getString(5), cursor.getString(6),cursor.getString(7));

		// if results !=null, parse the first one
		if (cursor != null)
			cursor.moveToFirst();

		Generic gen = new Generic();

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

		//	cursor.close();
		db.close();

		return gen;

	}

	// All genericList
	public List<Generic> getAllGenerics() {
		List<Generic> generics = new LinkedList<Generic>();

		// get reference of the GenericDB database

		SQLiteDatabase db = _dbHelper.getWritableDatabase();


		Cursor cursor = db.rawQuery("SELECT * FROM generics ORDER BY gen_name", null);

		// parse all results
		Generic generic = null;
		if (cursor.moveToFirst()) {
			do {
				generic = new Generic();

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
				//	System.out.println("generic");
			} while (cursor.moveToNext());

			db.close();

		}

		return generics;
	}


	//****Specific Medicine for Generic Name
	public List<Medicine> getSpecificMed() {
		List<Medicine> medGen = new LinkedList<Medicine>();

		// get reference of the GenericDB database
		SQLiteDatabase db = _dbHelper.getWritableDatabase();

		// select medicine for generic query
		String[] COLUMNS = new String[]{SqlLiteDbHelper.KEY_MEDICINE};

		String whereClause = "generics.gen_id = ?  AND  generics.gen_id =  medicines.gen_id ";
		//String whereClause = "med_id = ? AND  medicines.com_id = companies.com_id AND  medicines.gen_id = generics.gen_id";

		Generic generic = new Generic();
		String[] whereArgs = new String[]{
				String.valueOf(generic.gen_id)
		};
		String orderBy = "med_name";
		/*Cursor c = sqLiteDatabase.query("table1", tableColumns, whereClause, whereArgs,
				null, null, orderBy);*/
		// get medicine query
		Cursor cursor = db.query("generics,medicines", COLUMNS, whereClause, whereArgs, null, null, orderBy, null);


		// parse all results

		Medicine medicine = null;
		if (cursor.moveToFirst()) {
			do {
				medicine = new Medicine();
				medicine.setMed_id(Integer.parseInt(cursor.getString(0)));
				medicine.setMed_name(cursor.getString(1));
				medicine.setCom_name(cursor.getString(2));
				medicine.setGen_name(cursor.getString(3));

				//Add medicine to medicines
				medGen.add(medicine);
			} while (cursor.moveToFirst());
		}
		return medGen;
	}


	//******Start  Search MedicineList

	// All medicineList
	public List<Search> getAllSearchMedicines() {
		List<Search> medicines = new LinkedList<Search>();

		// get reference of the MedicineDB database

		SQLiteDatabase db = _dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT  * FROM  medicines ORDER BY med_name", null);

		// parse all results
		Search medicine = null;
		if (cursor.moveToFirst()) {
			do {
				medicine = new Search();
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

	// Getting single search
	public Search getSearchMedicineDetail(int med_id) {
		// get reference of the MedicineDB database
		SQLiteDatabase db = _dbHelper.getReadableDatabase();

		String[] COLUMNS = new String[]{SqlLiteDbHelper.KEY_MEDID,SqlLiteDbHelper.KEY_GENERIC, SqlLiteDbHelper.KEY_INDICATION,
				SqlLiteDbHelper.KEY_DOSAGE, SqlLiteDbHelper.KEY_CONTRAINDICATION, SqlLiteDbHelper.KEY_SIDE_EFFECT,
				SqlLiteDbHelper.KEY_ACTION, SqlLiteDbHelper.KEY_PRICE,SqlLiteDbHelper.KEY_COMPANY};

		String whereClause = "med_id = ? AND  medicines.gen_id = generics.gen_id AND  medicines.com_id = companies.com_id ";

		// get medicine query
		Cursor cursor = db.query("medicines,companies,generics", // a. table
				COLUMNS, whereClause, new String[]{String.valueOf(med_id)}, null, null, null, null);

		//	Cursor cursor = db.query("medicines,companies", // a. table
		//			COLUMNS, " med_id = ?", new String[] { String.valueOf(med_id) }, null, null, null, null);


		// if results !=null, parse the first one
		if (cursor != null)
			cursor.moveToFirst();

		Search med = new Search();
		med.setMed_id(Integer.parseInt(cursor.getString(0)));
		med.setGen_name(cursor.getString(1));
		med.setIndication(cursor.getString(2));
		med.setDosage(cursor.getString(3));
		med.setContraindication(cursor.getString(4));
		med.setSideEffect(cursor.getString(5));
		med.setAction(cursor.getString(6));
		med.setSize_price(cursor.getString(7));
		med.setCom_name(cursor.getString(8));

		return med;
	}



	/*public long createList(String name) {

		ContentValues initialValues = new ContentValues();


		initialValues.put(_dbHelper.KEY_MEDICINE, name);

		return database.insert("medicines", null, initialValues);

	}
*/

	public Cursor searchByInputText(String inputText) throws SQLException {

		String query = "SELECT med_id," +
				_dbHelper.KEY_MEDICINE +  " from " + "medicines" +
				" where " + _dbHelper.KEY_MEDICINE + " MATCH '" + inputText + "';";

		Cursor mCursor = database.rawQuery(query,null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;

	}
	/*//Start Search medicine
	public ArrayList<Search> getsearchMedicine(String keyword) {
		ArrayList<Search> md = new ArrayList<Search>();
		SQLiteDatabase db = _dbHelper.getWritableDatabase();
		//  Cursor cr=database.query("medicines", null,KEY_MEDICINE +" LIKE '%"+ Keyword +"%'",null,null,null,null);
		Cursor cr = database.rawQuery(
				"SELECT med_name FROM medicines WHERE med_name LIKE '%" + keyword + "%';", null);

		if (cr != null && cr.getCount() > 0) {
			cr.moveToFirst();
			for (int i = 0; i < cr.getCount(); i++) {

				String med_name = cr.getString(cr.getColumnIndex(SqlLiteDbHelper.KEY_MEDICINE));
				*//*String indication = cr.getString(cr.getColumnIndex(SqlLiteDbHelper.KEY_INDICATION));
				String dosage = cr.getString(cr.getColumnIndex(SqlLiteDbHelper.KEY_DOSAGE));
				String action = cr.getString(cr.getColumnIndex(SqlLiteDbHelper.KEY_ACTION));
				String contraindication = cr.getString(cr.getColumnIndex(SqlLiteDbHelper.KEY_CONTRAINDICATION));
				String side = cr.getString(cr.getColumnIndex(SqlLiteDbHelper.KEY_SIDE_EFFECT));
				String price = cr.getString(cr.getColumnIndex(SqlLiteDbHelper.KEY_PRICE));
				String company = cr.getString(cr.getColumnIndex(SqlLiteDbHelper.KEY_COMPANY));
				String gen_name = cr.getString(cr.getColumnIndex(SqlLiteDbHelper.KEY_COMPANY));*//*


				Search med = new Search();
				md.add(med);
				cr.moveToNext();
			}
		}
		cr.close();
		db.close();
		return md;
	}

	public ArrayList<Search> getAllInformation() {
		ArrayList<Search> allinformation = new ArrayList<Search>();
		SQLiteDatabase db = _dbHelper.getWritableDatabase();
		Cursor c = database.query("medicines", null, null, null, null, null, null);
		if (c != null && c.getCount() > 0) {
			c.moveToFirst();
			for (int i = 0; i < c.getCount(); i++) {

				String med_name = c.getString(c.getColumnIndex(SqlLiteDbHelper.KEY_MEDICINE));
				String indication = c.getString(c.getColumnIndex(SqlLiteDbHelper.KEY_INDICATION));
				String dosage = c.getString(c.getColumnIndex(SqlLiteDbHelper.KEY_DOSAGE));
				String action = c.getString(c.getColumnIndex(SqlLiteDbHelper.KEY_ACTION));
				String contraindication = c.getString(c.getColumnIndex(SqlLiteDbHelper.KEY_CONTRAINDICATION));
				String side = c.getString(c.getColumnIndex(SqlLiteDbHelper.KEY_SIDE_EFFECT));
				String price = c.getString(c.getColumnIndex(SqlLiteDbHelper.KEY_PRICE));
				String company = c.getString(c.getColumnIndex(SqlLiteDbHelper.KEY_COMPANY));
				String gen_name = c.getString(c.getColumnIndex(SqlLiteDbHelper.KEY_COMPANY));

				Search med = new Search();
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
		String[] columns = new String[]{SqlLiteDbHelper.KEY_MEDICINE, SqlLiteDbHelper.KEY_GENERIC, SqlLiteDbHelper.KEY_INDICATION, SqlLiteDbHelper.KEY_DOSAGE, SqlLiteDbHelper.KEY_CONTRAINDICATION, SqlLiteDbHelper.KEY_SIDE_EFFECT, SqlLiteDbHelper.KEY_ACTION, SqlLiteDbHelper.KEY_PRICE, SqlLiteDbHelper.KEY_COMPANY};
		SQLiteDatabase db = _dbHelper.getWritableDatabase();
		Cursor c = db.query("medicines", columns, SqlLiteDbHelper.KEY_MEDICINE + "=" + l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String name = c.getString(1);
			return name;
		}
		c.close();
		db.close();
		return null;
	}

	public String getGeneric(long l) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{SqlLiteDbHelper.KEY_MEDICINE, SqlLiteDbHelper.KEY_GENERIC, SqlLiteDbHelper.KEY_INDICATION, SqlLiteDbHelper.KEY_DOSAGE, SqlLiteDbHelper.KEY_CONTRAINDICATION, SqlLiteDbHelper.KEY_SIDE_EFFECT, SqlLiteDbHelper.KEY_ACTION, SqlLiteDbHelper.KEY_PRICE, SqlLiteDbHelper.KEY_COMPANY};
		SQLiteDatabase db = _dbHelper.getWritableDatabase();
		Cursor c = db.query("generics", columns, SqlLiteDbHelper.KEY_GENERIC + "=" + l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String generic = c.getString(2);
			return generic;
		}
		c.close();
		db.close();
		return null;
	}

	public String getIndication(long l) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{SqlLiteDbHelper.KEY_MEDICINE, SqlLiteDbHelper.KEY_GENERIC, SqlLiteDbHelper.KEY_INDICATION, SqlLiteDbHelper.KEY_DOSAGE, SqlLiteDbHelper.KEY_CONTRAINDICATION, SqlLiteDbHelper.KEY_SIDE_EFFECT, SqlLiteDbHelper.KEY_ACTION, SqlLiteDbHelper.KEY_PRICE, SqlLiteDbHelper.KEY_COMPANY};
		SQLiteDatabase db = _dbHelper.getWritableDatabase();
		Cursor c = db.query("generics", columns, SqlLiteDbHelper.KEY_GENERIC + "=" + l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String ind = c.getString(3);
			return ind;
		}
		c.close();
		db.close();
		return null;
	}

	public String getDosage(long l) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{SqlLiteDbHelper.KEY_MEDICINE, SqlLiteDbHelper.KEY_GENERIC, SqlLiteDbHelper.KEY_INDICATION, SqlLiteDbHelper.KEY_DOSAGE, SqlLiteDbHelper.KEY_CONTRAINDICATION, SqlLiteDbHelper.KEY_SIDE_EFFECT, SqlLiteDbHelper.KEY_ACTION, SqlLiteDbHelper.KEY_PRICE, SqlLiteDbHelper.KEY_COMPANY};
		SQLiteDatabase db = _dbHelper.getWritableDatabase();
		Cursor c = db.query("generics", columns, SqlLiteDbHelper.KEY_DOSAGE + "=" + l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String dos = c.getString(4);
			return dos;
		}
		c.close();
		db.close();
		return null;
	}

	public String getContraindication(long l) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{SqlLiteDbHelper.KEY_MEDICINE, SqlLiteDbHelper.KEY_GENERIC, SqlLiteDbHelper.KEY_INDICATION, SqlLiteDbHelper.KEY_DOSAGE, SqlLiteDbHelper.KEY_CONTRAINDICATION, SqlLiteDbHelper.KEY_SIDE_EFFECT, SqlLiteDbHelper.KEY_ACTION, SqlLiteDbHelper.KEY_PRICE, SqlLiteDbHelper.KEY_COMPANY};
		SQLiteDatabase db = _dbHelper.getWritableDatabase();
		Cursor c = db.query("generics", columns, SqlLiteDbHelper.KEY_CONTRAINDICATION + "=" + l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String con = c.getString(5);
			return con;
		}
		c.close();
		db.close();
		return null;
	}

	public String getSideEffect(long l) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{SqlLiteDbHelper.KEY_MEDICINE, SqlLiteDbHelper.KEY_GENERIC, SqlLiteDbHelper.KEY_INDICATION, SqlLiteDbHelper.KEY_DOSAGE, SqlLiteDbHelper.KEY_CONTRAINDICATION, SqlLiteDbHelper.KEY_SIDE_EFFECT, SqlLiteDbHelper.KEY_ACTION, SqlLiteDbHelper.KEY_PRICE, SqlLiteDbHelper.KEY_COMPANY};
		SQLiteDatabase db = _dbHelper.getWritableDatabase();
		Cursor c = db.query("generics", columns, SqlLiteDbHelper.KEY_SIDE_EFFECT + "=" + l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String side = c.getString(5);
			return side;
		}
		c.close();
		db.close();
		return null;
	}

	public String getAction(long l) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{SqlLiteDbHelper.KEY_MEDICINE, SqlLiteDbHelper.KEY_GENERIC, SqlLiteDbHelper.KEY_INDICATION, SqlLiteDbHelper.KEY_DOSAGE, SqlLiteDbHelper.KEY_CONTRAINDICATION, SqlLiteDbHelper.KEY_SIDE_EFFECT, SqlLiteDbHelper.KEY_ACTION, SqlLiteDbHelper.KEY_PRICE, SqlLiteDbHelper.KEY_COMPANY};
		SQLiteDatabase db = _dbHelper.getWritableDatabase();
		Cursor c = db.query("generics", columns, SqlLiteDbHelper.KEY_ACTION + "=" + l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String act = c.getString(5);
			return act;
		}
		c.close();
		db.close();
		return null;
	}

	public String getPrice(long l) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{SqlLiteDbHelper.KEY_MEDICINE, SqlLiteDbHelper.KEY_GENERIC, SqlLiteDbHelper.KEY_INDICATION, SqlLiteDbHelper.KEY_DOSAGE, SqlLiteDbHelper.KEY_CONTRAINDICATION, SqlLiteDbHelper.KEY_SIDE_EFFECT, SqlLiteDbHelper.KEY_ACTION, SqlLiteDbHelper.KEY_PRICE, SqlLiteDbHelper.KEY_COMPANY};
		SQLiteDatabase db = _dbHelper.getWritableDatabase();
		Cursor c = db.query("generics", columns, SqlLiteDbHelper.KEY_PRICE + "=" + l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String price = c.getString(5);
			return price;
		}
		c.close();
		db.close();
		return null;
	}

	public String getCompany(long l) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{SqlLiteDbHelper.KEY_MEDICINE, SqlLiteDbHelper.KEY_GENERIC, SqlLiteDbHelper.KEY_INDICATION, SqlLiteDbHelper.KEY_DOSAGE, SqlLiteDbHelper.KEY_CONTRAINDICATION, SqlLiteDbHelper.KEY_SIDE_EFFECT, SqlLiteDbHelper.KEY_ACTION, SqlLiteDbHelper.KEY_PRICE, SqlLiteDbHelper.KEY_COMPANY};
		SQLiteDatabase db = _dbHelper.getWritableDatabase();
		Cursor c = db.query("company", columns, SqlLiteDbHelper.KEY_COMPANY + "=" + l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String company = c.getString(5);
			return company;
		}
		c.close();
		db.close();
		return null;
	}*/
//close search
}