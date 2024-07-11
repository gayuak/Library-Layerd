package lk.ijse.library.bo.custom.impl;

import lk.ijse.library.bo.custom.ReservationBO;
import lk.ijse.library.dao.DAOFactory;
import lk.ijse.library.dao.custom.BookDAO;
import lk.ijse.library.dao.custom.CatagoryDAO;
import lk.ijse.library.dao.custom.CustomerDAO;
import lk.ijse.library.dao.custom.ReservationDAO;
import lk.ijse.library.dto.BookDTO;
import lk.ijse.library.dto.CustomerDTO;
import lk.ijse.library.dto.PlaceOrderDTO;
import lk.ijse.library.entity.Book;

import java.sql.SQLException;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {
    BookDAO bookDAO = (BookDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.Book);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.Customer);
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.Reservation);
    @Override
    public List<String> getBookCodes() throws SQLException, ClassNotFoundException {
        List<String> ids = bookDAO.getIds();
        return ids;
    }

    @Override
    public List<String> getCustomerId() throws SQLException, ClassNotFoundException {
        List<String> ids = customerDAO.getIds();
        return ids;
    }

    @Override
    public String currentId() throws SQLException, ClassNotFoundException {
        String s = reservationDAO.currentId();
        return s;
    }

    @Override
    public BookDTO searchById(String book) throws SQLException, ClassNotFoundException {
        Book exist = bookDAO.exist(book);
        BookDTO bookDTO = new BookDTO(exist.getBookId(), exist.getBookName(), exist.getCatagoryId(), exist.getPrice(), exist.getQty());
        return bookDTO;
    }

    @Override
    public boolean placeOrder(PlaceOrderDTO po) {
        return false;
    }

    @Override
    public CustomerDTO searchByCustomerId(String cusId) {
        return null;
    }

    @Override
    public BookDTO searchByBookCode(String code) {
        return null;
    }
}
