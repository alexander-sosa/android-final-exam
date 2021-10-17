package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class AlumnoActivity extends AppCompatActivity {

    NuevoAlumnoFragment nuevoAlumno;
    ListaAlumnoFragment listaAlumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);

        nuevoAlumno = new NuevoAlumnoFragment();
        listaAlumnos = new ListaAlumnoFragment();

        // default fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.llAlumnos, listaAlumnos).commit();

    }

    public void ocAlumnoMain(View view){
        switch (view.getId()){
            case R.id.btListaAlumnos:
                // aqui utilizamos el id del contenedor general
                getSupportFragmentManager().beginTransaction().replace(R.id.llAlumnos, listaAlumnos).commit();
                break;
            case R.id.btNuevoAlumno:
                getSupportFragmentManager().beginTransaction().replace(R.id.llAlumnos, nuevoAlumno).commit();
                break;
        }
    }
}