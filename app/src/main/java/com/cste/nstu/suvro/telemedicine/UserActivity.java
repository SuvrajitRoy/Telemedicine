package com.cste.nstu.suvro.telemedicine;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class UserActivity extends Activity implements View.OnClickListener {

    EditText name, email, gender, age, skype, mob;
    Button register;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);

        name = (EditText) findViewById(R.id.etname);
        email = (EditText) findViewById(R.id.etemail);
        gender = (EditText) findViewById(R.id.etgender);
        age = (EditText) findViewById(R.id.etage);
        skype = (EditText) findViewById(R.id.etphoneNo);
        mob = (EditText) findViewById(R.id.etphoneNo);
        register = (Button) findViewById(R.id.btnRegister);

        register.setOnClickListener(this);

        database = new Database(this);

    }


    @Override
    public void onClick(View v) {

        String uName = name.getText().toString();
        String uEmail = email.getText().toString();
        String uGender = gender.getText().toString();
        String uAge = age.getText().toString();
        String uSkype = skype.getText().toString();
        String uMobile = mob.getText().toString();

        if (uName.equals("") || uEmail.equals("") || uGender.equals("") || uAge.equals("") || uSkype.equals("") || uMobile.equals("")) {
            Toast.makeText(getApplicationContext(), "You Cannot leave any field Blank Except Mobile Number", Toast.LENGTH_LONG).show();
            return;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}