package com.example.ocean14;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.SliderView;

public class S_impresos extends AppCompatActivity {

    SliderView sliderView;
    int[] images = {R.drawable.impresos,
            R.drawable.triptico,
            R.drawable.impresos,
            R.drawable.video,
            R.drawable.impresos,
            R.drawable.impresos};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpresos);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.busqueda);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.busqueda:
                        startActivity(new Intent(getApplicationContext()
                                ,ActivityRecyclerView.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.servicios:
                        startActivity(new Intent(getApplicationContext()
                                ,Menu_servicio.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.videos:
                        startActivity(new Intent(getApplicationContext()
                                ,MostrarVideos.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}