package lk.ijse.library.bo.custom.impl;

import lk.ijse.library.bo.custom.ReceivedBO;
import lk.ijse.library.dao.DAOFactory;
import lk.ijse.library.dao.custom.BookDAO;
import lk.ijse.library.dao.custom.CustomerDAO;
import lk.ijse.library.dao.custom.FinesDAO;
import lk.ijse.library.dao.custom.ReservationDAO;
import lk.ijse.library.dto.CustomerDTO;
import lk.ijse.library.dto.FinesDTO;
import lk.ijse.library.dto.ReservationDTO;
import lk.ijse.library.entity.Customer;
import lk.ijse.library.entity.Fines;
import lk.ijse.library.entity.Reservation;

import java.sql.SQLException;
import java.util.List;

public class ReceivedBOImpl implements ReceivedBO {
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.Reservation);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.Customer);
    FinesDAO finesDAO = (FinesDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.Fines);

    @Override
    public ReservationDTO searchByReservationId(String value) throws SQLException, ClassNotFoundException {
        Reservation exist = reservationDAO.exist(value);
        ReservationDTO reservationDTO = new ReservationDTO(exist.getReservationId(), exist.getUserId(), exist.getReseravtionDate(), exist.getBookId(), exist.getReturnDate(), exist.getCId());
        return reservationDTO;
    }

    @Override
    public CustomerDTO searchByCustomerId(String reservation) throws SQLException, ClassNotFoundException {
        Customer exist = customerDAO.exist(reservation);
        CustomerDTO customerDTO = new CustomerDTO(exist.getId(), exist.getName(), exist.getContact(), exist.getAddress(), exist.getUserId());
        return customerDTO;
    }

    @Override
    public boolean save(FinesDTO fines) throws SQLException, ClassNotFoundException {
        Fines fines1 = new Fines(fines.getFinesId(), fines.getReservationId(), fines.getDayLate(), fines.getAmount(), fines.getBookId(), fines.getPaymentId());
        boolean save = finesDAO.save(fines1);
        return save;
    }

    @Override
    public String currentId() throws SQLException, ClassNotFoundException {
        String s = finesDAO.currentId();
        return s;
    }

    @Override
    public List<String> getReservationIds() throws SQLException, ClassNotFoundException {
        List<String> ids = reservationDAO.getIds();
        return ids;
    }
}
