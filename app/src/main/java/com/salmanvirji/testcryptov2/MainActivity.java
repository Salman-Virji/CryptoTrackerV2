package com.salmanvirji.testcryptov2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Crypto> Crypto = new ArrayList<>();


    private RecyclerView recyclerView;

    Button btn2;
    Button btn3;
    Button btnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        Crypto = new ArrayList<>();
        getCrypto();
        setAdapter();
        btn2 =(Button) findViewById(R.id.button2);
        btn2.setOnClickListener(v -> openFavs());

        btn3 =(Button) findViewById(R.id.button3);
        btn3.setOnClickListener(v -> openSettings());

        btnRefresh =(Button) findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(v -> setAdapter());



    }



    public void openFavs(){
        // Intent to open the favorites screen
        Intent intent1 = new Intent(this, Favorites.class);
        //passing the Crypto array with the intent as a parcelable
        intent1.putParcelableArrayListExtra("Crypto", Crypto);
        startActivity(intent1);
        finish();
    }

    public void openSettings(){
        // Intent to open the settings screen
        Intent intent2 = new Intent(this, Settings.class);
        startActivity(intent2);
        finish();
    }



    //Adapter for the RecycleViewer
    //Reference: https://developer.android.com/guide/topics/ui/layout/recyclerview
    private void setAdapter() {
        recyclerAdapter adapter = new recyclerAdapter(Crypto);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    public void getCrypto() {
        //Clear the list of all values
        Crypto.clear();



/*
     //Place holder values to not use up API limit
       Crypto.add(new Crypto(" 123","23 ",1));
        Crypto.add(new Crypto(" 123","23 ",1));
        Crypto.add(new Crypto(" 123","23 ",1));
        Crypto.add(new Crypto(" 123","23 ",1));
        Crypto.add(new Crypto(" 123","23 ",1));
        Crypto.add(new Crypto(" 123","23 ",1));
        Crypto.add(new Crypto(" 123","23 ",1));
        Crypto.add(new Crypto(" 123","23 ",1));
        Crypto.add(new Crypto(" 123","23 ",1));*/


        // Empty addition to the Crypto ArrayLit to initalize the array
        Crypto.add(new Crypto("Please Refresh  "," ",1));






        //Reference for api JSON parsing  https://youtu.be/y2xtLqP8dSQ
        //Documentation CMC API: https://coinmarketcap.com/api/documentation/v1/#operation/getV1CryptocurrencyTrendingLatest
        //Building the API object
        String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //Getting the "data" array from the API
                            JSONArray dataArray = response.getJSONArray("data");
                            //Iterating through the data array
                            for (int i = 0; i < dataArray.length(); i++) {

                                JSONObject dataObj = dataArray.getJSONObject(i);
                                // Getting the Name of the crypto from the data array
                                String name = dataObj.getString("name");
                                // Getting the Name of the crypto from the data array
                                String symbol = dataObj.getString("symbol");
                                // Getting the Quote Array from the API
                                JSONObject quote = dataObj.getJSONObject("quote");
                                // Getting the Price for the Crypto in USD from the quote array
                                JSONObject USD = quote.getJSONObject("USD");
                                //Getting the price and statically converting(price USD to CAD 4/14/2022) to Canadian dollar and rounding it
                                double price = USD.getDouble("price");
                                double priceToCAD =  (price * 1.26);
                                double priceRounded = Math.round(priceToCAD);

                                // Adding each Crypto to the ArrayList
                                Crypto.add(new Crypto(name,symbol,priceRounded));





                            }

                        } catch (JSONException e) {
                            //Error catch
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            // Passing the key as well as the API Key to access the API
            @Override
            public Map<String, String> getHeaders() {

                HashMap<String, String> headers = new HashMap<>();
                headers.put("X-CMC_PRO_API_KEY", "ac3ebeda-8e2a-44fc-88a9-58f77257426f");

                return headers;
            }




        };
        queue.add(jsonObjectRequest);


    }


}




