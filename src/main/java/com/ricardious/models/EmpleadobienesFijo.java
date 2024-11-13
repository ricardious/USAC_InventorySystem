package com.ricardious.models;

public class EmpleadobienesFijo {
    private int Tarjeta;
    private String CodigoActivo;
    private String Descripcion;
    private String Estado;
    private String Condicion;


    public int getTarjeta() {
        return Tarjeta;
    }

    public void setTarjeta(int tarjeta) {Tarjeta = tarjeta;}


    public String getCodigoActivo() {
        return CodigoActivo;
    }

    public void setCodigoActivo(String codigoActivo) {CodigoActivo = codigoActivo;}


    public String getDescripcion() {return Descripcion;}

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }


    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {Estado = estado;}


    public String getCondicion() {
        return Condicion;
    }

    public void setCondicion(String condicion) {Condicion = condicion;}


}
