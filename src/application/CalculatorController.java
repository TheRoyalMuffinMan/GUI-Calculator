package application;

import java.util.Arrays;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CalculatorController {
	// Top screens
	@FXML public Label answerScreen, operationScreen;
	// All the buttons
	public Button zeroButton, oneButton, twoButton, threeButton, fourButton, fiveButton,
				  sixButton, sevenButton, eightButton, nineButton, tenButton;
	public Button plusButton, minusButton, multiplyButton, divisionButton;
	public Button enterButton, deleteButton;
	
	private String operationStr = "", answerStr = "";
	private int maxLength = 6;
	
	/* The 'enter' button that allows for addition,
	 * subtraction, multiplication, and division
	 * of the user clicked text field (named
	 * initialize) to allow Label to be used.
	 */
	@FXML public void initialize() {
		/* The following buttons adds integers from
		 * zero to nine to the following string. This
		 * string is eventually outputted to the screen.
		 */
		zeroButton.setOnAction(e -> {
			if (maxLength > operationStr.length()) {
				operationStr += " 0";
				operationScreen.setText(operationStr);
			}
		});
		oneButton.setOnAction(e -> {
			if (maxLength > operationStr.length()) {
				operationStr += " 1";
				operationScreen.setText(operationStr);
			}
		});
		twoButton.setOnAction(e -> {
			if (maxLength > operationStr.length()) {
				operationStr += " 2";
				operationScreen.setText(operationStr);
			}
		});
		threeButton.setOnAction(e -> {
			if (maxLength > operationStr.length()) {
				operationStr += " 3";
				operationScreen.setText(operationStr);
			}
		});
		fourButton.setOnAction(e -> {
			if (maxLength > operationStr.length()) {
				operationStr += " 4";
				operationScreen.setText(operationStr);
			}
		});
		fiveButton.setOnAction(e -> {
			if (maxLength > operationStr.length()) {
				operationStr += " 5";
				operationScreen.setText(operationStr);
			}
		});
		sixButton.setOnAction(e -> {
			if (maxLength > operationStr.length()) {
				operationStr += " 6";
				operationScreen.setText(operationStr);
			}
		});
		sevenButton.setOnAction(e -> {
			if (maxLength > operationStr.length()) {
				operationStr += " 7";
				operationScreen.setText(operationStr);
			}
		});
		eightButton.setOnAction(e -> {
			if (maxLength > operationStr.length()) {
				operationStr += " 8";
				operationScreen.setText(operationStr);
			}
		});
		nineButton.setOnAction(e -> {
			if (maxLength > operationStr.length()) {
				operationStr += " 9";
				operationScreen.setText(operationStr);
			}
		});
		
		/*
		 * The following buttons are the operator buttons,
		 * they consist of +, -, *, and /. They are injected
		 * in the string and outputted to the screen.
		 */
		plusButton.setOnAction(e -> {
			if (maxLength > operationStr.length()) {
				operationStr += " +";
				operationScreen.setText(operationStr);
			}
		});
		minusButton.setOnAction(e -> {
			if (maxLength > operationStr.length()) {
				operationStr += " -";
				operationScreen.setText(operationStr);
			}
		});
		multiplyButton.setOnAction(e -> {
			if (maxLength > operationStr.length()) {
				operationStr += " *";
				operationScreen.setText(operationStr);
			}
		});
		divisionButton.setOnAction(e -> {
			if (maxLength > operationStr.length()) {
				operationStr += " /";
				operationScreen.setText(operationStr);
			}
		});
		
		if (maxLength == operationStr.length()) {
			for (int i = 0; i < operationStr.length(); i++) {
				if (operationStr.charAt(i) == '+') {
					answerStr = AddNums(operationStr);
				} else if (operationStr.charAt(i) == '-') {
					answerStr = SubtractNums(operationStr);
				} else if (operationStr.charAt(i) == '*') {
					answerStr = MultiplyNums(operationStr);
				} else if (operationStr.charAt(i) == '/') {
					answerStr = DivideNums(operationStr);
				}
			}
			answerScreen.setText(answerStr);
		}
		
	}
	// Adds the numbers
	private String AddNums(String str) {
		// Replaces all non-numbers with spaces
		str = str.replaceAll("[^-?0-9]+", " ");
		/* trims the string, removes all spaces " " and only
		 * keeps the numbers
		 */
		List<String> arr = Arrays.asList(str.trim().split(" "));
		
		// Converts all the numbers to doubles
		double numOne = Double.parseDouble(arr.get(0));
		double numTwo = Double.parseDouble(arr.get(1));
		return String.valueOf(numOne + numTwo);
	}
	// Subtracts the numbers (Not Working)
	private String SubtractNums(String str) {
		// Replaces all non-numbers with spaces
		str = str.replaceAll("[^-?0-9]+", " ");
		/* trims the string, removes all spaces " " and only
		 * keeps the numbers
		 */
		List<String> arr = Arrays.asList(str.trim().split(" "));
		
		// Converts all the numbers to doubles
		double numOne = Double.parseDouble(arr.get(0));
		double numTwo = Double.parseDouble(arr.get(1));
		return String.valueOf(numOne - numTwo);
	}
	// Multiples the numbers
	private String MultiplyNums(String str) {
		// Replaces all non-numbers with spaces
		str = str.replaceAll("[^-?0-9]+", " ");
		/* trims the string, removes all spaces " " and only
		 * keeps the numbers
		 */
		List<String> arr = Arrays.asList(str.trim().split(" "));
		// Converts all the numbers to doubles
		double numOne = Double.parseDouble(arr.get(0));
		double numTwo = Double.parseDouble(arr.get(1));
		return String.valueOf(numOne * numTwo);
	}
	// Divides the numbers
	private String DivideNums(String str) {
		// Replaces all non-numbers with spaces
		str = str.replaceAll("[^-?0-9]+", " ");
		/* trims the string, removes all spaces " " and only
		 * keeps the numbers
		 */
		List<String> arr = Arrays.asList(str.trim().split(" "));
		// Converts all the numbers to doubles
		double numOne = Double.parseDouble(arr.get(0));
		double numTwo = Double.parseDouble(arr.get(1));
		if (numTwo == 0) {
			return "DNE";
		} else {
			return String.valueOf(numOne / numTwo);
		}
	}
}
