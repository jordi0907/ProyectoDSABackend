package edu.upc.dsa;

import edu.upc.dsa.models.Mapa;
import edu.upc.dsa.models.Usuario;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapaManagerImpl implements MapaManager {
    private static MapaManager instance;
    final static Logger logger = Logger.getLogger(MapaManagerImpl.class);

    public MapaManagerImpl() {
    }

    public static MapaManager getInstance() {
        if (instance == null) instance = new MapaManagerImpl();
        return instance;
    }

    @Override
    public Mapa getMapa(String id) {
        Mapa mapa = null;
        Session session = null;
        List<Usuario> usuarioList2 = null;
        List<Usuario> usuarioListRanking = null;

        try {
            session = FactorySession.openSession();
            mapa = (Mapa) session.get(Mapa.class, id);


        } catch (Exception e) {
            // LOG
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
        return mapa;

    }

}
