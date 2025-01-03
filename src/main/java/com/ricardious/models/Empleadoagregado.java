package com.ricardious.models;

public class Empleadoagregado {
    private int Codigo;
    private String DPI;
    private String Nombre;
    private String Apellido;
    private String Direccion;
    private String Telefono;
    private String Puesto;
    private String Correo;


    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {Codigo = codigo;}


    public String getDPI() {
        return DPI;
    }

    public void setDPI(String dpi) {DPI = dpi;}


    public String getNombre() {return Nombre; }

    public void setNombre(String nombre) {Nombre = nombre;}


    public String getApellido() {return Apellido; }

    public void setApellido(String apellido) {Apellido = apellido;}


    public String getDireccion() {return Direccion; }

    public void setDireccion(String direccion) {Direccion = direccion;}


    public String getTelefono() {return Telefono;}

    public void setTelefono(String telefono) {Telefono = telefono;}


    public String getPuesto() {return Puesto;}

    public void setPuesto(String puesto) {Puesto = puesto;}


    public String getCorreo() {return Correo;}

    public void setCorreo(String correo) {Correo = correo;}


}
