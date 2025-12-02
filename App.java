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
import java.util.Random;
/**
 * Template JavaFX application.
 */
public class App extends Application {
    File selectedFile;
    Text fileText;
    Button selectFileBtn;
    Button genWordSearchBtn;
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
        genWordSearchBtn = new Button("Generate Word Cloud ");
        genWordSearchBtn.setStyle("-fx-background-color: darkred; -fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 15;");
        
        
        
        
        
        // Set up reactions (aka callbacks).
        selectFileBtn.setOnAction(e -> openFileChooser());
        genWordSearchBtn.setOnAction(e -> {});
        
        // Add components to the content box.
        contentBox.getChildren().addAll(labelBox, buttonBox, cloudPane);
        labelBox.getChildren().add(promptLabel);
        buttonBox.getChildren().addAll(selectFileBtn, genWordSearchBtn);
        

        // Set up the window and display it.
        Scene scene = new Scene(contentBox, 800, 800);
        stage.setScene(scene);
        stage.setTitle("Word Cloud Generator ");
        stage.show();
    }
    // open file chooser popup method
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

    // Read words from file method
    static String[] readWordsFromFile(File file) {
        String[] words = new String[6];
        try {
            Scanner fileReader = new Scanner(file);
            for (int i = 0; i < 6; i++) {
                words[i] = fileReader.next();
            }
            fileReader.close();
        } catch (Exception error) {
            System.out.println("Error reading File! ");
        }
        return words;
    }

    // generate random int method
    static int getRandomInt(int max) {
        Random random = new Random();
        int randomIndex = random.nextInt(max);
        return randomIndex;
    }
    
    // create method to place words in the grid
    static void placeWords(char[][] grid, String[] words) {
        for (int rows = 0; rows < words.length; rows++) {
            String word = words[rows];
            if (word == null) {
                word = "";
            }
            for (int columns = 0; columns < grid[rows].length; columns++) {
                if (columns < word.length()) {
                    grid[rows][columns] = word.charAt(columns);
                } else {
                    grid[rows][columns] = ' ';
                }
            }
        }
    }

    // create method to fill any empty spaces
    static void fillEmptySpaces(char[][] grid) {
        char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };
        for (int rows = 0; rows < grid.length; rows++) {
            for (int columns = 0; columns < grid[rows].length; columns++) {
                if (grid[rows][columns] == ' ') {
                    int index = getRandomInt(alphabet.length);
                    grid[rows][columns] = alphabet[index];
                }
            }
        }
    }


}
