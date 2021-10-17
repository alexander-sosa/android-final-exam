package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class InscripcionActivity extends AppCompatActivity {

    ListaInscripcionFragment listaInscripcion;
    NuevaInscripcionFragment nuevaInscripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscripcion);

        listaInscripcion = new ListaInscripcionFragment();
        nuevaInscripcion = new NuevaInscripcionFragment();

        // default
        getSupportFragmentManager().beginTransaction().replace(R.id.llInscripciones, listaInscripcion).commit();
    }

    public void ocInscripcionMain(View view){
        switch (view.getId()){
            case R.id.btListaInscripciones:
                // aqui utilizamos el id del contenedor general
                getSupportFragmentManager().beginTransaction().replace(R.id.llInscripciones, listaInscripcion).commit();
                break;
            case R.id.btNuevaInscripcion:
                getSupportFragmentManager().beginTransaction().replace(R.id.llInscripciones, nuevaInscripcion).commit();
                break;
        }
    }
}