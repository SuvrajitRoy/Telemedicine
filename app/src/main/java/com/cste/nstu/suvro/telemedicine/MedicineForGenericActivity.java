package com.cste06.nstu.suvro.telemedicine;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SUVRO on 07-04-16.
 */
public class MedicineForGenericActivity extends ListActivity implements AdapterView.OnItemClickListener {

    Medicine selectedMedicine;
    List<Medicine> listMed;
    ArrayAdapter<String> myAdapter;
    //  Medicine selectedDoctor;
    private SqlLiteManger sqlLiteManger;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.medicine_layout);

        sqlLiteManger=new SqlLiteManger(this);
        sqlLiteManger.open();
        selectedMedicine = new Medicine();

        // find specific medicine
        listMed = sqlLiteManger.getSpecificMed();
        List<String> listTitle = new ArrayList<String>();

        for (int i = 0; i < listMed.size(); i++) {
            listTitle.add(i, listMed.get(i).getMed_name());

        }


        myAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, listTitle);
        getListView().setOnItemClickListener(this);
        setListAdapter(myAdapter);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // start ExtendedGenericActivity with extras the medicine id
        Intent intent = new Intent(this, ExtendedGenericActivity.class);
        intent.putExtra("generic", listMed.get(position).getMed_id());
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

}

