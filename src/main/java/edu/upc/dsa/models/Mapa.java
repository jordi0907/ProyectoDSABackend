package edu.upc.dsa.models;

public class Mapa {

    String id;
    String nombre;
    String dato;
    public Mapa() {
    }

    public Mapa(String nombre, String dato) {
        this.nombre = nombre;
        this.dato = dato;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    @Override
    public String toString() {
        return "Mapa{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", dato='" + dato + '\'' +
                '}';
    }
}
