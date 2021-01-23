package edu.upc.dsa;

import edu.upc.dsa.models.Objetos;
import edu.upc.dsa.models.Usuario;

public interface UsuarioManager {

    public Usuario getUser(String username);
    //public Usuario addUser(String nombre, String password);
    public Usuario updateUser(Usuario user); // Vida, posicion, defensa, comprar objetos, Perder Vida...
    public void deleteUser(String idUser);
    public Object getObjeto(Objetos Objeto, String idUser);
    public void sellObjeto(Objetos Objeto);
    public int sizeUser();
    public Usuario addUser(Usuario u);
    public Usuario loggin(Usuario u);
    public Usuario getUsuarioActualizado(String id);

}
