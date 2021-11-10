package com.example.ocean14;

import android.widget.ImageView;

public class NegocioModelo {
    private String nombre,servicio,telefono,domicilio,hora,facobook;
    private int imgnegocio;

    public NegocioModelo(String nombre, String servicio, String telefono, String domicilio, String hora, String facobook, int imgnegocio) {
        this.nombre = nombre;
        this.servicio = servicio;
        this.telefono = telefono;
        this.domicilio = domicilio;
        this.hora = hora;
        this.facobook = facobook;
        this.imgnegocio = imgnegocio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFacobook() {
        return facobook;
    }

    public void setFacobook(String facobook) {
        this.facobook = facobook;
    }

    public int getImgnegocio() {
        return imgnegocio;
    }

    public void setImgnegocio(int imgnegocio) {
        this.imgnegocio = imgnegocio;
    }
}
