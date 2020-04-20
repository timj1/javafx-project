package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

public class App extends Application {

    public App() {
        System.out.println("constructor");
    }

    @Override
    public void start(Stage stage) {
        // Locale locale = Locale.getDefault();
        Locale locale = new Locale("fi", "FI");
        ResourceBundle labels = ResourceBundle.getBundle("ui", locale);
        String title = labels.getString("title");
        String fileString = labels.getString("fileString");
        String newItemString = labels.getString("newItemString");
        String openItemString = labels.getString("openItemString");
        String saveItemString = labels.getString("saveItemString");
        String exitItemString = labels.getString("exitItemString");

        String editString = labels.getString("editString");
        String cutItemString = labels.getString("cutItemString");
        String copyItemString = labels.getString("copyItemString");
        String pasteItemString = labels.getString("pasteItemString");

        String runString = labels.getString("runString");
        String compRunItemString = labels.getString("compRunItemString");

        String aboutString = labels.getString("aboutString");
        String aboutItemString = labels.getString("aboutItemString");

        String alertTitle = labels.getString("alertTitle");
        String alertText = labels.getString("alertText");

        TextArea textA = new TextArea();
        textA.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.TAB) {
                int index = textA.getCaretPosition();
                textA.replaceText(index-1, index, "    ");
            }
        });

        MenuBar menuBar = new MenuBar();

        Menu file = new Menu(fileString);
        MenuItem newItem = new MenuItem(newItemString);
        MenuItem openItem = new MenuItem(openItemString);
        MenuItem saveItem = new MenuItem(saveItemString);
        MenuItem exitItem = new MenuItem(exitItemString);
        menuBar.getMenus().add(file);
        file.getItems().addAll(newItem, openItem, saveItem, exitItem);

        newItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN));
        newItem.setOnAction(actionEvent -> System.out.println("Ctrl-N"));

        FileChooser fileChooser = new FileChooser();
        openItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN));
        openItem.setOnAction(actionEvent -> fileChooser.showOpenDialog(stage));

        saveItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN));
        saveItem.setOnAction(actionEvent -> {
            File selectedFile = fileChooser.showOpenDialog(stage);
            System.out.println("Ctrl-S");
        });

        exitItem.setOnAction(actionEvent -> System.exit(0));

        Menu edit = new Menu(editString);
        MenuItem cutItem = new MenuItem(cutItemString);
        MenuItem copyItem = new MenuItem(copyItemString);
        MenuItem pasteItem = new MenuItem(pasteItemString);
        menuBar.getMenus().add(edit);
        edit.getItems().addAll(cutItem, copyItem, pasteItem);

        //cutItem.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.SHORTCUT_DOWN));
        cutItem.setOnAction(actionEvent -> {
            textA.cut();
            System.out.println("Ctrl-X");
        });

        //copyItem.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.SHORTCUT_DOWN));
        copyItem.setOnAction(actionEvent -> {
            textA.copy();
            System.out.println("Ctrl-C");
        });

        //pasteItem.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.SHORTCUT_DOWN));
        pasteItem.setOnAction(actionEvent -> {
            textA.paste();
            System.out.println("Ctrl-V");
        });

        Menu run = new Menu(runString);
        MenuItem compRunItem = new MenuItem(compRunItemString);
        menuBar.getMenus().add(run);
        run.getItems().add(compRunItem);

        Menu about = new Menu(aboutString);
        MenuItem aboutItem = new MenuItem(aboutItemString);
        menuBar.getMenus().add(about);
        about.getItems().add(aboutItem);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(alertTitle);
        alert.setHeaderText(null);
        alert.setContentText(alertText);

        aboutItem.setOnAction(actionEvent -> alert.showAndWait());

        BorderPane bPane = new BorderPane();
        Button clearB = new Button("clear");
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
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

        //world.setTranslateX(640 - world.getWidth());
        //world.setTranslateY(480 - world.getHeight());
    }

    public static void main(String args[]) {

        launch(args);
    }
}