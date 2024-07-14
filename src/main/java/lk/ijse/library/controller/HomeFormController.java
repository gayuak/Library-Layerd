package lk.ijse.library.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class HomeFormController {

    @FXML
    public   Button btnBooks;

    @FXML
    public   Button btnClient;

    @FXML
    public   Button btnHome;

    @FXML
    public   Button btnReservation;

    @FXML
   public   AnchorPane homeContext;
    public Label time;
    public Label date;
    public AnchorPane centernode;
    public Button btnReservation1;
    public Button btnReservation11;

    public void initialize() throws IOException {
        btnHomeOnAction(new ActionEvent());
        Date thisd = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        date.setText("Date :  "+ LocalDate.now());
        time.setText("Time :  "+ simpleDateFormat.format(thisd));

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    LocalTime currentTime = LocalTime.now();
                    LocalDate date1 = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss a");
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy, MMM dd");
                    String formattedDate = date1.format(dateFormatter);
                    String formattedTime = currentTime.format(formatter);
                    time.setText(formattedTime);
                    date.setText(formattedDate);
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    @FXML
   public void btnBooksOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNodeDash = FXMLLoader.load(this.getClass().getResource("/view/book.fxml"));
        centernode.getChildren().clear();
        centernode.getChildren().add(rootNodeDash);
    }

    @FXML
   public void btnClinetOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNodeDash = FXMLLoader.load(this.getClass().getResource("/view/customer.fxml"));
        centernode.getChildren().clear();
        centernode.getChildren().add(rootNodeDash);
    }

    @FXML
   public void btnHomeOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNodeDash = FXMLLoader.load(this.getClass().getResource("/view/dashboard.fxml"));
        centernode.getChildren().clear();
        centernode.getChildren().add(rootNodeDash);
    }

    @FXML
   public void btnreservationOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNodeDash = FXMLLoader.load(this.getClass().getResource("/view/reservation.fxml"));
        centernode.getChildren().clear();
        centernode.getChildren().add(rootNodeDash);
    }

    public void closeonaction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNodeDash = FXMLLoader.load(this.getClass().getResource("/view/Login_form.fxml"));
        Scene scene = new Scene(rootNodeDash);
        Stage stage = (Stage) this.homeContext.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    public void btnfinsOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNodeDash = FXMLLoader.load(this.getClass().getResource("/view/Fines.fxml"));
        centernode.getChildren().clear();
        centernode.getChildren().add(rootNodeDash);
    }

    public void btnresceivedOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNodeDash = FXMLLoader.load(this.getClass().getResource("/view/received.fxml"));
        centernode.getChildren().clear();
        centernode.getChildren().add(rootNodeDash);
    }
}
