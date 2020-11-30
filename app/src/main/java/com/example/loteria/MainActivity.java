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
        carta(numero);
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
                    view(carta(numero));
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

    private ImageView carta(int x)
    {
        ImageView imagen= new ImageView(this);

        switch (x)
        {
            case 1:
                imagen.setImageResource(R.drawable.c1);
                break;
            case 2:
                imagen.setImageResource(R.drawable.c2);
                break;
            case 3:
                imagen.setImageResource(R.drawable.c3);
                break;
            case 4:
                imagen.setImageResource(R.drawable.c4);
                break;
            case 5:
                imagen.setImageResource(R.drawable.c5);
                break;
            case 6:
                imagen.setImageResource(R.drawable.c6);
                break;
            case 7:
                imagen.setImageResource(R.drawable.c7);
                break;
            case 8:
                imagen.setImageResource(R.drawable.c8);
                break;
            case 9:
                imagen.setImageResource(R.drawable.c9);
                break;
            case 10:
                imagen.setImageResource(R.drawable.c10);
                break;
            case 11:
                imagen.setImageResource(R.drawable.c11);
                break;
            case 12:
                imagen.setImageResource(R.drawable.c12);
                break;
            case 13:
                imagen.setImageResource(R.drawable.c13);
                break;

        }
        imagen.setLayoutParams(new LinearLayout.LayoutParams(400,370));
        return imagen;
    }

    private void view(ImageView imagen)
    {
        LinearLayout layout = (LinearLayout) findViewById(R.id.cartas);
        layout.addView(imagen);
    }
}