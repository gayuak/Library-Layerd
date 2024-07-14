package lk.ijse.library.dao.custom.impl;

import lk.ijse.library.dao.CrudDAO;
import lk.ijse.library.dao.SQLUtil;
import lk.ijse.library.dao.custom.CustomerDAO;
import lk.ijse.library.entity.Book;
import lk.ijse.library.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Customer VALUES(?, ?, ?, ?,?)",customer.getId(),customer.getName(),customer.getContact(),customer.getAddress(),customer.getUserId());
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Customer SET Cname = ?, address = ?, contact = ? WHERE CId = ?",customer.getName(),customer.getAddress(),customer.getContact(),customer.getId());
    }

    @Override
    public boolean delete(String Id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Customer WHERE CId = ?",Id);
    }

    @Override
    public List getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> customersList = new ArrayList<>();
        while (resultSet.next()) {
            String CId = resultSet.getString(1);
            String Cname = resultSet.getString(2);
            String contact = resultSet.getString(3);
            String address = resultSet.getString(4);
            String userId = resultSet.getString(5);

            Customer customer = new Customer(CId, Cname, contact, address, userId);
            customersList.add(customer);
        }
        return customersList;
    }


    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT CId FROM Customer");
        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    @Override
    public String currentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet=SQLUtil.execute("SELECT CId FROM Customer ORDER BY CAST(SUBSTRING(CId,2)AS UNSIGNED)  desc LIMIT 1");

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;    }

    @Override
    public Customer exist(String id) throws SQLException, ClassNotFoundException {
        System.out.println(id);
        ResultSet resultSet =  SQLUtil.execute("SELECT * FROM Customer WHERE CId = ?",id);
       resultSet.next();
        String CId = resultSet.getString(1);
        String Cname = resultSet.getString(2);
        String contact = resultSet.getString(3);
        String address = resultSet.getString(4);
        String userId = resultSet.getString(5);

            Customer customer = new Customer(CId, Cname, contact, address, userId);

        return customer;
    }
}
