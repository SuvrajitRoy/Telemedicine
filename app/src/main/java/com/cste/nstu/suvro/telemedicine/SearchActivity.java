package com.cste06.nstu.suvro.telemedicine;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends ListActivity implements AdapterView.OnItemClickListener,SearchView.OnQueryTextListener,
        SearchView.OnCloseListener {

   // private ListView myList;
    Search medicines;
    private SearchView searchView;
    List<Search> list;
    private ArrayList<String> listTitle;
    ArrayAdapter<String> myAdapter;
    private SqlLiteManger sqlLiteManger;
    private SqlLiteDbHelper dbHelper;
  //  private MyCustomAdapter defaultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        sqlLiteManger=new SqlLiteManger(this);
        sqlLiteManger.open();
        medicines = new Search();

        // find all generic
        list = sqlLiteManger.getAllSearchMedicines();
       // List<String> listTitle = new ArrayList<String>();
        listTitle = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            listTitle.add(i, list.get(i).getMed_name());

        }


        //relate the listView from java to the one created in xml
     //   myList = (ListView) findViewById(R.id.list);

        myAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, listTitle);
        getListView().setOnItemClickListener(this);
        setListAdapter(myAdapter);

        //  Toast.makeText(this, "Working", Toast.LENGTH_LONG).show();


        //prepare the SearchView
                searchView = (SearchView) findViewById(R.id.search);

        //Sets the default or resting state of the search field. If true, a single search icon is shown by default and
        // expands to show the text field and other buttons when pressed. Also, if the default state is iconified, then it
        // collapses to that state when the close button is pressed. Changes to this property will take effect immediately.
        //The default value is true.
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);

     /*   // Create the list of names which will be displayed on search
        for (String name : listTitle) {
            sqlLiteManger.createList(name);
        }
*/
        sqlLiteManger.close();
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //  start MedicineActivity with extras the generic id
        Intent intent = new Intent(this, ExtendedSearchActivity.class);
        intent.putExtra("medicine", list.get(position).getMed_id());
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // super.onActivityResult(requestCode, resultCode, data);


    }

    /*@Override
    protected void onDestroy() {
        super.onDestroy();

        if (sqlLiteManger  != null) {
            sqlLiteManger.close();
        }
    }*/

    @Override
    public boolean onClose() {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
      //  displayResults(query + "*");
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
      /*  if (!newText.isEmpty()){
            displayResults(newText + "*");
        } else {
            myList.setAdapter(defaultAdapter);
        }*/

        return false;
    }

  /*  private void displayResults(String query) {

        Cursor cursor = sqlLiteManger.searchByInputText((query != null ? query : "@@@@"));

        if (cursor != null) {

            String[] from = new String[] {dbHelper.KEY_MEDICINE};

            // Specify the view where we want the results to go
            int[] to = new int[] {R.id.search_result_text_view};

            // Create a simple cursor adapter to keep the search data
            SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.result_search_item, cursor, from, to);
            myList.setAdapter(cursorAdapter);

            // Click listener for the searched item that was selected
            myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    // Get the cursor, positioned to the corresponding row in the result set
                    Cursor cursor = (Cursor) myList.getItemAtPosition(position);

                    // Get the state's capital from this row in the database.
                    String selectedName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    Toast.makeText(SearchActivity.this, selectedName, Toast.LENGTH_SHORT).show();

                    // Set the default adapter
                    myList.setAdapter(myAdapter);

                    // Find the position for the original list by the selected name from search
                    for (int pos = 0; pos < listTitle.size(); pos++) {
                        if (listTitle.get(pos).equals(selectedName)){
                            position = pos;
                            break;
                        }
                    }

                    // Create a handler. This is necessary because the adapter has just been set on the list again and
                    // the list might not be finished setting the adapter by the time we perform setSelection.
                    Handler handler = new Handler();
                    final int finalPosition = position;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            myList.setSelection(finalPosition);
                        }
                    });

                    searchView.setQuery("",true);
                }
            });

        }
    }*/
}




/*
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

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends Activity {

    EditText etSearch;
    Button btnSearchbutton;
    ListView lvMedicinelist;
    private SqlLiteManger sqlLiteManger;

    AdapterClass adapterclass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        sqlLiteManger=new SqlLiteManger(this);
        sqlLiteManger.open();

        etSearch=(EditText) findViewById(R.id.etmedicine);
        btnSearchbutton=(Button) findViewById(R.id.btn_ser);
        lvMedicinelist=(ListView) findViewById(R.id.lvMedicine);
        sqlLiteManger=new SqlLiteManger(this);

      ((Button)findViewById(R.id.btn_call)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search(v);
            }
        });

    }



    public void search(View view) {
        String keyword=etSearch.getText().toString();
        ArrayList<Search> al=sqlLiteManger.getsearchMedicine(keyword);
        if(al!=null && al.size()>0)
        {
            adapterclass=new AdapterClass(getApplicationContext(),al);
            lvMedicinelist.setAdapter(adapterclass);

            Toast.makeText(this,"Searching.....",Toast.LENGTH_LONG).show();
        }

     //  Toast.makeText(this,"Work",Toast.LENGTH_LONG).show();

    }

    public static class MedicineActivity extends ListActivity implements AdapterView.OnItemClickListener {

            Search searchs;
            List<Search> listMedicine;
            ArrayAdapter<String> myAdapter;
            private SqlLiteManger sqlLiteManger;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.medicine_layout);

                    sqlLiteManger=new SqlLiteManger(this);
                    sqlLiteManger.open();
                   searchs = new Search();

                    // find all generic
                    listMedicine = sqlLiteManger.getsearchMedicine(String);
                    List<String> listTitle = new ArrayList<String>();

                    for (int i = 0; i < listMedicine.size(); i++) {
                            listTitle.add(i, listMedicine.get(i).getMed_name());

                    }


                    myAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.listText, listTitle);
                    getListView().setOnItemClickListener(this);
                    setListAdapter(myAdapter);
            }



       @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    // start MedicineActivity with extras the generic id
                    Intent intent = new Intent(this, ExtendedMedicineActivity.class);
                    intent.putExtra("search", listMedicine.get(position).getMed_name());
                    startActivityForResult(intent, 1);
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
*/
