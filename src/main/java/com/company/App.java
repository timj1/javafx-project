package com.company;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.initStyle(StageStyle.DECORATED);
        stage.setWidth(640);
        stage.setHeight(480);
        stage.centerOnScreen();
        stage.setTitle("JavaFX");
        stage.show();
    }

    public static void main(String args[]) {

        launch(args);
    }
}