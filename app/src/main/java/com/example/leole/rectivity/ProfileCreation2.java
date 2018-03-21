package com.example.leole.rectivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ProfileCreation2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation2);
        Context context = getApplicationContext();

        Spinner dropdown1 = findViewById(R.id.favorite_spinner1);
        Spinner dropdown2 = findViewById(R.id.favorite_spinner2);
        Spinner dropdown3 = findViewById(R.id.favorite_spinner3);
        //create a list of items for the spinner.
        String[] items = new String[]{"Walking", "Running", "Biking"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown1.setAdapter(adapter);
        dropdown2.setAdapter(adapter);
        dropdown3.setAdapter(adapter);

        TextView favText1 = (TextView) findViewById(R.id.textView6);
        favText1.setText("Select favorite activity");
        TextView favText2 = (TextView) findViewById(R.id.textView7);
        favText2.setText("Select second favorite activity");
        TextView favText3 = (TextView) findViewById(R.id.textView8);
        favText3.setText("Seclect third favorite activity");
        TextView signupText = (TextView) findViewById(R.id.textView4);
        signupText.setText("SignUp");
        EditText weightText = (EditText) findViewById(R.id.editText8);
        weightText.setText("Weight");
        EditText goalText = (EditText) findViewById(R.id.editText9);
        goalText.setText("Enter goal (minutes)");
        Button p4_button = (Button)findViewById(R.id.button4);
        p4_button.setText("Submit");


        final Intent mLaunchIntent = getIntent();
        p4_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toMain = new Intent(getApplicationContext(), MainActivity.class);
                //toMain.putExtra(Intent.EXTRA_INTENT, mLaunchIntent);
                Bundle bundles = getIntent().getExtras();
                String firstName = bundles.getString("first name");
                String lastName = bundles.getString("last name");
                String userName = bundles.getString("user name");
                String age = bundles.getString( "age");
                String height = bundles.getString("height");

                Log.i("first name in profileCreation 2", ""+firstName);

                toMain.putExtra("first name", firstName);
                toMain.putExtra("last name", lastName);
                toMain.putExtra("user name", userName);
                toMain.putExtra("age", age);
                toMain.putExtra("height", height);
                startActivity(toMain);
            }
        });


    }
}
