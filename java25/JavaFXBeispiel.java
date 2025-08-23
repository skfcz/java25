package de.groygroy.linuxmagazin;


//import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HeaderBar;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class JavaFXBeispiel extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {


        // Hier ist der MenuBar in den HeadrBar gepackt, damit kann das Menu
        // wie nornmal aufgebaut werden, liegt aber in der Fensterdekoration
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: #a0a0a0;");

        Menu dateiMenu = new Menu("Datei");
        MenuItem neuItem = new MenuItem("Neu");
        MenuItem oeffnenItem = new MenuItem("Öffnen");
        MenuItem beendenItem = new MenuItem("Beenden");
        dateiMenu.getItems().addAll(neuItem, oeffnenItem, beendenItem);

        Menu hilfeMenu = new Menu("Hilfe");
        MenuItem infoItem = new MenuItem("Info");
        hilfeMenu.getItems().add(infoItem);

        menuBar.getMenus().addAll(dateiMenu, hilfeMenu);

        // Statt Icon hier ein wenig Unicode
        var label = new Label("◀cz▶");
        label.setPadding(new Insets(10, 10, 10, 10));

        // Der neue HeaderBar
        var headerBar = new HeaderBar();
        headerBar.setLeading(label);
        headerBar.setCenter(menuBar);
        headerBar.setStyle("-fx-background-color: #a0a0a0;");

        var root = new BorderPane();
        root.setTop(headerBar);
        root.setCenter(new Label("JavaFX Beispiel"));

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        // !!! Ohne EXTENDED kein HeaderBar !!!
        primaryStage.initStyle(StageStyle.EXTENDED);
        primaryStage.show();
    }
}

