package lk.ijse.library.dao.custom.impl;

import lk.ijse.library.dao.CrudDAO;
import lk.ijse.library.dao.SQLUtil;
import lk.ijse.library.dao.custom.ReservationDetailDAO;
import lk.ijse.library.entity.ReservationDetail;

import java.sql.SQLException;
import java.util.List;

public class ReservationDetailDAOImpl implements ReservationDetailDAO {

    @Override
    public boolean save(ReservationDetail reservationDetail) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Reservation_details VALUES(?, ?)",reservationDetail.getReservationId(),reservationDetail.getBookId());
    }

    @Override
    public boolean update(ReservationDetail reservationDetail) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String Id) throws SQLException {
        return false;
    }

    @Override
    public List getAll() throws SQLException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }

    @Override
    public String currentId() throws SQLException {
        return null;
    }

    @Override
    public ReservationDetail exist(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
