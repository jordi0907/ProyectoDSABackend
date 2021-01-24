package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.LinkedList;
import java.util.List;

public class Usuario {

    String id; // id del usuario
    String username; // nombre del usuario
    String password; // password del usuario
    int vida; // Vida del usuario
    int defensa; // Defensa del usuario
    int dinero; //Dinero del usuario
    int tiempo; // Tiempo que tarda el usuario en pasarse la partida

     List<Objetos> objetosList;

    // lista de objetos.

    public Usuario() {
        this.id = RandomUtils.getId();
        this.vida = 100;
        this.defensa = 0;
        this.tiempo= 0;
        this.dinero = 100;
        this.objetosList = new LinkedList<>();
    }

    public Usuario(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public List<Objetos> getObjetosList() {
        return objetosList;
    }

    public void setObjetosList(List<Objetos> objetosList) {
        this.objetosList = objetosList;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUser='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", vida=" + vida +
                ", defensa=" + defensa +
                ", dinero=" + dinero +
                ", tiempo=" + tiempo +
                ", objetosList=" + objetosList +
                '}';
    }
}
