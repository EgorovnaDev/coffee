package su.egorovna.coffee.service;

import su.egorovna.coffee.dao.GoodsDAO;
import su.egorovna.coffee.dao.impl.GoodsDAOImpl;
import su.egorovna.coffee.entity.GoodsEntity;

import java.util.List;

public class GoodsService {

    private final GoodsDAO dao = new GoodsDAOImpl();

    public GoodsEntity findById(int id) {
        return dao.findById(id);
    }

    public void save(GoodsEntity entity) {
        dao.save(entity);
    }

    public void update(GoodsEntity entity) {
        dao.update(entity);
    }

    public void delete(GoodsEntity entity) {
        dao.delete(entity);
    }

    public List<GoodsEntity> findAll() {
        return dao.findAll();
    }

    public List<GoodsEntity> findAllByPage(int page, int count) {
        return dao.findAllByPage(page, count);
    }

}
