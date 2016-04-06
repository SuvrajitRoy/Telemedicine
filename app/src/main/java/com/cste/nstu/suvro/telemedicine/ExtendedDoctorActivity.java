package com.cste.nstu.suvro.telemedicine;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ExtendedDoctorActivity extends Activity {

    TextView name, designation, qualification, specialist, location, number;
    Doctor selectedDoctor;
    Database db;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_doctor);

        name = (TextView) findViewById(R.id.tvName);
        designation = (TextView) findViewById(R.id.tvDesignation);
        qualification = (TextView) findViewById(R.id.tvQualification);
        specialist = (TextView) findViewById(R.id.tvSpecalist);
        location = (TextView) findViewById(R.id.tvLocation);
        number = (TextView) findViewById(R.id.tvNumber);


        // get the intent that we have passed from AndroidDatabaseExample
        Intent intent = getIntent();
        int id = intent.getIntExtra("doctor", -1);

        // open the database of the application context
        db = new Database(getApplicationContext());

        // read the book with "id" from the database
        selectedDoctor = db.getDoctorDetail();

        //   long inserted = database.insertUser(user);



        initializeViews();
    }


    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:number.setText(selectedDoctor.getNumber())"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }

    public void initializeViews() {
        name.setText(selectedDoctor.getDoctor_name());
        designation.setText(selectedDoctor.getDesignation());
        qualification.setText(selectedDoctor.getQualification());
        specialist.setText(selectedDoctor.getSpecialist());
        location.setText(selectedDoctor.getLocation());
        number.setText(selectedDoctor.getNumber());




    }

}
