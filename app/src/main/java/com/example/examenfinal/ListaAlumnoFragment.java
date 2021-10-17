package com.example.examenfinal;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.examenfinal.adapters.AdaptadorAlumno;
import com.example.examenfinal.helpers.Controller;
import com.example.examenfinal.modelos.ModeloAlumno;

import java.util.ArrayList;
import java.util.List;

public class ListaAlumnoFragment extends Fragment {

    List<ModeloAlumno> lista, bruteList;
    AdaptadorAlumno adaptadorAlumno;
    RecyclerView rvAlumnos;
    Controller controller;
    Button recuperar;

    public ListaAlumnoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_alumno, container, false);

        lista = new ArrayList<>();
        bruteList = new ArrayList<>();
        adaptadorAlumno = new AdaptadorAlumno(lista);
        rvAlumnos = view.findViewById(R.id.rvAlumnos);
        controller = new Controller(getContext());
        recuperar = view.findViewById(R.id.btRecuperarBajas);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvAlumnos.setLayoutManager(mLayoutManager);
        rvAlumnos.setItemAnimator(new DefaultItemAnimator());
        rvAlumnos.setAdapter(adaptadorAlumno);
        refrescarAlumnos();

        rvAlumnos.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
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
        rvAlumnos.addOnItemTouchListener(new RecyclerTouchListener(getContext(), rvAlumnos, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getContext(),"un toque", Toast.LENGTH_SHORT).show();
                ModeloAlumno prodElegido = lista.get(position);
                baja(prodElegido);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getContext(),"Toque largo", Toast.LENGTH_SHORT).show();
                ModeloAlumno prodElegido = lista.get(position);
                //cambio(prodElegido);
            }
        }));

        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("phx", "entra al click");
                for(int i = 0; i<bruteList.size(); i++){
                    Log.i("phx", "recuparando bajas...");
                    controller.recuperarBajas(bruteList.get(i));
                }
                refrescarAlumnos();
            }
        });

        return view;
    }

    public void refrescarAlumnos() {
        if (adaptadorAlumno == null) return;
        lista.clear();
        bruteList = controller.obtenerArtistas();
        for(int i = 0; i<bruteList.size(); i++){
            Log.i("phx", "flag del alumno: " + bruteList.get(i).getFlagEstado());
            Log.i("phx", "nomber del alumno: " + bruteList.get(i).getNombreCompleto());
            if (bruteList.get(i).getFlagEstado() != 0) {
                lista.add(bruteList.get(i));
                Log.i("phx", "se agrega a la lista...  " + bruteList.get(i).getNombreCompleto());
            }
        }
        adaptadorAlumno.setListaDeProductos((ArrayList<ModeloAlumno>) lista);
        adaptadorAlumno.notifyDataSetChanged();
    }

    void baja(final ModeloAlumno prodele){
        AlertDialog.Builder alerta = new AlertDialog.Builder(getContext());
        alerta.setTitle("Dar de baja al alumno?");
        alerta.setCancelable(false);
        alerta.setMessage("nombre: "+prodele.getNombreCompleto()+"\nCarrera: "+prodele.getCarrera()+"\nId: "+prodele.getIdAlumno());
        alerta.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int res = controller.bajaAlumno(prodele);
                if(res == -1){
                    Toast.makeText(getContext(),"No puede eliminar este artista", Toast.LENGTH_SHORT).show();
                }
                refrescarAlumnos();;
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

    public void ocRecuperarBajas(){
        for(int i = 0; i<lista.size(); i++){
            controller.recuperarBajas(lista.get(i));
        }
    }
}