package org.padaria.paokentinho.models.repositories;

import java.sql.SQLException;
import java.util.List;

public interface GenericRepository <T, I> {
    public void create(T t) throws SQLException;
    public List<T> readAll() throws SQLException;
    T read(I id) throws SQLException;
}
