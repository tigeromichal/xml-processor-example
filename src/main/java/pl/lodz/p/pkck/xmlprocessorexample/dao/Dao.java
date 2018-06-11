package pl.lodz.p.pkck.xmlprocessorexample.dao;

public interface Dao<T> {

    public T read(String path);

    public void write(T obj, String path);

}
