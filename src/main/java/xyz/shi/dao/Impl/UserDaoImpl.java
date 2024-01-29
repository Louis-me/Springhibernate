package xyz.shi.dao.Impl;

import org.hibernate.SessionFactory;
import xyz.shi.dao.UserDao;
import xyz.shi.domain.QueryResult;
import xyz.shi.domain.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;
    //    private HibernateTemplate template;
    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);

    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);

    }

    @Override
    public User findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        return user;
    }


    /**
     * 分页的查询数据列表
     * @param firstResult 从结果列表中的哪个索引开始取数据
     * @param maxResults 最多取多少条数据
     * @return 一页的数据列表
     */
    @Override
    public QueryResult findAll(int firstResult, int maxResults) {
        Session session = sessionFactory.getCurrentSession();

        List<User> list = session.createQuery(
                        "FROM User")
                .setFirstResult(firstResult)
                .setMaxResults(maxResults)
                .list();
        Long count = (Long)session.createQuery( //
                        "SELECT COUNT(*) FROM User") //
                .uniqueResult();
        return new QueryResult(count.intValue(), list);
    }


    @Override
    public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);

    }
}
