package lk.ijse.library.dao.custom.impl;

import lk.ijse.library.dao.CrudDAO;
import lk.ijse.library.dao.SQLUtil;
import lk.ijse.library.dao.custom.FinesDAO;
import lk.ijse.library.entity.Book;
import lk.ijse.library.entity.Fines;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FinesDAOImpl implements FinesDAO {
    @Override
    public boolean save(Fines fines) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Fines VALUES(?, ?, ?, ?,?,?)",fines.getFinesId(),fines.getReservationId(),fines.getDayLate(),fines.getAmount(),fines.getBookId(),fines.getPaymentId());
    }

    @Override
    public boolean update(Fines fines) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String Id) throws SQLException {
        return false;
    }

    @Override
    public List getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Fines");
        ArrayList<Fines> finesList = new ArrayList<>();
        while (resultSet.next()) {
            String CId = resultSet.getString(1);
            String Cname = resultSet.getString(2);
            String contact = resultSet.getString(3);
            double address = resultSet.getDouble(4);
            String userId = resultSet.getString(5);
            String userId1 = resultSet.getString(6);

            Fines customer = new Fines(CId, Cname, contact, address, userId,userId1);
            finesList.add(customer);
        }
        return finesList;
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
    public Fines exist(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
