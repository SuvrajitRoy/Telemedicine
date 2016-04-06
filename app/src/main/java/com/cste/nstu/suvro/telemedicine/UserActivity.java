package com.cste.nstu.suvro.telemedicine;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class UserActivity extends Activity implements View.OnClickListener {

    EditText name, email, age, skype, mob;
    TextView tvRadio;
    RadioGroup rg;
    RadioButton rgbtn;
    Button register;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);

        name = (EditText) findViewById(R.id.etname);
        email = (EditText) findViewById(R.id.etemail);
       // gender = (EditText) findViewById(R.id.etgender);
        age = (EditText) findViewById(R.id.etage);
        skype = (EditText) findViewById(R.id.etphoneNo);
        mob = (EditText) findViewById(R.id.etphoneNo);

        tvRadio= (TextView) findViewById(R.id.tvRadio);

        register = (Button) findViewById(R.id.btnRegister);

        register.setOnClickListener(this);

        database = new Database(this);


         rg = (RadioGroup) findViewById(R.id.rdGroup);
        rgbtn = (RadioButton) findViewById(rg.getCheckedRadioButtonId());

        /*final String value = ((RadioButton)findViewById(rg.getCheckedRadioButtonId() )).getText().toString();

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                Toast.makeText(getBaseContext(), value, Toast.LENGTH_SHORT).show();
            }
        });*/





}


    @Override
    public void onClick(View v) {

        String uName = name.getText().toString();
        String uEmail = email.getText().toString();
        String uGender = rgbtn.getText().toString();;
        String uAge = age.getText().toString();
        String uSkype = skype.getText().toString();
        String uMobile = mob.getText().toString();

        if (uName.equals("") || uEmail.equals("") || uGender.equals("") || uAge.equals("") || uSkype.equals("") || uMobile.equals("")) {
            Toast.makeText(getApplicationContext(), "You Cannot leave any field Blank Except Mobile Number", Toast.LENGTH_LONG).show();
           // return ;
        } else {
            User user = new User(uName, uEmail, uGender, Integer.parseInt(uAge), uSkype, uMobile);
//            Toast.makeText(getApplicationContext(), user.getString(), Toast.LENGTH_LONG).show();
            long inserted = database.insertUser(user);
            if (inserted >= 0) {
                finish();
                Toast.makeText(getApplicationContext(), "Login SuccessFull", Toast.LENGTH_LONG).show();

            }

        }
    }


}