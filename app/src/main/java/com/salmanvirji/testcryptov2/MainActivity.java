package com.salmanvirji.testcryptov2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    public ArrayList<String> arr = new ArrayList<>();

    private RecyclerView recyclerView;
    TextView coin;
    Button btn2 ,btnfav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //coin =findViewById(R.id.textView9);
        recyclerView = findViewById(R.id.recyclerView);

        Crypto = new ArrayList<>();
        arr = new ArrayList<>();
        getCrypto();
        setAdapter();



        btn2 =(Button) findViewById(R.id.button2);
        btn2.setOnClickListener(v -> openFavs());
        //System.out.println("Crypto: "+ Crypto.get(0));
        //coin.setText(Crypto.get(0).getName());
        //btnfav =(Button) findViewById(R.id.button5);
        //btnfav.setOnClickListener(v -> readCrypto());

    }

    public void openFavs(){


        Intent intent1 = new Intent(this, Favorites.class);
        intent1.putParcelableArrayListExtra("Crypto", Crypto);


        startActivity(intent1);
        finish();
    }



    private void setAdapter() {
        recyclerAdapter adapter = new recyclerAdapter(Crypto);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }


    public void getCrypto() {

        Crypto.clear();
        /*Crypto.add(new Crypto("Hello1"));
        Crypto.add(new Crypto("Hello2"));
        Crypto.add(new Crypto("Hello3"));
        Crypto.add(new Crypto("Hello4"));
        Crypto.add(new Crypto("Hello5"));
        Crypto.add(new Crypto("Hello1"));
        Crypto.add(new Crypto("Hello2"));
        Crypto.add(new Crypto("Hello3"));
        Crypto.add(new Crypto("Hello4"));
        Crypto.add(new Crypto("Hello5"));*/

       /*
        Crypto.add(new Crypto(" 123","23 ",1));
        Crypto.add(new Crypto(" 123","23 ",1));
        Crypto.add(new Crypto(" 123","23 ",1));
        Crypto.add(new Crypto(" 123","23 ",1));
        Crypto.add(new Crypto(" 123","23 ",1));
        Crypto.add(new Crypto(" 123","23 ",1));
        Crypto.add(new Crypto(" 123","23 ",1));
        Crypto.add(new Crypto(" 123","23 ",1));
        Crypto.add(new Crypto(" 123","23 ",1));*/

        Crypto.add(new Crypto(" Nameq ","Symbol ",1));



        //arr.add( Crypto.get(0).getPrice());


       String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray dataArray = response.getJSONArray("data");
                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject dataObj = dataArray.getJSONObject(i);
                                String name = dataObj.getString("name");
                                String symbol = dataObj.getString("symbol");
                                JSONObject quote = dataObj.getJSONObject("quote");

                                JSONObject USD = quote.getJSONObject("USD");
                                double price = USD.getDouble("price");

                                double priceToCAD =  (price * 1.26);
                                double priceRounded = Math.round(priceToCAD);
                                Crypto.add(new Crypto(name,symbol,priceRounded));
                                arr.add(name);
                                arr.add(symbol);

                                arr.add(Double.toString(priceRounded));


                                //

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
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





