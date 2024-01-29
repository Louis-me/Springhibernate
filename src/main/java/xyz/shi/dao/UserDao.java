package xyz.shi.dao;

import xyz.shi.domain.QueryResult;
import xyz.shi.domain.User;

public interface UserDao {
    void save(User user);
    void update(User user);
    User findById(int id);
    QueryResult findAll(int firstResult, int maxResults);
    void delete(User user);
}
