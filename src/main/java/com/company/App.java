package com.company;

import com.company.util.FileHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.util.LinkedList;
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

        String fontsString = labels.getString("fontsString");

        String [] textCSS = new String[]{"-fx-text-fill:", "blue;"};
        String [] fontCSS = new String[]{"-fx-font-family:", "Arial;"};
        String [] sizeCSS = new String[]{"-fx-font-size:", "12 px;"};

        TextArea textA = new TextArea();
        textA.setStyle(textCSS[0] + textCSS[1] + fontCSS[0] + fontCSS[1] + sizeCSS[0] + sizeCSS[1]);
        textA.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.TAB) {
                int index = textA.getCaretPosition();
                textA.replaceText(index-1, index, "    ");
            }
        });

        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setOnAction(actionEvent -> {
            Color value = colorPicker.getValue();
            textCSS[1] = value.toString().replaceFirst("0x", "#")+";";
            textA.setStyle(textCSS[0] + textCSS[1] + fontCSS[0] + fontCSS[1] + sizeCSS[0] + sizeCSS[1]);
        });

        LinkedList<String> list = new LinkedList<>(Font.getFontNames());
        System.out.println(list);
        System.out.println(textA.getStyle());
        System.out.println(textA.getFont());
        System.out.println(textA.getText());

        MenuBar menuFont = new MenuBar();
        Menu fonts = new Menu(fontsString);
        MenuItem arialFont = new MenuItem("Arial");
        MenuItem serifFont = new MenuItem("Times");
        MenuItem cursiveFont = new MenuItem("Cursive");
        menuFont.getMenus().add(fonts);
        fonts.getItems().addAll(arialFont, serifFont, cursiveFont);

        arialFont.setOnAction(actionEvent -> {
            fontCSS[1] = "sans-serif;";
            textA.setStyle(textCSS[0] + textCSS[1] + fontCSS[0] + fontCSS[1] + sizeCSS[0] + sizeCSS[1]);
        });
        serifFont.setOnAction(actionEvent -> {
            fontCSS[1] = "serif;";
            textA.setStyle(textCSS[0] + textCSS[1] + fontCSS[0] + fontCSS[1] + sizeCSS[0] + sizeCSS[1]);
        });
        cursiveFont.setOnAction(actionEvent -> {
            fontCSS[1] = "cursive;";
            textA.setStyle(textCSS[0] + textCSS[1] + fontCSS[0] + fontCSS[1] + sizeCSS[0] + sizeCSS[1]);
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
        openItem.setOnAction(actionEvent -> {
            File openFile = fileChooser.showOpenDialog(stage);
            FileHandler fileHandler = new FileHandler();
            fileHandler.setFilePath(openFile.getPath());
            String content = fileHandler.open();
            textA.setText(content);

        });

        saveItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN));
        saveItem.setOnAction(actionEvent -> {
            File saveFile = fileChooser.showSaveDialog(stage);
            FileHandler saveHandler = new FileHandler(saveFile.getPath());
            String saveContent = textA.getText();
            saveHandler.save(saveContent);

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
        HBox hBox = new HBox(menuBar, colorPicker, menuFont);
        Button clearB = new Button("clear");
        bPane.setTop(hBox);
        bPane.setCenter(textA);

        EventHandler<ActionEvent> eventHandler = actionEvent -> textA.clear();
        clearB.setOnAction(eventHandler);

        Scene scene = new Scene(bPane, 640, 480);

        stage.initStyle(StageStyle.DECORATED);
        //stage.setWidth(640);
        //stage.setHeight(480);
        stage.centerOnScreen();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String args[]) {

        launch(args);
    }
}