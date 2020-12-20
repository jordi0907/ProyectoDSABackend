package edu.upc.dsa.util;

import org.apache.log4j.Logger;

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
            sb.append("?,");
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

        return sb.toString();
    }

}

