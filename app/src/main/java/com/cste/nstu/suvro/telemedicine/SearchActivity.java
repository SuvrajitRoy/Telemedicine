package com.cste.nstu.suvro.telemedicine;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class SearchActivity extends Activity implements View.OnClickListener{

    EditText etSearch;
    Button btnSearchbutton;
    Database database;
    ListView lvMedicinelist;
    AdapterClass adapterclass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        etSearch=(EditText) findViewById(R.id.etmedicine);
        btnSearchbutton=(Button) findViewById(R.id.btn_ser);
        lvMedicinelist=(ListView) findViewById(R.id.lvMedicine);
        database=new Database(this);
     //   searchbutton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
      /*  String keyword=etSearch.getText().toString();
        ArrayList<Medicine> al=database.searchMedicine(keyword);
        if(al!=null && al.size()>0)
        {
            adapterclass=new AdapterClass(getApplicationContext(),al);
            lvMedicinelist.setAdapter(adapterclass);
        }*/

       Toast.makeText(this,"Work",Toast.LENGTH_LONG).show();

    }

}
