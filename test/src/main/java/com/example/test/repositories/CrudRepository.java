package com.example.test.repositories;

import java.sql.SQLException;
import java.util.Collection;

public interface CrudRepository<T, ID> {

    Collection<T> findAll();

    T findById(ID id);

    void save(T object) ;

    void update(T object);

    void delete(ID id) ;
}
