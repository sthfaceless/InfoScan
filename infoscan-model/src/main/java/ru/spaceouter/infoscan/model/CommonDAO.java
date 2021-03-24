package ru.spaceouter.infoscan.model;

/**
 * @author danil
 * @date 22.04.19
 */
public interface CommonDAO {

    <T> T getEntityProxy(Class<T> clazz, long id);

    void persistEntity(Object object);
}
