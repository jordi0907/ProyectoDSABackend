package edu.upc.dsa;

import java.util.List;

public interface Session<E> {
    String save(Object entity) throws Exception;
    void close();
    Object get(Class theClass, String ID);
//    void update(Object object);
//    void delete(Object object);
//    List<Object> findAll(Class theClass);
//    List<Object> findAll(Class theClass, HashMap params);
    List<Object> query(String query, Class theClass, List params);
    List<Object> queryObjects(String query, Class theClass, List params);
    int update(Object object);
    int delete(Object object);
}
