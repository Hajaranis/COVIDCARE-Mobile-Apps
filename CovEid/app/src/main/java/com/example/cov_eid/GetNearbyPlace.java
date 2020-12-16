package com.example.cov_eid;

import android.os.AsyncTask;
import android.content.Context;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import okhttp3.*;
import okhttp3.OkHttpClient;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetNearbyPlace extends AsyncTask<Object,String,String>{

    GoogleMap map;
    String url;
    InputStream is;
    BufferedReader bufferedReader;
    StringBuilder stringBuilder;
    String data;
    Context context;
    OkHttpClient client = new OkHttpClient();

    GetNearbyPlace(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(Object... params) {
        map = (GoogleMap)params[0];
        url = (String)params[1];

        try {
            URL myurl = new URL(url);

            /*Request request = new Request.Builder().url(myurl).build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    data = response.body().string();
                }
            });*/

            HttpURLConnection httpURLConnection = (HttpURLConnection)myurl.openConnection();
            httpURLConnection.connect();
            is = httpURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(is));

            String line = "";
            stringBuilder = new StringBuilder();

            while((line = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(line);
            }

            data = stringBuilder.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    protected void onPostExecute(String s){

        try{
            JSONObject parentObject = new JSONObject(s);
            JSONArray resultArray = parentObject.getJSONArray("results");

            for(int i=0;i<resultArray.length();i++)
            {
                JSONObject jsonObject = resultArray.getJSONObject(i);
                JSONObject locationObject = jsonObject.getJSONObject("geometry").getJSONObject("location");

                String latitude = locationObject.getString("lat");
                String longitude = locationObject.getString("lng");

                JSONObject nameObject = resultArray.getJSONObject(i);

                String hospital_name = nameObject.getString("name");
                String vicinity = nameObject.getString("vicinity");

                LatLng latLng = new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude));

                MarkerOptions options = new MarkerOptions();
                options.title(vicinity);
                options.position(latLng);

                map.addMarker(options);
            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }

    }

}
