package com.example.a1141_final;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.a1141_final.adapters.ProductoAdapter;
import com.example.a1141_final.databinding.ActivityProductosBinding;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class  ProductosActivity extends AppCompatActivity implements ProductoAdapter.OnItemClickListener {
    private ActivityProductosBinding binding;
    ArrayList arrayList = new ArrayList<HashMap<String,String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        leerDatos();
    }

    private void leerDatos() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://servicios.campus.pe/servicioproductostodos.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DATOS",response);
                        llenarLista(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("DATOSERROR",error.getMessage());
            }
        });
        queue.add(stringRequest);
    }
    private void llenarLista(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for(int i = 0; i< jsonArray.length(); i++ ){
                String idproducto = jsonArray.getJSONObject(i).getString("idproducto");
                String nombre = jsonArray.getJSONObject(i).getString("nombre");
                String precio = jsonArray.getJSONObject(i).getString("precio");
                String imagengrande = jsonArray.getJSONObject(i).getString("imagengrande");
                HashMap<String,String> map = new HashMap<>();
                map.put("idproducto",idproducto);
                map.put("nombre",nombre);
                map.put("precio",precio);
                map.put("imagengrande",imagengrande);
                arrayList.add(map);
            }


            ProductoAdapter productoAdapter = new ProductoAdapter(arrayList);
            binding.rvProductos.setAdapter(productoAdapter);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            binding.rvProductos.setLayoutManager(linearLayoutManager);

            productoAdapter.setOnItemClickListener(this);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(int position) {
        //Log.d("POS-A", String.valueOf(posicion));
        HashMap<String,String> map = (HashMap<String, String>) arrayList.get(position);
        String nombre = map.get("nombre");
        String idcategoria = map.get("idcategoria");
        //
        // Toast.makeText(this, nombre, Toast.LENGTH_SHORT).show();

        Bundle bundle = new Bundle();
        bundle.putString("idcategoria",idcategoria);
        bundle.putString("nombre",nombre);

        Intent intent = new Intent(this, ProductoDetalleActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}