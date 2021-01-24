package edu.upc.dsa;

import edu.upc.dsa.util.ObjectHelper;
import edu.upc.dsa.util.QueryHelper;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Arrays;
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
                logger.info("EL PARAMETRO ES " +params.get(i-1) );
                i++;
            }

            ResultSet resultSet = pstm.executeQuery();
            logger.info("ELRESULTADO DE LA QUERY OBJETOS " +resultSet );
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

    public int update(Object entity) {
        String[] updateSentenciaCampos = QueryHelper.createQueryUPDATE(entity);
        String updateQuery = updateSentenciaCampos[0];
        String[] fieldsOrdenados = Arrays.copyOfRange(updateSentenciaCampos,1,(updateSentenciaCampos.length));
        PreparedStatement preparedStatement;int affectedRows = 0;
        try {
            preparedStatement = conn.prepareStatement(updateQuery);
            int i = 1;
            for (String field: fieldsOrdenados) {
                Object objt = ObjectHelper.getter(entity, field);
                preparedStatement.setObject(i, objt);
                i++;
            }
            affectedRows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    @Override
    public int delete(Object object) {
        String delete = QueryHelper.createQueryDELETE(object);
        int affectedRows = 0;
        PreparedStatement pstm = null;
        try {
            pstm=conn.prepareStatement(delete);
            for(String field: ObjectHelper.getStrFields(object)){
                if(field.equals("id")) {
                    pstm.setObject(1, ObjectHelper.getter(object, field));
                }
                if(field.equals("ID")) {
                    pstm.setObject(1, ObjectHelper.getter(object, field));
                }
            }
            affectedRows = pstm.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return affectedRows;
    }

    @Override
    public List<Object> findAll(Class theClass) {
        PreparedStatement pstm = null;
        //Instantiating a object of type class for the getters
        List<Object> objList = new LinkedList<>();
        try {
            String selectQuery = QueryHelper.createQuerySELECTALL(theClass.newInstance());
            pstm = conn.prepareStatement(selectQuery);
            ResultSet resultSet = pstm.executeQuery();
            while(resultSet.next()) {
                Object obj = theClass.newInstance();
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
                    String name = resultSetMetaData.getColumnName(i);
                    obj = ObjectHelper.setter(obj,name, resultSet.getObject(i));
                }
                objList.add(obj);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return objList;
    }

    @Override
    public int updatePassword(Object entity) {
        String[] updateSentenciaCampos = QueryHelper.createQueryPasswordUPDATE(entity);
        String updateQuery = updateSentenciaCampos[0];
        String[] fieldsOrdenados = Arrays.copyOfRange(updateSentenciaCampos,1,(updateSentenciaCampos.length));
        PreparedStatement preparedStatement;int affectedRows = 0;
        try {
            preparedStatement = conn.prepareStatement(updateQuery);
            int i = 1;
            for (String field: fieldsOrdenados) {
                Object objt = ObjectHelper.getter(entity, field);
                preparedStatement.setObject(i, objt);
                i++;
            }
            affectedRows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return affectedRows;
    }


}
