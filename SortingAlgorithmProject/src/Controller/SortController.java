package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.event.ActionEvent;

public class SortController{
    private int MAX_BAR_HEIGHT = 1000;
	int[] arr;
	Sort sort;
	private FXMLLoader loader;
	private Stage stage;
	private Parent root;
	private Scene scene;
	private String algochoice;
	public SortController(String algochoice) {
		super();
		this.algochoice = algochoice;
	}

    @FXML
    private CategoryAxis xAxis = new CategoryAxis();
    @FXML
    private NumberAxis yAxis = new NumberAxis(0, MAX_BAR_HEIGHT, 0);
	@FXML
	private BarChart<String,Number> Chart = new BarChart<String,Number>(xAxis,yAxis);
    @FXML
	private Button StartButton;
    @FXML
    private TextField TextArray;


	@FXML
	private Button Start;
	@FXML
	void StartButton(ActionEvent e) {
		try {
		Chart.getData().clear();
		Chart.layout();
		XYChart.Series set1 = new XYChart.Series<>();

		String arrstring = TextArray.getText().trim();

		if (arrstring.isEmpty()) {

			if (algochoice.equals("BubbleSort")) {

				sort = new BubbleSort(20);
			}
			else if (algochoice.equals("HeapSort")) {
				sort = new HeapSort(20);
			}
			else if (algochoice.equals("ShellSort")){
				sort = new ShellSort(20);
			}
		}else {
			String[] arrString = arrstring.split(",");
			arr = new int[arrString.length];
			for (int i = 0; i < arrString.length; ++i) {
				arr[i] =Integer.parseInt(arrString[i]);
			}
			if (algochoice.equals("BubbleSort")) {
				sort = new BubbleSort(arr);
			}
			else if (algochoice.equals("HeapSort")) {
				sort = new HeapSort(arr);
			}
			else if (algochoice.equals("ShellSort")){
				sort = new ShellSort(arr);
			}
	    }
		sort.sort();
		arr = sort.displayStart();
		set1 = drawingArray(sort.currentSwitchIndex.get(sort.getCurrentSteps()));
		Chart.getData().setAll(set1);}

		catch(NumberFormatException except) {
			JOptionPane.showMessageDialog(null, "Please only input numbers, separated only by comma.");
		}
	}

	@FXML
	private Button FinishButton;
    @FXML 
    void FinishButtonControl(ActionEvent e) {
    	try {
    	XYChart.Series set_temp = new XYChart.Series<>();
    	arr = sort.displayFinish();
    	Chart.getData().clear();
		Chart.layout();
		set_temp = drawingArray();
    	Chart.getData().setAll(set_temp);
    } catch(NullPointerException except) {
		JOptionPane.showMessageDialog(null,"Please starting an algorithm or input an array");

    	}
    }


    @FXML
    private Button NextButton;
    @FXML
    void NextButtonControl(ActionEvent e) {
    	try {
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
    	catch(NullPointerException except) {
    		JOptionPane.showMessageDialog(null,"Please starting an algorithm or input an array");

    	}
    }
	@FXML
    private Button PreviousButton;
	@FXML
	void PreviousButtonControl(ActionEvent e) {
		try {
	
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
    	catch(NullPointerException except) {
    		JOptionPane.showMessageDialog(null,"Please starting an algorithm or input an array");

    	}
	}
    @FXML
    private Button BackButton;
    @FXML
    void BackBtnControl(ActionEvent event) throws IOException {
		
		
		loader = new FXMLLoader(getClass().getResource("/View/MainScene.fxml"));
		root = loader.load();
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Sorting Visualization");
		stage.setScene(scene);
		
		stage.show();
    }
    @FXML
    private Button HelpSort;
    @FXML
    void HelpSortBtn(ActionEvent event) throws IOException {
		loader = new FXMLLoader(getClass().getResource("/View/HelpMenu.fxml"));
		root = loader.load();
		Stage newstage = new Stage();
		scene = new Scene(root);
		newstage.setTitle("Sorting Visualization");
		newstage.setScene(scene);
		
		newstage.show();

    }
	private static boolean contains_int( int[] arr,int key) {
	    return Arrays.stream(arr).anyMatch(i -> i == key);
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