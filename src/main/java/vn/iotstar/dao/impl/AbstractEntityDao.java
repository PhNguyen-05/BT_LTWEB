package vn.iotstar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import vn.iotstar.configs.JPAConfigs;
import vn.iotstar.entity.Category;

public abstract class AbstractEntityDao<T> {
	private Class<T> entityClass;

	public AbstractEntityDao(Class<T> cls) {
		this.entityClass = cls;
	}

	public void insert(T entity) {
		EntityManager enma = JPAConfigs.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(entity);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	public void update(T entity) {
		EntityManager enma = JPAConfigs.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(entity);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	public void delete(Object id) {
		EntityManager enma = JPAConfigs.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			T entity = enma.find(entityClass, id);
			enma.remove(entity);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	public T findById(Object id) {
		EntityManager enma = JPAConfigs.getEntityManager();
		T entity = enma.find(entityClass, id);
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		EntityManager enma = JPAConfigs.getEntityManager();
		try {
			// taọ truy vấn từ entity class
			CriteriaQuery<Object> cq = enma.getCriteriaBuilder().createQuery();
			cq.select(cq.from(entityClass));
			Query q = enma.createQuery(cq);
			return q.getResultList();
		} finally {
			enma.close();
		}
	}

	public Long countAll() {
		EntityManager enma = JPAConfigs.getEntityManager();
		try {
			// taọ truy vấn từ entity class
			CriteriaQuery<Object> cq = enma.getCriteriaBuilder().createQuery();
			Root<T> rt = cq.from(entityClass);

			cq.select(enma.getCriteriaBuilder().count(rt));
			Query q = enma.createQuery(cq);
			return (Long) q.getSingleResult();
		} finally {
			enma.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> finAll(boolean all, int firstResult, int maxResult) {
		EntityManager enma = JPAConfigs.getEntityManager();
		try {
			// taọ truy vấn từ entity class
			CriteriaQuery<Object> cq = enma.getCriteriaBuilder().createQuery();
			cq.select(cq.from(entityClass));
			Query q = enma.createQuery(cq);
			if (!all) {
				q.setFirstResult(firstResult);
				q.setMaxResults(maxResult);
			}
			return q.getResultList();
		} finally {
			enma.close();
		}
	}

}
