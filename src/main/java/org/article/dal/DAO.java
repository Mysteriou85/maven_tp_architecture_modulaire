package org.article.dal;

import java.util.List;

public interface DAO<T> {

    // --- insert ---
    void insert(T data) throws DALException;

    // --- delete ---
    void delete(T data) throws DALException;

    // --- update ---
    void update(T data) throws DALException;

    // --- select ---
    T selectById(long id) throws DALException;

    List<T> selectAll() throws DALException;

}
