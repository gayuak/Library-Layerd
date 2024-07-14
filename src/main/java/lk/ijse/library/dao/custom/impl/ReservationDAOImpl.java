package lk.ijse.library.dao.custom.impl;

import lk.ijse.library.dao.CrudDAO;
import lk.ijse.library.dao.SQLUtil;
import lk.ijse.library.dao.custom.ReservationDAO;
import lk.ijse.library.dao.custom.ReservationDetailDAO;
import lk.ijse.library.entity.Customer;
import lk.ijse.library.entity.Reservation;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    @Override
    public boolean save(Reservation reservation) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Reservation VALUES(?, ?, ?,?,?,?)",reservation.getReservationId(),reservation.getUserId(),reservation.getReseravtionDate(),reservation.getBookId(),reservation.getReturnDate(),reservation.getCId());
    }

    @Override
    public boolean update(Reservation reservation) throws SQLException {
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
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT reservationId FROM Reservation");
        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    @Override
    public String currentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet=SQLUtil.execute("SELECT reservationId FROM Reservation ORDER BY CAST(SUBSTRING(reservationId,2)AS UNSIGNED)  desc LIMIT 1");
        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public Reservation exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet =  SQLUtil.execute("SELECT * FROM Reservation WHERE reservationId = ?",id);
        resultSet.next();
            String CId = resultSet.getString(1);
            String Cname = resultSet.getString(2);
            Date contact = resultSet.getDate(3);
            String address = resultSet.getString(4);
            Date userId = resultSet.getDate(5);
            String userId1 = resultSet.getString(6);

            Reservation reservation = new Reservation(CId,Cname,contact,address,userId,userId1);

        return reservation;    }
}
