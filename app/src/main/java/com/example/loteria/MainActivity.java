package com.example.loteria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private int numero=0;
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
        if(n<=21)
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
        LinearLayout layout = (LinearLayout) findViewById(R.id.cartas);
        if(layout.getChildCount()>0)
        {
            layout.removeAllViews();
        }
    }

    private void ObtenerNumero()
    {
        String urln = "https://ramiro174.com/api/numero";
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET, urln, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    numero=response.getInt("numero");
                    suma+=numero;
                    TextView textView= findViewById(R.id.numero);
                    textView.setText(String.valueOf(suma));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"No se ha podido realizar la peticion",Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MainActivity.this,"No se ha podido realizar la peticion",Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
        suma=0;
        numero.setText(String.valueOf(suma));
        LinearLayout layout = (LinearLayout) findViewById(R.id.cartas);
        if(layout.getChildCount()>0)
        {
            layout.removeAllViews();
        }
    }

    private void carta(int x)
    {
        switch (x)
        {
            case 1:
                ImageView imagen1= new ImageView(this);
                imagen1.setImageResource(R.drawable.c1);
                view(imagen1);
                break;
            case 2:
                ImageView imagen2= new ImageView(this);
                imagen2.setImageResource(R.drawable.c2);
                view(imagen2);
                break;
            case 3:
                ImageView imagen3= new ImageView(this);
                imagen3.setImageResource(R.drawable.c3);
                view(imagen3);
                break;
            case 4:
                ImageView imagen4= new ImageView(this);
                imagen4.setImageResource(R.drawable.c4);
                view(imagen4);
                break;
            case 5:
                ImageView imagen5= new ImageView(this);
                imagen5.setImageResource(R.drawable.c5);
                view(imagen5);
                break;
            case 6:
                ImageView imagen6= new ImageView(this);
                imagen6.setImageResource(R.drawable.c6);
                view(imagen6);
                break;
            case 7:
                ImageView imagen7= new ImageView(this);
                imagen7.setImageResource(R.drawable.c7);
                view(imagen7);
                break;
            case 8:
                ImageView imagen8= new ImageView(this);
                imagen8.setImageResource(R.drawable.c8);
                view(imagen8);
                break;
            case 9:
                ImageView imagen9= new ImageView(this);
                imagen9.setImageResource(R.drawable.c9);
                view(imagen9);
                break;
            case 10:
                ImageView imagen10= new ImageView(this);
                imagen10.setImageResource(R.drawable.c10);
                view(imagen10);
                break;
            case 11:
                ImageView imagen11= new ImageView(this);
                imagen11.setImageResource(R.drawable.c11);
                view(imagen11);
                break;
            case 12:
                ImageView imagen12= new ImageView(this);
                imagen12.setImageResource(R.drawable.c12);
                view(imagen12);
                break;
            case 13:
                ImageView imagen13= new ImageView(this);
                imagen13.setImageResource(R.drawable.c13);
                view(imagen13);
                break;

        }
    }

    private void view(ImageView imagen)
    {
        LinearLayout layout = (LinearLayout) findViewById(R.id.cartas);
        layout.addView(imagen);
    }
}