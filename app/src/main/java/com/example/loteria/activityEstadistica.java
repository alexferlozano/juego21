package com.example.loteria;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class activityEstadistica extends AppCompatActivity {
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);
        queue=VolleySingleton.getInstance(getApplicationContext()).getRequestQueue();
        //
        ObtenerDatos();
    }

    private void ObtenerDatos()
    {
        String url="https://ramiro174.com/api/obtener/numero";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray resultados= response.getJSONArray("resultados");
                    Gson convertidor= new Gson();
                    Type tipolistaPersonas =new TypeToken<List<Persona>>(){}.getType();
                    List<Persona> personas=convertidor.fromJson(resultados.toString(),tipolistaPersonas);
                    PersonaAdapter personaAdapter= new PersonaAdapter(personas,activityEstadistica.this);
                    RecyclerView recyclerView= findViewById(R.id.reView);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(activityEstadistica.this));
                    recyclerView.setAdapter(personaAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activityEstadistica.this,"No se ha podido realizar la peticion",Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }
}
