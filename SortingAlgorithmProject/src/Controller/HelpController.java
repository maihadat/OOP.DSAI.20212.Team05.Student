package Controller;

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
	private Stage stage;
	private FXMLLoader loader;
	private Parent root;
	private Scene scene;
    @FXML
    private AnchorPane HelpMenuPane;
    @FXML
    private TextArea HelpText;

    @FXML
    private Button backbutton;

    @FXML
    void BackButtonControl(ActionEvent event) throws IOException{
    	loader = new FXMLLoader(getClass().getResource("/View/MainScene.fxml"));
		//loader.setController(new SortController());
		root = loader.load();
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Sorting Visualization");
		stage.setScene(scene);
		
		stage.show();


    }

@Override
public void initialize(URL url, ResourceBundle rb) {
    try {

        Scanner s = new Scanner(new File("src/View/HelpMenu.txt"));
        while (s.hasNextLine()) {

            	HelpText.appendText(s.nextLine()+"\n");
            }

        
    } catch (FileNotFoundException ex) {
        System.err.println(ex);
    }
}

}
