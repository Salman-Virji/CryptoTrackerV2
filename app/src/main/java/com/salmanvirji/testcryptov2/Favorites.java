package com.salmanvirji.testcryptov2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Favorites extends AppCompatActivity {

    TextView txtSymbol , txtName,txtPrice,txtInput;
    Button button, btnHome,btnSettings;
    ArrayList<Crypto> q = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fav_items);
        txtSymbol =(TextView)findViewById(R.id.textView3);
        txtName =(TextView)findViewById(R.id.txtSymbol);
        txtPrice =(TextView)findViewById(R.id.txtPrice);
        txtInput = (TextView)findViewById(R.id.txtInput);
        button = (Button)findViewById(R.id.btnEnter);
        btnHome = (Button) findViewById(R.id.button4);
        btnHome.setOnClickListener(v -> goHome());

        btnHome = (Button) findViewById(R.id.button7);
        btnHome.setOnClickListener(v -> openSettings());

        //get the bundle
        Bundle b = getIntent().getExtras();
        //getting the arraylist
        q =  b.getParcelableArrayList("Crypto");

        button =(Button) findViewById(R.id.btnEnter);
        button.setOnClickListener(v -> setFavs());



    }


    public void setFavs(){

        ArrayList<String> arr =new ArrayList<>();

        arr.add("Bitcoin");
        arr.add("Tether");
        arr.add("BNB");


        //For loop to iterate through the q arraylist which holds the crypto data from the parcelable intent
        int i =0;
        for(int w =0 ; w<q.size(); w++){
            //Iterating through that array which hold the names of favorited crypto
            for(int r = 0; r<arr.size(); r++){
                //Matching the arr arraylist with the q array list if a match is found setText
                if(q.get(w).getName().equals(arr.get(r)))
                    i=w;
                txtName.setText(q.get(i).getName());
                txtSymbol.setText(q.get(i).getSymbol());
                txtPrice.setText(Double.toString(q.get(i).getPrice()));


            }
        }



    }

    public void goHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void openSettings(){
        // Intent to open the settings screen
        Intent intent2 = new Intent(this, Settings.class);
        startActivity(intent2);
        finish();
    }
}


