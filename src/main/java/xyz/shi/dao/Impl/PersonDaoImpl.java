package xyz.shi.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.shi.dao.PersonDao;
import xyz.shi.domain.Person;
import xyz.shi.domain.QueryResult;
import xyz.shi.domain.User;

import java.util.List;

@Repository
public class PersonDaoImpl implements PersonDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);

    }

    @Override
    public void update(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.update(person);
    }

    @Override
    public Person findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        return person;
    }
    @Override
    public QueryResult findAll(int firstResult, int maxResults) {
        Session session = sessionFactory.getCurrentSession();

        List<User> list = session.createQuery(
                        "FROM Person")
                .setFirstResult(firstResult)
                .setMaxResults(maxResults)
                .list();
        Long count = (Long)session.createQuery( //
                        "SELECT COUNT(*) FROM User") //
                .uniqueResult();
        return new QueryResult(count.intValue(), list);
    }

    @Override
    public void delete(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(person);
    }
}
