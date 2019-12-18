package Exceptions;

import javafx.scene.control.Alert;

public class AlreadyInSaved extends RuntimeException {
    protected String title;

    public AlreadyInSaved(String title){
        super("Already in Saved!");
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void displayError(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(super.getMessage());
        alert.setHeaderText(title);
        alert.setContentText("Go to your account if you want to remove this");
        alert.showAndWait();
    }

}
