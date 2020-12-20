package edu.upc.dsa;

import edu.upc.dsa.models.Objetos;
import edu.upc.dsa.models.Usuario;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class UsuarioManagerImpl implements UsuarioManager{

    private static UsuarioManager instance;
    final static Logger logger = Logger.getLogger(UsuarioManagerImpl.class);
    protected HashMap<String, Usuario> usuarioContendor; // Key = username , valor = Usuario
    protected List<Usuario> usuarioList;
    Objetos bolsabasura;

    //protected List<Objetos> objetosList;
   // Objetos bolsabasura;



    public UsuarioManagerImpl() {
        this.usuarioList = new ArrayList<>();
        this.usuarioContendor = new HashMap<String, Usuario>();
        //this.bolsabasura= new Objetos("bolsabasura",0,0,1);
       // this.objetosList = new LinkedList<>();
    }


    public static UsuarioManager getInstance() {
        if (instance==null) instance = new UsuarioManagerImpl();
        return instance;
    }


    @Override
    public Usuario getUser(String username) {
        for (Usuario i: this.usuarioList){
            String nombre = i.getUsername();
            if (nombre.equals(username)) {
                logger.info("Usuario"+ username+"encontrado");
                return i;
            }
        }
        logger.info("Usuario"+ username+" no existe");
        return null;
    }

   /* @Override
    public Usuario addUser(String nombre, String password) {

        *//*return this.addUser(new Usuario(nombre, password));*//*
        return null;

    }*/

    @Override
    public Usuario addUser(Usuario u) {
        Session session = null;

        try{
            session = FactorySession.openSession();
            session.save(u);
        }
        catch(Exception e) {
            e.printStackTrace();

        }
        finally {
            session.close();
        }
        return u;
    }


//    @Override
//    public Usuario addUser(Usuario u) {
//
//        for (String i : usuarioContendor.keySet()) {
//            String nombre = usuarioContendor.get(i).getUsername();
//            if (nombre.equals(u.getUsername())) {
//                logger.info("el nombre ya esta escogido"+u.getUsername());
//                return null;
//            }
//        }
//        u.getObjetosList().add(bolsabasura);
//        this.usuarioContendor.put(u.getIdUser() ,u);
//        usuarioList.add(u);
//        logger.info("Se añade porque no esta en la lista" + this.usuarioContendor.get(u.getIdUser()));
//        return this.usuarioContendor.get(u.getIdUser());
//
//    }
//








    @Override
    public Usuario loggin(Usuario u) {
        Session session = null;
        Usuario usuario = null;
        List<Objetos> objetosList=new LinkedList<>();;
        logger.info("El ID es " + u.getId());
        String username = u.getUsername();
        String password = u.getPassword();
        String query = "SELECT id FROM Usuario WHERE username = ? AND password = ?";
        String query2 = "SELECT objetoId FROM usuarioobjetos WHERE usuarioId = ?";

        List<String> params= new LinkedList<>();
        List<String> params2= new LinkedList<>();
        params.add(username);
        params.add(password);
        try {
            session = FactorySession.openSession();

            List ID = session.query(query, Usuario.class, params);
            String idFinal = (String) ID.get(0);
            usuario = (Usuario) session.get(Usuario.class, idFinal);
            params2.add(usuario.getId());

            if (idFinal != null) {
                List idsObjetosUsuario = session.query(query2, Objetos.class, params2);
                for (Object f : idsObjetosUsuario) {
                    objetosList.add((Objetos)session.get(Objetos.class, (String) f));
                }
            }
            usuario.setObjetosList(objetosList);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally{
            session.close();
        }
        logger.info("retorna el usuario" + usuario);
        return usuario;
    }

    @Override
    public Usuario getUsuarioActualizado(String id) {
        Session session = null;
        Usuario usuario = null;
        List<Objetos> objetosList=new LinkedList<>();
        logger.info("El id es " + id);

        String query = "SELECT * FROM Usuario where id = ?";
        String query2 = "SELECT objetoId FROM usuarioobjetos WHERE usuarioId = ?";
        Usuario Usuario;
        List<String> params= new LinkedList<>();
        List<String> params2= new LinkedList<>();
        params.add(id);
        try {
            session = FactorySession.openSession();
            usuario = (Usuario) session.get(Usuario.class, id);
            params2.add(usuario.getId());

            if (id != null) {
                List idsObjetosUsuario = session.query(query2, Objetos.class, params2);
                for (Object f : idsObjetosUsuario) {
                    logger.info("el objeto añadidio  " +(Objetos)session.get(Objetos.class, (String) f));
                    objetosList.add((Objetos)session.get(Objetos.class, (String) f));
                }
            }
            usuario.setObjetosList(objetosList);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally{
            session.close();
        }
        return usuario;
    }


//    @Override
//    public Usuario loggin(Usuario u) {
//        for (String i : usuarioContendor.keySet()) {
//            String nombre = usuarioContendor.get(i).getUsername();
//            String password = usuarioContendor.get(i).getPassword();
//            if (nombre.equals(u.getUsername()) && password.equals(u.getPassword())) {
//                logger.info("el usuario coincide" + usuarioContendor.get(i) );
//                return usuarioContendor.get(i);
//            } else
//                logger.info("el usuario no coincide");
//            return null;
//        }
//
//        return null;
//    }


    @Override
    public void updateUser(UsuarioManager User) {

    }

    @Override
    public void deleteUser(String idUser) {

    }

    @Override
    public Object getObjeto(Objetos Objeto, String idUser) {
        return null;
    }

    @Override
    public void sellObjeto(Objetos Objeto) {

    }

    @Override
    public int sizeUser() {
        return this.usuarioContendor.size();
    }


}
