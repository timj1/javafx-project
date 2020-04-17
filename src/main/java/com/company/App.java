package com.company;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("JavaFX");
        stage.show();
        System.out.println("start");
    }

    public static void main(String args[]) {

        launch(args);
    }
}