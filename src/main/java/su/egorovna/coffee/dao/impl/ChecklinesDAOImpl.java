package su.egorovna.coffee.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import su.egorovna.coffee.dao.ChecklinesDAO;
import su.egorovna.coffee.entity.ChecklinesEntity;
import su.egorovna.coffee.util.DatabaseUtil;

import java.util.ArrayList;
import java.util.List;

public class ChecklinesDAOImpl implements ChecklinesDAO {

    @Override
    public ChecklinesEntity findById(int id) {
        return DatabaseUtil.getSessionFactory().openSession().get(ChecklinesEntity.class, id);
    }

    @Override
    public void save(ChecklinesEntity entity) {
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
    public void update(ChecklinesEntity entity) {
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
    public void delete(ChecklinesEntity entity) {
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
    public List<ChecklinesEntity> findAll() {
        try (Session session = DatabaseUtil.getSessionFactory().openSession()) {
            return session.createQuery(" FROM ChecklinesEntity", ChecklinesEntity.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
