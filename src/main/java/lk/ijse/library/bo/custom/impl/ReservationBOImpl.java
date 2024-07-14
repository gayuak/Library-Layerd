package lk.ijse.library.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.library.bo.custom.ReservationBO;
import lk.ijse.library.dao.DAOFactory;
import lk.ijse.library.dao.custom.*;
import lk.ijse.library.db.DBConnection;
import lk.ijse.library.dto.*;
import lk.ijse.library.entity.Book;
import lk.ijse.library.entity.Customer;
import lk.ijse.library.entity.Reservation;
import lk.ijse.library.entity.ReservationDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {
    BookDAO bookDAO = (BookDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.Book);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.Customer);
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.Reservation);
    ReservationDetailDAO reservationDetailDAO = (ReservationDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ReservationDetail);
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
    public boolean placeOrder(PlaceOrderDTO po) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);


        try {
            ReservationDTO reservation = po.getReservation();
            Reservation reservation1 = new Reservation(reservation.getReservationId(), reservation.getUserId(), reservation.getReseravtionDate(), reservation.getBookId(), reservation.getReturnDate(), reservation.getCId());
            boolean isOrderSaved = reservationDAO.save(reservation1);
            if (isOrderSaved) {
                boolean isOrderDetailSaved = save(po.getRdList());
                if (isOrderDetailSaved) {
                    connection.commit();
                    return true;
                }

            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
public boolean save(List<ReservationDetailDTO> reservationDetailDTOS) throws SQLException, ClassNotFoundException {
    for (ReservationDetailDTO r : reservationDetailDTOS
         ) {
        if (!reservationDetailDAO.save(new ReservationDetail(r.getReservationId(),r.getBookId()))){
            return false;
        }
    }
    return true;
}
    @Override
    public CustomerDTO searchByCustomerId(String cusId) throws SQLException, ClassNotFoundException {
        Customer exist = customerDAO.exist(cusId);
        CustomerDTO customerDTO = new CustomerDTO(exist.getId(), exist.getName(), exist.getContact(), exist.getAddress(), exist.getUserId());
        return customerDTO;
    }

    @Override
    public BookDTO searchByBookCode(String code) throws SQLException, ClassNotFoundException {
        Book exist = bookDAO.exist(code);
        BookDTO bookDTO = new BookDTO(exist.getBookId(), exist.getBookName(), exist.getCatagoryId(), exist.getPrice(), exist.getQty());
        return bookDTO;
    }
}
