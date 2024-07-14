package lk.ijse.library.util;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.paint.Paint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean isTextFiledValid(TextField textField, String text){
        String field = "";

        switch (textField){
            case ID :
                field = "^([A-Z0-9]{3,})$";
                break;
            case PHONE :
                field = "^([+]94{1,3}|[0])([0-9]{2})([0-9]){7}$";
                break;
            case ADDRESS :
                field = "^([A-z0-9]|[-/,.@+]|\\\\s){4,}$";
                break;
            case NAME :
                field =  "^[A-z|\\s]{3,}$";
                break;
            case PASSWORD :
                field = ".{3,}$";
                break;
        }

        Pattern pattern = Pattern.compile(field);

        if (text != null){
            if (text.trim().isEmpty()){
                return false;
            }
        }else {
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()){
            return true;
        }
        return false;
    }

    public static boolean setTextColor(TextField location, JFXTextField field){
        if (Regex.isTextFiledValid(location,field.getText())){
            field.setFocusColor(Paint.valueOf("#207402"));
            field.setUnFocusColor(Paint.valueOf("#207402"));
            return true;
        }else {
            field.setFocusColor(Paint.valueOf("#eb3b5a"));
            field.setUnFocusColor(Paint.valueOf("#eb3b5a"));
            return false;
        }
    }
    public static boolean setTextColor(TextField location, JFXPasswordField field){
        if (Regex.isTextFiledValid(location,field.getText())){
            field.setFocusColor(Paint.valueOf("#207402"));
            field.setUnFocusColor(Paint.valueOf("#207402"));
            return true;
        }else {
            field.setFocusColor(Paint.valueOf("#eb3b5a"));
            field.setUnFocusColor(Paint.valueOf("#eb3b5a"));
            return false;
        }
    }
    public static boolean setTextColor(TextField location, javafx.scene.control.TextField field){
        if (isTextFiledValid(location,field.getText())) {
            field.setStyle("-fx-border-color: blue");
            return true;
        } else {
            field.setStyle("-fx-border-color: red");
            return false;
        }
    }
}