package com.salmanvirji.testcryptov2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Settings extends AppCompatActivity {
    Button btnBack, btnHome , btnFavs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_settings);





        btnHome = (Button) findViewById(R.id.button5);
        btnHome.setOnClickListener(v -> goHome());

        btnFavs = (Button) findViewById(R.id.button8);
        btnFavs.setOnClickListener(v -> openFavs());
    }


    public void goHome(){
        Intent intent3 = new Intent(this, MainActivity.class);

        startActivity(intent3);
        finish();
    }
    public void openFavs(){
        // Intent to open the favorites screen
        ArrayList<Crypto> hi = new ArrayList<>();
        Intent intent5 = new Intent(this, Favorites.class);
        intent5.putParcelableArrayListExtra("Crypto", hi);
        startActivity(intent5);
        finish();

    }
}
