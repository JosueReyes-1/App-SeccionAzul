package com.example.ocean14;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Menu_servicio extends AppCompatActivity {

    ImageButton btnimpreso,btncontactanos,btnvideos,btnredes,btnfotos,btnimpresosgrandes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_servicio);

        btnimpreso=(ImageButton)findViewById(R.id.btnimpreso);
        btncontactanos=(ImageButton)findViewById(R.id.btncontactanos);
        btnfotos=(ImageButton)findViewById(R.id.btnfotos);
        btnredes=(ImageButton)findViewById(R.id.btnredes);
        btnvideos=(ImageButton)findViewById(R.id.btnvideos);
        btnimpresosgrandes=(ImageButton)findViewById(R.id.btnimpresogrande);


        btnimpreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Mostrar_servicio_impresos.class);
                startActivityForResult(intent,0);
            }
        });
        btncontactanos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ContactanosActivity.class);
                startActivityForResult(intent,0);
            }
        });
        btnfotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Mostar_servicio_fotografico.class);
                startActivityForResult(intent,0);
            }
        });
        btnredes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Mostrar_servicio_redes.class);
                startActivityForResult(intent,0);
            }
        });
        btnvideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Mostrar_servicio_videos.class);
                startActivityForResult(intent,0);
            }
        });
        btnimpresosgrandes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Mostrar_servicio_impresoG.class);
                startActivityForResult(intent,0);
            }
        });

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.servicios);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.busqueda:
                        startActivity(new Intent(getApplicationContext()
                                ,ActivityRecyclerView.class));
                        overridePendingTransition(0,0);
                        finish();


                        return true;

                    case R.id.servicios:
                        return true;
                    case R.id.videos:
                        startActivity(new Intent(getApplicationContext()
                                ,MostrarVideos.class));
                        overridePendingTransition(0,0);
                        finish();

                        return true;
                }
                return false;
            }
        });



    }


}