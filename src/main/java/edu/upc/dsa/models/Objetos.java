package edu.upc.dsa.models;

public class Objetos {

    //id
    String nombre;
    int coste;
    int puntoSaludRecuperados;
    int puntosDefensAdd;

    public Objetos() {
    }

    public Objetos(String nombre, int coste, int puntoSaludRecuperados, int puntosDefensAdd) {
        this.nombre = nombre;
        this.coste = coste;
        this.puntoSaludRecuperados = puntoSaludRecuperados;
        this.puntosDefensAdd = puntosDefensAdd;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
                "nombre='" + nombre + '\'' +
                ", coste=" + coste +
                ", puntoSaludRecuperados=" + puntoSaludRecuperados +
                ", puntosDefensAdd=" + puntosDefensAdd +
                '}';
    }
}
