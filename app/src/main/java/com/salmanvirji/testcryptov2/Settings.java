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
    Button btnBack, btnHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_settings);
        btnHome = (Button) findViewById(R.id.button5);
        btnHome.setOnClickListener(v -> goHome());
    }

    public void goHome(){
        Intent intent3 = new Intent(this, MainActivity.class);

        startActivity(intent3);
        finish();
    }
}
