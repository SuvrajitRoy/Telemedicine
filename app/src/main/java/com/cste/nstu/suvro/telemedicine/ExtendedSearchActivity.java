package com.cste06.nstu.suvro.telemedicine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by SUVRO on 06-04-16.
 */
public class ExtendedSearchActivity extends Activity {

    TextView  gen_name,indication,dosage,contradication,side,action, size,com_name;
    Search selectedMedicine;
    private SqlLiteManger sqlLiteManger;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_search);

        gen_name = (TextView) findViewById(R.id.tvGenName);
        indication = (TextView) findViewById(R.id.tvIndication);
        dosage = (TextView) findViewById(R.id.tvDosage);
        contradication = (TextView) findViewById(R.id.tvContraindication);
        side = (TextView) findViewById(R.id.tvSide);
        action = (TextView) findViewById(R.id.tvAction);
        size = (TextView) findViewById(R.id.tvSizePrice);
        com_name = (TextView) findViewById(R.id.tvCompany);


        // get the intent that we have passed from AndroidDatabaseExample
        Intent intent = getIntent();
        int med_id = intent.getIntExtra("medicine", -1);

        // open the database of the application context
        sqlLiteManger = new SqlLiteManger(getApplicationContext());

        // read the doctor with "id" from the database
        selectedMedicine = sqlLiteManger.getSearchMedicineDetail(med_id);

        //   long inserted = database.insertUser(user);



        initializeViews();
    }



    public void initializeViews() {


        gen_name.setText(selectedMedicine.getGen_name());
        indication.setText(selectedMedicine.getIndication());
        dosage.setText(selectedMedicine.getDosage());
        contradication.setText(selectedMedicine.getContraindication());
        side.setText(selectedMedicine.getSideEffect());
        action.setText(selectedMedicine.getAction());
        size.setText(selectedMedicine.getSize_price());
        com_name.setText(selectedMedicine.getCom_name());




    }
}
