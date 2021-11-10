package com.example.ocean14;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Altausuarios extends AppCompatActivity {
    EditText edtid,edtnombre,edtapellido,edtcorreo,edtcontraseña,edttelefono,edtfecha_nac,edtsexo,edtcorreo2;
    Button  btnagregarusuario;
    String nombre,apellido,correo,contraseña,telefono,fecha_nac,sexo,correo2;
    ImageButton btnregresarusu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altausuarios);

        edtid=(EditText)findViewById(R.id.edtid);
        edtnombre=(EditText)findViewById(R.id.edtnombre);
        edtapellido=(EditText)findViewById(R.id.edtapellido);
        edtcorreo=(EditText)findViewById(R.id.edtcorreo);
        edtcontraseña=(EditText)findViewById(R.id.edtcontraseña);
        edttelefono=(EditText)findViewById(R.id.edttelefono);
        edtfecha_nac=(EditText)findViewById(R.id.edtfecha);
        edtsexo=(EditText)findViewById(R.id.edtsexo);
        btnagregarusuario=(Button)findViewById(R.id.btnagregarusuario);
        btnregresarusu= (ImageButton) findViewById(R.id.btnregresaralrausu);
        edtcorreo2=(EditText)findViewById(R.id.edtcorreo2);

        btnregresarusu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnagregarusuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre=edtnombre.getText().toString();
                apellido=edtapellido.getText().toString();
                correo=edtcorreo.getText().toString();
                contraseña=edtcontraseña.getText().toString();
                telefono=edttelefono.getText().toString();
                fecha_nac=edtfecha_nac.getText().toString();
                sexo=edtsexo.getText().toString();
                correo2=edtcorreo2.getText().toString();

                if(correo2.equals(correo)){
                    if (validar()){
                        ejercutarservicio("https://seccionazul.000webhostapp.com/insertar_usuario.php");
                    }
                }else{
                     edtcorreo2.setError("Los correos no coinciden");
                }






                //
            }
        });

    }
    private void ejercutarservicio(String URL){
        StringRequest stringReques = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String, String>();
                parametros.put("id",edtid.getText().toString());
                parametros.put("nombre",edtnombre.getText().toString());
                parametros.put("apellido",edtapellido.getText().toString());
                parametros.put("correo_electronico",edtcorreo.getText().toString());
                parametros.put("contraseña",edtcontraseña.getText().toString());
                parametros.put("telefono",edttelefono.getText().toString());
                parametros.put("fecha_nac",edtfecha_nac.getText().toString());
                parametros.put("Sexo",edtsexo.getText().toString());
                return parametros;
            }

        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringReques);
    }

    public boolean validar(){
        boolean retorno=true;
        String nombre=edtnombre.getText().toString();
        String apellido=edtapellido.getText().toString();
        String correo=edtcorreo.getText().toString();
        String correo2=edtcorreo2.getText().toString();
        String contraseña=edtcontraseña.getText().toString();
        String telefono=edttelefono.getText().toString();
        String fecha=edtfecha_nac.getText().toString();
        String sexo=edtsexo.getText().toString();

        if(nombre.isEmpty()){
            edtnombre.setError("Este campo es obligatorio");
            retorno=false;
        }
        if (apellido.isEmpty()){
            edtapellido.setError("Este campo es obligatorio");
            retorno=false;
        }
        if (correo.isEmpty()){
            edtcorreo.setError("Este campo es obligatorio");
            retorno=false;
        }
        if (correo2.isEmpty()){
            edtcorreo2.setError("Este campo es obligatorio");
            retorno=false;
        }
        if (contraseña.isEmpty()){
            edtcontraseña.setError("Este campo es obligatorio");
            retorno=false;
        }
        if (telefono.isEmpty()){
            edttelefono.setError("Este campo es obligatorio");
            retorno=false;
        }
        if (fecha.isEmpty()){
            edtfecha_nac.setError("Este campo es obligatorio");
            retorno=false;
        }
        if (sexo.isEmpty()){
            edtsexo.setError("Este campo es obligatorio");
            retorno=false;
        }


        return retorno;
    }

}