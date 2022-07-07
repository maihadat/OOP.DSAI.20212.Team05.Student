package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HelpController implements Initializable {
	protected Stage stage;
	protected FXMLLoader loader;
	protected Parent root;
	protected Scene scene;
    @FXML
    private AnchorPane HelpMenuPane;
    @FXML
    private TextArea HelpText;

    @FXML
    private Button backbutton;

    @FXML
    void BackButtonControl(ActionEvent event) throws IOException{
    	loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
		//loader.setController(new SortController());
		root = loader.load();
		System.out.println("a");
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Sorting Visualization");
		stage.setScene(scene);
		
		stage.show();


    }

@Override
public void initialize(URL url, ResourceBundle rb) {
    try {
    	File file = new File(".");
    	for(String fileNames : file.list()) System.out.println(fileNames);
        Scanner s = new Scanner(new File("src/application/HelpMenu.txt")).useDelimiter("\\s+");
        while (s.hasNext()) {
            if (s.hasNextInt()) { // check if next token is an int
            	HelpText.appendText(s.nextInt() + " "); // display the found integer
            } else {
            	HelpText.appendText(s.next() + " "); // else read the next token
            }
        }
    } catch (FileNotFoundException ex) {
        System.err.println(ex);
    }
}

}
