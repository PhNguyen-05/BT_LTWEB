package vn.iotstar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfigs;
import vn.iotstar.dao.CategoryDao;
import vn.iotstar.entity.Category;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public void insert(Category category) {
        EntityManager enma = JPAConfigs.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(category);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public void update(Category category) {
        EntityManager enma = JPAConfigs.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.merge(category);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public void delete(int cateid) throws Exception {
        EntityManager enma = JPAConfigs.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            Category category = enma.find(Category.class, cateid);
            if (category != null) {
                enma.remove(category);
            } else {
                throw new Exception("Không tìm thấy");
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public Category findById(int categoryId) {
        EntityManager enma = JPAConfigs.getEntityManager();
        Category category = enma.find(Category.class, categoryId);
        enma.close();  // Thêm close
        return category;
    }

    @Override
    public List<Category> findAll() {
        EntityManager enma = JPAConfigs.getEntityManager();
        TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
        List<Category> result = query.getResultList();
        enma.close();
        return result;
    }

    @Override
    public List<Category> findAll(int page, int pagesize) {
        EntityManager enma = JPAConfigs.getEntityManager();
        TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
        query.setFirstResult(page * pagesize);
        query.setMaxResults(pagesize);
        List<Category> result = query.getResultList();
        enma.close();
        return result;
    }

    @Override
    public List<Category> findByCategoryname(String catname) {
        EntityManager enma = JPAConfigs.getEntityManager();
        String jpql = "SELECT c FROM Category c WHERE c.CategoryName like :catname";  // Sửa field name
        TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
        query.setParameter("catname", "%" + catname + "%");
        List<Category> result = query.getResultList();
        enma.close();
        return result;
    }

    @Override
    public int count() {
        EntityManager enma = JPAConfigs.getEntityManager();
        String jpql = "SELECT count(c) FROM Category c";
        Query query = enma.createQuery(jpql);
        int count = ((Long) query.getSingleResult()).intValue();
        enma.close();
        return count;
    }
}