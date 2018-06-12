package pl.lodz.p.pkck.xmlprocessorexample.dao;

import pl.lodz.p.pkck.xmlprocessorexample.exception.DaoException;

public interface Dao<T> {

    public T read(String path) throws DaoException;

    public void write(T obj, String path) throws DaoException;

}
