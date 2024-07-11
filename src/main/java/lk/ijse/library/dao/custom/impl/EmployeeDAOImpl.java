package lk.ijse.library.dao.custom.impl;

import lk.ijse.library.dao.custom.EmployeeDAO;
import lk.ijse.library.dto.EmployeeDTO;
import lk.ijse.library.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String Id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Employee> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String currentId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Employee exist(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
