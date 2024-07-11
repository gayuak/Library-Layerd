package lk.ijse.library.bo.custom.impl;

import lk.ijse.library.bo.custom.CustomerBO;
import lk.ijse.library.dao.DAOFactory;
import lk.ijse.library.dao.DAOFactory.*;
import lk.ijse.library.dao.custom.CustomerDAO;
import lk.ijse.library.dto.BookDTO;
import lk.ijse.library.dto.CustomerDTO;
import lk.ijse.library.entity.Book;
import lk.ijse.library.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOTypes.Customer);


    @Override
    public List<CustomerDTO> getAllCus() throws SQLException, ClassNotFoundException {
        List<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> books= new ArrayList<>();
        for (Customer c : all) {
            CustomerDTO bookDTO = new CustomerDTO(c.getId(),c.getName(),c.getContact(),c.getAddress(),c.getUserId());
            books.add(bookDTO);
        }
        return books;    }

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getContact(), customerDTO.getAddress(), customerDTO.getUserId());
        boolean save = customerDAO.save(customer);
        return save;
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getContact(),customerDTO.getAddress(),customerDTO.getUserId());
        boolean update = customerDAO.update(customer);
        return update;
    }

    @Override
    public boolean deleteCustomer(String customerDTO) throws SQLException, ClassNotFoundException {
        boolean delete = customerDAO.delete(customerDTO);
        return delete;
    }

    @Override
    public CustomerDTO searchById(String id) throws SQLException, ClassNotFoundException {
        Customer c = customerDAO.exist(id);
        CustomerDTO customerDTO = new CustomerDTO(c.getId(),c.getName(),c.getContact(),c.getAddress(),c.getUserId());

        return customerDTO;
    }
}
