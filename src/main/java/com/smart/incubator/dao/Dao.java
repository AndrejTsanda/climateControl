package com.smart.incubator.dao;

import com.smart.incubator.exception.DatabaseException;

import java.util.List;

/**
 * Interface Dao, provides definitions (signatures) of methods
 * for performing basic operations for working with databases.
 *
 * @author Tsanda Andrej
 *
 * @param <Domain> generalized essence
 * @param <Key> the key (parameter) by which the operation will be performed
 */
public interface Dao <Domain, Key> {
    public void save(final Domain domain) throws DatabaseException;
    public void delete(final Key key) throws DatabaseException;
    public void update(final Domain domain) throws DatabaseException;
    public Domain getById(final Key key) throws DatabaseException;
    public List<Domain> getAll() throws DatabaseException;
    public Integer getCount() throws DatabaseException;
}
