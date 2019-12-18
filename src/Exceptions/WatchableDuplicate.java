package Exceptions;

import javafx.scene.control.Alert;

public class WatchableDuplicate extends Exception {
    protected String text;
    protected String year;

    public WatchableDuplicate(String title, String year){
        super("Duplicate found!");
        this.text = title;
        this.year = year;
    }

    public void displayError(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(super.getMessage());
        alert.setContentText("There was a error with: " + text + ", " + year);
        alert.showAndWait();
    }
}
