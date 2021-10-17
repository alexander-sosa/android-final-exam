package com.example.examenfinal;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.examenfinal.adapters.AdaptadorAlumno;
import com.example.examenfinal.adapters.AdaptadorInscripcion;
import com.example.examenfinal.helpers.Controller;
import com.example.examenfinal.modelos.ModeloAlumno;
import com.example.examenfinal.modelos.ModeloInscripcion;

import java.util.ArrayList;
import java.util.List;

public class ListaInscripcionFragment extends Fragment {

    List<ModeloInscripcion> lista;
    AdaptadorInscripcion adaptadorInscripcion;
    RecyclerView rvInscripciones;
    Controller controller;

    public ListaInscripcionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_lista_inscripcion, container, false);

        lista = new ArrayList<>();
        adaptadorInscripcion = new AdaptadorInscripcion(lista);
        rvInscripciones = view.findViewById(R.id.rvInscripciones);
        controller = new Controller(getContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvInscripciones.setLayoutManager(mLayoutManager);
        rvInscripciones.setItemAnimator(new DefaultItemAnimator());
        rvInscripciones.setAdapter(adaptadorInscripcion);
        refrescarInscripciones();

        rvInscripciones.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        rvInscripciones.addOnItemTouchListener(new RecyclerTouchListener(getContext(), rvInscripciones, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getContext(),"un toque", Toast.LENGTH_SHORT).show();
                ModeloInscripcion prodElegido = lista.get(position);
                baja(prodElegido);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getContext(),"Toque largo", Toast.LENGTH_SHORT).show();
                ModeloInscripcion prodElegido = lista.get(position);
                //cambio(prodElegido);
            }
        }));

        return view;
    }

    public void refrescarInscripciones() {
        if (adaptadorInscripcion == null) return;
        lista = controller.obtenerInscripciones();
        adaptadorInscripcion.setListaDeProductos((ArrayList<ModeloInscripcion>) lista);
        adaptadorInscripcion.notifyDataSetChanged();
    }

    void baja(final ModeloInscripcion prodele){
        AlertDialog.Builder alerta = new AlertDialog.Builder(getContext());
        alerta.setTitle("Eliminar inscripcion?");
        alerta.setCancelable(false);
        alerta.setMessage("materia: "+prodele.getNombreMateria()+"\nalumno: "+prodele.getNombreAlumno()+"\nId: "+prodele.getId());
        alerta.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int res = controller.bajaInscripcion(prodele);
                if(res == -1){
                    Toast.makeText(getContext(),"No puede eliminar esta inscripcion", Toast.LENGTH_SHORT).show();
                }
                refrescarInscripciones();;
            }
        });
        alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        //AlertDialog dialog  = builder.create();
        //dialog.show();
        alerta.create().show();

    }

    /*
    void cambio(final ModeloArtist prodele){
        LayoutInflater inflador = LayoutInflater.from(this);
        View subView = inflador.inflate(R.layout.layout_cambio_artista, null);

        final EditText etnuevonombre = subView.findViewById(R.id.etNuevoNombreArtista);

        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaArtistaActivity.this);
        alerta.setCancelable(false);
        alerta.setTitle("Esta seguro de actualizar los siguientes campos");
        alerta.setMessage(prodele.toString());
        alerta.setView(subView);
        alerta.setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ModeloArtist newObj = new ModeloArtist(etnuevonombre.getText().toString(), prodele.getId());
                int filasAfectadas = controller.cambioArtista(newObj);
                if(filasAfectadas > 0){
                    refrescarListaDeProductos();;
                    Toast.makeText(getApplicationContext(),"Exito en el cambio", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Error no se realizo el cambio", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alerta.create().show();
    }

     */
}