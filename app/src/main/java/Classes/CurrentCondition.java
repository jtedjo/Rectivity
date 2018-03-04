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
    private String TAG = "BaseActivity";

   public CurrentCondition(){};


    public void ApiCall(Context context){

        RequestQueue queue = Volley.newRequestQueue(context);
        Log.i("API", "Call");
        String url = "http://api.wunderground.com/api/" + context.getResources().getString(R.string.weatherAPIKey) + "/geolookup/q/37.776289,-122.395234.json";

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonRes = new JSONObject(response);
                            JSONObject jsonArray = new JSONObject(jsonRes.getString("current_observation"));
                            Log.i("getting Condition", jsonArray.getString("weather"));
                        } catch (JSONException e) {e.printStackTrace();};
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
