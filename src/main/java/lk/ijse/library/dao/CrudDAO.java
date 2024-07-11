package lk.ijse.library.dao;

import lk.ijse.library.entity.Book;
import lk.ijse.library.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    boolean save(T entity) throws SQLException, ClassNotFoundException;
    boolean update(T entity) throws SQLException, ClassNotFoundException;
    boolean delete(String Id) throws SQLException, ClassNotFoundException;
    List<T> getAll() throws SQLException, ClassNotFoundException;
    List<String> getIds() throws SQLException, ClassNotFoundException;
    String currentId() throws SQLException, ClassNotFoundException;
    T exist(String id) throws SQLException , ClassNotFoundException;

}
