package com.example.ocean14;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class presentacionActivity extends AppCompatActivity {
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentacion2);
        progressBar=findViewById(R.id.progressBar2);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
                boolean sesion=preferences.getBoolean("sesion",false);

                if (sesion){
                    Intent intent=new Intent(getApplicationContext(),Mostrar_Usuario.class);
                    startActivity(intent);
                    finish();


                }else{
                    Intent intent=new Intent(getApplicationContext(),IniciarSesion.class);
                    startActivity(intent);
                    finish();



                }
            }
        },1000);
    }
}