package com.cste06.nstu.suvro.telemedicine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by SUVRO on 06-04-16.
 */
public class ExtendedMedicineActivity extends Activity {

    TextView name,com_name, gen_name, contradication, side, action, size;
    Medicine selectedMedicine;
    private SqlLiteManger sqlLiteManger;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_medicine);

        name = (TextView) findViewById(R.id.tvName);
        com_name = (TextView) findViewById(R.id.tvDesignation);
        gen_name = (TextView) findViewById(R.id.tvQualification);
//        contradication = (TextView) findViewById(R.id.tvSpecalist);
//        side = (TextView) findViewById(R.id.tvLocation);
//        action = (TextView) findViewById(R.id.tvNumber);
//        size = (TextView) findViewById(R.id.tvSizePrice);


        // get the intent that we have passed from AndroidDatabaseExample
        Intent intent = getIntent();
        int med_id = intent.getIntExtra("medicine", -1);

        // open the database of the application context
        sqlLiteManger = new SqlLiteManger(getApplicationContext());

        // read the doctor with "id" from the database
        selectedMedicine = sqlLiteManger.getMedicineDetail(med_id);

        //   long inserted = database.insertUser(user);



        initializeViews();
    }



    public void initializeViews() {
        name.setText(selectedMedicine.getMed_name());
        com_name.setText(selectedMedicine.getCom_name());
        gen_name.setText(selectedMedicine.getGen_name());




    }
}
