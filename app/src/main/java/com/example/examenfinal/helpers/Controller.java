package com.example.examenfinal.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.examenfinal.modelos.ModeloAlumno;
import com.example.examenfinal.modelos.ModeloInscripcion;

import java.util.ArrayList;

public class Controller {
    // aqui va a estar toda la logica de la BDD
    private BDDHelper ayudanteBaseDeDatos;
    private String ALUMNO = "alumno";
    private String INSCRIPCION = "inscripcion";

    public Controller(Context contexto) {
        ayudanteBaseDeDatos = new BDDHelper(contexto);
    }

    // Campos de Soporte al Alumno

    public long nuevoAlumno(ModeloAlumno alumno) {
        // writable porque vamos a insertar
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombreCompleto", alumno.getNombreCompleto());
        valoresParaInsertar.put("carrera", alumno.getCarrera());
        valoresParaInsertar.put("flagEstado", alumno.getFlagEstado());
        return baseDeDatos.insert(ALUMNO, null, valoresParaInsertar);
    }

    public int cambioAlumno(ModeloAlumno alumnoEditado) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombreCompleto", alumnoEditado.getNombreCompleto());
        valoresParaActualizar.put("carrera", alumnoEditado.getCarrera());
        // where id...
        String campoParaActualizar = "idAlumno = ?";
        String[] argumentosParaActualizar = {String.valueOf(alumnoEditado.getIdAlumno())};
        return baseDeDatos.update(ALUMNO, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public int eliminaAlumno(ModeloAlumno alumno) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(alumno.getIdAlumno())};
        return baseDeDatos.delete(ALUMNO, "id = ?", argumentos);
    }

    public int bajaAlumno(ModeloAlumno alumnoEditado) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("flagEstado", 0);
        // where id...
        String campoParaActualizar = "idAlumno = ?";
        String[] argumentosParaActualizar = {String.valueOf(alumnoEditado.getIdAlumno())};
        return baseDeDatos.update(ALUMNO, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
        //return baseDeDatos.delete(ARTISTA, campoParaActualizar, argumentosParaActualizar);
    }

    public int recuperarBajas(ModeloAlumno alumnoEditado) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("flagEstado", 1);
        // where id...
        String campoParaActualizar = "idAlumno = ?";
        String[] argumentosParaActualizar = {String.valueOf(alumnoEditado.getIdAlumno())};
        return baseDeDatos.update(ALUMNO, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
        //return baseDeDatos.delete(ARTISTA, campoParaActualizar, argumentosParaActualizar);
    }

    public ArrayList<ModeloAlumno> obtenerArtistas() {
        ArrayList<ModeloAlumno> listaProductos = new ArrayList<>();
        // readable porque no vamos a modificar, solamente leer
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getReadableDatabase();
        // SELECT nombreCompleto, carrera, flagEstado, id
        String[] columnasAConsultar = {"nombreCompleto", "flagEstado", "carrera", "idAlumno"};
        Cursor cursor = baseDeDatos.query(
                ALUMNO,
                columnasAConsultar,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor == null) {
            Log.i("phx", "No hay ningun dato ");
            // Salimos aquí porque hubo un error, regresar
            // lista vacía
            return listaProductos;
        }
        // Si no hay datos, igualmente regresamos la lista vacía
        if (!cursor.moveToFirst()) return listaProductos;
        // En caso de que sí haya, iteramos y vamos agregando los
        // datos a la lista de productos
        do {
            // El 0 es el número de la columna, como seleccionamos
            // nombre, edad,id entonces el nombre es 0, edad 1 e id es 2
            String nombreObtenidoDeBD = cursor.getString(0);
            int flagEstado = cursor.getInt(1);
            String carrera = cursor.getString(2);
            long idAlumno = cursor.getLong(3);
            ModeloAlumno artistaObtenidaDeBD = new ModeloAlumno(idAlumno, flagEstado, nombreObtenidoDeBD, carrera);
            listaProductos.add(artistaObtenidaDeBD);
        } while (cursor.moveToNext());
        cursor.close();
        return listaProductos;
    }

    public ModeloAlumno buscaAlumno(ModeloAlumno reg){
        SQLiteDatabase db = ayudanteBaseDeDatos.getReadableDatabase();
        //Cursor c = db.rawQuery("SELECT id, nombre, edad FROM NOMBRE_TABLA", null);
        Log.i("phx", "Se esta buscando... " + reg.getIdAlumno());
        Cursor c=db.rawQuery("SELECT nombreCompleto, idAlumno FROM "+ALUMNO+" WHERE idAlumno = '"+ reg.getIdAlumno()+"' ",null);
        ModeloAlumno res = null;
        if (c != null) {
            try {
                c.moveToFirst();
                String nombre = c.getString(c.getColumnIndex("nombreCompleto"));
                int id = c.getInt(c.getColumnIndex("idAlumno"));
                res = new ModeloAlumno(id, nombre);
            }catch (Exception e){

            }
        }else{

        }
        //Cerramos el cursor y la conexion con la base de datos
        c.close();
        db.close();
        return res;
    }


    // Campos de Soporte a las Canciones
    public long nuevaInscripcion(ModeloInscripcion inscripcion) {
        // writable porque vamos a insertar
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombreMateria", inscripcion.getNombreMateria());
        valoresParaInsertar.put("paralelo", inscripcion.getParalelo());
        valoresParaInsertar.put("idAlumno", inscripcion.getIdAlumno());
        Log.i("phx", "insertando... " + valoresParaInsertar.toString());
        return baseDeDatos.insert(INSCRIPCION, null, valoresParaInsertar);
    }

    /*
    public int cambioInscripcion(ModeloInscripcion inscripcionEditada) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombre", cancionEditada.getNombre());
        // where id...
        String campoParaActualizar = "id = ?";
        String[] argumentosParaActualizar = {String.valueOf(cancionEditada.getId())};
        return baseDeDatos.update(CANCION, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }
     */

    public int eliminaInscripcion(ModeloInscripcion inscripcion) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(inscripcion.getId())};
        return baseDeDatos.delete(INSCRIPCION, "idInscripcion = ?", argumentos);
    }

    public int bajaInscripcion(ModeloInscripcion inscripcionEditada) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombreMateria", inscripcionEditada.getNombreMateria());
        // where id...
        String campoParaActualizar = "idInscripcion = ?";
        String[] argumentosParaActualizar = {String.valueOf(inscripcionEditada.getId())};
        //return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
        return baseDeDatos.delete(INSCRIPCION, campoParaActualizar, argumentosParaActualizar);
    }

    public ArrayList<ModeloInscripcion> obtenerInscripciones() {
        ArrayList<ModeloInscripcion> listaProductos = new ArrayList<>();
        // readable porque no vamos a modificar, solamente leer
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getReadableDatabase();
        // SELECT nombre, edad, id
        String[] columnasAConsultar = {"nombreMateria", "idAlumno", "idInscripcion", "paralelo"};
        Cursor cursor = baseDeDatos.query(
                INSCRIPCION,
                columnasAConsultar,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor == null) {
            Log.i("phx", "No hay ningun dato ");
            // Salimos aquí porque hubo un error, regresar
            // lista vacía
            return listaProductos;
        }
        // Si no hay datos, igualmente regresamos la lista vacía
        if (!cursor.moveToFirst()) return listaProductos;
        // En caso de que sí haya, iteramos y vamos agregando los
        // datos a la lista de productos
        do {
            // El 0 es el número de la columna, como seleccionamos
            // nombre, edad,id entonces el nombre es 0, edad 1 e id es 2
            String nombreMateria = cursor.getString(0);
            long idAlumno = cursor.getLong(1);
            long idInscripcion = cursor.getLong(2);
            String paralelo = cursor.getString(3);

            //ModeloArtist aux = buscaArtista(new ModeloArtist("", idArtista));
            //ModeloCancion artistaObtenidaDeBD = new ModeloCancion(nombreObtenidoDeBD, aux.getNombre(), idCancion);
            Log.i("phx", "ID del alumno... " + idAlumno);
            ModeloAlumno aux = buscaAlumno(new ModeloAlumno(idAlumno, ""));
            Log.i("phx", "Modelo Artista... " + aux.toString());
            ModeloInscripcion alumnoObtenidaDeBD = new ModeloInscripcion(nombreMateria, aux.getNombreCompleto(), paralelo, idAlumno, idInscripcion);
            Log.i("phx", "Llegando... " + alumnoObtenidaDeBD.toString());
            listaProductos.add(alumnoObtenidaDeBD);
        } while (cursor.moveToNext());
        cursor.close();
        return listaProductos;
    }

    /*
    public ModeloCancion buscaCancion(ModeloCancion reg){
        SQLiteDatabase db = ayudanteBaseDeDatos.getReadableDatabase();
        //Cursor c = db.rawQuery("SELECT id, nombre, edad FROM NOMBRE_TABLA", null);
        Cursor c=db.rawQuery("SELECT nombre, artista, id  FROM "+CANCION+" WHERE id = '"+ reg.getId()+"' ",null);
        ModeloCancion res = null;
        if (c != null) {
            try {
                c.moveToFirst();
                String nombre = c.getString(c.getColumnIndex("nombre"));
                int idArtista = c.getInt(c.getColumnIndex("artista"));
                int id = c.getInt(c.getColumnIndex("id"));
                res = new ModeloCancion(nombre, idArtista, id);
            }catch (Exception e){

            }
        }else{

        }
        //Cerramos el cursor y la conexion con la base de datos
        c.close();
        db.close();
        return res;
    }

    public ModeloCancion buscaArtistadeCancion(ModeloCancion reg){
        SQLiteDatabase db = ayudanteBaseDeDatos.getReadableDatabase();
        //Cursor c = db.rawQuery("SELECT id, nombre, edad FROM NOMBRE_TABLA", null);
        Cursor c=db.rawQuery("SELECT nombre, artista, id  FROM "+CANCION+" WHERE artista = '"+ reg.getId()+"' ",null);
        ModeloCancion res = null;
        if (c != null) {
            try {
                c.moveToFirst();
                String nombre = c.getString(c.getColumnIndex("nombre"));
                int idArtista = c.getInt(c.getColumnIndex("artista"));
                int id = c.getInt(c.getColumnIndex("id"));
                res = new ModeloCancion(nombre, idArtista, id);
            }catch (Exception e){

            }
        }else{

        }
        //Cerramos el cursor y la conexion con la base de datos
        c.close();
        db.close();
        return res;
    }

     */
}
