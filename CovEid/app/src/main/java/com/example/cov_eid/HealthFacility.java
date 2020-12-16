package com.example.cov_eid;

import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class HealthFacility extends AppCompatActivity implements OnMapReadyCallback
        ,LocationListener,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener{

    private GoogleMap map;
    GoogleApiClient client;
    LocationRequest request;
    LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_facility);
        getSupportActionBar().setTitle("Locate Screening Facility");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.facility_google_map);
        mapFragment.getMapAsync(this);


    }

    public void findScreening(View v){

        StringBuilder stringBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        stringBuilder.append("location="+latLng.latitude + "," +latLng.longitude);
        stringBuilder.append("&radius="+1000);
        stringBuilder.append("&keyword="+"restaurant");
        stringBuilder.append("&key="+getResources().getString(R.string.google_map_key));

        String url = stringBuilder.toString();

        Object dataTransfer[] = new Object[2];
        dataTransfer[0] = map;
        dataTransfer[1] = url;

        GetNearbyPlace getNearbyPlace = new GetNearbyPlace(this);
        getNearbyPlace.execute(dataTransfer);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        client = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        client.connect();

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        request = new LocationRequest().create();
        request.setInterval(1000);
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.FusedLocationApi.requestLocationUpdates(client,request,this);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        if(location == null)
        {
            Toast.makeText(getApplicationContext(),"Location not found", Toast.LENGTH_LONG).show();
        }
        else
        {
            latLng = new LatLng(location.getLatitude(),location.getLongitude());

            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 15);
            map.animateCamera(update);

            MarkerOptions options = new MarkerOptions();
            options.position(latLng);
            options.title("Current Location");
            map.addMarker(options);
        }

    }



    /*private void getCurrentLocation(){
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                if(location != null)
                {
                    currentLat = location.getLatitude();
                    currentLong = location.getLongitude();

                    map.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {

                            maps = googleMap;

                            LatLng latLng = new LatLng(currentLat, currentLong);

                            MarkerOptions options = new MarkerOptions().position(latLng).title("I am there");

                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

                            googleMap.addMarker(options);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults ){
        if(requestCode == 44)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            }
        }

    }*/


    /*

    private class PlaceTask extends AsyncTask<String,Integer,String> {

        @Override
        protected String doInBackground(String... strings) {
            String data = null;
            try {
                data = downloadUrl(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s){
            new ParserTask().execute(s);
        }
    }

    private String downloadUrl(String string) throws IOException {
        URL url = new URL(string);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        InputStream stream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder builder = new StringBuilder();
        String line = "";

        while((line = reader.readLine()) != null)
        {
            builder.append(line);
        }

        String data = builder.toString();
        reader.close();
        return data;

    }

    private class ParserTask extends AsyncTask<String,Integer, List<HashMap<String,String>>>{

        @Override
        protected List<HashMap<String, String>> doInBackground(String... strings) {
            JsonParser jsonParser = new JsonParser();
            List<HashMap<String,String>> mapList = null;
            JSONObject object = null;
            try {
                object = new JSONObject(strings[0]);
                mapList = jsonParser.parseResult(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return mapList;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> hashMaps){

            maps.clear();

            for(int i=0; i<hashMaps.size(); i++){
                HashMap<String, String> hashMapList = hashMaps.get(i);
                double lat = Double.parseDouble(hashMapList.get("lat"));
                double lng = Double.parseDouble(hashMapList.get("lng"));
                String name = hashMapList.get("name");
                LatLng latLng = new LatLng(lat, lng);
                MarkerOptions options = new MarkerOptions();
                options.position(latLng);
                options.title(name);
                maps.addMarker(options);

            }
        }
    }*/

}
