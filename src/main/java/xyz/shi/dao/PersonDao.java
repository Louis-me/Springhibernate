package xyz.shi.dao;

import xyz.shi.domain.QueryResult;
import xyz.shi.domain.Person;

public interface PersonDao {
    void save(Person person);
    void update(Person person);
    Person findById(int id);
    QueryResult findAll(int firstResult, int maxResults);
    void delete(Person person);
}
