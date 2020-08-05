package su.egorovna.coffee.dao;

import su.egorovna.coffee.entity.GoodsEntity;

import java.util.List;

public interface GoodsDAO extends DAO<GoodsEntity> {

    List<GoodsEntity> findAllByPage(int page, int count);

}
