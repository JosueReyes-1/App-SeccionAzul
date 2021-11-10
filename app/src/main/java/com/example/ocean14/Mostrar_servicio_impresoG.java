package com.example.ocean14;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Mostrar_servicio_impresoG extends AppCompatActivity {
    ImageButton btnregresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_servicio_impreso_g);
        btnregresar=(ImageButton)findViewById(R.id.btnregre4);
        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}