package lk.ijse.library.bo.custom;

import lk.ijse.library.bo.SuperBO;
import lk.ijse.library.dto.CatagoryDTO;
import lk.ijse.library.dto.CustomerDTO;
import lk.ijse.library.dto.FinesDTO;
import lk.ijse.library.dto.ReservationDTO;

import java.sql.SQLException;
import java.util.List;

public interface FinesBO extends SuperBO {
    ReservationDTO searchByReservationId(String customer) throws SQLException, ClassNotFoundException;
    CustomerDTO searchByCustomerId(String reservation) throws SQLException, ClassNotFoundException;
    List<FinesDTO> getAll() throws SQLException, ClassNotFoundException;
}
