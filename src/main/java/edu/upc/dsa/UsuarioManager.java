package edu.upc.dsa;

import edu.upc.dsa.models.Objetos;
import edu.upc.dsa.models.Usuario;

public interface UsuarioManager {

    public Usuario getUser(String username);
   /* public Usuario addUser(String nombre, String password);*/
    public void updateUser(UsuarioManager user); // Vida, posicion, defensa, comprar objetos, Perder Vida...
    public void deleteUser(String idUser);
    public Object getObjeto(Objetos Objeto, String idUser);
    public void sellObjeto(Objetos Objeto);
    public int sizeUser();
    public Usuario addUser(Usuario u);
    public Usuario loggin(Usuario u);


   /* public Track addTrack(String title, String singer);
    public Track addTrack(Track t);
    public Track getTrack(String id);
    public List<Track> findAll();
    public void deleteTrack(String id);
    public Track updateTrack(Track t);

    public int size();*/

}
