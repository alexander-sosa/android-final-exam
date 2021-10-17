package com.example.examenfinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.examenfinal.helpers.Controller;
import com.example.examenfinal.modelos.ModeloAlumno;

import java.util.ArrayList;

public class NuevoAlumnoFragment extends Fragment {

    EditText etNombreAlumno;
    String carrera;
    int flagEstado;
    Button btGuardar, btSalir;
    Controller controller;

    Spinner spCarrera;
    ArrayList<String> carreras;

    public NuevoAlumnoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nuevo_alumno, container, false);

        etNombreAlumno = view.findViewById(R.id.etAgregarAlumnoNombre);
        flagEstado = 1;
        btGuardar = view.findViewById(R.id.btGuardarNuevoAlumno);
        btSalir = view.findViewById(R.id.btSalirNuevoAlumno);
        controller = new Controller(getContext());

        spCarrera = view.findViewById(R.id.spnSelectCarrera);
        carreras = new ArrayList<>();
        loadSpinner();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, carreras);
        spCarrera.setAdapter(adaptador);
        spCarrera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0)
                    carrera = carreras.get(position-1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombreAlumno.getText().toString();
                if(carrera == null){
                    Toast.makeText(getContext(), "Carrera no valida", Toast.LENGTH_SHORT).show();
                    return;
                }
                ModeloAlumno nuevoProducto = new ModeloAlumno(nombre, flagEstado, carrera);
                long res = controller.nuevoAlumno(nuevoProducto);
                if(res == -1){
                    // hay error
                    Toast.makeText(getContext(), "ERROR: el registro no se ha guardado", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
                    limpiaCampos();
                }
            }
        });

        /*
        btSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "SALIR", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
         */

        return view;
    }

    void loadSpinner(){
        carreras.add("Seleccionar...");
        carreras.add("Biomedica");
        carreras.add("Sistemas");
        carreras.add("Psicologia");
        carreras.add("Musica");
        carreras.add("Mecatronica");
        carreras.add("Industrial");
        carreras.add("Diseño grafico");
        carreras.add("Economia");
        carreras.add("Biologia");
        carreras.add("Medicina");
    }

    void limpiaCampos(){
        etNombreAlumno.setText(null);
        //etArtistaID.setText(null);
        flagEstado = -1;
        etNombreAlumno.requestFocus();
    }
}