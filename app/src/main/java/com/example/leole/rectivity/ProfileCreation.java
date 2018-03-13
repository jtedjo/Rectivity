package com.example.leole.rectivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class ProfileCreation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation);

        EditText firstText = (EditText) findViewById(R.id.editText);
        firstText.setText("First Name");
        EditText lastText = (EditText) findViewById(R.id.editText2);
        lastText.setText("Last Name");
        EditText userText = (EditText) findViewById(R.id.editText3);
        userText.setText("Username");
        EditText passwordText = (EditText) findViewById(R.id.editText4);
        passwordText.setText("Password");
        EditText ageText = (EditText) findViewById(R.id.editText5);
        ageText.setText("Age");
        EditText heightText = (EditText) findViewById(R.id.editText6);
        heightText.setText("Height");
        TextView headerText = (TextView) findViewById(R.id.textView6);
        headerText.setText("SignUp");
        Button next_button = (Button)findViewById(R.id.button);
        next_button.setText("Next");
        RadioButton male_button = (RadioButton)findViewById(R.id.radioButton);
        male_button.setText("Male");
        RadioButton female_button = (RadioButton)findViewById(R.id.radioButton2);
        female_button.setText("Female");
    }
}
