package pl.lodz.p.pkck.xmlprocessorexample.dao;

import pl.lodz.p.pkck.xmlprocessorexample.exception.DaoException;

public interface Dao<T> {

    T read(String path) throws DaoException;

    void write(T obj, String path) throws DaoException;

}
