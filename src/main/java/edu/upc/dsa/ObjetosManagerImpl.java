package edu.upc.dsa;

import edu.upc.dsa.models.Objetos;
import edu.upc.dsa.models.Usuario;
import edu.upc.dsa.util.RandomUtils;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
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
//        this.allObjects.add(new Objetos("mascarilla",25,0,20));
//        this.allObjects.add(new Objetos("pocion",50,50,0));
//        this.allObjects.add(new Objetos("regeneron",100,100,75));
//        this.allObjects.add(new Objetos("pcr",20,0,0));
//        this.allObjects.add(new Objetos("bolsabasura",0,0,1));
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
    public boolean addObject(Objetos o) {
        Session session = null;
        List<String> params= new LinkedList<>();
        logger.info("objeto: "+ o);
        Usuario user = us.getUsuarioActualizado(o.getUserId());
        int coste = o.getCoste();
        int dinero = us.getUsuarioActualizado(o.getUserId()).getDinero();
        int dineroFinal = dinero-coste;

        if (coste>dinero)
        {
            logger.info("No hay dinero");
            return false;
        }
        else{
            user.setDinero(dineroFinal);
            us.updateUser(user);

            try{
                session = FactorySession.openSession();
                String query = "INSERT INTO usuarioobjetos(ID, objetoId, usuarioId) VALUES (?,?,?)";

                String ID = RandomUtils.getId();
                String idObject = o.getId();
                String idUser = o.getUserId();
                params.add(ID);
                params.add(idObject);
                params.add(idUser);

                session.query(query, Objetos.class, params);
             }
            catch(Exception e) {
            e.printStackTrace();

            }
            finally {
               session.close();
            }

        return true;
        }
    }

    @Override
    public List<Objetos> getAllObject() {
        Session session = null;
        List<Objetos> objetosList = null;
        List<String> params= new LinkedList<>();


        try{
            session = FactorySession.openSession();
            String query = "SELECT * FROM Objetos";
            //String query = "SELECT * FROM objetos";
            objetosList = (List) session.queryObjects(query, Objetos.class, params);
            logger.info("objetoslist: " + objetosList);
        }
        catch(Exception e) {
            e.printStackTrace();

        }
        finally {
            session.close();
        }
         return objetosList;

    }

    @Override
    public int deleteObjectUsuario(Objetos o) {
        Session session = null;
        List<String> params= new LinkedList<>();
        logger.info("deleteobjeto: " + o.getUserId());
        try{
            session = FactorySession.openSession();

            String query = "DELETE from usuarioobjetos WHERE objetoId = ? and usuarioId = ? limit 1";

            String idObject = o.getId();
            String idUser = o.getUserId();
            params.add(idObject);
            params.add(idUser);
            session.query(query, Objetos.class, params);
        }
        catch(Exception e) {
            e.printStackTrace();
            return 1;

        }
        finally {
            session.close();
        }

        return 0;


    }


}
