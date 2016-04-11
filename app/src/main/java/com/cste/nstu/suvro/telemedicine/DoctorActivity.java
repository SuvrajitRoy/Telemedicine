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

    Doctor doctors;
    List<Doctor> list;
    ArrayAdapter<String> myAdapter;
    private SqlLiteManger sqlLiteManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_layout);


        sqlLiteManger=new SqlLiteManger(this);
        sqlLiteManger.open();
        doctors = new Doctor();


        // find all doctors
        list = sqlLiteManger.getAllDoctors();
        List<String> listTitle = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            listTitle.add(i, list.get(i).getDoctor_name());

        }


        myAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, listTitle);
        getListView().setOnItemClickListener(this);
        setListAdapter(myAdapter);
        //   finish();
      //  Toast.makeText(this, "Login SuccessFull", Toast.LENGTH_LONG).show();

        sqlLiteManger.close();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {

        // start ExtendedDoctorActivity with extras the doctor id
        Intent intent = new Intent(this, ExtendedDoctorActivity.class);
        intent.putExtra("doctor", list.get(position).getDoctor_id());
        startActivityForResult(intent, 1);

//        Toast.makeText(getApplicationContext(),
//                "Click ListItem Number " + position, Toast.LENGTH_LONG)
//                .show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      //  super.onActivityResult(requestCode, resultCode, data);

        // get all doctor again, because something changed
       /* list = sqlLiteManger.getAllDoctors();

        List<String> listTitle = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            listTitle.add(i, list.get(i).getDoctor_name());
        }

        myAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, listTitle);
        getListView().setOnItemClickListener(this);
        setListAdapter(myAdapter);*/
    }

}