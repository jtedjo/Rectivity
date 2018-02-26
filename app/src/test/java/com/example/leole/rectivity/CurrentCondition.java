package com.example.leole.rectivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.*;

import com.android.volley.toolbox.*;

import org.json.JSONObject;

/**
 * Call to API to get conditions (Location, Weather, and Pollen)
 */

public class CurrentCondition {
    private String TAG = "BaseActivity";

   public CurrentCondition(){};

   public JSONObject callAPI(String url) {
        JSONObject a = new JSONObject();

        url = "http://api.wunderground.com/api/e3683e271666b131/conditions/q/CA/San_Francisco.json";
       // Request a string response from the provided URL.
       StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
               new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       // Display the first 500 characters of the response string.
                       String getResponse = response;
                       System.out.println(getResponse);
                   }
               }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               error.printStackTrace();
           }
             });

       return a;
    }
}
