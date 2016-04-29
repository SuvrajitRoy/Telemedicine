package com.cste06.nstu.suvro.telemedicine;

import android.Manifest;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SUVRO on 06-04-16.
 */
public class ExtendedGenericActivity extends Activity {

    TextView name,indication, dosage, contradication, side, action, size;
    Generic selectedGeneric;
    private SqlLiteManger sqlLiteManger;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_generic);

        name = (TextView) findViewById(R.id.tvName);
        indication = (TextView) findViewById(R.id.tvDesignation);
        dosage = (TextView) findViewById(R.id.tvQualification);
        contradication = (TextView) findViewById(R.id.tvSpecalist);
        side = (TextView) findViewById(R.id.tvLocation);
        action = (TextView) findViewById(R.id.tvNumber);
        size = (TextView) findViewById(R.id.tvSizePrice);


        // get the intent that we have passed from AndroidDatabaseExample
        Intent intent = getIntent();
        int gen_id = intent.getIntExtra("generic", -1);

        // open the database of the application context
        sqlLiteManger = new SqlLiteManger(getApplicationContext());

        // read the doctor with "id" from the database
        selectedGeneric = sqlLiteManger.getGenericDetail(gen_id);

        //   long inserted = database.insertUser(user);



        initializeViews();
    }



    public void initializeViews() {
        name.setText(selectedGeneric.getGen_name());
        indication.setText(selectedGeneric.getIndication());
        dosage.setText(selectedGeneric.getDosage());
        contradication.setText(selectedGeneric.getContraindication());
        side.setText(selectedGeneric.getSideEffect());
        action.setText(selectedGeneric.getAction());
        size.setText(selectedGeneric.getSize_price());




    }
}
