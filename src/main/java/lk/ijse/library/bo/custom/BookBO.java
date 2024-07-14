package lk.ijse.library.bo.custom;

import lk.ijse.library.bo.SuperBO;
import lk.ijse.library.dto.BookDTO;

import java.sql.SQLException;
import java.util.List;

public interface BookBO extends SuperBO {
    List<String> getCatagoryIds() throws SQLException, ClassNotFoundException;
    List<BookDTO> getAllBook() throws SQLException, ClassNotFoundException;
    boolean deleteBook(String bookId) throws SQLException, ClassNotFoundException;
    boolean updateBook(BookDTO book) throws SQLException, ClassNotFoundException;
    BookDTO searchById(String id) throws SQLException, ClassNotFoundException;
    boolean save(BookDTO bookDTO) throws SQLException, ClassNotFoundException;
}
