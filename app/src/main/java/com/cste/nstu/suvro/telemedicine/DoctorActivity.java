package com.cste.nstu.suvro.telemedicine;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class DoctorActivity extends ListActivity implements AdapterView.OnItemClickListener {

    Database db ;
    Doctor doctors;
    List<Doctor> list;
    ArrayAdapter<String> myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_layout);


        db=new Database(this);
        db.opendatabase();
        doctors = new Doctor();

   //     doctors = db.getDoctorDetail();
      //  ListView listView = (ListView) findViewById(R.id.list);

     //   TextView textView= (TextView) findViewById(R.id.textView);

    //   textView.setText("Name: " + doctors.getDoctor_name() + "\nMobile No: " + doctors.getNumber());

      //  db.getAllDoctors();
      //  AssetManager assetManager = getAssets();

        // fine all doctors
        list = db.getAllDoctors();
        List<String> listTitle = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            listTitle.add(i, list.get(i).getDoctor_name());

        }


        myAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, listTitle);
        getListView().setOnItemClickListener(this);
        setListAdapter(myAdapter);
        //   finish();
      //  Toast.makeText(this, "Login SuccessFull", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {

        // start BookActivity with extras the doctor id
        Intent intent = new Intent(this, ExtendedDoctorActivity.class);
        intent.putExtra("doctor", list.get(position).getDoctor_id());
        startActivityForResult(intent, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // get all doctor again, because something changed
        list = db.getAllDoctors();

        List<String> listTitle = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            listTitle.add(i, list.get(i).getDoctor_name());
        }

        myAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, listTitle);
        getListView().setOnItemClickListener(this);
        setListAdapter(myAdapter);
    }

}