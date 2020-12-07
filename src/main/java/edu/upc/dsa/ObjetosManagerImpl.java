package edu.upc.dsa;

import edu.upc.dsa.models.Objetos;
import edu.upc.dsa.models.Usuario;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ObjetosManagerImpl implements ObjetosManager {

    private static ObjetosManager instance;
    final static Logger logger = Logger.getLogger(ObjetosManagerImpl.class);
    protected List<Objetos> allObjects;
    UsuarioManager us;
   // protected Usuario user;



    public ObjetosManagerImpl(){
        this.allObjects= new LinkedList<>();
        this.allObjects.add(new Objetos("mascarilla",25,0,20));
        this.allObjects.add(new Objetos("pocion",50,50,60));
        this.allObjects.add(new Objetos("regeneron",100,100,75));
        this.allObjects.add(new Objetos("pcr",20,0,15));
        this.allObjects.add(new Objetos("bolsabasura",0,0,1));
        this.us= UsuarioManagerImpl.getInstance();


    }
    public static ObjetosManager getInstance() {
        if (instance==null) instance = new ObjetosManagerImpl();
        return instance;
    }


    @Override
    public List<Objetos> getAllObjects() {
        return this.allObjects;
    }

    @Override
    public List<Objetos> getListObjectsUser(Usuario user) {
        return user.getObjetosList();
    }

    @Override
    public Objetos getObjectUser(Usuario user, String objectName) {

        Usuario usuario = this.us.getUser(user.getUsername());

        for(Objetos i: usuario.getObjetosList()){
            if(i.getNombre().equals(objectName)){
                logger.info("objeto: "+ i);
                return i;
            }
        }
        logger.info("El usuario "+usuario.getUsername()+" no tiene este objeto "+ objectName);
        return null;
    }

    @Override
    public Objetos getObjectFromAllObjects(String objectName) {
        for(Objetos i: this.allObjects){
            if(i.getNombre().equals(objectName)) return i;
        }
        logger.info("El objeto no existe");
        return null;
    }

    @Override
    public void deleteObject(Usuario user, String objectName) {

        if(this.us.getUser(user.getUsername())== null) logger.warn("El user no existe");

        Objetos o= getObjectUser(user,objectName);

            if(o==null){
                logger.warn("Not found "+o);
            }
            else logger.info(o+" Deleted");

            user.getObjetosList().remove(o);

    }

    @Override
    public void addObject() {

    }
}
