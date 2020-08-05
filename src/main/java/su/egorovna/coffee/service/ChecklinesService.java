package su.egorovna.coffee.service;

import su.egorovna.coffee.dao.ChecklinesDAO;
import su.egorovna.coffee.dao.impl.ChecklinesDAOImpl;
import su.egorovna.coffee.entity.ChecklinesEntity;

import java.util.List;

public class ChecklinesService {

    ChecklinesDAO dao = new ChecklinesDAOImpl();

    public ChecklinesEntity findById(int id) {
        return dao.findById(id);
    }

    public void save(ChecklinesEntity entity) {
        dao.save(entity);
    }

    public void update(ChecklinesEntity entity) {
        dao.update(entity);
    }

    public void delete(ChecklinesEntity entity) {
        dao.delete(entity);
    }

    public List<ChecklinesEntity> findAll() {
        return dao.findAll();
    }

}
