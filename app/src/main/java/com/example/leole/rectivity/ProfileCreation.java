package com.example.leole.rectivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileCreation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation);

        TextView headerText = (TextView) findViewById(R.id.signupTxtView);
        headerText.setText("SignUp");
        final EditText firstText = (EditText) findViewById(R.id.firstNameTextBox);
        firstText.setText("First Name");
        final EditText lastText = (EditText) findViewById(R.id.lastNameTextBox);
        lastText.setText("Last Name");
        final EditText userText = (EditText) findViewById(R.id.editText3);
        userText.setHint("Username");
        final EditText passwordText = (EditText) findViewById(R.id.editText4);
        passwordText.setHint("Password");
        final EditText ageText = (EditText) findViewById(R.id.ageBox);
        ageText.setText("Age");
        final EditText heightText = (EditText) findViewById(R.id.HeightBox);
        heightText.setText("Height");
        RadioButton male_button = (RadioButton)findViewById(R.id.maleSelect);
        male_button.setText("Male");
        RadioButton female_button = (RadioButton)findViewById(R.id.femaleSelect);
        female_button.setText("Female");


        Button next_button = (Button)findViewById(R.id.nextBtn);
        next_button.setText("Next");

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goTonext = new Intent(getApplicationContext() , ProfileCreation2.class);
                String firstName =firstText.getText().toString();
                goTonext.putExtra("first name", firstName);
                String lastName = lastText.getText().toString();
                goTonext.putExtra("last name", lastName);
                String userName = userText.getText().toString();
                goTonext.putExtra("user name", userName);
                String age = ageText.getText().toString();
                goTonext.putExtra("age", age);
                String height = heightText.getText().toString();
                goTonext.putExtra("height", height);
                startActivity(goTonext);
            }
        });

    }
}
