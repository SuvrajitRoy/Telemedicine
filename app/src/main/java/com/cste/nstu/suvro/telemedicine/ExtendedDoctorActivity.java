package com.cste06.nstu.suvro.telemedicine;

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
import android.widget.Toast;


public class ExtendedDoctorActivity extends Activity {

    TextView name, designation, qualification, specialist, location, number;
    Button btn;
    Doctor selectedDoctor;
    private SqlLiteManger sqlLiteManger;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_doctor);

        sqlLiteManger=new SqlLiteManger(this);
        sqlLiteManger.open();
        selectedDoctor = new Doctor();


        name = (TextView) findViewById(R.id.tvName);
        designation = (TextView) findViewById(R.id.tvDesignation);
        qualification = (TextView) findViewById(R.id.tvQualification);
        specialist = (TextView) findViewById(R.id.tvSpecalist);
        location = (TextView) findViewById(R.id.tvLocation);
        number = (TextView) findViewById(R.id.tvNumber);
        ((Button)findViewById(R.id.btn_call)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDoctor(v);
            }
        });


        // get the intent that we have passed from DoctorActivity
        Intent intent = getIntent();
        int doctor_id = intent.getIntExtra("doctor", -1);

        // open the database of the application context
        sqlLiteManger = new SqlLiteManger(getApplicationContext());

        // read the doctor with "id" from the database
        selectedDoctor =sqlLiteManger.getDoctorDetail(doctor_id);





        initializeViews();
    }


    public void callDoctor(View view) {
        Toast.makeText(this,"Calling...",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+selectedDoctor.getNumber()));
        startActivity(intent);

    }

    public void initializeViews() {
        name.setText(selectedDoctor.getDoctor_name());
        designation.setText(selectedDoctor.getDesignation());
        qualification.setText(selectedDoctor.getQualification());
        specialist.setText(selectedDoctor.getSpecialist());
        location.setText(selectedDoctor.getLocation());
        number.setText(selectedDoctor.getNumber());
      //  btn.setText(selectedDoctor.getNumber());




    }

}
