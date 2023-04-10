package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<User> getUserByCar(String carModel, int carSeries) {
        Query<User> query = sessionFactory.getCurrentSession().createQuery("from User WHERE car.model=:model AND car.series=:series", User.class);
        query.setParameter("model", carModel);
        query.setParameter("series", carSeries);
        List<User> list = query.getResultList();
        return list.isEmpty() ? Collections.emptyList():list;
  }
}
