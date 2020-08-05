package su.egorovna.coffee.dao;

import java.util.List;

public interface DAO<R> {

    R findById(int id);

    void save(R entity);

    void update(R entity);

    void delete(R entity);

    List<R> findAll();

}
