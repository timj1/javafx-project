package com.company;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void init() {
        System.out.println("init");
    }
    @Override
    public void start(Stage stage) {
        stage.setTitle("JavaFX");
        stage.show();
        System.out.println("start");
    }
    @Override
    public void stop() {
        System.out.println("stop");
    }

    public static void main(String args[]) {
        var app = new Application() {
            @Override
            public void start(Stage stage) throws Exception {

            }
        };
        System.out.println("constructor");

        app.launch(args);
    }
}