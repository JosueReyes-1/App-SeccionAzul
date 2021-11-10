package com.example.ocean14;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class detalles extends AppCompatActivity {
    TextView nombre,servicio,telefono,domicilio,hora,facebook,telefono2,whatsapp,correo_electronico,instagram,youtube,twitter;
    ImageView imagen;
    ImageButton btnregre;

   
    String Nombre,Servicio,Telefono,Domicilio,Hora,Facebook,Telefono2,Whatsapp,Correo_electronico,Instagram,Youtube,Twitter,Imagen,linkfacebook,linktwitter,linkyoutube,linkinstagram;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        nombre=findViewById(R.id.nombre);
        servicio=findViewById(R.id.servicio);
        telefono=findViewById(R.id.telefono);
        domicilio=findViewById(R.id.domicilio);
        hora=findViewById(R.id.hora);
        facebook=findViewById(R.id.facebook);
        telefono2=findViewById(R.id.telefono2);
        whatsapp=findViewById(R.id.whatsapp);
        correo_electronico=findViewById(R.id.correo);
        instagram=findViewById(R.id.instagram);
        youtube=findViewById(R.id.youtube);
        twitter=findViewById(R.id.twitter);
        imagen=(ImageView)findViewById(R.id.img);
        btnregre= (ImageButton) findViewById(R.id.btnregre);


        Nombre=getIntent().getStringExtra("nombre");
        Servicio=getIntent().getStringExtra("servicio");
        Telefono=getIntent().getStringExtra("telefono");
        Domicilio=getIntent().getStringExtra("domicilio");
        Hora=getIntent().getStringExtra("hora");
        Facebook=getIntent().getStringExtra("facebook");
        Telefono2=getIntent().getStringExtra("telefono2");
        Whatsapp=getIntent().getStringExtra("whatsapp");
        Correo_electronico=getIntent().getStringExtra("correo");
        Instagram=getIntent().getStringExtra("instagram");
        Youtube=getIntent().getStringExtra("youtube");
        Twitter=getIntent().getStringExtra("twitter");
        Imagen=getIntent().getStringExtra("imagen");




        nombre.setText(Nombre);
        servicio.setText(Servicio);
        telefono.setText(Telefono);
        domicilio.setText(Domicilio);
        hora.setText(Hora);
        facebook.setText(Facebook);
        telefono2.setText(Telefono2);
        whatsapp.setText(Whatsapp);
        correo_electronico.setText(Correo_electronico);
        instagram.setText(Instagram);
        youtube.setText(Youtube);
        twitter.setText(Twitter);


        facebook=(TextView)findViewById(R.id.facebook);


        btnregre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse(Facebook);
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
        Glide.with(this)
                .load(Imagen)
                .into(imagen);


    }
}