package com.ricardious.models;

public class EdificioFijo {
    private int Numero;
    private String Nombreedificio;
    private String Ubicacion;
    private String Descripcion;
    private String Seccion;


    public int getNumero() {
        return Numero;
    }

    public void setNumero(int numero) {Numero = numero;}


    public String getNombreedificio() {
        return Nombreedificio;
    }

    public void setNombreedificio(String nombreedificio) {Nombreedificio = nombreedificio;}


    public String getUbicacion() {return Ubicacion;}

    public void setUbicacion(String ubicacion) {
        Ubicacion = ubicacion;
    }


    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {Descripcion = descripcion;}


    public String getSeccion() {
        return Seccion;
    }

    public void setSeccion(String seccion) {Seccion = seccion;}


}