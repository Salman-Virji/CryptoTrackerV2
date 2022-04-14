package com.salmanvirji.testcryptov2;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Favorites extends AppCompatActivity {

    TextView txtSymbol , txtName,txtPrice,txtInput;
    Button button;
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

        //get the bundle
        Bundle b = getIntent().getExtras();
        //getting the arraylist from the key
        q =  b.getParcelableArrayList("Crypto");


        //Log.i("List", "size :: "+ q.get(2).getSymbol().toString());

        button =(Button) findViewById(R.id.btnEnter);
        button.setOnClickListener(v -> setFavs());



    }


    public void setFavs(){
        ArrayList<String> arr =new ArrayList<>();

        arr.add("Bitcoin");
        arr.add("Tether");


        //arr. = txtInput.getText().toString();
        int i =0;
        for(int w =0 ; w<q.size(); w++){
            for(int r = 0; r<arr.size(); r++){
                if(q.get(w).getName().equals(arr.get(r)))
                    i=w;
                txtName.setText(q.get(i).getName());
                txtSymbol.setText(q.get(i).getSymbol());
                txtPrice.setText(Double.toString(q.get(i).getPrice()));


            }
            }



        }
}



