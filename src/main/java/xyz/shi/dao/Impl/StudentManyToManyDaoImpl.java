package xyz.shi.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.shi.dao.StudentManyToManyDao;
import xyz.shi.domain.QueryResult;
import xyz.shi.domain.Student;

import java.util.List;

@Repository
public class StudentManyToManyDaoImpl implements StudentManyToManyDao {
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
        String hql = "SELECT s, c, g FROM Student s " +
                "JOIN s.courses c " +
                "JOIN s.grade g";
        List<Object[]> list = session.createQuery(hql)
                .setFirstResult(firstResult)
                .setMaxResults(maxResults)
                .list();

        // 查询总记录数
        Long count = (Long) session.createQuery( //
                        "SELECT COUNT(*) FROM Student") //
                .uniqueResult();
        return new QueryResult(count.intValue(), list);
    }

}
