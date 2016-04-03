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

import java.util.ArrayList;


public class SearchActivity extends Activity implements View.OnClickListener{

    EditText search;
    Button searchbutton;
    Database database;
    ListView medicinlist;
    AdapterClass adapterclass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        search=(EditText) findViewById(R.id.etmedicine);
        searchbutton=(Button) findViewById(R.id.btn_ser);
        medicinlist=(ListView) findViewById(R.id.lvMedicine);
        database=new Database(this);
        searchbutton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String keyword=search.getText().toString();
        ArrayList<Medicine> al=database.cursor.(keyword);
        if(al!=null && al.size()>0)
        {
            adapterclass=new AdapterClass(this,al);
            medicinlist.setAdapter(adapterclass);
        }


    }

}
