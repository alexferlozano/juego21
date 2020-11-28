package com.example.loteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RequestQueue queue;
    private int suma=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue=VolleySingleton.getInstance(getApplicationContext()).getRequestQueue();
    }

    public void verEstadisticas(View view) {
        Intent i = new Intent(MainActivity.this,activityEstadistica.class);
        startActivity(i);
    }

    public void sumarNumero(View view) {
        ObtenerNumero();
    }

    public void enviarNumero(View view) {
        TextView numero=findViewById(R.id.numero);
        int n= Integer.parseInt(numero.getText().toString());
        if(21>n)
        {
            EnviarNumero();
        }
        else
        {
            Toast.makeText(MainActivity.this,"Superaste el limite de 21",Toast.LENGTH_SHORT).show();
        }
    }

    public void Reiniciar(View view) {
        suma=0;
        TextView textView= findViewById(R.id.numero);
        textView.setText(String.valueOf(suma));
    }

    private void ObtenerNumero()
    {
        String urln = "https://ramiro174.com/api/numero";
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET, urln, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    suma+=response.getInt("numero");
                    TextView textView= findViewById(R.id.numero);
                    textView.setText(String.valueOf(suma));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    private void EnviarNumero()
    {
        String url = "https://ramiro174.com/api/enviar/numero";
        TextView numero=findViewById(R.id.numero);
        int n= Integer.parseInt(numero.getText().toString());
        JSONObject jugador=new JSONObject();
        try {
            jugador.put("nombre", "Alexferloz");
            jugador.put("numero",n);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.POST, url, jugador, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            Toast.makeText(MainActivity.this,"Has enviado los datos correctamente",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
        suma=0;
        numero.setText(String.valueOf(suma));
    }
}