package Classes;

/**
 * Created by Sonny on 3/10/2018.
 */

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import java.util.Iterator;


public class PersonActivity {
    private Context context;
    public PersonActivity(Context cont){
        context = cont;
        processSegment();
    }

    public void processPersonicle(){

        String json = loadJson();
        try{
            JSONObject jsonFile = new JSONObject(json);
            JSONObject lifeLogTotal = new JSONObject(jsonFile.get("lifeLog").toString());

            Iterator dayKeys = lifeLogTotal.keys();
            //Iterate through the days in Lifelog
            JSONArray lifeLog = new JSONArray();
            while(dayKeys.hasNext()) {
                Object key = dayKeys.next();
                JSONObject day = lifeLogTotal.getJSONObject((String) key);
                lifeLog.put(day);
            }
            //Iterate through days
            for(int i = 0; i < lifeLog.length(); i++) {
                try {
                    JSONObject inMobile = new JSONObject(lifeLog.getJSONObject(i)
                            .getString("mobile"));
                    Iterator iter = inMobile.keys();
                    while(iter.hasNext()) {
                        Object acti = iter.next();
                        JSONObject segment = inMobile.getJSONObject((String) acti);
                        Log.i("segment",segment.toString());
                        processActivity(segment);
                    }

                }catch (JSONException e) {
                    //When the mobile was saved as an Array
                    JSONArray inMobile = new JSONArray(lifeLog.getJSONObject(i)
                            .getString("mobile"));
                    for(int j = 0; j < inMobile.length(); j++) {
                        if(!inMobile.get(j).toString()
                                .equalsIgnoreCase("null")) {
                            JSONObject segment = new JSONObject(inMobile.get(j)
                                    .toString());
                            processActivity(segment);
                        }
                    }
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void processActivity(JSONObject segment){
        try {
            JSONObject activity = new JSONObject(segment.getString("activity"));
            JSONArray sequence = new JSONArray(activity.getString("sequence"));
            for(int i = 0; i < sequence.length(); i++) {
                JSONObject sequenceObject=  sequence.getJSONObject(i);
                boolean toAdd = sequenceObject.getString("type").equalsIgnoreCase("walking");

                if(toAdd) {
                    //TODO Do something with the duration
                    sequenceObject.getString("duration");
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    };

    public float[] processSegment() {

        String json = loadJson();
        float[] activityArray = new float[3];
        try{
            JSONObject jsonFile = new JSONObject(json);
            JSONObject mainSegmentObj = jsonFile.getJSONObject("segment");
            Log.i("SegmentObj Length: ", "" + mainSegmentObj.length());

            Iterator segIter = mainSegmentObj.keys();
            while(segIter.hasNext()) {
                //Get day object
                Object key = segIter.next();
                JSONObject dayObject = mainSegmentObj.getJSONObject((String) key);


                //Iteratee through day to get segments
                Iterator dayIter = dayObject.keys();
                while(dayIter.hasNext()) {
                    Object dayKey = dayIter.next();
                    JSONObject daySegmentObject = dayObject.getJSONObject((String) dayKey);
                    JSONArray  activitySet = daySegmentObject.getJSONArray("activitySet");
                    //bicycle
                    activityArray[0] += Double.parseDouble(activitySet.getJSONObject(0).getString("bicycle"))/60;
                    //running
                    activityArray[1] += Double.parseDouble(activitySet.getJSONObject(0).getString("running"))/60;
                    //walking
                    activityArray[2] += Double.parseDouble(activitySet.getJSONObject(0).getString("walking"))/60;
                }
                //activityArray[0]= activityArray[0] /60;
                //activityArray[1] = activityArray[1] /60;
                //activityArray[2] = activityArray[2] /60;

            }
            Log.i("Activity Array Bicycle", "" +activityArray[0]);
            Log.i("Activity Array Running", "" +activityArray[1]);
            Log.i("Activity Array Walking", "" +activityArray[2]);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return activityArray;
    }

    private String loadJson() {
        String json;

        try  {

            byte[] buffer;
            AssetManager asset = context.getAssets();

            try (InputStream input = asset.open("personicle_data.json")) {
                int size = input.available();
                buffer = new byte[size];
                Log.i("Size", ""+ size);
                input.read(buffer);
                input.close();
            }
            json = new String(buffer, "UTF-8");
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}
