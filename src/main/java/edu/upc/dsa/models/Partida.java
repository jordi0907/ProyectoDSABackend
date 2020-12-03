package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.LinkedList;
import java.util.List;

public class Partida {
    String idPartida;
    String idUser; //En partida
    int nivelCompletado = 0; //Para saber en que nivel esta y si pasar al mapa siguiente.
    int puntuacion; // Ranking puede tener lista de puntuacines.
    int Ranking;

    public Partida() {
        this.idPartida = RandomUtils.getId();
    }

    public Partida(String idUser, int nivelCompletado, int puntuacion, int ranking) {
        this();
        this.idUser = idUser;
        this.nivelCompletado = nivelCompletado;
        this.puntuacion = puntuacion;
        Ranking = ranking;
    }

    public String getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(String idPartida) {
        this.idPartida = idPartida;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public int getNivelCompletado() {
        return nivelCompletado;
    }

    public void setNivelCompletado(int nivelCompletado) {
        this.nivelCompletado = nivelCompletado;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getRanking() {
        return Ranking;
    }

    public void setRanking(int ranking) {
        Ranking = ranking;
    }

    @Override
    public String toString() {
        return "Partida{" +
                "idPartida='" + idPartida + '\'' +
                ", idUser='" + idUser + '\'' +
                ", nivelCompletado=" + nivelCompletado +
                ", puntuacion=" + puntuacion +
                ", Ranking=" + Ranking +
                '}';
    }
}
