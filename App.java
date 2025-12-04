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
import javafx.scene.layout.TilePane;
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
    File userFile;
    VBox cloudPane;
    Button selectFileBtn;
    Button generateCloudBtn;
    VBox container;
    TilePane rowBox;
    Text letter;    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        // GUI layout
        VBox root = new VBox();
        root.setSpacing(20);
        root.setAlignment(Pos.TOP_CENTER);

        container = new VBox();

        cloudPane = new VBox();
        cloudPane.setSpacing(5);

        selectFileBtn = new Button("Select .txt File");
        generateCloudBtn = new Button("Generate Word Search");

        HBox buttonBox = new HBox(10, selectFileBtn, generateCloudBtn);
        buttonBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(buttonBox, cloudPane, container);

        // all actions(callbacks)
        selectFileBtn.setOnAction(event -> openFileChooser());
        generateCloudBtn.setOnAction(event -> generateWordSearch());

        // scene
        Scene scene = new Scene(root, 400, 500);
        stage.setScene(scene);
        stage.setTitle("Word Search Generator");
        stage.show();
    }
     void openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        userFile = fileChooser.showOpenDialog(null);
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

    void horizontal(char[][] grid, String word) {
        int row = getRandomInt(13);
        int position = getRandomInt(13);
        boolean fits = false;

        while (fits == false) {

            if (13 - position >= word.length()) {

                // check if all empty spaces, if not pick a new position
                int i = position;
                boolean empty = true;
                while (i < 13 && empty) {
                    if (grid[row][i] != ' ') {
                        empty = false;
                    }
                    i++;
                }

                // if all positions are empty we can add word to row
                if (empty) {

                    for(int k = position, j = 0; j < word.length() && k < 13; j++, k++) {
                        grid[row][k] = word.charAt(j);
                    }
                    // we added word so get out of while loop
                    fits = true;
                } else {
                    row = getRandomInt(13);
                    position = getRandomInt(13);
                }
            } else {
                // word doesnt fit we need a new position and row
                row = getRandomInt(13);
                position = getRandomInt(13);
            } 
        }
    }

    void vertical(char[][] grid, String word) {
        
    }
    
    // Place words first in each row
    void placeWords(char[][] grid, String[] words) {
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int getRand = getRandomInt(2);
            System.out.println(getRand);
            if (getRand == 0) {
                horizontal(grid, word);
            } else if (getRand == 1) {
                vertical(grid, word);
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

    // Method to display in javafx
    void displayGrid(char[][] grid) {
        container.getChildren().clear();
        for (int row = 0; row < grid.length; row++) {
            rowBox = new TilePane();
            rowBox.setAlignment(Pos.CENTER);
            rowBox.setTileAlignment(Pos.CENTER);
          
            for (int column = 0; column < grid[row].length; column++) {
                letter = new Text("" + grid[row][column]);
                letter.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
                letter.setWrappingWidth(45);
            
                rowBox.getChildren().add(letter);
            }

            container.getChildren().add(rowBox);
        }
    }

     // Method to generate wordsearch and display it fully
    void generateWordSearch() {
        if (userFile != null) {
            String[] words = readWordsFromFile(userFile);

            // Initialize 8x8 grid
            char[][] grid = new char[8][8];
            for (int row = 0; row < 8; row++) {
                for (int column = 0; column < 8; column++) {
                    grid[row][column] = ' ';
                }
            }

            // Place words and fill random letters
            placeWords(grid, words);
            fillEmptySpaces(grid);

            // Display in cloudPane
            displayGrid(grid);
        }
    }

}
