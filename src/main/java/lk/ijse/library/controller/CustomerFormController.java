package lk.ijse.library.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.library.dto.CustomerDTO;
import lk.ijse.library.view.tdm.CustomerTm;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerFormController {



    public TextField txtcontact;
    public AnchorPane homeContext;
    public TableColumn colId;
    public TableColumn coleName;
    public TableColumn address;
    public TableColumn colcontact;
    public Button btnSave;
    public TextField txtId;
    public TextField txtname;
    public TextField txtAddres;

    public TableView <CustomerTm> custable;
    private List<CustomerDTO> customerList = new ArrayList<>();

    public void initialize() {
        this.customerList = getAllCustomers();
        setCellValueFactory();
        loadCustomerTable();
    }
    private void loadCustomerTable() {
        ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();

        for (CustomerDTO customer : customerList) {
            CustomerTm customerTm = new CustomerTm(
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getContact()
            );

            tmList.add(customerTm);
        }
        custable.setItems(tmList);
        CustomerTm selectedItem = custable.getSelectionModel().getSelectedItem();
    }
    private List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customerList = null;
        try {
            customerList = CustomerRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }
    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        coleName.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colcontact.setCellValueFactory(new PropertyValueFactory<>("address"));
    }
    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException {
        String CId = txtId.getText();
        String Cname = txtname.getText();
        String address = txtcontact.getText();
        String contact = txtAddres.getText();


        CustomerDTO customer = new CustomerDTO(CId, Cname, contact, address, null);
        try {
            boolean isSaved = CustomerRepo.save(customer);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        initialize();

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String CId = txtId.getText();
        String Cname = txtname.getText();
        String address = txtcontact.getText();
        String contact = txtAddres.getText();


        CustomerDTO customer = new CustomerDTO(CId, Cname, contact, address, contact);

        try {
            boolean isUpdated = CustomerRepo.update(customer);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        initialize();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            boolean isDeleted = CustomerRepo.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        initialize();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }
    private void clearFields() {
        txtId.setText("");
        txtname.setText("");
        txtAddres.setText("");
        txtcontact.setText("");
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            CustomerDTO customer = CustomerRepo.searchById(id);

            if (customer != null) {
                txtId.setText(customer.getId());
                txtname.setText(customer.getName());
                txtAddres.setText(customer.getAddress());
                txtcontact.setText(customer.getContact());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
