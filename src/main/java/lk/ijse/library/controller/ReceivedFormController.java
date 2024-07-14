package lk.ijse.library.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import lk.ijse.library.bo.BOFactory;
import lk.ijse.library.bo.custom.ReceivedBO;
import lk.ijse.library.dto.CustomerDTO;
import lk.ijse.library.dto.FinesDTO;
import lk.ijse.library.dto.ReservationDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ReceivedFormController {

        @FXML
        private Label book;

        @FXML
        private JFXComboBox<String> cmbCustomerId;

        @FXML
        private Label cusid;

        @FXML
        private Label cusname;

        @FXML
        private Label date;

        @FXML
        private Label latedays;


        @FXML
        private Label payid;

        @FXML
        private Label payment;

        @FXML
        private DatePicker retune;
        private String next;
        private int amount;
        private long daysBetween;
        private ReservationDTO reservation;

        ReceivedBO receivedBO = (ReceivedBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RECIEVED);
        @FXML
        void cmbCustomerOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
            String value = cmbCustomerId.getValue();
             reservation = receivedBO.searchByReservationId(value);
            setdat(reservation);

        }

    private void calculate(ReservationDTO reservation) {
        LocalDate returnDate = retune.getValue();
        LocalDate now = reservation.getReseravtionDate().toLocalDate();

                LocalDate endDate = LocalDate.of(returnDate.getYear(),returnDate.getMonth(),returnDate.getDayOfMonth()); // Year, month, day
                LocalDate startDate = LocalDate.of(now.getYear(),now.getMonth(),now.getDayOfMonth()); // Year, month, day

        daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

                latedays.setText(String.valueOf(daysBetween));

                 amount = (int) daysBetween*5;
                payment.setText(String.valueOf(amount));
            }

    private void setdat(ReservationDTO reservation) throws SQLException, ClassNotFoundException {
        CustomerDTO customer = receivedBO.searchByCustomerId(reservation.getCId());
        cusid.setText(customer.getId());
        cusname.setText(customer.getName());
        date.setText(String.valueOf(reservation.getReseravtionDate()));
        book.setText(reservation.getBookId());
    }

    @FXML
        void payOn(ActionEvent event) throws SQLException, ClassNotFoundException {
        String daysBetween1 = String.valueOf(daysBetween);
        FinesDTO fines = new FinesDTO(next,reservation.getReservationId(),daysBetween1,amount,book.getText(),"P001");
        boolean save = receivedBO.save(fines);
        if (save){
            new Alert(Alert.AlertType.CONFIRMATION, "Payment Success!").show();
        }else {
            new Alert(Alert.AlertType.CONFIRMATION, "Payment Unsuccess!").show();

        }
    }

    public void initialize() throws SQLException, ClassNotFoundException {
        getorders();
        String curunt = receivedBO.currentId();
         next = nextId(curunt);
        payid.setText(next);
    }
    private String nextId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("F");
            int id = Integer.parseInt(split[1]);
            return "F" + ++id;

        }
        return "F1";
    }

    private void getorders() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> codeList = null;
        try {
            codeList = receivedBO.getReservationIds();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (String code : codeList) {
            obList.add(code);
        }

        cmbCustomerId.setItems(obList);

    }

    public void returnon(ActionEvent actionEvent) {
        calculate(reservation);
    }
}
