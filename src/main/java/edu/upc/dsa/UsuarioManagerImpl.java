package edu.upc.dsa;

import edu.upc.dsa.models.Objetos;
import edu.upc.dsa.models.Usuario;
import org.apache.log4j.Logger;

import java.util.*;

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
        u.setPassword("");
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
        String query = "SELECT id FROM Usuario WHERE username = ? AND password = AES_ENCRYPT(?,'thePassword')";
        //String query = "SELECT id FROM usuario WHERE username = ? AND password = AES_ENCRYPT(?,'thePassword')";
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
       // usuario.setPassword(password);

        return usuario;
    }

    @Override
    public Usuario getUsuarioActualizado(String id) {
        Session session = null;
        Usuario usuario = null;
        List<Objetos> objetosList=new LinkedList<>();
        logger.info("El id es : " + id);
        String query = "SELECT * FROM Usuario where id = ?";
        //String query = "SELECT * FROM usuario where id = ?";
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

    @Override
    public List<Usuario> getRankingUsuarios() {
        Session session = null;
        List<Usuario> usuarioList2 = null;
        List<Usuario> usuarioListRanking= null;

        try {
            session = FactorySession.openSession();
            usuarioList2 = session.findAll(Usuario.class);

        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
        }
        finally {
            if(session!=null)
                session.close();
        }
        usuarioListRanking = listarUsuarioRankingAsc(usuarioList2);
        return usuarioListRanking;
    }

    @Override
    public Usuario getUpdateFinalPartida(Usuario u) {
        Usuario user = getUsuarioActualizado(u.getId());
        int puntuacionBBDD = u.getTiempo();
        int puntuacion;
        Usuario u2 = null;
        puntuacion = u.getDinero() + u.getVida();

        if(puntuacion>puntuacionBBDD)
        {
            u.setTiempo(puntuacion);
            u2 = updateUser(u);
        }
        else {
            u.setTiempo(puntuacionBBDD);
            u2 = updateUser(u);

        }
        return u2;

    }

    @Override
    public Usuario updatePassword(Usuario user) {
        String passwordOriginal = user.getPassword();
        Session session = null;
        try {
            session = FactorySession.openSession();
            if(session.updatePassword(user)<=0)
            {
                user =  null;
            }
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
        }
        finally {
            if(session!=null)
                session.close();
        }
        //user.setPassword(passwordOriginal);
        return user;
    }


    public List<Usuario> listarUsuarioRankingAsc(List<Usuario> list) {

          Collections.sort(list, new Comparator<Usuario>() {
            @Override
            public int compare(Usuario p1, Usuario p2) {

                logger.info("comparing por precio primer product: " +p1 +" segundo product: " +p2);
                return (int)(p2.getTiempo()-p1.getTiempo());
            }
        });
        logger.info("return lista de productos ordenada por precio " + list.toString());
        return list;
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
    public Usuario updateUser(Usuario user) {
        String passwordOriginal = user.getPassword();
        Session session = null;
        try {
            session = FactorySession.openSession();
            if(session.update(user)<=0)
            {
                user =  null;
            }
        }
        catch (Exception e) {
            // LOG
            e.printStackTrace();
        }
        finally {
            if(session!=null)
                session.close();
        }
        //user.setPassword(passwordOriginal);
        return user;

    }

    @Override
    public int deleteUser(String idUser) {
        Usuario user = getUsuarioActualizado(idUser);
        int res;
        Session session = null;
        try {
            session = FactorySession.openSession();
            res =  session.delete(user);
        }
        catch (Exception e) {
            e.printStackTrace();
            res = -1;
        }
        finally {
            if(session!=null)
                session.close();
        }
        return res;
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
