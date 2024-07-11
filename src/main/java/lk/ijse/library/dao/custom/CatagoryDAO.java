package lk.ijse.library.dao.custom;

import lk.ijse.library.dao.CrudDAO;
import lk.ijse.library.db.DBConnection;
import lk.ijse.library.dto.CatagoryDTO;
import lk.ijse.library.entity.Catagory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CatagoryDAO extends CrudDAO<Catagory> {


}
