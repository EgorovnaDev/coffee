package su.egorovna.coffee.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import su.egorovna.coffee.dao.GoodsDAO;
import su.egorovna.coffee.entity.GoodsEntity;
import su.egorovna.coffee.util.DatabaseUtil;

import java.util.ArrayList;
import java.util.List;

public class GoodsDAOImpl implements GoodsDAO {

    @Override
    public GoodsEntity findById(int id) {
        return DatabaseUtil.getSessionFactory().openSession().get(GoodsEntity.class, id);
    }

    @Override
    public void save(GoodsEntity entity) {
        Transaction transaction;
        try (Session session = DatabaseUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GoodsEntity entity) {
        Transaction transaction;
        try (Session session = DatabaseUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(GoodsEntity entity) {
        Transaction transaction;
        try (Session session = DatabaseUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<GoodsEntity> findAll() {
        try (Session session = DatabaseUtil.getSessionFactory().openSession()) {
            return session.createQuery(" FROM GoodsEntity ORDER BY name", GoodsEntity.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<GoodsEntity> findAllByPage(int page, int count) {
        try (Session session = DatabaseUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM GoodsEntity ORDER BY name", GoodsEntity.class).setFirstResult(page * count).setMaxResults(count).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
