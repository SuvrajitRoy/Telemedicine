package com.cste.nstu.suvro.telemedicine;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;


public class GenericActivity extends ListActivity implements AdapterView.OnItemClickListener {

    Database db ;
    Generic generics;
    List<Generic> listGen;
    ArrayAdapter<String> myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_layout);

        db=new Database(this);
        db.opendatabase();
        generics = new Generic();

        // find all generic
      //  listGen = db.getAllGenerics();
        List<String> listTitle = new ArrayList<String>();

        for (int i = 0; i < listGen.size(); i++) {
            listTitle.add(i, listGen.get(i).getGen_name());

        }


        myAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, listTitle);
        getListView().setOnItemClickListener(this);
        setListAdapter(myAdapter);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
