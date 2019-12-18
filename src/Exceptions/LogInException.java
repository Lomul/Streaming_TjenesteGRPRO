package Exceptions;

import javafx.scene.control.Alert;

public class LogInException extends RuntimeException {
    protected String text;

    public LogInException(String title){
        super("Log In Error");
        this.text = title;
    }

    public String getTitle(){
        return text;
    }

    public void displayError(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(super.getMessage());
        alert.setHeaderText(text);
        alert.setContentText("");
        alert.showAndWait();
    }

}
