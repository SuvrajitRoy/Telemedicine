package com.cste06.nstu.suvro.telemedicine;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.Uri;
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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserActivity extends Activity implements View.OnClickListener {

    EditText name, email, password, gender, age, skype, mob;
    TextView tvRadio;
    RadioGroup rg;
    RadioButton rgbtn;
    Button register;
    private SqlLiteManger sqlLiteManger;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);

        name = (EditText) findViewById(R.id.etname);
        email = (EditText) findViewById(R.id.etemail);
        password = (EditText) findViewById(R.id.etpassword);
        gender = (EditText) findViewById(R.id.etgender);
        age = (EditText) findViewById(R.id.etage);
        skype = (EditText) findViewById(R.id.etphoneNo);
        mob = (EditText) findViewById(R.id.etphoneNo);

        // tvRadio= (TextView) findViewById(R.id.tvRadio);

        register = (Button) findViewById(R.id.btnRegister);

        register.setOnClickListener(this);

        sqlLiteManger = new SqlLiteManger(this);
        // sqlLiteManger.getWritableDatabase();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onClick(View v) {


        String uGender = gender.getText().toString();
        ;
        String uAge = age.getText().toString();
        String uSkype = skype.getText().toString();
        //  String uMobile = mob.getText().toString();

        final String uName = name.getText().toString();

        final String uEmail = email.getText().toString();

        final String pass = password.getText().toString();
      /*  if (!isValidPassword(pass)) {
            password.setError("Invalid Password");
        }*/

        final String uMobile = mob.getText().toString();

        if (!isValidName(uName)) {
            name.setError("Invalid Name");
        }
        else if (!isValidEmail(uEmail)) {
            email.setError("Invalid Email");
        }

       else if (!isValidPassword(pass)) {
            password.setError("Invalid Password");
        }

       else if (!isValidNumber(uMobile)) {
            mob.setError("Invalid Number");
        } else if (uName.equals("") || uEmail.equals("") || pass.equals("") || uGender.equals("") || uAge.equals("") || uSkype.equals("") || uMobile.equals("")) {
            Toast.makeText(getApplicationContext(), "You Cannot leave any field Blank Except Mobile Number", Toast.LENGTH_LONG).show();
            // return ;
        } else {
            User user = new User(uName, uEmail, pass, uGender, uAge, uSkype, uMobile);
            //   User user = new User(uName, uEmail, uGender, Integer.parseInt(uAge), uSkype, uMobile);
//            Toast.makeText(getApplicationContext(), user.getString(), Toast.LENGTH_LONG).show();
            long inserted = sqlLiteManger.insertUser(user);
            if (inserted >= 0) {
                finish();
                Toast.makeText(getApplicationContext(), "Login SuccessFull", Toast.LENGTH_LONG).show();

            }

        }
    }


    // validating name
    private boolean isValidName(String name) {
        String NAME_PATTERN = "[a-zA-z]+([ '-][a-zA-Z]+)*";

        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }

    private boolean isValidNumber(String uMobile) {
        if (uMobile != null && uMobile.length() > 10 && uMobile.length() <= 14) {
            return true;
        }
        return false;
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "User Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.cste06.nstu.suvro.telemedicine/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "User Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.cste06.nstu.suvro.telemedicine/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}