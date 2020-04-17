package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        BorderPane bPane = new BorderPane();
        Button clearB = new Button("clear");
        TextArea textA = new TextArea();
        bPane.setTop(clearB);
        bPane.setCenter(textA);

        EventHandler<ActionEvent> eventHandler = actionEvent -> textA.clear();
        clearB.setOnAction(eventHandler);

        //Button world = new Button("World");
        //Group group = new Group(new Button("Hello"), world);
        Scene scene = new Scene(bPane, 640, 480);

        stage.initStyle(StageStyle.DECORATED);
        //stage.setWidth(640);
        //stage.setHeight(480);
        stage.centerOnScreen();
        stage.setTitle("JavaFX");
        stage.setScene(scene);
        stage.show();

        //world.setTranslateX(640 - world.getWidth());
        //world.setTranslateY(480 - world.getHeight());
    }

    public static void main(String args[]) {

        launch(args);
    }
}