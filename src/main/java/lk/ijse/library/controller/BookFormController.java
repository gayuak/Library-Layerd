package lk.ijse.library.controller;



import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.library.bo.BOFactory;
import lk.ijse.library.bo.custom.BookBO;
import lk.ijse.library.dto.BookDTO;
import lk.ijse.library.view.tdm.BookTm;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookFormController {
    public AnchorPane homeContext;
    public JFXComboBox txtCatagory;
    @FXML
    private TableView<BookTm> tblItem;
    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtUnitPrice;
    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);

    private List<BookDTO> bookList = new ArrayList<>();

    public void initialize() {
        this.bookList = getAllBooks();
        setCellValueFactory();
        loadBookTable();
        getCatogery();
    }

    private void getCatogery() {

        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = bookBO.getCatagoryIds();

            for (String id : idList) {
                obList.add(id);
            }
            txtCatagory.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadBookTable() {
        ObservableList<BookTm> tmList = FXCollections.observableArrayList();

        for (BookDTO book : bookList) {
            BookTm bookTm = new BookTm(book.getBookId(),   book.getBookName(),    book.getPrice(),    book.getQty());

            tmList.add(bookTm);
        }
        tblItem.setItems(tmList);
        BookTm selectedBook = tblItem.getSelectionModel().getSelectedItem();
    }


    private void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("Code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("Unit_Price"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("Qty_On_Hand"));
    }

    private List<BookDTO> getAllBooks() {
        List<BookDTO> bookList = null;
        try {
            bookList = bookBO.getAllBook();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return bookList;
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String bookId = txtCode.getText();
        String bookName = txtDescription.getText();
        int priceText = Integer.parseInt(txtUnitPrice.getText());
        int qty = Integer.parseInt(txtQtyOnHand.getText());


        BookDTO book = new BookDTO(bookId, bookName, (String) txtCatagory.getValue(), priceText, qty);
        try {
            boolean isSaved = bookBO.save(book);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "book saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        initialize();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String bookId = txtCode.getText();

        try {
            boolean isDeleted = bookBO.deleteBook(bookId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "book deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        initialize();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String bookId = txtCode.getText();
        String bookName = txtDescription.getText();
        double price = Double.parseDouble(txtUnitPrice.getText());
        int qty = Integer.parseInt(txtQtyOnHand.getText());


        BookDTO book = new BookDTO(bookId, bookName, (String) txtCatagory.getValue(), price, qty);

        try {
            boolean isUpdated = bookBO.updateBook(book);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "book updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        initialize();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) throws IOException {
            txtCode.setText("");
            txtDescription.setText("");
            txtUnitPrice.setText("");
            txtQtyOnHand.setText("");

    }


    public void codeSearchOnAction(ActionEvent actionEvent) {
        String id = txtCode.getText();

        try {
            BookDTO book = bookBO.searchById(id);

            if (book != null) {
                txtCode.setText(book.getBookId());
                txtDescription.setText(book.getBookName());
                txtUnitPrice.setText(String.valueOf(book.getPrice()));
                txtQtyOnHand.setText(String.valueOf(book.getQty()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {

    }
}

