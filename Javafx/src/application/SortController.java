package application;

import java.net.URL;
import java.util.ResourceBundle;

import SortingAlgorithm.BubbleSort;
import SortingAlgorithm.HeapSort;
import SortingAlgorithm.ShellSort;
import SortingAlgorithm.ShellSort.*;
import SortingAlgorithm.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.event.ActionEvent;

public class SortController implements Initializable {
    private int MAX_BAR_HEIGHT = 50;
    private ObservableList<Data<String, Number>> bars;
	Collection<XYChart.Data<String,Integer>> samples = new ArrayList<>(8);
	int[] arr;
	Sort sort;


    @FXML
    private CategoryAxis xAxis = new CategoryAxis();
    @FXML
    private NumberAxis yAxis = new NumberAxis(0, MAX_BAR_HEIGHT, 0);
	@FXML
	private BarChart<String,Number> Chart = new BarChart<String,Number>(xAxis,yAxis);
	@FXML
	private ChoiceBox<String> AlgoChoice;
    @FXML
	private Button StartButton;
    @FXML
    private TextField TextArray;


	@FXML
	private Button Start;
	@FXML
	void StartButton(ActionEvent e) {
		Chart.getData().clear();
		Chart.layout();
		XYChart.Series set1 = new XYChart.Series<>();
		String AlgoChoiceBox = AlgoChoice.getValue(); 

		String arrstring = TextArray.getText().trim();
		if (arrstring.isEmpty()) {
			if (AlgoChoiceBox.equals("Bubble Sort")) {
				sort = new BubbleSort(20);
			}
			else if (AlgoChoiceBox.equals("Heap Sort")) {
				sort = new HeapSort(20);
			}
			else if (AlgoChoiceBox.equals("Shell Sort")){
				sort = new ShellSort(20);
			}
		}else {
			String[] arrString = arrstring.split(",");
			arr = new int[arrString.length];
			for (int i = 0; i < arrString.length; ++i) {
				arr[i] =Integer.parseInt(arrString[i]);
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
	    }
		sort.sort();
		arr = sort.displayStart();
		set1 = drawingArray(sort.currentSwitchIndex.get(sort.getCurrentSteps()));
		Chart.getData().setAll(set1);
	}

	@FXML
	private Button FinishButton;
    @FXML 
    void FinishButtonControl(ActionEvent e) {
    	XYChart.Series set_temp = new XYChart.Series<>();
    	arr = sort.displayFinish();
    	Chart.getData().clear();
		Chart.layout();
		set_temp = drawingArray();
    	Chart.getData().setAll(set_temp);
    }
    
    @FXML
    private Button NextButton;
    @FXML
    void NextButtonControl(ActionEvent e) {
    	XYChart.Series set_temp = new XYChart.Series<>();
    	if(sort.getCurrentSteps() < sort.getNumberSteps()-1) {
    		Chart.getData().clear();
    		Chart.layout();
        	sort.nextStep();
    		arr = sort.arrayOfSteps.get(sort.getCurrentSteps());
    		set_temp = drawingArray(sort.currentSwitchIndex.get(sort.getCurrentSteps()));
    		Chart.getData().setAll(set_temp);
    	}else if(sort.getCurrentSteps() == sort.getNumberSteps()-1 ){
    		Chart.getData().clear();
    		Chart.layout();
        	arr = sort.displayFinish();
        	set_temp = drawingArray();
        	Chart.getData().setAll(set_temp);
    	}

    }
	@FXML
    private Button PreviousButton;
	@FXML
	void PreviousButtonControl(ActionEvent e) {
		XYChart.Series set_temp = new XYChart.Series<>();
    	if(sort.getCurrentSteps() > 0) {
    		Chart.getData().clear();
    		Chart.layout();
    		sort.preStep();
    		arr = sort.arrayOfSteps.get(sort.getCurrentSteps());
    		set_temp = drawingArray(sort.currentSwitchIndex.get(sort.getCurrentSteps()));
    		Chart.getData().setAll(set_temp);
    	}
	}

	private String[] algo = {"Heap Sort","Bubble Sort", "Shell Sort"};
	private static boolean contains_int( int[] arr,int key) {
	    return Arrays.stream(arr).anyMatch(i -> i == key);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		AlgoChoice.getItems().addAll(algo);
		/*XYChart.Series set1 = new XYChart.Series<>();
		set1.getData().add(new XYChart.Data("James",5000));*/
		//Chart.setAnimated(false);
	}
	private XYChart.Series drawingArray(int[] switchIndex) {
		XYChart.Series set_temp = new XYChart.Series<>();
		for (int i = 0; i < arr.length; ++i) {
	    	final int j =i;
	    	XYChart.Data<String, Number> data = new XYChart.Data<String,Number>(String.valueOf(arr[i]),arr[i]);
	    	data.nodeProperty().addListener(new ChangeListener<Node>() {
	    		@Override
	            public void changed(ObservableValue<? extends Node> ov, Node oldNode, Node newNode) {
	    			if (Arrays.stream(switchIndex).anyMatch(i -> i == j) == true) {
	                	newNode.setStyle("-fx-bar-fill: black;");
	                }
	                else {
	                    newNode.setStyle("-fx-bar-fill: red;");
	                }
	            }
	        });	
	    	set_temp.getData().add(data);
		}
		return set_temp;
	}
	private XYChart.Series drawingArray() {
		XYChart.Series set_temp = new XYChart.Series<>();
		for (int i = 0; i < arr.length; ++i) {
    		XYChart.Data<String, Number> data = new XYChart.Data<String,Number>(String.valueOf(arr[i]),arr[i]);
    		data.nodeProperty().addListener(new ChangeListener<Node>() {
                @Override
                public void changed(ObservableValue<? extends Node> ov, Node oldNode, Node newNode) {
                	newNode.setStyle("-fx-bar-fill: red;");
                }
            });	
    		set_temp.getData().add(data);	
    	}
		return set_temp;
	}
}
