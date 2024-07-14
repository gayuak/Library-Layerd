package lk.ijse.library.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.library.bo.BOFactory;
import lk.ijse.library.bo.custom.CustomerBO;
import lk.ijse.library.bo.custom.FinesBO;
import lk.ijse.library.dto.CustomerDTO;
import lk.ijse.library.dto.FinesDTO;
import lk.ijse.library.dto.ReservationDTO;
import lk.ijse.library.view.tdm.Finestm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FinesFormController {

    @FXML
    private TableColumn<?, ?> colamount;

    @FXML
    private TableColumn<?, ?> colorder;

    @FXML
    private TableColumn<?, ?> colpay;

    @FXML
    private TableColumn<?, ?> cusid;
    private List<FinesDTO> customerList = new ArrayList<>();

    @FXML
    private TableView<Finestm> tblOrderCart;
    FinesBO finesBO = (FinesBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.FINES);

    public void initialize()   {
        this.customerList = getAllCustomers();
        setCellValueFactory();
        try {
            loadCustomerTable();
        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }
    }
    private void loadCustomerTable() throws SQLException, ClassNotFoundException {
        ObservableList<Finestm> tmList = FXCollections.observableArrayList();

        for (FinesDTO customer : customerList) {
            ReservationDTO reservation = finesBO.searchByReservationId(customer.getReservationId());
            CustomerDTO customer1 = finesBO.searchByCustomerId(reservation.getCId());
            Finestm customerTm = new Finestm(
                    customer.getFinesId(),
                    customer.getReservationId(),
                    customer.getAmount(),
                    customer1.getName()
            );

            tmList.add(customerTm);
        }
        tblOrderCart.setItems(tmList);
        Finestm selectedItem = tblOrderCart.getSelectionModel().getSelectedItem();
    }
    private List<FinesDTO> getAllCustomers() {
        List<FinesDTO> customerList = null;
        try {
            customerList = finesBO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }
    private void setCellValueFactory() {
        colpay.setCellValueFactory(new PropertyValueFactory<>("payid"));
        colorder.setCellValueFactory(new PropertyValueFactory<>("orderid"));
        colamount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        cusid.setCellValueFactory(new PropertyValueFactory<>("cusid"));
    }
}
