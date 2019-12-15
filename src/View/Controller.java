package View;

import Exceptions.NoSearchMatched;
import Model.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.paint.Color;
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

import static Model.User.users;
import static View.Main.loggedInAs;

public class Controller implements Initializable{
    private ArrayList<Movie> allMovies;
    private ArrayList<Series> allSeries;
    @FXML private ComboBox<String> comboBox;
    @FXML private TextField textField;
    @FXML public VBox watchBox;
    @FXML public VBox searchedBox;
    public static String currentScene;
    @FXML public TextField temptUsername;
    @FXML public Button loginButton;
    @FXML public Button adminButton;

    @FXML
    private void changeSceneToMovies(ActionEvent event) throws Exception {
        currentScene = "Movies";
        Parent root = FXMLLoader.load(getClass().getResource("movies_scene.fxml"));
        setScene(event, root);

        VBox mB = (VBox) root.lookup("#watchBox");
        BorderPane p = makeBorderPane(allMovies);
        mB.getChildren().add(p);
    }
    @FXML
    private void changeSceneToHome(ActionEvent event) throws Exception {
        currentScene = "Home";
        Parent root = FXMLLoader.load(getClass().getResource("home_scene.fxml"));
        setScene(event, root);

        if(Main.loggedIn == true){
            VBox vbox = (VBox) root.lookup("#homeContent");
            VBox vbox2 = (VBox) root.lookup("#logIn");
            vbox.getChildren().remove(vbox2);
            vbox.getChildren().add(new Label("Logged in as: " + loggedInAs));
        }
    }
    @FXML
    private void changeSceneToAccount(ActionEvent event) throws Exception {
        currentScene = "Account";
        Parent root = FXMLLoader.load(getClass().getResource("account_scene.fxml"));
        setScene(event, root);
        VBox vBox = (VBox) root.lookup("#accountInfo");

        if(Main.loggedIn == true) {
            Label header1 = new Label("Membership");
            Label content1 = new Label("Username: " + loggedInAs);
            Label content2 = new Label("Password: " + "****");
            Label header2 = new Label("Settings");
            Button content3 = new Button("Delete Account");
            Button content4 = new Button("Sign Out");

            content3.setOnAction(e -> {
                try {
                    deleteAccount(e);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            content4.setOnAction(e -> {
                try {
                    signOut(e);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            header1.setId("content-header2");
            content1.setId("content-text");
            content2.setId("content-text");
            header2.setId("content-header2");
            content3.setId("content-text");
            content4.setId("content-text");

            vBox.getChildren().addAll(header1, content1, content2, header2, content3, content4);
        }else{
            Label label = new Label("You're not logged in");
            vBox.getChildren().addAll(label);
        }
    }
    @FXML
    private void changeSceneToSeries(ActionEvent event) throws Exception {
        currentScene = "Series";
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
    private void logIn(ActionEvent event) throws Exception {
        String username = temptUsername.getText();
        Boolean doesNotExist = true;
        for(String user: users){
            if(username.equals(user)){
                doesNotExist = false;
                Main.loggedIn = true;
                loggedInAs = username;
                changeSceneToHome(event);
                break;
            }
        }
        if(doesNotExist) {System.out.println(username + ": Does not exist");}
    }

    @FXML
    private void logInAdmin(ActionEvent event) throws Exception {
        Main.loggedIn = true;
        loggedInAs = "ADMIN";
        changeSceneToHome(event);
    }

    @FXML
    private void createNewAccount(ActionEvent event) throws Exception {
        String username = temptUsername.getText();
        Boolean doesExist = false;
        for(String user: users) {
            if (username.equals(user)) {
                System.out.println(username + ": Already exist");
                doesExist = true;
                break;
            }
        }
        if(!doesExist){
            //man kan kun lave en bruger så.
            User newuser = new User(username);
            users.add(username);
            Main.loggedIn = true;
            loggedInAs = username;
            changeSceneToHome(event);
        }


    }

    @FXML
    private void signOut(ActionEvent event) throws Exception {
        Main.loggedIn = false;
        loggedInAs = "";
        changeSceneToAccount(event);
    }

    @FXML
    private void deleteAccount(ActionEvent event) throws Exception {
        users.remove(loggedInAs);
        Main.loggedIn = false;
        loggedInAs = "";
        changeSceneToAccount(event);
    }

    @FXML
    private void searchComboBox(ActionEvent event) throws Exception {
        String text = textField.getText();
        String value = (String) comboBox.getValue();
        String sss = currentScene;

        if(getSearched(text).size() <= 0) {
            throw new NoSearchMatched(text);
        }
        BorderPane searched = makeBorderPane(trimArray(getSearched(text)));
        Parent root = FXMLLoader.load(getClass().getResource("search_scene.fxml"));

        setScene(event, root);

        //BorderPane searched = makeBorderPane(getSearched(text));

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
        scrollPane.setFitToWidth(true);
        BorderPane root = new BorderPane(scrollPane);

        try {

            tile_pane.setVgap(10);
            tile_pane.setHgap(45);

            for (Object o : arrayList) {
                Watchable w = (Watchable) o;
                Label label = new Label (w.getTitle());
                label.setFont(Font.font("Times New Roman",18));
                label.setMaxHeight(60);
                label.setMinHeight(60);
                label.setAlignment(Pos.TOP_LEFT);
                label.setWrapText(true);
                label.setMaxWidth(w.getImg().getWidth());
                ImageView iv = new ImageView(w.getImg());
                iv.setOnMouseClicked(e -> System.out.println(w.getTitle()));

                iv.setOnMouseClicked(e -> {
                    try {
                        changeSceneToWatchablePage(e, w);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });

                VBox vBox = new VBox();
                vBox.getChildren().addAll(iv,label);
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
            Insets insets = new Insets(40,0,0,0);
            tile_pane.setPadding(insets);
            root.setCenter(scrollPane);


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
                    int[] year = serie.getYearArray();
                    int searchTermInt = Integer.parseInt(searchTerm);
                    for(int i = 0; i < serie.getYearArray().length; i++)
                    {
                        if(year[i] == searchTermInt) {
                            searchedWatchables.add(serie);
                        }
                    }
                }
                return searchedWatchables;

            case "Genre":
                searchTerm = searchTerm.replaceAll("\\s","");
                for (Movie movie : allMovies)
                {
                    String genre = movie.getGenre().toLowerCase();
                    genre = genre.substring(1);
                    String[] parts = genre.split(", ");
                    for (int i = 0; i < parts.length; i++)
                    {
                        if (searchTerm.contains(parts[i]))
                        {
                            if (searchedWatchables.contains(movie))
                            {
                                continue;
                            }
                            searchedWatchables.add(movie);
                        }
                    }
                }
                for (Series serie : allSeries)
                {
                    String genre = serie.getGenre().toLowerCase();
                    String[] parts2 = genre.split(", ");
                    for (int i = 0; i < parts2.length; i++)
                    {
                        if (searchTerm.contains(parts2[i]))
                        {
                            if (searchedWatchables.contains(serie))
                            {
                                continue;
                            }
                            searchedWatchables.add(serie);
                        }
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

    private ArrayList<Watchable> trimArray(ArrayList<Watchable> searched)
    {
        ArrayList<Watchable> result = new ArrayList<>();
        switch (currentScene)
        {
            case "Movies":
                for (Watchable w : searched)
                {
                    if (w instanceof Movie)
                    {
                        result.add(w);
                    }
                }
                return result;

            case "Series":
                for (Watchable w : searched)
                {
                    if (w instanceof Series)
                    {
                        result.add(w);
                    }
                }
                return result;

            default:
                return searched;
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

        Label wYear = new Label("Fail displaying year");

        if(w instanceof Movie) {
            Movie m = (Movie) w;
            wYear.setText(m.getYear()+ " \u25CF ");
        }else if(w instanceof Series){
            Series s = (Series) w;
            wYear.setText(s.getDisplayYear()+ " \u25CF ");
        }

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
            episodeList.setOnAction((e1)-> playSetActionSeries(episodeList, play));
            playSetActionSeries(episodeList, play);

            seasonList.setId("seasonList");
            seasonList.setOnAction(e2 -> {
                episodeList.setItems(FXCollections.observableList(seasonList.getValue().getEpisodes()));
                episodeList.setValue(seasonList.getValue().getEpisodes().get(0));
                playSetActionSeries(episodeList, play);
            });

            vBoxDetail.getChildren().addAll(seasonList);
            vBoxDetail.getChildren().addAll(episodeList);
        }else{
            save.setOnAction((e)->addToSaved(w));
        }

        vBoxDetail.getChildren().addAll(hBoxPS);
        vBox.getChildren().add(hBox);

    }

    public void playSetActionSeries(ComboBox<Episode> episodeList, Button play){
        boolean watchedExist = false; //tjek først
        if(!watchedExist){
        addEpToWatched(episodeList.getValue());
        }else {
            play.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    public void addToSaved(Watchable w){
        // get user with username = loggedInAs
        //User.getSaved();
        boolean savedExist = false;
        if(!savedExist){
            if(w instanceof Series){
                //User.addSaved(w);
                System.out.println("You have now saved this series");
            }
            if(w instanceof Movie){
                //User.addSaved(w);
                System.out.println("You have now saved this movie");
            }
        }
    }

    public void addEpToWatched(Episode e){
        // get user with username = loggedInAs
        boolean watchedExist = false;
        if(!watchedExist){
            //User.addWatched(e);
            System.out.println("You have now watched this episode");
        }
    }
    public void addToWatched(Watchable w){
        // get user with username = loggedInAs
        boolean watchedExist = false;
        if(!watchedExist){
            //User.addWatched(e);
            System.out.println("You have now watched this movie");
        }
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

