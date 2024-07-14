package lk.ijse.library.bo.custom;

import lk.ijse.library.bo.SuperBO;
import lk.ijse.library.dto.BookDTO;
import lk.ijse.library.dto.CustomerDTO;
import lk.ijse.library.dto.PlaceOrderDTO;

import java.sql.SQLException;
import java.util.List;

public interface ReservationBO extends SuperBO {
    List<String> getBookCodes() throws SQLException, ClassNotFoundException;
    List<String> getCustomerId() throws SQLException, ClassNotFoundException;
    String currentId() throws SQLException, ClassNotFoundException;
    BookDTO searchById(String book) throws SQLException, ClassNotFoundException;
    boolean placeOrder(PlaceOrderDTO po) throws SQLException;
    CustomerDTO searchByCustomerId(String cusId) throws SQLException, ClassNotFoundException;
    BookDTO searchByBookCode(String code) throws SQLException, ClassNotFoundException;
}
