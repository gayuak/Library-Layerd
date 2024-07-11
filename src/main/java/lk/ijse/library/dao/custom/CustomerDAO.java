package lk.ijse.library.dao.custom;

import lk.ijse.library.controller.LoginFormController;
import lk.ijse.library.dao.CrudDAO;
import lk.ijse.library.db.DBConnection;
import lk.ijse.library.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {

}
