package lk.ijse.library.bo.custom;

import lk.ijse.library.bo.SuperBO;
import lk.ijse.library.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    List<CustomerDTO> getAllCus() throws SQLException, ClassNotFoundException;
    boolean saveCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String customer) throws SQLException, ClassNotFoundException;
    CustomerDTO searchById(String id) throws SQLException, ClassNotFoundException;
}
