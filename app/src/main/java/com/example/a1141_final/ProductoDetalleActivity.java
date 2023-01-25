package com.example.a1141_final;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1141_final.databinding.ActivityProductoDetalleBinding;
import com.example.a1141_final.databinding.ActivityProductosBinding;

public class ProductoDetalleActivity extends AppCompatActivity {
    TextView tvNombre;
    private ActivityProductoDetalleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductoDetalleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tvNombre = findViewById(R.id.tvNombre);

        Bundle bundle = getIntent().getExtras();
        String nombre = bundle.getString("nombre");
        this.setTitle(nombre);

        tvNombre.setText(nombre);


    }
}