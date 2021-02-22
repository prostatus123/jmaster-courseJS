package jmaster.dao.impl;

import jmaster.dao.ProductDao;
import jmaster.entity.Product;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void add(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public void update(Product product) {
        sessionFactory.getCurrentSession().merge(product);
    }

    @Override
    public void delete(int id) {
        sessionFactory.getCurrentSession().delete(get(id));
    }

    @Override
    public Product get(int id) {
        return (Product)sessionFactory.getCurrentSession().get(Product.class,id);
    }

    @Override
    public List<Product> getAll() {
        Criteria criteria =  sessionFactory.getCurrentSession().createCriteria(Product.class);
        return criteria.list();
    }
}
