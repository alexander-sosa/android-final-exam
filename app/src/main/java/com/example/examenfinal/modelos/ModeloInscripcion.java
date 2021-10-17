package com.example.examenfinal.modelos;

public class ModeloInscripcion {
    private long id;
    private long idAlumno;
    private String nombreAlumno;
    private String nombreMateria;
    private String paralelo;

    public ModeloInscripcion(String nombreMateria, String nombreAlumno) {
        this.nombreMateria = nombreMateria;
        this.nombreAlumno = nombreAlumno;
    }

    public ModeloInscripcion(String nombreMateria, int idAlumno) {
        this.nombreMateria = nombreMateria;
        this.idAlumno = idAlumno;
    }

    public ModeloInscripcion(String nombreMateria, long id) {
        this.nombreMateria = nombreMateria;
        this.id = id;
    }

    public ModeloInscripcion(String nombreMateria, String paralelo, long idAlumno) {
        this.nombreMateria = nombreMateria;
        this.paralelo = paralelo;
        this.idAlumno = idAlumno;
    }

    public ModeloInscripcion(String nombreMateria, long idAlumno, long id) {
        this.nombreMateria = nombreMateria;
        this.idAlumno = idAlumno;
        this.id = id;
    }

    public ModeloInscripcion(String nombreMateria, String nombreAlumno, String paralelo, long idAlumno, long id) {
        this.nombreMateria = nombreMateria;
        this.nombreAlumno = nombreAlumno;
        this.idAlumno = idAlumno;
        this.id = id;
        this.paralelo = paralelo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(long idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getParalelo() {
        return paralelo;
    }

    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }

    @Override
    public String toString() {
        return "ModeloInscripcion{" +
                "nombreMateria='" + nombreMateria + '\'' +
                ", paralelo=" + paralelo +
                ", idInscripcion=" + id +
                ", nombre alumno=" + nombreAlumno +
                ", idAlumno=" + idAlumno +
                '}';
    }
}
