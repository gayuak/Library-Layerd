package lk.ijse.library.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.library.bo.BOFactory;
import lk.ijse.library.bo.custom.ReservationBO;
import lk.ijse.library.db.DBConnection;
import lk.ijse.library.dto.*;
import lk.ijse.library.view.tdm.CartTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class ReservationFormController {

    public AnchorPane homeContext;
    public DatePicker retune;
    public Label lblOrderId;
    public Label lblOrderDate;
    public JFXComboBox cmbCustomerId;
    public Label lblCustomerName;
    public JFXComboBox cmbItemCode;
    public Label lblDescription;
    public Label lblUnitPrice;
    public Label lblQtyOnHand;
    public TextField txtQty;
    public TableView<CartTm> tblOrderCart;
    public TableColumn<?, ?> colorder;
    public TableColumn<?, ?> colcustomer;
    public TableColumn<?, ?> colbook;
    public TableColumn<?, ?> colqty;
    public TableColumn<?, ?> coldate;
    public TableColumn<?, ?> colAction;
    public JFXButton btnAddToCart;
    public Label lblNetTotal;
    public Label lbltotal;


    private ObservableList<CartTm> cartList = FXCollections.observableArrayList();
    private double netTotal = 0;
    private TableColumnBase<Object, Object> colTotal;

    ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);

    public void initialize() {
        setCellValueFactory();
        loadNextOrderId();
        setDate();
        getCustomerIds();
        getItemCodes();
    }

    private void setCellValueFactory() {
        colorder.setCellValueFactory(new PropertyValueFactory<>("orderid"));
        colcustomer.setCellValueFactory(new PropertyValueFactory<>("customerid"));
        colbook.setCellValueFactory(new PropertyValueFactory<>("bookid"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("returndate"));
    }

    private void getItemCodes() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> codeList = null;
        try {
            codeList = reservationBO.getBookCodes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (String code : codeList) {
            obList.add(code);
        }

        cmbItemCode.setItems(obList);

    }

    private void getCustomerIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = reservationBO.getCustomerId();

            for (String id : idList) {
                obList.add(id);
            }
            cmbCustomerId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadNextOrderId() {
        try {
            String currentId = reservationBO.currentId();
            String nextId = nextId(currentId);

            lblOrderId.setText(nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private String nextId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("O");
            int id = Integer.parseInt(split[1]);
            return "O" + ++id;

        }
        return "O1";
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblOrderDate.setText(String.valueOf(now));
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String code = String.valueOf(cmbItemCode.getValue());
        String value = (String) cmbCustomerId.getValue();
        String book = (String) cmbItemCode.getValue();
        int qty = Integer.parseInt(txtQty.getText());
        String value1 = String.valueOf(retune.getValue());

        JFXButton btnRemove = new JFXButton("remove");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if(type.orElse(no) == yes) {
                int selectedIndex = tblOrderCart.getSelectionModel().getSelectedIndex();
                cartList.remove(selectedIndex);
                tblOrderCart.refresh();
            }
        });

        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            if (code.equals(colorder.getCellData(i))) {
                qty += cartList.get(i).getQty();

                cartList.get(i).setQty(qty);

                tblOrderCart.refresh();
                txtQty.setText("");
                return;
            }
        }
        calculateNetTotal(book);
        CartTm cartTm = new CartTm(code, value, book, qty,value1 , btnRemove);

        cartList.add(cartTm);

        tblOrderCart.setItems(cartList);
        txtQty.setText("");
    }

    private void calculateNetTotal(String book) throws SQLException, ClassNotFoundException {
        BookDTO book1 = reservationBO.searchByBookCode(book);
        netTotal = 0;
            netTotal += book1.getPrice();

        lblNetTotal.setText(String.valueOf(netTotal));
    }


    @FXML
    void btnNewCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String reservationId = lblOrderId.getText();
        String customerId = String.valueOf(cmbCustomerId.getValue());
        Date reservationDate = Date.valueOf(LocalDate.now());
        String bookId = String.valueOf(cmbItemCode.getValue());
        Date returnDate = Date.valueOf(retune.getValue());

        ReservationDTO reservation = new ReservationDTO(reservationId, "U001" , reservationDate, bookId,returnDate,customerId);

        List<ReservationDetailDTO> rdList = new ArrayList<>();
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            CartTm tm = cartList.get(i);

            ReservationDetailDTO rd = new ReservationDetailDTO(reservationId, (String) cmbItemCode.getValue());
            rdList.add(rd);
        }

        PlaceOrderDTO po = new PlaceOrderDTO(reservation, rdList);
        try {
            boolean isPlaced = reservationBO.placeOrder(po);
            if(isPlaced) {
                printbill();
                new Alert(Alert.AlertType.CONFIRMATION, "order placed!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "order not placed!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (JRException e) {
            throw new RuntimeException(e);
        }

    }

    private void printbill() throws JRException, SQLException {
        JasperDesign jasperDesign =
                JRXmlLoader.load("src/main/resources/report/library.jrxml");
        JasperReport jasperReport =
                JasperCompileManager.compileReport(jasperDesign);

        Map<String, Object> data = new HashMap<>();
        data.put("reservationId",lblOrderId.getText());

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(
                        jasperReport,
                        data,
                        DBConnection.getInstance().getConnection());

        JasperViewer.viewReport(jasperPrint,false);
    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) {
        String cusId = String.valueOf(cmbCustomerId.getValue());

        try {
            CustomerDTO customer = reservationBO.searchByCustomerId(cusId);

            lblCustomerName.setText(customer.getName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String code = String.valueOf(cmbItemCode.getValue());
        BookDTO book = reservationBO.searchByBookCode(code);
        if (book != null) {
           /* lblDescription.setText(book.getCatagoryId());
            lblUnitPrice.setText(String.valueOf(book.getPrice()));
            lblQtyOnHand.setText(String.valueOf(book.getQty()));*/
        }

    }


}
