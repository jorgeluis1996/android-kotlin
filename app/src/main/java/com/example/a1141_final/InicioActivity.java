package com.example.a1141_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.a1141_final.databinding.ActivityInicioBinding;
import com.example.a1141_final.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class InicioActivity extends AppCompatActivity{

    private ActivityInicioBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInicioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.nav_CerrarSesion:
                CerrarSesion();
                return true;
            case R.id.nav_Productos:
                Nav_Productos();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void Nav_Productos() {
        startActivity(new Intent(this, ProductosActivity.class));
    }

    private void CerrarSesion() {
        FirebaseAuth.getInstance().signOut();

        Toast.makeText(this, "Hasta luego :)", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this, MainActivity.class));
    }
}