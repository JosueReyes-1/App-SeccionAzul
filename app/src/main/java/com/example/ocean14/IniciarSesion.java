package com.example.ocean14;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
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

public class IniciarSesion extends AppCompatActivity {

    EditText edtUsuario,edtPassword;
    Button btnLogin,btncrearcuenta;
    String contraseña,correo;
    ImageButton btnregresar;
    TextView tverror;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);


        edtUsuario=(EditText)findViewById(R.id.edtUsuario);
        edtPassword=(EditText)findViewById(R.id.edtPassword);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btncrearcuenta=(Button)findViewById(R.id.btncrearcuenta);
        btnregresar=(ImageButton)findViewById(R.id.btnregresarinisiosesion);
        tverror=(TextView)findViewById(R.id.tverror);
        recuperarPreferencias();

        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        btncrearcuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Altausuarios.class);
                startActivityForResult(intent,0);

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correo=edtUsuario.getText().toString();
                contraseña=edtPassword.getText().toString();
                if(validar()){
                    validarUsuario("https://seccionazul.000webhostapp.com/validar_cliente.php");
                }
            }
        });

    }
    private void validarUsuario(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    GuardarPreferencias();
                    Intent intent=new Intent(getApplicationContext(),ActivityRecyclerView.class);
                    startActivity(intent);
                    finish();
                }else{
                    tverror.setText("Usuario o contraseña incorrectos");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(IniciarSesion.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String, String>();
                parametros.put("correo_electronico",correo);
                parametros.put("contraseña",contraseña);
                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    private void GuardarPreferencias(){
        SharedPreferences preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("correo_electronico",correo);
        editor.putString("contraseña",contraseña);
        editor.putBoolean("sesion",true);
        editor.commit();
    }

    private void recuperarPreferencias(){
        SharedPreferences preferences=getSharedPreferences("preferenciasLogin",Context.MODE_PRIVATE);
        edtUsuario.setText(preferences.getString("correo_electronico",""));
        edtPassword.setText(preferences.getString("contraseña",""));
    }
    public boolean validar(){
        boolean retorno=true;
        String correo2=edtUsuario.getText().toString();
        String password2=edtPassword.getText().toString();
        if(correo.isEmpty()){
            edtUsuario.setError("Este campo es obligatorio");
            retorno=false;
        }
        if(password2.isEmpty()){
            edtPassword.setError("Este capo es obligatorio");
            retorno=false;
        }
        if(correo2 == "correo_electronico"){
            edtUsuario.setError("Este correo no es correcto");
            retorno=false;
        }
        return retorno;
    }
}
