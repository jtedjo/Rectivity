package Classes;

import android.content.Context;
import android.content.BroadcastReceiver;
import android.util.Log;
import android.content.Intent;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.*;


import com.android.volley.toolbox.*;

import org.json.JSONException;
import org.json.JSONObject;

import static java.lang.System.*;

import com.example.leole.rectivity.R;
/**
 * Call to API to get conditions (Location, Weather, and Pollen)
 */

public class CurrentCondition extends BroadcastReceiver {
    private Context context;
    String[] API = {"breeze", "weather"};
    private String currentAir;

    public CurrentCondition(Context c ){
        this.context = c;
    }

    public void getWeather(double lat, double lon) {
        String url = "https://api.darksky.net/forecast/"+context.getResources().getString(R.string.darkSkyKey)+ "/" +
                    + lat+"," + lon;
        Log.i("weather URL", url);
        callingAPI(url, 1);
    }

    public void getPollen(double lat, double lon) {
        String url = "https://api.breezometer.com/baqi/?lat=" +lat+"&lon="+lon+"&key="
                + context.getResources().getString(R.string.breezoMeterKey);
        callingAPI(url, 0);
    }

    private void callingAPI(final String url, final int type){
        RequestQueue queue = Volley.newRequestQueue(context);
        Log.i("API", "Call");

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        onSuccess(response, type);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("security.error", error.toString());
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(postRequest);

        return ;
    }

    public void onSuccess(String response, int type) {
        String a = response;
        Log.i("in onSuccess", response);
        if(type == 0) {
            Log.i("onSuccess", "calling to process breeze JSON");
            processBreezeJson(response);
        } else {
            Log.i("onSuccess", "calling to process weather JSON");
            processWeatherJson(response);
        }
    }

    public void processWeatherJson(String response) {
            //process  Weather Json
            try {
                String currentObs  =  (new JSONObject(response)).getString("currently").toString();
                Log.i("currently", currentObs);
                JSONObject jsonCurrenObs = new JSONObject(currentObs);
                //TODO do something with summary weather info
                Log.i("getting Condition", jsonCurrenObs.getString("summary"));




                String temp = jsonCurrenObs.getString("temperature");
                String humidity = jsonCurrenObs.getString("humidity");

                Log.i("temp",temp);
                Log.i("humidity",humidity);


            }
            catch (JSONException e) {
                e.printStackTrace();
            }
    }

    public void processBreezeJson(String response) {
        try {
            String currentObs  =  (new JSONObject(response)).getString("breezometer_aqi");
            Log.i("Air Quality", currentObs);

            //TODO do something with Air Quality  info



            currentAir = currentObs;

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onReceive(Context con, Intent intent) {
        //TODO call the API for current weather.  Might want to be once every hour

    }
};
