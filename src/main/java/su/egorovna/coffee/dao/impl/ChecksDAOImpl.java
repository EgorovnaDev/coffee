package su.egorovna.coffee.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import su.egorovna.coffee.dao.ChecksDAO;
import su.egorovna.coffee.entity.ChecksEntity;
import su.egorovna.coffee.util.DatabaseUtil;

import java.util.ArrayList;
import java.util.List;

public class ChecksDAOImpl implements ChecksDAO {

    @Override
    public ChecksEntity findById(int id) {
        return DatabaseUtil.getSessionFactory().openSession().get(ChecksEntity.class, id);
    }

    @Override
    public void save(ChecksEntity entity) {
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
    public void update(ChecksEntity entity) {
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
    public void delete(ChecksEntity entity) {
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
    public List<ChecksEntity> findAll() {
        try (Session session = DatabaseUtil.getSessionFactory().openSession()) {
            return session.createQuery(" FROM ChecksEntity ORDER BY date, time", ChecksEntity.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
