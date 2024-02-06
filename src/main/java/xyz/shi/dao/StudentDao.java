package xyz.shi.dao;

import xyz.shi.domain.Student;
import xyz.shi.domain.QueryResult;

public interface StudentDao {
    void save(Student student);
    QueryResult findAll(int firstResult, int maxResults);
    void update(Student student);
    void delete(int id);
}
