package application;


import java.net.URL;
import java.util.ResourceBundle;

import SortingAlgorithm.BubbleSort;
import SortingAlgorithm.HeapSort;
import SortingAlgorithm.ShellSort;
import SortingAlgorithm.ShellSort.*;
import SortingAlgorithm.Sort;

import java.util.ArrayList;
import java.util.Collection;
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
	Collection<XYChart.Data<String,Integer>> samples = new ArrayList<>(8);
	int[] arr;
	int currentstep = 1;
	Sort sort;


    @FXML
    private CategoryAxis xAxis = new CategoryAxis();

    @FXML
    private NumberAxis yAxis = new NumberAxis(0, MAX_BAR_HEIGHT, 0);
	@FXML
	private BarChart<String,Number> Chart = new BarChart<String,Number>(xAxis,yAxis);
	;
	@FXML
	private ChoiceBox<String> AlgoChoice;
    @FXML
	private Button StartButton;

    @FXML
    private TextField TextArray;

	@FXML
	private Button RandomButton;
    
	@FXML
	private Button Start;
	@FXML
	void StartButton(ActionEvent e) {
		Chart.getData().clear();
		Chart.layout();
		XYChart.Series set1 = new XYChart.Series<>();
		String AlgoChoiceBox = AlgoChoice.getValue(); 

		int n = 8;
	String arrstring = TextArray.getText().trim();
	if  (arrstring.isEmpty()) {

		Random rand = new Random();
		arr = new int[n];
		for (int i = 0; i < n; i++) {
		arr[i] = rand.nextInt(20);
		set1.getData().add(new XYChart.Data<String,Number>(String.valueOf(arr[i]),arr[i]));
		}
	}
	else {
		String[] arrString = arrstring.split(",");
		arr = new int[arrString.length];
		for (int i = 0; i < arrString.length; ++i) {
			arr[i] =Integer.parseInt(arrString[i]);
		}
	}


	if (AlgoChoiceBox.equals("Bubble Sort")) {
		sort = new BubbleSort(arr);
	}
	else if (AlgoChoiceBox.equals("Heap Sort")) {
		sort = new HeapSort(arr);
	}
	else if (AlgoChoiceBox.equals("Shell Sort")){
		sort = new ShellSort(arr);
	}
	for (int i = 0; i < arr.length; ++i) {
		set1.getData().add(new XYChart.Data<String,Number>(String.valueOf(arr[i]),arr[i]));	}

	Chart.getData().setAll(set1);
	sort.sort();

	}





    @FXML
    private Button NextButton;
    @FXML
    void NextButtonControl(ActionEvent e) {

		XYChart.Series set_temp = new XYChart.Series<>();

    	Chart.getData().clear();
		Chart.layout();
		arr = sort.arrayOfSteps.get(currentstep +1);
    	for (int i = 0; i < arr.length; ++i) {
    		set_temp.getData().add(new XYChart.Data<String,Number>(String.valueOf(arr[i]),arr[i]));	}
    		Chart.getData().setAll(set_temp);
    		currentstep += 1;
    		System.out.println(currentstep);


    }
	@FXML
    private Button PreviousButton;
	@FXML
	    void PreviousButtonControl(ActionEvent e) {

			XYChart.Series set_temp = new XYChart.Series<>();
	    	

	    	arr = sort.arrayOfSteps.get(currentstep);
	    	Chart.getData().clear();
			Chart.layout();
	    	for (int i = 0; i < arr.length; ++i) {
	    		set_temp.getData().add(new XYChart.Data<String,Number>(String.valueOf(arr[i]),arr[i]));	}
	    		Chart.getData().setAll(set_temp);
	    		currentstep -= 1;
	    		System.out.println(currentstep);




	    }

	private String[] algo = {"Heap Sort","Bubble Sort", "Shell Sort"};
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		AlgoChoice.getItems().addAll(algo);
		/*XYChart.Series set1 = new XYChart.Series<>();
		set1.getData().add(new XYChart.Data("James",5000));*/
		//Chart.setAnimated(false);




	}

}
