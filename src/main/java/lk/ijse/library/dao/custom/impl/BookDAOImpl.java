package lk.ijse.library.dao.custom.impl;

import lk.ijse.library.dao.CrudDAO;
import lk.ijse.library.dao.SQLUtil;
import lk.ijse.library.dao.custom.BookDAO;
import lk.ijse.library.db.DBConnection;
import lk.ijse.library.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {


    @Override
    public boolean save(Book entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "INSERT INTO Book VALUES(?, ?, ?, ?,?)",entity.getBookId(),entity.getBookName(),entity.getCatagoryId(),entity.getPrice(),entity.getQty());

    }

    @Override
    public boolean update(Book entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Book SET qty = ?, bookName = ?, catagoryId = ? ,price = ? WHERE bookId = ?",entity.getQty(),entity.getBookName(),entity.getCatagoryId(),entity.getPrice(),entity.getBookId());
    }

    @Override
    public boolean delete(String Id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Book WHERE bookId = ?",Id);
    }

    @Override
    public List<Book> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Book");
        ArrayList<Book> BookList = new ArrayList<>();
        while (resultSet.next()){
            String bookId = resultSet.getString(1);
            String bookName = resultSet.getString(2);
            String catagoryId = resultSet.getString(3);
            double price = resultSet.getDouble(4);
            int qty = resultSet.getInt(5);

            Book book = new Book(bookId, bookName, catagoryId, price,qty);
            BookList.add(book);
        }
        return BookList;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT bookId FROM Book");
        List<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;

    }

    @Override
    public String currentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet=SQLUtil.execute("SELECT bookId FROM Reservation ORDER BY CAST(SUBSTRING(bookId,2)AS UNSIGNED)  desc LIMIT 1");

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public Book exist(String id) throws SQLException, ClassNotFoundException {
      ResultSet resultSet =  SQLUtil.execute("SELECT * FROM Book WHERE bookId = ?",id);

        resultSet.next();
            String bookId = resultSet.getString(1);
            String bookName = resultSet.getString(2);
            String catagoryId = resultSet.getString(3);
            double price = resultSet.getDouble(4);
            int qty = Integer.parseInt(resultSet.getString(5));

           Book book = new Book(bookId, bookName, catagoryId,price, qty);

        return book;
    }
}
