package com.example.ocean14;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Mostrar_Usuario extends AppCompatActivity {
    Button btncerrarsesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_usuario);
        btncerrarsesion=(Button)findViewById(R.id.btncerrarsesion);

        btncerrarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences=getSharedPreferences("preferenciasLogin",Context.MODE_PRIVATE);
                preferences.edit().clear().commit();

                Intent intent=new Intent(getApplicationContext(),IniciarSesion.class);
                startActivity(intent);
                finish();

            }
        });

    }


}