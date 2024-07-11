package lk.ijse.library.bo.custom;

import lk.ijse.library.bo.SuperBO;
import lk.ijse.library.dto.CustomerDTO;
import lk.ijse.library.dto.FinesDTO;
import lk.ijse.library.dto.ReservationDTO;

import java.sql.SQLException;
import java.util.List;

public interface ReceivedBO extends SuperBO {
    ReservationDTO searchByReservationId(String value) throws SQLException, ClassNotFoundException;
    CustomerDTO searchByCustomerId(String reservation) throws SQLException, ClassNotFoundException;
    boolean save(FinesDTO fines) throws SQLException, ClassNotFoundException;
    String currentId() throws SQLException, ClassNotFoundException;
    List<String> getReservationIds() throws SQLException, ClassNotFoundException;
}
