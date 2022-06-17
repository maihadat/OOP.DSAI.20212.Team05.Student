package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class SortController implements Initializable {
	@FXML
	private Button RandomButton;
	private Button StartButton;
	private ChoiceBox AlgoChoice;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	    AlgoChoice.getItems().removeAll(AlgoChoice.getItems());
	    AlgoChoice.getItems().addAll("Bubble Sort", "Shell Sort", "Heap Sort");
	}

}
