package com.example.a1141_final.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a1141_final.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder>{
    ArrayList<HashMap<String,String>> arrayList;

    public static OnItemClickListener onItemClickListener;

    public ProductoAdapter(ArrayList<HashMap<String,String>> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ProductoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_producto,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ViewHolder holder, int position) {
        HashMap<String,String> map = arrayList.get(position);
        holder.tvProductoNombre.setText(map.get("nombre"));
        float precio = Float.parseFloat(map.get("precio"));
        holder.tvProductoPrecio.setText("S/ " + String.format("%.2f", precio));
        String ruta = "https://servicios.campus.pe/" + map.get("imagengrande");
        Picasso.get().load(ruta).into(holder.ivProductoFoto);
        if(map.get("imagengrande") == "null"){
            Picasso.get().load(R.drawable.nophoto).into(holder.ivProductoFoto);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvProductoNombre, tvProductoPrecio;
        ImageView ivProductoFoto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductoNombre = itemView.findViewById(R.id.tvProductoNombre);
            tvProductoPrecio = itemView.findViewById(R.id.tvProductoPrecio);
            ivProductoFoto = itemView.findViewById(R.id.ivProductoFoto);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //imprime en la consola la posicion del viewholder
            Log.d("POSICION", String.valueOf(getLayoutPosition()));
            onItemClickListener.onItemClick(getLayoutPosition());
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int posicion);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        ProductoAdapter.onItemClickListener = onItemClickListener;
    }
}
