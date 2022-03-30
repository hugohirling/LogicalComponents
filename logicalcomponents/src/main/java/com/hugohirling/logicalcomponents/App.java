package com.hugohirling.logicalcomponents;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Hugo Hirling
 * @version 30.03.2022
 * @url https://hugohirling.com
 * 
 * App
 */
public class App extends Application {

    private static Scene scene;

    public static int stageHeight = 800;
    public static int stageWidth = 1200;

    @Override
    public void start(final Stage primaryStage) throws IOException {
        primaryStage.setTitle("Logical Components");

        primaryStage.setResizable(false);

        scene = new Scene(loadFXML("primary"), App.stageWidth, App.stageHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static void setRoot(final String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(final String fxml) throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}