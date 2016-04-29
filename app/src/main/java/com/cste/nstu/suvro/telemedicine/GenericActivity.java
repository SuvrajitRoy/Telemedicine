package com.cste06.nstu.suvro.telemedicine;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class GenericActivity extends ListActivity implements AdapterView.OnItemClickListener {

    Generic generics;
    List<Generic> list;
    ArrayAdapter<String> myAdapter;
    private SqlLiteManger sqlLiteManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_layout);


        sqlLiteManger=new SqlLiteManger(this);
        sqlLiteManger.open();
        generics = new Generic();


        // find all generics
        list = sqlLiteManger.getAllGenerics();
        List<String> listTitle = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {

            listTitle.add(i, list.get(i).getGen_name());

          //  System.out.println("work");

        }


        myAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, listTitle);
        getListView().setOnItemClickListener(this);
      //  System.out.println("work");
        setListAdapter(myAdapter);
        //   finish();
        //  Toast.makeText(this, "Login SuccessFull", Toast.LENGTH_LONG).show();

        sqlLiteManger.close();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {

        // start ExtendedDoctorActivity with extras the generic id
        Intent intent = new Intent(this,ExtendedGenericActivity.class);
        intent.putExtra("generic", list.get(position).getGen_id());
        startActivityForResult(intent, 1);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //  super.onActivityResult(requestCode, resultCode, data);
    }

}
   /* private SqlLiteManger sqlLiteManger;
    Doctor doctors;
    List<Doctor> list;
    ArrayAdapter<String> myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_layout);


        sqlLiteManger = new SqlLiteManger(this);
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


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //  super.onActivityResult(requestCode, resultCode, data);


    }

}*/