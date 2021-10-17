package com.example.examenfinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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
import com.example.examenfinal.modelos.ModeloInscripcion;

import java.util.ArrayList;

public class NuevaInscripcionFragment extends Fragment {

    int idAlumno;
    String materia, paralelo;
    Button btGuardar, btSalir;
    Controller controller;

    Spinner spAlumno, spMateria, spParalelo;
    ArrayList<String> materias, paralelos, stringAlumnos;
    ArrayList<ModeloAlumno> alumnos;

    public NuevaInscripcionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_nueva_inscripcion, container, false);

        idAlumno = -1;
        btGuardar = view.findViewById(R.id.btGuardarNuevoInscripcion);
        btSalir = view.findViewById(R.id.btSalirNuevoInscripcion);
        controller = new Controller(getContext());

        spAlumno = view.findViewById(R.id.spnSelectAlumno);
        spMateria = view.findViewById(R.id.spnSelectMateria);
        spParalelo = view.findViewById(R.id.spnSelectParalelo);

        alumnos = new ArrayList<>();
        materias = new ArrayList<>();
        paralelos = new ArrayList<>();
        stringAlumnos = new ArrayList<>();

        loadMaterias();
        loadParalelos();
        loadAlumnos();

        ArrayAdapter<CharSequence> adaptadorMaterias = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, materias);
        spMateria.setAdapter(adaptadorMaterias);
        spMateria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0)
                    materia = materias.get(position-1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adaptadorParalelos = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, paralelos);
        spParalelo.setAdapter(adaptadorParalelos);
        spParalelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0)
                    paralelo = paralelos.get(position-1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adaptadorAlumnos = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, stringAlumnos);
        spAlumno.setAdapter(adaptadorAlumnos);
        spAlumno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0)
                    idAlumno = (int) alumnos.get(position-1).getIdAlumno();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paralelo == null){
                    Toast.makeText(getContext(), "Paralelo no valido", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(materia == null){
                    Toast.makeText(getContext(), "Materia no valida", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(idAlumno == -1){
                    Toast.makeText(getContext(), "Alumno no valido", Toast.LENGTH_SHORT).show();
                    return;
                }
                ModeloInscripcion nuevoProducto = new ModeloInscripcion(materia, paralelo, idAlumno);
                long res = controller.nuevaInscripcion(nuevoProducto);
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

    void loadMaterias(){
        materias.add("Seleccionar Materia...");
        materias.add("materia 1...");
        materias.add("materia 2...");
        materias.add("materia 3...");
        materias.add("materia 4...");
        materias.add("materia 5...");
        materias.add("materia 6...");
        materias.add("materia 7...");
        materias.add("materia 8...");
        materias.add("materia 9...");
        materias.add("materia 10...");
    }

    void loadParalelos(){
        paralelos.add("Seleccionar Paralelo...");
        paralelos.add("A");
        paralelos.add("B");
        paralelos.add("C");
        paralelos.add("D");
        paralelos.add("E");
    }

    void loadAlumnos(){
        alumnos = controller.obtenerArtistas();
        stringAlumnos.add("Seleccionar Alumno...");
        for(int i = 0; i<alumnos.size(); i++){
            stringAlumnos.add(alumnos.get(i).getNombreCompleto());
        }
    }

    void limpiaCampos(){
        spParalelo.setSelection(0);
        spMateria.setSelection(0);
        spAlumno.setSelection(0);
        //etArtistaID.setText(null);
        idAlumno = -1;
        materia = null;
        paralelo = null;
    }
}