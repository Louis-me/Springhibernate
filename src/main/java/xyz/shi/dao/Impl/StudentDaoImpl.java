package xyz.shi.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.shi.dao.StudentDao;
import xyz.shi.domain.QueryResult;
import xyz.shi.domain.Student;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void save(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.save(student);
    }

    @Override
    public QueryResult findAll(int firstResult, int maxResults) {
        Session session = sessionFactory.getCurrentSession();
        List<Object[]> list = session.createQuery( //
                        "select s, g from Student s join s.grade g") //
                .setFirstResult(firstResult) //
                .setMaxResults(maxResults) //
                .list();

        // 查询总记录数
        Long count = (Long) session.createQuery( //
                        "SELECT COUNT(*) FROM Student") //
                .uniqueResult();
        return new QueryResult(count.intValue(), list);
    }

    @Override
    public void update(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.update(student);
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Object user = session.get(Student.class, id);
        session.delete(user);

    }
}
