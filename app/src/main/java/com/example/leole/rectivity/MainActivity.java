package com.example.leole.rectivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Classes.CurrentCondition;
import Classes.Person;
import Classes.PersonActivity;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.INTERNET;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    //charting variables
    private static String TAG = "MainActivity";
    private float[] yData = {25.3f, 10.6f, 66.76f, 44.32f, 46.01f, 16.89f, 23.9f};
    private String[] xData = {"Bicycle", "Running", "Walking"};
    PieChart pieChart;

    //GPS Google Instance Variables
    private static final int RESULT_PERMS_INITIAL=1339;
    private GoogleApiReceiver googleApiReceiver;
    private static final String[] PERMISSIONS={
            ACCESS_FINE_LOCATION,
            ACCESS_COARSE_LOCATION,
            INTERNET
    };


    //private Boolean mLocationPermissionsGranted = false;
    //private Location currentLocation;
    //private FusedLocationProviderClient mFusedLocationProviderClient;
    private static FirebaseDatabase mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();

        //PersonActivity
//        PersonActivity personAct = new PersonActivity(context);
        //TODO Do something with personActivity data
        yData = personAct.processSegment();

        //Accessing Firebase
        initFireBase();

        //chart onCreate
        Log.d(TAG, "onCreate: starting to create Pie Chart");
        pieChart = (PieChart) findViewById(R.id.idPieChart);

        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(50f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setDrawEntryLabels(true);

        addDataSet();
        init();
//
//        getLocationPermission();
//        getDeviceLocation();

        Intent intent = new Intent();
        intent.setAction("com.example.broadcast.MY_NOTIFICATION");
        intent.putExtra("data","Notice me senpai!");
        sendBroadcast(intent);

        double latitude = googleApiReceiver.newLatitude;
        Log.i("current latitude in Main", "" + latitude );

        Log.i("Current Lat", ""+googleApiReceiver.newLatitude);

//        double currentLat = currentLocation.getAltitude();
//        double currentLong = currentLocation.getLongitude();

        //UI Modification for Activity Main
        Button p1_button = (Button)findViewById(R.id.button1);
        p1_button.setText("Running");
        Button p2_button = (Button)findViewById(R.id.button2);
        p2_button.setText("Biking");
        Button p3_button = (Button)findViewById(R.id.button3);
        p3_button.setText("Swimming");
        TextView homeText = (TextView) findViewById(R.id.textView1);
        homeText.setText("Home");
        //ImageView img= (ImageView) findViewById(R.id.image);
        //img.setImageResource(R.drawable.run);


        //Toast.makeText(MainActivity.this,
         //       "Current Latitude : " +latitude, Toast.LENGTH_LONG).show();



//        double lat = 33.7486097;
//        double lon = -117.9776172;
//        CurrentCondition currentCond = new CurrentCondition(context);
//        currentCond.getPollen(lat, lon);
    }

    //add the data sets to the pi
    private void addDataSet() {
        Log.d(TAG, "addDataSet: started");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        //for (int i = 0; i < yData.length; i++){
        //    yEntrys.add(new PieEntry(yData[i], i));
        //}
        yEntrys.add(new PieEntry(yData[0], "Biking"));
        yEntrys.add(new PieEntry(yData[1], "Running"));
        yEntrys.add(new PieEntry(yData[2], "Walking"));
        for (int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }

        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Activity Counter");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);
        pieDataSet.setColors(colors);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }

    public void init() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)
      requestPermissions(); // Android 6.0 + (runtime permission)
    else
      startGoogleApi();

    }

    public void startGoogleApi() {

        googleApiReceiver = new GoogleApiReceiver(this);

    }

    public void requestPermissions() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            List<String> permission_list = new ArrayList<>();

            for(int i = 0; i < PERMISSIONS.length; i++) {
                if(ContextCompat.checkSelfPermission(this,PERMISSIONS[i]) == -1)
                    permission_list.add(PERMISSIONS[i]);
            }

            if(permission_list.size() == 0) {
                startGoogleApi();
            } else {
                String[] NEW_PERMISSIONS = new String[permission_list.size()];
                for(int i = 0; i < permission_list.size(); i ++)
                    NEW_PERMISSIONS[i] = permission_list.get(i);

                requestPermissions(NEW_PERMISSIONS, RESULT_PERMS_INITIAL);
            }
        }
    }

    //get the location permission before using the GPS
    /* private void getLocationPermission(){
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;

                //permission is granted do something
            }else{
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    //get the device location
    private void getDeviceLocation(){
        Log.d(TAG, "getDeviceLocation: getting the devices current location");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try{
            if(mLocationPermissionsGranted){

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "onComplete: found location!");
                            currentLocation = (Location) task.getResult();


                        }else{
                            Log.d(TAG, "onComplete: current location is null");
                            Toast.makeText(MainActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }catch (SecurityException e){
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
        }
    }
    */



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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static final int RC_SIGN_IN = 123;

    public void initFireBase() {
        //TODO get firebase Connection
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("User").child("name");
//        Person person = new Person();
//        person.setAllergy(true);
//        person.setName("H");
//        person.setGender(0);
//        person.setHeight(5.4);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
//    public void ApiCall(){
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//        Log.i("API", "Call");
//        String url = "http://api.wunderground.com/api/e3683e271666b131/conditions/q/CA/San_Francisco.json";
//
//        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>()
//                {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonRes = new JSONObject(response);
//                            JSONObject jsonArray = new JSONObject(jsonRes.getString("current_observation"));
//                            Log.i("getting Condition", jsonArray.getString("weather"));
//                        } catch (JSONException e) {e.printStackTrace();};
//
//                    }
//                },
//                new Response.ErrorListener()
//                {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // error
//                        Log.d("security.error", error.toString());
//                    }
//                });
//
//        // Add the request to the RequestQueue.
//        queue.add(postRequest);
//
//        return ;
//    }
}
