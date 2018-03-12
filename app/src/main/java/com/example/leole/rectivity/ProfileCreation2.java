package com.example.leole.rectivity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
    }
}
