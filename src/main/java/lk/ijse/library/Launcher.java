package lk.ijse.library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
    @Override
    public void start(Stage stage)throws Exception{
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Login_form.fxml"))));
        stage.setTitle("Login Form");
        stage.centerOnScreen();
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}