package jmaster.dao.impl;
import jmaster.dao.UserDao;
import jmaster.entity.User;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    SessionFactory sessionFactory;
    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().merge(user);
    }

    @Override
    public void delete(int id) {
        sessionFactory.getCurrentSession().delete(get(id));
    }

    @Override
    public User get(int id) {
        return (User)sessionFactory.getCurrentSession().get(User.class,id);
    }

    @Override
    public List<User> getAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        return criteria.list();
    }
}
