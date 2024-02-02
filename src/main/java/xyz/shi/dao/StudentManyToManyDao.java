package xyz.shi.dao;

import xyz.shi.domain.QueryResult;
import xyz.shi.domain.Student;

public interface StudentManyToManyDao {
    void save(Student student);
    QueryResult findAll(int firstResult, int maxResults);
}
