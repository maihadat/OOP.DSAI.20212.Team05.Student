package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.collections.*;
import javafx.event.ActionEvent;

public class SortController implements Initializable {
    private int MAX_BAR_HEIGHT = 50;
    private ObservableList<Data<String, Number>> bars;




    @FXML
    private CategoryAxis xAxis = new CategoryAxis();

    @FXML
    private NumberAxis yAxis = new NumberAxis(0, MAX_BAR_HEIGHT, 0);
	@FXML
	private BarChart<Number, String> Chart = new BarChart<Number,String>(yAxis,xAxis);
	;

    @FXML
	private Button StartButton;
	@FXML
    private Button PreviousButton;
    @FXML
    private TextField TextArray;
    @FXML
    private Button NextButton;
	@FXML
	private Button RandomButton;
    
	@FXML
	private Button Start;
	public void StartButton(ActionEvent e) {
		int n = 8;
		int arr[];
	
	String arrstring = TextArray.getText().trim();
	if  (arrstring.isEmpty()) {

		Random rand = new Random();
		arr = new int[n];
		for (int i = 0; i < n; i++) {
		arr[i] = rand.nextInt(80);
	}
	}
	else {
		String[] arrString = arrstring.split(",");
		arr = new int[arrString.length];
		for (int i = 0; i < arrString.length; ++i) {
			arr[i] =Integer.parseInt(arrString[i]);
		}
	}
	
    XYChart.Series<Number,String> series= new Series<Number, String>();
	for (int i = 0; i < arr.length; ++i) {
		series.getData().add(new Data<Number, String>(arr[i],String.valueOf(arr[i])));	}
	
	Chart.getData().addAll(series);

	}
	@FXML
	private ChoiceBox<String> AlgoChoice;
	private String[] algo = {"Heap Sort","Bubble Sort", "Shell Sort"};
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		AlgoChoice.getItems().addAll(algo);
	}

}
