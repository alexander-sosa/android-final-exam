package com.example.examenfinal.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examenfinal.R;
import com.example.examenfinal.modelos.ModeloAlumno;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorAlumno extends RecyclerView.Adapter<AdaptadorAlumno.ViewDataHolder>{
    List<ModeloAlumno> lista;

    public AdaptadorAlumno(List<ModeloAlumno> lista) {
        this.lista = lista;
    }

    public void setListaDeProductos (ArrayList<ModeloAlumno> listaDeProductos){
        this.lista = listaDeProductos;
        for(ModeloAlumno a: listaDeProductos){
            Log.i("phx", "Llega: " + a.getNombreCompleto());
        }
    }

    @NonNull
    @Override
    public AdaptadorAlumno.ViewDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_alumno, parent, false);
        return new ViewDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorAlumno.ViewDataHolder holder, int position) {
        holder.tvNombreAlumno.setText(lista.get(position).getNombreCompleto());
        holder.tvCarreraAlumno.setText(lista.get(position).getCarrera());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewDataHolder extends RecyclerView.ViewHolder{
        TextView tvNombreAlumno, tvCarreraAlumno;

        public ViewDataHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreAlumno = itemView.findViewById(R.id.tvNombreAlumno);
            tvCarreraAlumno = itemView.findViewById(R.id.tvCarreraAlumno);
            Log.i("phx", String.valueOf(tvNombreAlumno));
            Log.i("phx", String.valueOf(tvCarreraAlumno));
        }
    }
}
