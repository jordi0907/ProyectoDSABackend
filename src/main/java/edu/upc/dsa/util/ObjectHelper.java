package edu.upc.dsa.util;

import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ObjectHelper {
    final static Logger logger = Logger.getLogger(ObjectHelper.class);

    //MODIFICADO es el que utilizo REtorna la lista de atributos de la clase, no tiene en cuenta la Lista
    public static String[] getStrFields(Object entity) {

        Class theClass = entity.getClass();
        Field[] fields = theClass.getDeclaredFields();
        ArrayList<String> sFields = new ArrayList<String>();
        int i=0;

        for (Field f: fields) {
            if(!f.getName().contains("List"))
                sFields.add(f.getName());
        }
        String[] sFieldsArr = new String[sFields.size()];
        sFieldsArr = sFields.toArray(sFieldsArr);
        return sFieldsArr;

    }

    public static Object setter(Object object, String property, Object value)  throws NoSuchMethodException{

        Class<?> theClass = object.getClass();
        try{
            String gMethod = "set"+ property.substring(0,1).toUpperCase()+property.substring(1);
            Method setter = null;
            Class paramType = value.getClass();

            if(paramType == Integer.class){
                setter = theClass.getMethod(gMethod,Integer.TYPE);
                setter.invoke(object, value);
            }else{
                setter = theClass.getMethod(gMethod,paramType);
                setter.invoke(object, value);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        logger.info("retorna el objeto : " + object);
        return object;
    }


    public static Object getter(Object object, String property)throws Exception {

        Object ret = null;
        Class theClass = object.getClass();
        logger.info("la clase es  : " + theClass + " y el property " + property);

        String sMethod = "get"+ property.substring(0,1).toUpperCase()+property.substring(1);
        logger.info("el metodo es  : " + sMethod);
        Method getter = theClass.getMethod(sMethod);

        ret = getter.invoke(object);
        logger.info("retorno  : " + ret);
        return ret;
    }

}
