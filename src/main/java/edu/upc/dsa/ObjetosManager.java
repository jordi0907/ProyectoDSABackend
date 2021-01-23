package edu.upc.dsa;

import edu.upc.dsa.models.Objetos;
import edu.upc.dsa.models.Usuario;

import java.util.*;

public interface ObjetosManager {
    public List<Objetos> getAllObjects();// Devolver todos los objetos que hay en el juego
    public Objetos getObjectFromAllObjects(String objectName); // Devuelve un objeto de la lista total de objetos del juego
    public List<Objetos> getListObjectsUser(Usuario user);// Devolver todos los objetos de un Usuario
    public Objetos getObjectUser(Usuario user, String objectName);// Devuelve un objeto especifico de un usuario
    public void deleteObject(Usuario user, String objectName); // acceder a la lista de objetos y buscarlo por el nombre

    public boolean addObject(Objetos o);


    public List<Objetos> getAllObject();
    int deleteObjectUsuario(Objetos o);


}
