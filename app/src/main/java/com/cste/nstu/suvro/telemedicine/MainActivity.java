package com.cste06.nstu.suvro.telemedicine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);

        /**
         * Creating all buttons instances
         * */
        // Dashboard doctor button
        Button btn_doc = (Button) findViewById(R.id.btn_doc);

        // Dashboard user button
        Button btn_usr = (Button) findViewById(R.id.btn_usr);

        // Dashboard Medicine button
        Button btn_med = (Button) findViewById(R.id.btn_med);

        // Dashboard Generic button
        Button btn_gen = (Button) findViewById(R.id.btn_gen);

        // Dashboard Search button
        Button btn_ser = (Button) findViewById(R.id.btn_ser);


        /**
         * Handling all button click events
         * */

        // Listening to Doctor button click
        btn_doc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), DoctorActivity.class);
                startActivity(i);
            }
        });

        // Listening Friends button click
        btn_usr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), UserActivity.class);
                startActivity(i);
            }
        });

        // Listening Messages button click
        btn_med.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), MedicineActivity.class);
                startActivity(i);
            }
        });

        // Listening to Places button click
        btn_gen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), GenericActivity.class);
                startActivity(i);
            }
        });

        // Listening to Events button click
        btn_ser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(i);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //*//*noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        /*switch (item.getItemId()) {
            case R.id.action_settings:
                // Single menu item is selected do something
                // Ex: launching new activity/screen or show alert message
                Toast.makeText(MainActivity.this, "Setting is Selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.menu_about:
                Toast.makeText(MainActivity.this, "about is Selected", Toast.LENGTH_SHORT).show();
                return true;*/

          //  default:
                return super.onOptionsItemSelected(item);
        }
    }
