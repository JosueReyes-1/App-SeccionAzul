package com.example.ocean14;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ContactanosActivity extends AppCompatActivity {
    ImageButton btnregresarcontactanos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactanos2);
        btnregresarcontactanos=(ImageButton)findViewById(R.id.btnregresarcontactanos);
        btnregresarcontactanos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}