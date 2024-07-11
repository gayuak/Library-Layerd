package lk.ijse.library.bo.custom.impl;

import lk.ijse.library.bo.custom.BookBO;
import lk.ijse.library.dao.DAOFactory;
import lk.ijse.library.dao.custom.BookDAO;
import lk.ijse.library.dao.custom.CatagoryDAO;
import lk.ijse.library.dao.custom.CustomerDAO;
import lk.ijse.library.dto.BookDTO;
import lk.ijse.library.entity.Book;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookBOImpl implements BookBO {
    BookDAO bookDAO = (BookDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.Book);
    CatagoryDAO catagoryDAO = (CatagoryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.Catagory);

    @Override
    public List<String> getCatagoryIds() throws SQLException, ClassNotFoundException {
        List<String> ids = catagoryDAO.getIds();
        return ids;
    }

    @Override
    public List<BookDTO> getAllBook() throws SQLException, ClassNotFoundException {
        List<Book> all = bookDAO.getAll();
        ArrayList<BookDTO> books= new ArrayList<>();
        for (Book c : all) {
            BookDTO bookDTO = new BookDTO(c.getBookId(), c.getBookName(), c.getCatagoryId(), c.getPrice(), c.getQty());
            books.add(bookDTO);
        }
        return books;
    }

    @Override
    public boolean deleteBook(String bookId) throws SQLException, ClassNotFoundException {
        boolean delete = bookDAO.delete(bookId);
        return delete;
    }

    @Override
    public boolean updateBook(BookDTO c) throws SQLException, ClassNotFoundException {
        Book book = new Book(c.getBookId(), c.getBookName(), c.getCatagoryId(), c.getPrice(), c.getQty());
        boolean update = bookDAO.update(book);
        return update;
    }

    @Override
    public BookDTO searchById(String id) throws SQLException, ClassNotFoundException {
        Book c = bookDAO.exist(id);
        BookDTO bookDTO = new BookDTO(c.getBookId(), c.getBookName(), c.getCatagoryId(), c.getPrice(), c.getQty());
        return bookDTO;
    }
}
