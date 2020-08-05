package su.egorovna.coffee.service;

import su.egorovna.coffee.dao.ChecksDAO;
import su.egorovna.coffee.dao.impl.ChecksDAOImpl;
import su.egorovna.coffee.entity.ChecksEntity;

import java.util.List;

public class ChecksService {

    ChecksDAO dao = new ChecksDAOImpl();

    public ChecksEntity findById(int id) {
        return dao.findById(id);
    }

    public void save(ChecksEntity entity) {
        dao.save(entity);
    }

    public void update(ChecksEntity entity) {
        dao.update(entity);
    }

    public void delete(ChecksEntity entity) {
        dao.delete(entity);
    }

    public List<ChecksEntity> findAll() {
        return dao.findAll();
    }

}
