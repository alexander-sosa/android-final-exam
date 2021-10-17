package com.example.examenfinal.modelos;

public class ModeloAlumno {
    private long idAlumno;
    private int flagEstado;
    private String nombreCompleto;
    private String carrera;

    public ModeloAlumno(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public ModeloAlumno(String nombreCompleto, int flagEstado, String carrera) {
        this.nombreCompleto = nombreCompleto;
        this.flagEstado = flagEstado;
        this.carrera = carrera;
    }

    public ModeloAlumno(long idAlumno, String nombre) {
        this.nombreCompleto = nombre;
        this.idAlumno = idAlumno;
    }

    public ModeloAlumno(long idAlumno, int flagEstado, String nombre, String carrera) {
        this.nombreCompleto = nombre;
        this.idAlumno = idAlumno;
        this.flagEstado = flagEstado;
        this.carrera = carrera;
    }

    public long getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(long idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getFlagEstado() {
        return flagEstado;
    }

    public void setFlagEstado(int flagEstado) {
        this.flagEstado = flagEstado;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "ModeloAlumno{" +
                "nombre completo='" + nombreCompleto + '\'' +
                ", flagEstado=" + flagEstado +
                ", carrera=" + carrera +
                ", id=" + idAlumno +
                '}';
    }
}
