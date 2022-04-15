package com.salmanvirji.testcryptov2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
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
    ArrayList<Crypto> cryptoArr = new ArrayList<>();
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fav_items);

        dbHandler = new DBHandler(Favorites.this);

        txtSymbol =(TextView)findViewById(R.id.textView3);
        txtName =(TextView)findViewById(R.id.txtSymbol);
        txtPrice =(TextView)findViewById(R.id.txtPrice);
        txtInput = (TextView)findViewById(R.id.txtInput);
        button = (Button)findViewById(R.id.btnEnter);

        //get the bundle
        Bundle b = getIntent().getExtras();
        //getting the arraylist from the key
        cryptoArr =  b.getParcelableArrayList("Crypto");


        //Log.i("List", "size :: "+ q.get(2).getSymbol().toString());

        button =(Button) findViewById(R.id.btnEnter);
        button.setOnClickListener(v -> setFavs());



    }


    public void setFavs(){
        // array for favourites
        ArrayList<String> favArr = getTable();

        //favArr.add("Bitcoin");

        // comparing crypto array with fav list
        int i = 0;
        for(int w = 0 ; w < cryptoArr.size(); w++){
            for(int r = 0; r < favArr.size(); r++){
                if(cryptoArr.get(w).getName().equals(favArr.get(r)))
                    i=w;

                txtName.setText(cryptoArr.get(i).getName());
                txtSymbol.setText(cryptoArr.get(i).getSymbol());
                txtPrice.setText(Double.toString(cryptoArr.get(i).getPrice()));


            }
        }

    }

    public ArrayList<String> getTable() {
        // new array for table data
        ArrayList<String> tableArr = this.dbHandler.readFavItems();

        //SQLiteDatabase db = this.getRead;

        for (int k = 0; k < 5; k++){

            String item = tableArr.get(k);



        }


        return tableArr;
    }

}



