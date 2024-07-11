package lk.ijse.library.dao.custom.impl;

import lk.ijse.library.dao.SQLUtil;
import lk.ijse.library.dao.custom.CatagoryDAO;
import lk.ijse.library.db.DBConnection;
import lk.ijse.library.dto.CatagoryDTO;
import lk.ijse.library.entity.Catagory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatagoryDAOImpl implements CatagoryDAO {
    @Override
    public boolean save(Catagory entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Catagory entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String Id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Catagory> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT catagoryId FROM Catagory");

        List<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }


    @Override
    public String currentId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Catagory exist(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
