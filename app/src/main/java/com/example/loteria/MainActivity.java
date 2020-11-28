package com.example.loteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
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
        //ObtenerNumero();
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

    public void enviarNumero(View view) {
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
        queue.add(request);
    }

    public void Reiniciar(View view) {
        suma=0;
        TextView textView= findViewById(R.id.numero);
        textView.setText(String.valueOf(suma));
    }
}