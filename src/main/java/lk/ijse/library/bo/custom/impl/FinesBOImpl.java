package lk.ijse.library.bo.custom.impl;

import lk.ijse.library.bo.custom.FinesBO;
import lk.ijse.library.dao.DAOFactory;
import lk.ijse.library.dao.custom.*;
import lk.ijse.library.dto.*;
import lk.ijse.library.entity.Book;
import lk.ijse.library.entity.Customer;
import lk.ijse.library.entity.Fines;
import lk.ijse.library.entity.Reservation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FinesBOImpl implements FinesBO {
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.Reservation);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.Customer);
    FinesDAO finesDAO = (FinesDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.Fines);

    @Override
    public ReservationDTO searchByReservationId(String Id) throws SQLException, ClassNotFoundException {
        Reservation exist = reservationDAO.exist(Id);
        ReservationDTO reservationDTO = new ReservationDTO(exist.getReservationId(), exist.getUserId(), exist.getReseravtionDate(), exist.getBookId(), exist.getReturnDate(), exist.getCId());
        return reservationDTO;
    }

    @Override
    public CustomerDTO searchByCustomerId(String reservation) throws SQLException, ClassNotFoundException {
        Customer exist = customerDAO.exist(reservation);
        CustomerDTO customerDTO = new CustomerDTO(exist.getId(), exist.getName(), exist.getContact(), exist.getAddress(), exist.getUserId());
        System.out.println(customerDTO);
        return customerDTO;
    }

    @Override
    public List<FinesDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Fines> all = finesDAO.getAll();
        ArrayList<FinesDTO> books= new ArrayList<>();
        for (Fines c : all) {
            FinesDTO bookDTO = new FinesDTO(c.getFinesId(),c.getReservationId(),c.getDayLate(),c.getAmount(),c.getBookId(),c.getPaymentId());
            books.add(bookDTO);
        }
        return books;    }
}
