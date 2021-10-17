package com.example.examenfinal.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examenfinal.R;
import com.example.examenfinal.modelos.ModeloInscripcion;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorInscripcion extends RecyclerView.Adapter<AdaptadorInscripcion.ViewDataHolder>{
    List<ModeloInscripcion> lista;

    public AdaptadorInscripcion(List<ModeloInscripcion> lista) {
        this.lista = lista;
    }

    public void setListaDeProductos (ArrayList<ModeloInscripcion> listaDeProductos){
        this.lista = listaDeProductos;
    }

    @NonNull
    @Override
    public AdaptadorInscripcion.ViewDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_inscripcion, parent, false);
        return new AdaptadorInscripcion.ViewDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorInscripcion.ViewDataHolder holder, int position) {
        holder.tvNombreMateria.setText(lista.get(position).getNombreMateria());
        holder.tvNombreAlumnoInscrito.setText(lista.get(position).getNombreAlumno());
        holder.tvParalelo.setText(lista.get(position).getParalelo());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewDataHolder extends RecyclerView.ViewHolder{
        TextView tvNombreMateria, tvNombreAlumnoInscrito, tvParalelo;

        public ViewDataHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreMateria = itemView.findViewById(R.id.tvNombreMateria);
            tvNombreAlumnoInscrito = itemView.findViewById(R.id.tvNombreAlumnoInscrito);
            tvParalelo = itemView.findViewById(R.id.tvParaleloInscrito);
            Log.i("phx", String.valueOf(tvNombreMateria));
            Log.i("phx", String.valueOf(tvNombreAlumnoInscrito));
            Log.i("phx", String.valueOf(tvParalelo));
        }
    }
}
