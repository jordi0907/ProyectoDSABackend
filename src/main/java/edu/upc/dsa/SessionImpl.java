package edu.upc.dsa;

import edu.upc.dsa.util.ObjectHelper;
import edu.upc.dsa.util.QueryHelper;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class SessionImpl implements Session {
    private final Connection conn;
    final static Logger logger = Logger.getLogger(SessionImpl.class);
    public SessionImpl(Connection conn) {
        this.conn = conn;
    }


    @Override
    public String save(Object entity) throws Exception {
        String insertQuery = QueryHelper.createQueryINSERT(entity);
        PreparedStatement pstm = null;
        String ID = "";

        try {
            pstm = conn.prepareStatement(insertQuery);

//           He añadido el valor de ID al usuario, y ahora recorro todos los valores desde el principio porque quiero poner el ID
            int i = 1;
            // recorro la lista de nombres de los atributos de la clase
            for (String field: ObjectHelper.getStrFields(entity)) {
               pstm.setObject(i++, ObjectHelper.getter(entity, field));
            }

            pstm.executeQuery();



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (String) ObjectHelper.getter(entity, "id");
    }

    @Override
    public void close() {

    }

    @Override
    public Object get(Class theClass, String ID) {
        Object obj = null;
        PreparedStatement pstm = null;
        //Instantiating a object of type class for the getters

        try {
            String selectQuery = QueryHelper.createQuerySELECT(theClass.newInstance());

            obj = theClass.newInstance();
            pstm = conn.prepareStatement(selectQuery);

            pstm.setObject(1, ID);
            ResultSet resultSet =  pstm.executeQuery();
            int i = 0;

            while (resultSet.next()){

                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                for(i=1;i<=resultSetMetaData.getColumnCount();i++){
                    String name = resultSetMetaData.getColumnName(i);
                    ObjectHelper.setter(obj,name, resultSet.getObject(i));
                }

            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        logger.info("el objeto es " +obj );
        return obj;
    }

    @Override
    public List<Object> query(String query, Class theClass, List params) {
        PreparedStatement pstm = null;
        List<Object> objResultado = new LinkedList<>();

        try{
            pstm = conn.prepareStatement(query);
            int i = 1;
            for(Object obj: params){
                pstm.setObject(i, params.get(i-1));
                i++;
            }

            ResultSet resultSet = pstm.executeQuery();
            while(resultSet.next()) {
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                for(int j=1;j<=resultSetMetaData.getColumnCount();j++){
                    objResultado.add(resultSet.getObject(j));

                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return objResultado;
    }

    @Override
    public List<Object> queryObjects(String query, Class theClass, List params) {
        PreparedStatement pstm = null;
        List<Object> objResultado = new LinkedList<>();
        Object obj = null;
        try{
            obj = theClass.newInstance();
            pstm = conn.prepareStatement(query);
            int i = 1;
            for(Object o: params){
                pstm.setObject(i, params.get(i-1));
                i++;
            }

            ResultSet resultSet = pstm.executeQuery();
            while(resultSet.next()) {
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                for(int j=1;j<=resultSetMetaData.getColumnCount();j++){
                    String name = resultSetMetaData.getColumnName(j);
                    ObjectHelper.setter(obj,name, resultSet.getObject(j));

                }
                logger.info("objeto añadido " +obj);
                objResultado.add(obj);
                obj = theClass.newInstance();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return objResultado;
    }
}