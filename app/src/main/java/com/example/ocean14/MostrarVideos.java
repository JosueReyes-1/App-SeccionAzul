package com.example.ocean14;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MostrarVideos extends AppCompatActivity {

    ImageView btn_link;
    String url="https://www.youtube.com/watch?v=RjGH9JXbg4s";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_videos);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.videos);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull  MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.busqueda:
                        startActivity(new Intent(getApplicationContext()
                                ,ActivityRecyclerView.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.servicios:
                        startActivity(new Intent(getApplicationContext()
                                ,Menu_servicio.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.videos:
                        return true;
                }
                return false;
            }
        });

        btn_link= (ImageView) findViewById(R.id.btn_link);


        btn_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri _link= Uri.parse(url);
                Intent i=new Intent(Intent.ACTION_VIEW,_link);
                startActivity(i);

            }
        });


    }
}