package edu.upc.dsa.util;

import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class QueryHelper {
    final static Logger logger = Logger.getLogger(QueryHelper.class);
    public static String createQueryINSERT(Object entity) {

        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("(");

        //INSERT INTO Usuario (
        String [] fields = ObjectHelper.getStrFields(entity);
        for (String field: fields) {
            sb.append(field);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(") VALUES (");

        // INSERT INTO Usuario (ID,username,password,vida,defensa,dinero,tiempo) VALUES (
        for (String field: fields) {
            if(field.equals("password")){
                sb.append("AES_ENCRYPT(?,'thePassword'),");
            }
            else {
                sb.append("?,");
            }
        }
        sb.deleteCharAt(sb.length()-1);

        sb.append(")");
        //INSERT INTO Usuario (ID,username,password,vida,defensa,dinero,tiempo) VALUES (?,?,?,?,?,?,?)
        logger.info("QueryHelper la query es" + sb.toString());
        return sb.toString();

    }

    public static String createQuerySELECT(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE id = ?");
        //SELECT * FROM Usuario WHERE id = ?
        return sb.toString();
    }

    public static String[] createQueryUPDATE(Object entity) {
        StringBuffer sb = new StringBuffer("UPDATE ");
        List<String> fieldsOrdered = new LinkedList<>();
        sb.append(entity.getClass().getSimpleName());
        sb.append(" SET ");
        String [] fields = ObjectHelper.getStrFields(entity);

        for (String field: fields) {
            if(field.equals("password")){
                sb.append(field).append(" = AES_ENCRYPT(?,'thePassword'),");
                fieldsOrdered.add("password");
            }
            else if(!field.equals("id")){
                fieldsOrdered.add(field);
                sb.append(field).append(" = ?,");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(" WHERE id = ?");
        fieldsOrdered.add("id");
        String[] orderArry = new String[fieldsOrdered.size()+1];
        orderArry[0]= sb.toString();
        for(int k = 0;k<fieldsOrdered.size();k++){
            orderArry[k+1] = fieldsOrdered.get(k);
        }
        return orderArry;
    }

}

