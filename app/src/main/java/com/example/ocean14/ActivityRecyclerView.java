package com.example.ocean14;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class ActivityRecyclerView extends AppCompatActivity {

    final private int REQUEST_CODE_ASK_PERMISSION =111 ;
    //Variable a utilizar
    EditText etBuscador;
    String ciudad,cp,colonia,calle,estado,domicilio,c,co;
    TextView tvubicacion;
    ImageButton btnpantallasesion;
    Context context=this;
    RecyclerView rvLista;
    AdaptadorNegocio adaptador;
    List<Negocio> listaNegocio;
    FlipperLayout flipperLayout;
    String numberrandom;
     FirebaseAnalytics mFirebaseAnalytics;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        final Random myramdom=new Random();//variable para numero aleatorio
        solicitarpermisos();

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);//dar acceso a internet
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        //El flipperlayaut es para que haga el efecto carruceal a la imagenes del baner de la parte inferior de la pantalla
        flipperLayout=findViewById(R.id.flipper);
        String[] imageurls=new String[]{ //se ponen los links de donde se extraeran las imagenes
                "https://seccionazul.000webhostapp.com/baners/baner1.png",
                "https://seccionazul.000webhostapp.com/baners/baner2.png",
                "https://seccionazul.000webhostapp.com/baners/baner3.png",
                "https://seccionazul.000webhostapp.com/baners/baner4.png",
                "https://seccionazul.000webhostapp.com/baners/baner5.png",
                "https://seccionazul.000webhostapp.com/baners/baner6.png",
                "https://seccionazul.000webhostapp.com/baners/baner7.png",
                "https://seccionazul.000webhostapp.com/baners/baner8.png",
                "https://seccionazul.000webhostapp.com/baners/baner9.png",
                "https://seccionazul.000webhostapp.com/baners/baner10.png",


        };
        //Esto es para que automaticamente se visiaulice una imagen cada ciwerto tiempo
        for(int i=0;i<imageurls.length;i++){
            numberrandom=String.valueOf(myramdom.nextInt(10));
            FlipperView flipperView=new FlipperView(getBaseContext());
            flipperView.setImageUrl(imageurls[Integer.parseInt(numberrandom)]);
            flipperLayout.addFlipperView(flipperView);
        }


        tvubicacion=(TextView)findViewById(R.id.tvubicacion);
        recuperarPreferencias();//recupera la infomacion de la ubicacion


        //validara si hay internet, y si no mostrara un mensaje de error
        if (networkInfo != null && networkInfo.isConnected()) {
            // Si hay conexión a Internet en este momento
        } else {
            // No hay conexión a Internet en este momento
            AlertDialog.Builder alerta=new AlertDialog.Builder(ActivityRecyclerView.this);
            alerta.setMessage("No cuenta con acceso a internet, Intente de nuevo")
                    .setCancelable(false)
                    .setNegativeButton("salir", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog titulo=alerta.create();
            titulo.setTitle("Acceso a Internet");
            titulo.show();
        }


        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.busqueda);
        //mostrara el menu de navegacion en la parte inferior
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull  MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.busqueda:

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




        //eventos de el buscador que se ejecutaran antes, durante y despues de hacer una busqueda
        etBuscador = findViewById(R.id.etBuscador);
        etBuscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                gps();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                gps();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(cp!=null && ciudad!=null){
                    rvLista.setLayoutManager(new GridLayoutManager(ActivityRecyclerView.this, 1));//mostrara la lista segun la busqueda
                    filtrar(s.toString());//manda a llamar a la funcion filtrar
                }else{
                    gps();//manda a llamar a la funcion gps
                }


            }
        });


        rvLista = findViewById(R.id.rvLista);


        listaNegocio = new ArrayList<>();
        obtenerNegocio();//manda a llamar a la funcion obtenerNegocio

        adaptador = new AdaptadorNegocio(this, listaNegocio);

        rvLista.setAdapter(adaptador);
        //da permisos de ubicacion
        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
        }else{
            gps();

        }
        btnpantallasesion=(ImageButton)findViewById(R.id.btnpantallasesion);
        //evento click de el boton de iniciar sesion
        btnpantallasesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),presentacionActivity.class);
                startActivityForResult(intent,0);

            }
        });


    }
    //funcion para guardar la ubicaciopn actual
    private void GuardarPreferencias(){
        SharedPreferences preferences=getSharedPreferences("preferenciasubi", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("domicilio",domicilio);
        editor.putString("ciudad",c);
        editor.putString("cp",co);
        editor.putString("colonia",colonia);
        editor.putString("estado",estado);
        editor.putString("calle",calle);
        editor.commit();
    }

    //funcion para recuperar ubicacion actual
    private void recuperarPreferencias(){
        SharedPreferences preferences=getSharedPreferences("preferenciasubi",Context.MODE_PRIVATE);
        tvubicacion.setText(preferences.getString("domicilio",""));
        ciudad=preferences.getString("ciudad","");
        cp=preferences.getString("cp","");
        colonia=preferences.getString("colonia","");
        estado=preferences.getString("estado","");
        calle=preferences.getString("calle","");
    }

    //funcion para saber si esta activado o desctivado el gps
    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Tu GPS parece estar desactivado, ¿quieres activarlo?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    //funcion para obtener la ubicacion actual y guardar en variables
    private void gps(){
        LocationManager locationManager=(LocationManager)ActivityRecyclerView.this.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Geocoder geocoder=new Geocoder(getApplicationContext(),Locale.getDefault());
                try {
                    List<Address> direccion=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                    domicilio=direccion.get(0).getAddressLine(0);
                    tvubicacion.setText(domicilio);
                    ciudad=direccion.get(0).getLocality();
                    cp=direccion.get(0).getPostalCode();
                    colonia=direccion.get(0).getSubLocality();
                    estado=direccion.get(0).getAdminArea();
                    calle=direccion.get(0).getThoroughfare();
                    GuardarPreferencias();



                }catch (IOException error){

                }

            }
        };
        int permissionCheck= ContextCompat.checkSelfPermission(ActivityRecyclerView.this, Manifest.permission.ACCESS_FINE_LOCATION);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListener);



    }
    //solicita permisos de ubicaion al usuario
    private void solicitarpermisos(){
        int permisogps=ActivityCompat.checkSelfPermission(ActivityRecyclerView.this,Manifest.permission.ACCESS_FINE_LOCATION);
        if (permisogps!=PackageManager.PERMISSION_GRANTED){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_ASK_PERMISSION);
            }
        }

    }

    //funcion para extraer de la base de datos la informacion de cada negocio
    public void obtenerNegocio() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.URL_NEGOCIO),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Negocio");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                listaNegocio.add(
                                        new Negocio(
                                                jsonObject1.getString("nombre"),
                                                jsonObject1.getString("servicio"),
                                                jsonObject1.getString("telefono"),
                                                jsonObject1.getString("domicilio"),
                                                jsonObject1.getString("hora"),
                                                jsonObject1.getString("facebook"),
                                                jsonObject1.getString("imagen"),
                                                jsonObject1.getString("telefonodos"),
                                                jsonObject1.getString("whatsapp"),
                                                jsonObject1.getString("correo_electronico"),
                                                jsonObject1.getString("instagram"),
                                                jsonObject1.getString("youtube"),
                                                jsonObject1.getString("twitter"),
                                                jsonObject1.getString("ciudad"),
                                                jsonObject1.getString("codigo_postal"),
                                                jsonObject1.getString("colonia"),
                                                jsonObject1.getString("estado"),
                                                jsonObject1.getString("calle")
                                        )
                                );
                            }
                            adaptador = new AdaptadorNegocio(ActivityRecyclerView.this, listaNegocio);
                            rvLista.setAdapter(adaptador);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );
        requestQueue.add(stringRequest);
    }
    //funcion para filtrar los datos que se mostraran en el buscador segun la ubicacion del suario y lo que escriba en el buscador
    public void filtrar(String texto) {
        ArrayList<Negocio> filtarLista = new ArrayList<>();

        for (Negocio negocio : listaNegocio) {
            if(negocio.getEstado().contains(estado.toString())){
                if (negocio.getCalle().contains(calle.toString())){
                    if (negocio.getNombre().contains(texto.toLowerCase())) {
                        filtarLista.add(negocio);

                    }else{
                        if (negocio.getServicio().contains(texto.toLowerCase())) {

                            filtarLista.add(negocio);
                        }
                    }
                }else{
                    if (negocio.getColonia().contains(colonia.toString())){
                        if (negocio.getNombre().contains(texto.toLowerCase())) {
                            filtarLista.add(negocio);

                        }else{
                            if (negocio.getServicio().contains(texto.toLowerCase())) {

                                filtarLista.add(negocio);
                            }
                        }
                    }else{
                        if(negocio.getCodigo_postal().contains(cp.toString())) {
                            if (negocio.getNombre().contains(texto.toLowerCase())) {
                                filtarLista.add(negocio);

                            }else{
                                if (negocio.getServicio().contains(texto.toLowerCase())) {

                                    filtarLista.add(negocio);
                                }
                            }
                        }else{
                            if (negocio.getCiudad().contains(ciudad.toString())){
                                if (negocio.getNombre().contains(texto.toLowerCase())) {
                                    filtarLista.add(negocio);

                                }else{
                                    if (negocio.getServicio().contains(texto.toLowerCase())) {

                                        filtarLista.add(negocio);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        adaptador.filtrar(filtarLista);
    }
}