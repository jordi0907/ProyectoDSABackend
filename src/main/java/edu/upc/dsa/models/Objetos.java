package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Objetos {

    String id;
    String userId;
    String nombre;
    int coste;
    int puntoSaludRecuperados;
    int puntosDefensAdd;

    public Objetos() {
    }

    public Objetos( String userId,String nombre, int coste, int puntoSaludRecuperados, int puntosDefensAdd) {
        this.userId = userId;
        this.nombre = nombre;
        this.coste = coste;
        this.puntoSaludRecuperados = puntoSaludRecuperados;
        this.puntosDefensAdd = puntosDefensAdd;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCoste() {
        return coste;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }

    public int getPuntoSaludRecuperados() {
        return puntoSaludRecuperados;
    }

    public void setPuntoSaludRecuperados(int puntoSaludRecuperados) {
        this.puntoSaludRecuperados = puntoSaludRecuperados;
    }

    public int getPuntosDefensAdd() {
        return puntosDefensAdd;
    }

    public void setPuntosDefensAdd(int puntosDefensAdd) {
        this.puntosDefensAdd = puntosDefensAdd;
    }

    @Override
    public String toString() {
        return "Objetos{" +
                "ID='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", userId='" + userId + '\'' +
                ", coste=" + coste +
                ", puntoSaludRecuperados=" + puntoSaludRecuperados +
                ", puntosDefensAdd=" + puntosDefensAdd +
                '}';
    }
}
