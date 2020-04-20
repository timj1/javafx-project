package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    public App() {
        System.out.println("constructor");
    }

    @Override
    public void start(Stage stage) {

        MenuBar menuBar = new MenuBar();

        Menu file = new Menu("File");
        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem exitItem = new MenuItem("Exit");
        menuBar.getMenus().add(file);
        file.getItems().addAll(newItem, openItem, saveItem, exitItem);

        exitItem.setOnAction(actionEvent -> System.exit(0));

        Menu edit = new Menu("Edit");
        MenuItem cutItem = new MenuItem("Cut");
        MenuItem copyItem = new MenuItem("Copy");
        MenuItem pasteItem = new MenuItem("Paste");
        menuBar.getMenus().add(edit);
        edit.getItems().addAll(cutItem, copyItem, pasteItem);

        Menu run = new Menu("Run");
        MenuItem compRunItem = new MenuItem("Compile and Run");
        menuBar.getMenus().add(run);
        run.getItems().add(compRunItem);

        Menu about = new Menu("About");
        MenuItem aboutItem = new MenuItem("About App");
        menuBar.getMenus().add(about);
        about.getItems().add(aboutItem);

        BorderPane bPane = new BorderPane();
        Button clearB = new Button("clear");
        TextArea textA = new TextArea();
        bPane.setTop(menuBar);
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