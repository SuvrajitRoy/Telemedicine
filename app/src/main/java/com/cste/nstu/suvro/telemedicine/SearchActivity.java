package com.cste.nstu.suvro.telemedicine;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class SearchActivity extends Activity implements View.OnClickListener{

    EditText etSearch;
    Button btnSearchbutton;
    ListView lvMedicinelist;
    private SqlLiteManger sqlLiteManger;

   // AdapterClass adapterclass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        etSearch=(EditText) findViewById(R.id.etmedicine);
        btnSearchbutton=(Button) findViewById(R.id.btn_ser);
        lvMedicinelist=(ListView) findViewById(R.id.lvMedicine);
        sqlLiteManger=new SqlLiteManger(this);
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

    public static class MedicineActivity extends ListActivity implements AdapterView.OnItemClickListener {

           // Generic generics;
            //List<Generic> listMedicine;
            ArrayAdapter<String> myAdapter;
            private SqlLiteManger sqlLiteManger;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.medicine_layout);

    //                db=new Database(this);
    //                db.opendatabase();
    //                generics = new Generic();
    //
    //                // find all generic
    //                listMedicine = db.getAllMedicines();
    //                List<String> listTitle = new ArrayList<String>();
    //
    //                for (int i = 0; i < listMedicine.size(); i++) {
    //                        listTitle.add(i, listMedicine.get(i).getMed_name());
    //
    //                }
    //
    //
    //                myAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, listTitle);
    //                getListView().setOnItemClickListener(this);
    //                setListAdapter(myAdapter);
            }



       @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    //
    //                // start MedicineActivity with extras the generic id
    //                Intent intent = new Intent(this, ExtendedMedicineActivity.class);
    //                intent.putExtra("generic", listMedicine.get(position).get_id());
    //                startActivityForResult(intent, 1);
      }

            @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    //                super.onActivityResult(requestCode, resultCode, data);
    //
    //                // get all generic again, because something changed
    //                listMedicine = db.getAllMedicines();
    //
    //                List<String> listTitle = new ArrayList<String>();
    //
    //                for (int i = 0; i < listMedicine.size(); i++) {
    //                        listTitle.add(i, listMedicine.get(i).getMed_name());
    //                }
    //
    //                myAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, listTitle);
    //                getListView().setOnItemClickListener(this);
    //                setListAdapter(myAdapter);
      }

    }
}
