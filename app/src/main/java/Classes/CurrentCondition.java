package Classes;

import android.content.Context;
import android.util.Log;

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

public class CurrentCondition {
    private Context context;

    public CurrentCondition(Context c ){
        this.context = c;
    };

    private String TAG = "BaseActivity";
    private double lat = 33.7486097;
    private double lon = -117.9776172;
    private String breezeURL  = "https://api.breezometer.com/baqi/?lat=" +lat+"&lon="+lon+"&key=" + "b9023667dc3046d1ad00d71dc2772793";// R.string.breezoMeterKeys;
//    private String weatherURL =  "http://api.wunderground.com/api/" + R.string(R.string.weatherAPIKey) + "/geolookup/q/37.776289,-122.395234.json";
    //TODO refactor to generalize for all API calls
    public void ApiCall(){
        Log.i("APICall Checking breezekeys", String.valueOf(R.string.breezoMeterKeys));
        RequestQueue queue = Volley.newRequestQueue(context);
        Log.i("API", "Call");
        String url = breezeURL;
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        //Testing breeze Connection;
                        Log.i("Breeze", response);
                        //process  Weather Json
//                        try {
//                           JSONObject jsonRes  = (new JSONObject(response)).getString("current_observation").toString();
//                           //Need to get weather jsonRes.getString("weather");
//                            Log.i("getting Condition",jsonRes);
//                        } catch (JSONException e) {e.printStackTrace();};
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



};
