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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

public class App extends Application {

    //TextArea
    static TextArea textA;
    // String[] for setStyle ------------
    static String [] textCSS;
    static String [] fontCSS;
    static String [] sizeCSS;

    public App() {
        System.out.println("constructor");
    }

    //CSS font set method
    public static EventHandler<ActionEvent> setFont(MenuItem font) {

        EventHandler<ActionEvent> eventHandler = actionEvent -> {
            fontCSS[1] = font.getText() + ";";
            System.out.println(fontCSS[1]);
            textA.setStyle(textCSS[0] + textCSS[1] + fontCSS[0] + fontCSS[1] + sizeCSS[0] + sizeCSS[1]);
        };
        return eventHandler;
    }

    @Override
    public void start(Stage stage) {
        // Localization ------------
        // Locale locale = Locale.getDefault();
        Locale locale = new Locale("fi", "FI");
        ResourceBundle labels = ResourceBundle.getBundle("ui", locale);
        // Application title ------
        String title = labels.getString("title");
        //File menu names ------
        String fileString = labels.getString("fileString");
        String newItemString = labels.getString("newItemString");
        String newItemAlertTitle = labels.getString("newItemAlertTitle");
        String newItemAlertText = labels.getString("newItemAlertText");
        String openItemString = labels.getString("openItemString");
        String saveAsItemString = labels.getString("saveAsItemString");
        String saveItemString = labels.getString("saveItemString");
        String exitItemString = labels.getString("exitItemString");
        // Edit menu names ------
        String editString = labels.getString("editString");
        String cutItemString = labels.getString("cutItemString");
        String copyItemString = labels.getString("copyItemString");
        String pasteItemString = labels.getString("pasteItemString");
        // Run menu names ------
        String runString = labels.getString("runString");
        String compRunItemString = labels.getString("compRunItemString");
        // About menu names ------
        String aboutString = labels.getString("aboutString");
        String aboutItemString = labels.getString("aboutItemString");
        // Alert names ------
        String alertTitle = labels.getString("alertTitle");
        String alertText = labels.getString("alertText");
        // Fonts name ------
        String fontsString = labels.getString("fontsString");
        // Search field
        String searchFieldString = labels.getString("searchFieldString");
        String searchAlertTitle = labels.getString("searchAlertTitle");
        String searchAlertText = labels.getString("searchAlertText");

        // String[] for setStyle ------------
        textCSS = new String[]{"-fx-text-fill:", "blue;"};
        fontCSS = new String[]{"-fx-font-family:", "Arial;"};
        sizeCSS = new String[]{"-fx-font-size:", "12 px;"};

        // TextArea ------------
        textA = new TextArea();
        textA.setStyle(textCSS[0] + textCSS[1] + fontCSS[0] + fontCSS[1] + sizeCSS[0] + sizeCSS[1]);
        textA.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.TAB) {
                int index = textA.getCaretPosition();
                textA.replaceText(index-1, index, "    ");
            }
        });

        // Clear button ------------
        //EventHandler<ActionEvent> eventHandler = actionEvent -> textA.clear();
        Button clearB = new Button("clear");
        clearB.setOnAction(actionEvent -> textA.clear());

        // ColorPicker ------------
        ColorPicker colorPicker = new ColorPicker();
        Color colorInit = Color.web(textCSS[1].replaceFirst(";", ""));
        colorPicker.setValue(colorInit);

        colorPicker.setOnAction(actionEvent -> {
            Color value = colorPicker.getValue();
            textCSS[1] = value.toString().replaceFirst("0x", "#")+";";
            textA.setStyle(textCSS[0] + textCSS[1] + fontCSS[0] + fontCSS[1] + sizeCSS[0] + sizeCSS[1]);
            System.out.println(actionEvent.getTarget());
            System.out.println(actionEvent.getEventType());
            System.out.println(actionEvent.getSource());

        });

        // Font size TextField ------------
        TextField fontSizeField = new TextField();
        fontSizeField.setPrefColumnCount(2);
        fontSizeField.setPromptText(sizeCSS[1].replaceFirst("px;", ""));

        fontSizeField.setOnAction(actionEvent -> {
            int fontSizeInt = 0;
            try {
                fontSizeInt = Integer.parseInt(fontSizeField.getText());
            } catch (Exception e) {
                fontSizeField.clear();
            }
            if(fontSizeInt > 7 && fontSizeInt < 121 ) {
                sizeCSS[1] = fontSizeField.getText() + " px;";
                System.out.println(sizeCSS[1]);
                textA.setStyle(textCSS[0] + textCSS[1] + fontCSS[0] + fontCSS[1] + sizeCSS[0] + sizeCSS[1]);
            }
        });

        // Font MenuButton ------------
        MenuItem sans_serifFont = new MenuItem("Arial");
        MenuItem serifFont = new MenuItem("serif");
        MenuItem cursiveFont = new MenuItem("cursive");
        MenuItem monospaceFont = new MenuItem("monospace");
        MenuButton menuFont = new MenuButton(fontsString, null,
                sans_serifFont, serifFont, cursiveFont, monospaceFont);

        sans_serifFont.setOnAction(setFont(sans_serifFont));
        serifFont.setOnAction(setFont(serifFont));
        cursiveFont.setOnAction(setFont(cursiveFont));
        monospaceFont.setOnAction(setFont(monospaceFont));

        // Search box ------------
        HBox searchBox = new HBox();
        // Search TextField ------
        TextField searchField = new TextField();
        //fontSizeField.setPrefColumnCount(2);
        searchField.setPromptText(searchFieldString);
        searchField.setOnAction(actionEvent -> {
            int searchIndex = textA.getText().indexOf(searchField.getText());
            textA.selectRange(searchIndex, searchIndex + searchField.getLength());
        });
        //Search buttons ------
        ButtonBar searchButtons = new ButtonBar();
        Button backwardButton = new Button("<");
        Button forwardButton = new Button(">");
        searchButtons.getButtons().addAll(backwardButton, forwardButton);
        backwardButton.setOnAction(actionEvent -> {
            int caretPosition = textA.getCaretPosition();
            caretPosition = caretPosition - searchField.getLength() -1;
            int backward = textA.getText().lastIndexOf(searchField.getText(), caretPosition);
            if(backward > -1) {
                textA.selectRange(backward, backward + searchField.getLength());
            }
        });
        forwardButton.setOnAction(actionEvent -> {
            int caretPosition = textA.getCaretPosition();
            int forward = textA.getText().indexOf(searchField.getText(), caretPosition);
            if(forward > -1) {
                textA.selectRange(forward, forward + searchField.getLength());
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(searchAlertTitle);
                alert.setHeaderText(null);
                alert.setContentText(searchAlertText);
                alert.showAndWait();
            }
        });
        //Search box add elements ------
        searchBox.getChildren().addAll(searchField, backwardButton, forwardButton);

        // MenuBar ------------------
        MenuBar menuBar = new MenuBar();
        // File menu ------------
        Menu file = new Menu(fileString);
        MenuItem newItem = new MenuItem(newItemString);
        MenuItem openItem = new MenuItem(openItemString);
        MenuItem saveAsItem = new MenuItem(saveAsItemString);
        MenuItem saveItem = new MenuItem(saveItemString);
        MenuItem exitItem = new MenuItem(exitItemString);
        saveItem.setDisable(true);
        menuBar.getMenus().add(file);
        file.getItems().addAll(newItem, openItem, saveAsItem, saveItem, exitItem);

        // FileHandler for New, open, save as... and save ------------
        FileHandler fileHandler = new FileHandler();
        // New file ------
        newItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN));
        newItem.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(newItemAlertTitle);
            alert.setHeaderText(null);
            alert.setContentText(newItemAlertText);
            alert.showAndWait();
            System.out.println(alert.getResult().getText());
            if(alert.getResult().getText().equals("OK")) {
                saveItem.setDisable(true);
                fileHandler.setFilePath(null);
                textA.clear();
            }
        });

        // Open file ------
        FileChooser fileChooser = new FileChooser();
        openItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN));
        openItem.setOnAction(actionEvent -> {
            System.out.println(fileHandler.getFilePath());
            File openFile = fileChooser.showOpenDialog(stage);
            try {
                fileHandler.setFilePath(openFile.getPath());
                //String content = fileHandler.open();
                fileHandler.open((content -> {
                    textA.setText(content);
                    saveItem.setDisable(false);
                }));

                System.out.println(fileHandler.getFilePath());
            } catch (Exception e) {
                System.out.println("Open error: " + e);
            }
        });
        // Save as... file ------
        saveAsItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.SHIFT_DOWN,
                KeyCombination.SHORTCUT_DOWN));
        saveAsItem.setOnAction(actionEvent -> {
            try {
                fileHandler.setFilePath(new FileChooser().showSaveDialog(stage).getPath());
                String saveContent = textA.getText();

                fileHandler.save((boolean b) -> saveItem.setDisable(b), saveContent);
                //fileHandler.save(saveContent);

                //saveItem.setDisable(false);
            } catch (Exception e) {
                System.out.println("Save as... error: " + e);
            }
        });
        // Save file ------
        saveItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN));
        saveItem.setOnAction(actionEvent -> {
            try {
                fileHandler.save((boolean b) -> saveItem.setDisable(b), textA.getText());
            } catch(Exception e) {
                System.out.println("Save error: " + e);
            }
        });
        // Exit action ------
        exitItem.setOnAction(actionEvent -> System.exit(0));

        // Edit menu ------------
        Menu edit = new Menu(editString);
        MenuItem cutItem = new MenuItem(cutItemString);
        MenuItem copyItem = new MenuItem(copyItemString);
        MenuItem pasteItem = new MenuItem(pasteItemString);
        menuBar.getMenus().add(edit);
        edit.getItems().addAll(cutItem, copyItem, pasteItem);

        // Cut, copy and paste actions ------
        cutItem.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.SHORTCUT_DOWN));
        cutItem.setOnAction(actionEvent -> {
            textA.cut();
            System.out.println("Ctrl-X");
        });
        copyItem.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.SHORTCUT_DOWN));
        copyItem.setOnAction(actionEvent -> {
            textA.copy();
            System.out.println("Ctrl-C");
        });
        pasteItem.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.SHORTCUT_DOWN));
        pasteItem.setOnAction(actionEvent -> {
            textA.paste();
            System.out.println("Ctrl-V");
        });

        // Run menu ------------
        Menu run = new Menu(runString);
        MenuItem compRunItem = new MenuItem(compRunItemString);
        menuBar.getMenus().add(run);
        run.getItems().add(compRunItem);

        // About menu ------------
        Menu about = new Menu(aboutString);
        MenuItem aboutItem = new MenuItem(aboutItemString);
        menuBar.getMenus().add(about);
        about.getItems().add(aboutItem);

        // Alert information ------------
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(alertTitle);
        alert.setHeaderText(null);
        alert.setContentText(alertText);

        aboutItem.setOnAction(actionEvent -> alert.showAndWait());

        // Layout ------------
        BorderPane bPane = new BorderPane();
        HBox hBox = new HBox(clearB, colorPicker, menuFont, fontSizeField, searchBox);
        VBox vBox = new VBox(menuBar, hBox);
        bPane.setTop(vBox);
        bPane.setCenter(textA);

        // Scene ------------
        Scene scene = new Scene(bPane, 640, 480);
        // Stage ------------
        stage.initStyle(StageStyle.DECORATED);
        stage.centerOnScreen();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String args[]) {

        launch(args);
    }
}