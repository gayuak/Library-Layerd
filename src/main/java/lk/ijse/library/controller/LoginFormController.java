package lk.ijse.library.controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.library.db.DBConnection;
import lk.ijse.library.util.Regex;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {
    @FXML
    private ImageView imgLogin;

    @FXML
    private Label lblGmailCheck;

    @FXML
    private Label lbltime;

    @FXML
    private CubicCurve curve;

    @FXML
    private Button btnNext;

    @FXML
    private Hyperlink hypCeateAccunt;

    @FXML
    private AnchorPane rootNodeLogin;

    @FXML
    private TextField txtGmail;

    @FXML
    private Label lblWernning;

    @FXML
    private PasswordField txtpasswordF;

    @FXML
    private Hyperlink hypPasswordForgrt;

    boolean stop =true;

    private Thread thread;

    private FXMLLoader fxmlLoader;

    Timeline timeline;
    public static String userId;
    private static LoginFormController controller;
    public LoginFormController(){
        controller=this;
    }
    public static LoginFormController getInstance(){
        return controller;
    }

    @FXML
    public void initialize() {


//        timenow();

//        imgLogin.setDisable(true);

//        timeline = new Timeline(new KeyFrame(Duration.seconds(0.8), event -> {
//            boolean isVisible = imgLogin.isVisible();
//            imgLogin.setVisible(!isVisible);
//        }));
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();
    }

//    private void timenow(){
//         thread=new Thread(()->{
//            SimpleDateFormat format=new SimpleDateFormat("hh:mm:ss");
//            while (stop){
//                try {
//                    Thread.sleep(1000);
//
//                }catch (Exception ignored){
//
//                }
//                final String time=format.format(new Date());
//                Platform.runLater(() -> lbltime.setText(time));
//            }
//        });
//        thread.start();
//    }

    @FXML
    public void rootOnMouseExited(MouseEvent mouseEvent) {
        curve.setControlX2(50.8468017578125);
        curve.setControlY2(-5);
    }

    @FXML
    public void rootOnMouseMove(MouseEvent mouseEvent) {
        curve.setControlX2(mouseEvent.getX());
    }

    @FXML
    public void btnLoginOnAction(ActionEvent actionEvent) throws SQLException, IOException {
        userId = txtGmail.getText();
        String pw = txtpasswordF.getText();

        try {
            if (isValid()){
                checkCredential(userId, pw);}else {
                new Alert(Alert.AlertType.WARNING, "text not valied").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public boolean isValid(){
        if (!Regex.setTextColor(lk.ijse.library.util.TextField.ID,txtGmail)) return false;
        if (!Regex.setTextColor(lk.ijse.library.util.TextField.PASSWORD,txtpasswordF)) return false;
        return true;
    }

    private void checkCredential(String userId, String pw) throws SQLException, IOException {
        String sql = "SELECT userId, password FROM User WHERE userId = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, userId);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String dbPw = resultSet.getString(2);

            if(dbPw.equals(pw)) {
                navigateToTheDashboard();
            } else {
                new Alert(Alert.AlertType.ERROR, "Password is incorrect!").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "user id not found!").show();
        }
    }

    private void navigateToTheDashboard() throws IOException {
        AnchorPane rootNodeDash = FXMLLoader.load(this.getClass().getResource("/view/home_form.fxml"));

        Scene scene = new Scene(rootNodeDash);

        Stage stage = (Stage) this.rootNodeLogin.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    @FXML
    public void playMouseEnterAnimation(MouseEvent event) {

    }

    @FXML
    public void playMouseExitAnimation(MouseEvent event) {

    }

    @FXML
    public void hypCeateAccuntOnAction(ActionEvent actionEvent) throws IOException {
    }

    @FXML
    private void setUi(String fileName) throws IOException {

    }

    @FXML
    public void txtGmailKeyTyped(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.library.util.TextField.ID,txtGmail);
    }

    @FXML
    public void txtPasswordOnAction(ActionEvent actionEvent) {

    }

    public void hypPasswordForgrtOnAction(ActionEvent actionEvent) throws GeneralSecurityException, IOException {}

    public void txtGmailOnAction(ActionEvent actionEvent) {
    }

    public void txtLIBRARYOnAction(MouseEvent mouseEvent) {
    }


    public void PasswordKeyPressed(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.library.util.TextField.PASSWORD,txtpasswordF);
    }
}
