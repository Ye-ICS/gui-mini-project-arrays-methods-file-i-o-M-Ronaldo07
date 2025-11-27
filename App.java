import java.io.File;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.Scanner;
/**
 * Template JavaFX application.
 */
public class App extends Application {
    File selectedFile;
    Text fileText;
    Button selectFileBtn;
    Button generateCloudBtn;
    FlowPane cloudPane;
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        // Create components to add.
        VBox contentBox = new VBox(20);
        contentBox.setStyle("-fx-background-color: lightblue; -fx-padding: 24;");
        contentBox.setAlignment(Pos.TOP_CENTER);

        VBox labelBox = new VBox();
        labelBox.setAlignment(Pos.TOP_CENTER);
        Label promptLabel = new Label("Choose a .txt File To Create A Word Cloud");
        promptLabel.setAlignment(Pos.TOP_CENTER);
        promptLabel.setStyle("-fx-background-color: lightgreen;-fx-text-fill: black; -fx-font-size: 30px;");

       
        
        cloudPane = new FlowPane();
        cloudPane.setPrefHeight(400);
        cloudPane.setStyle("-fx-background-color: white; -fx-border-color: black;");
        cloudPane.setHgap(10);
        cloudPane.setVgap(10);
        
        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        selectFileBtn = new Button("Select a File ");
        selectFileBtn.setStyle("-fx-background-color: darkgreen; -fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 15;");
        generateCloudBtn = new Button("Generate Word Cloud ");
        generateCloudBtn.setStyle("-fx-background-color: darkred; -fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 15;");
        
        
        
        
        
        // Set up reactions (aka callbacks).
        
        selectFileBtn.setOnAction(e -> openFileChooser());
        generateCloudBtn.setOnAction(e -> {});
        
        // Add components to the content box.
        contentBox.getChildren().addAll(labelBox, buttonBox, cloudPane);
        labelBox.getChildren().add(promptLabel);
        buttonBox.getChildren().addAll(selectFileBtn, generateCloudBtn);
        

        // Set up the window and display it.
        Scene scene = new Scene(contentBox, 800, 800);
        stage.setScene(scene);
        stage.setTitle("Word Cloud Generator ");
        stage.show();
    }

    void openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Txt File Containing A Story!");
        selectedFile = fileChooser.showOpenDialog(null);
        try {  
            if (selectedFile != null) {
            fileText.setText(selectedFile.getName());
            } 
        } catch (Exception error) {
            return;
        }
    }
    static String[] readWords(File file) {
        String[] word = new String[6];
        try {
            Scanner fileReader = new Scanner(file);
            for (int i = 0; i < 6; i++) {
                word[i] = fileReader.next();
            }
            fileReader.close();
        } catch (Exception error) {
            System.out.println("Error reading File! ");
        }
        return word;
    }
}
