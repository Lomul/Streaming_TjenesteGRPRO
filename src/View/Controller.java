package View;

import Exceptions.NoSearchMatched;
import Model.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.fxml.*;
import javafx.scene.control.Button;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    private ArrayList<Movie> allMovies;
    private ArrayList<Series> allSeries;
    @FXML private ComboBox<String> comboBox;
    @FXML private TextField textField;
    @FXML public VBox watchBox;
    @FXML public VBox searchedBox;

    @FXML
    private void changeSceneToMovies(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("movies_scene.fxml"));
        setScene(event, root);

        VBox mB = (VBox) root.lookup("#watchBox");
        BorderPane p = makeBorderPane(allMovies);
        mB.getChildren().add(p);
    }
    @FXML
    private void changeSceneToHome(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("home_scene.fxml"));
        setScene(event, root);
    }
    @FXML
    private void changeSceneToAccount(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("account_scene.fxml"));
        setScene(event, root);

    }
    @FXML
    private void changeSceneToSeries(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("series_scene.fxml"));
        setScene(event, root);

        VBox mB = (VBox) root.lookup("#watchBox");
        BorderPane p = makeBorderPane(allSeries);
        mB.getChildren().add(p);
    }

    @FXML
    private void setScene(ActionEvent event, Parent root){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        scene.getStylesheets().add(getClass().getResource("demo.css").toExternalForm());
        stage.setScene(scene);
        //stage.setMaximized(true);
    }

    @FXML
    private void searchComboBoxMovies(ActionEvent event) throws Exception {
        String text = textField.getText();
        String value = (String) comboBox.getValue();
        System.out.println("Movies: " + text + " " + value);

        Parent root = FXMLLoader.load(getClass().getResource("search_scene.fxml"));
        setScene(event, root);
    }
    @FXML
    private void searchComboBoxSeries(ActionEvent event) throws Exception {
        String text = textField.getText();
        String value = (String) comboBox.getValue();
        System.out.println("Series: " + text + " " + value);

        Parent root = FXMLLoader.load(getClass().getResource("search_scene.fxml"));
        setScene(event, root);
    }
    @FXML
    private void searchComboBox(ActionEvent event) throws Exception {
        String text = textField.getText();
        String value = (String) comboBox.getValue();


        if(getSearched(text).size() <= 0) {
            throw new NoSearchMatched(text);
        }

            Parent root = FXMLLoader.load(getClass().getResource("search_scene.fxml"));

            setScene(event, root);

            BorderPane searched = makeBorderPane(getSearched(text));

            ComboBox<String> comboBoxValue = (ComboBox<String>) root.lookup("#comboBox");
            comboBoxValue.setValue(value);

            VBox sB = (VBox) root.lookup("#searchedBox");
            sB.getChildren().add(searched);
            String newtext = "Searched for: \n" + value + ": " + text + ", in all";
            System.out.println("All: " + text + " " + value);
            //Change search result display:
            Label searchLabel = (Label) root.lookup("#searchLabel");
            /*System.out.println(searchLabel.getText());*/
            searchLabel.setText(newtext);
            /*System.out.println(searchLabel.getText());*/

    }

    private BorderPane makeBorderPane(ArrayList arrayList)
    {
        TilePane tile_pane = new TilePane();
        ScrollPane scrollPane = new ScrollPane(tile_pane);
        scrollPane.setFitToHeight(true);
        BorderPane root = new BorderPane(scrollPane);

        try {

            tile_pane.setVgap(10);
            tile_pane.setHgap(50);

            for (Object o : arrayList) {
                Watchable w = (Watchable) o;
                Label label1 = new Label (w.getTitle());
                label1.setFont(Font.font("Times New Roman",18));
                label1.setWrapText(true);
                label1.setMaxWidth(w.getImg().getWidth());
                ImageView iv = new ImageView(w.getImg());
                iv.setOnMouseClicked(e -> System.out.println(w.getTitle()));

                //idk jeg prøver, ok?
                iv.setOnMouseClicked(e -> {
                    try {
                        changeSceneToWatchablePage(e, w);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
                // ------

                VBox vBox = new VBox();
                vBox.getChildren().addAll(iv,label1);
                tile_pane.getChildren().add(vBox);

                iv.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        vBox.setScaleX(1.2);
                        vBox.setScaleY(1.2);
                    }
                });

                iv.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        vBox.setScaleX(1);
                        vBox.setScaleY(1);
                    }
                });

            }

            // set Alignment of pane
            tile_pane.setAlignment(Pos.TOP_CENTER);


        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return root;
    }

    private ArrayList getSearched(String s)
    {
        ArrayList<Watchable> searchedWatchables = new ArrayList<>();
        String searchTerm = s.toLowerCase();
        switch (comboBox.getValue())
        {
            case "Title":

                for (Movie movie : allMovies)
                {
                    String title = movie.getTitle().toLowerCase();
                    if (title.contains(searchTerm))
                    {
                        searchedWatchables.add(movie);
                    }
                }
                for (Series serie : allSeries)
                {
                    String title = serie.getTitle().toLowerCase();
                    if (title.contains(searchTerm))
                    {
                        searchedWatchables.add(serie);
                    }
                }
                return searchedWatchables;

            case "Year":
                for (Movie movie : allMovies)
                {
                    String year = movie.getYear().toLowerCase();
                    if (year.contains(searchTerm))
                    {
                        searchedWatchables.add(movie);
                    }
                }
                for (Series serie : allSeries)
                {
                    String year = serie.getYear().toLowerCase();
                    if (year.contains(searchTerm))
                    {
                        searchedWatchables.add(serie);
                    }
                }
                return searchedWatchables;

            case "Genre":
                for (Movie movie : allMovies)
                {
                    String genre = movie.getGenre().toLowerCase();
                    if (genre.contains(searchTerm))
                    {
                        searchedWatchables.add(movie);
                    }
                }
                for (Series serie : allSeries)
                {
                    String genre = serie.getGenre().toLowerCase();
                    if (genre.contains(searchTerm))
                    {
                        searchedWatchables.add(serie);
                    }
                }
                return searchedWatchables;
            case "Rating":
                double rating = convertStringToDouble(searchTerm);
                for (Movie movie : allMovies)
                {
                    if (movie.getRating() >= rating)
                    {
                        searchedWatchables.add(movie);
                    }
                }
                for (Series serie : allSeries)
                {
                    if (serie.getRating() >= rating)
                    {
                        searchedWatchables.add(serie);
                    }
                }
                return searchedWatchables;

            default:
                return null;
        }
    }

    @FXML
    private void changeSceneToWatchablePage(MouseEvent event, Watchable w) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("watchable_page_scene.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        scene.getStylesheets().add(getClass().getResource("demo.css").toExternalForm());
        stage.setScene(scene);

        //Watchable page info
        Label wTitle = new Label(w.getTitle());
        wTitle.setFont(Font.font("Tahoma",30));

        Label wGenre = new Label(w.getGenre() + " \u25CF ");
        Label wYear = new Label(w.getYear()  + " \u25CF ");
        Label wRating = new Label(String.valueOf(w.getRating()));

        ImageView iv = new ImageView(w.getImg());
        iv.setId("infoIMG");
        iv.setPreserveRatio(true);
        iv.setFitHeight(300.0);
        Button play = new Button("Play \u25B6");
        Button save = new Button("Save");

        VBox vBox = (VBox) root.lookup("#watchableInfo");
        vBox.setSpacing(20);
        HBox hBox = new HBox(20);
        VBox vBoxDetail = new VBox(20);
        HBox hBoxDetail = new HBox();
        HBox hBoxPS = new HBox(20);

        hBox.getChildren().addAll(iv, vBoxDetail);
        hBoxDetail.getChildren().addAll(wGenre, wYear, wRating);
        hBoxPS.getChildren().addAll(play, save);
        vBoxDetail.getChildren().addAll(wTitle, hBoxDetail);

        if(w instanceof Series) {
            Series s = (Series) w;
            ComboBox<Season> seasonList
                    = new ComboBox<Season>(FXCollections.observableList(s.getSeasons()));
            seasonList.setValue(s.getSeasons().get(0));

            ArrayList e = new ArrayList(s.getSeasons().get(0).getEpisodes());

            ComboBox<Episode> episodeList
                    = new ComboBox<Episode>(FXCollections.observableList(e));
            episodeList.setValue(s.getSeasons().get(0).getEpisodes().get(0));

            seasonList.setId("seasonList");
            seasonList.setOnAction(e2 -> {
                episodeList.setItems(FXCollections.observableList(seasonList.getValue().getEpisodes()));
                episodeList.setValue(seasonList.getValue().getEpisodes().get(0));
            });

            vBoxDetail.getChildren().addAll(seasonList);
            vBoxDetail.getChildren().addAll(episodeList);
        }

        vBoxDetail.getChildren().addAll(hBoxPS);
        vBox.getChildren().add(hBox);

    }

    public static double convertStringToDouble(String s)
    {
        if (s.contains(","))
        {
            s = s.replace(",",".");
        }
        return Double.parseDouble(s);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            allMovies = MovieCreator.createMovies();
            allSeries = SeriesCreator.createSeries();
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}

