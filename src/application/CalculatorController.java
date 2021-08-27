package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.List;
import java.util.ArrayList;

/**
 * <h1> Calculator Controller Class <h1>
 * The following class controls all the buttons and screens in the GUI Calculator. It includes
 * public variables for the screens and buttons, that correlate to the "id" names in Calculator.fxml.
 * Along with 3 instance variables that represent the attributes of the expression and its answer.
 * @author Andy
 * @since 8/27/2021
 */

public class CalculatorController {
	// Output screens on the GUI
	@FXML public Label answerScreen, expressionScreen;
	// All the buttons on the GUI
	public Button zeroButton, oneButton, twoButton, threeButton, fourButton, fiveButton,
				  sixButton, sevenButton, eightButton, nineButton, tenButton;
	public Button plusButton, minusButton, multiplyButton, divisionButton;
	public Button enterButton, clearButton;
	
	/* 
	 * Attributes of the calculator, inputs represents the inputed expression,
	 * expression is the string that is shown based on the input list, and
	 * current represents the tracking index of the list.
	 */
	private List<String> inputs = new ArrayList<>();
	private String expression = "";
	private int current = 0;
	
	/**
	 * The initialize() method is called to implement the controller once the root 
	 * has been initialized and updates the GUI of the calculator interface as the user
	 * clicks the buttons on it.
	 */
	@FXML public void initialize() {
		/* 
		 * The following buttons adds integers from zero to nine to
		 * a string based List. This list is kept track of and every added
		 * element is shown on the expression screen within in the insertNumbers()
		 * method.
		 */
		zeroButton.setOnAction(e -> insertNumber("0"));
		oneButton.setOnAction(e -> insertNumber("1"));
		twoButton.setOnAction(e -> insertNumber("2"));
		threeButton.setOnAction(e -> insertNumber("3"));
		fourButton.setOnAction(e -> insertNumber("4"));
		fiveButton.setOnAction(e -> insertNumber("5"));
		sixButton.setOnAction(e -> insertNumber("6"));
		sevenButton.setOnAction(e -> insertNumber("7"));
		eightButton.setOnAction(e -> insertNumber("8"));
		nineButton.setOnAction(e -> insertNumber("9"));
		/*
		 * The following buttons are the operator buttons,
		 * they consist of +, -, *, and /. They are injected
		 * in the string based List and shown on the screen using
		 * insertOperator method.
		 */
		plusButton.setOnAction(e -> insertOperator("+"));
		minusButton.setOnAction(e -> insertOperator("-"));
		multiplyButton.setOnAction(e -> insertOperator("*"));
		divisionButton.setOnAction(e -> insertOperator("/"));
		/*
		 * The clearButton resets the list to a length of 0 and
		 * all the attributes attached the list. Once that is completed,
		 * an empty string that represents the expression is shown on the screen
		 * along side the answer screen.
		 */
		clearButton.setOnAction(e -> {
			inputs.clear(); current = 0; expression = "";
			expressionScreen.setText(expression); answerScreen.setText("");
		});
		/*
		 * The enterButton is the output button, it solves the inputed expression
		 * from the 0 -> 9 buttons and outputs the answer. It first validates that
		 * the expression is valid. The two statements between represent what a valid
		 * and invalid expression would be:
		 * Invalid: 9 + 2 // or 13214 / or ***
		 * Valid: 9 + 2 * 3 / 4 / 1 / 3 + 4 * 2 or 13214 / 3322
		 * If the expression is invalid, the screen is reset and the list is cleared, along
		 * with the tracking pointer being set to zero. If the expression is valid, the algorithm
		 * below follows PEMDAS and outputs the expression's answer once it is solved. 
		 */
		enterButton.setOnAction(e -> {
			boolean isValid = false;
			for (int i = 0; i < inputs.size(); i++) {
				/* 
				 * Checks if the current first character in the string based list of the 
				 * given index is operator or not.
				 */
				if (inputs.get(i).charAt(0) == '+' || inputs.get(i).charAt(0) == '-' ||
				    inputs.get(i).charAt(0) == '*' || inputs.get(i).charAt(0) == '/') {
					// System.out.println(inputs); <--- Used for debugging
					/*
					 * If the expression is / or 2 /, this statement is consider true and the 
					 * expression isn't valid.
					 */
					System.out.println(i);
					if (inputs.get(i + 1).equals("") || i == 0) {
						expressionScreen.setText("Error");
						inputs.clear(); current = 0;
						expression = "";
					/* 
					 * If the expression is setup like this 2 / / or 2 / / or 2 / / / 2, its consider
					 * true and the expression isn't valid.
					 */
					} else if ((inputs.get(i - 1).charAt(0) == '+' || inputs.get(i - 1).charAt(0) == '-' ||
						inputs.get(i - 1).charAt(0) == '*' || inputs.get(i - 1).charAt(0) == '/') ||
					    (inputs.get(i + 1).charAt(0) == '+' || inputs.get(i + 1).charAt(0) == '-' ||
					    inputs.get(i + 1).charAt(0) == '*' || inputs.get(i + 1).charAt(0) == '/')) {
						expressionScreen.setText("Error");
						inputs.clear(); current = 0; expression = "";
					} else {
						isValid = true;
					}
				}
			}
			if (isValid && inputs.size() != 0) {
				/*
				 * Checks for all the division operators in the list and computes their respective
				 * operands first.
				 */
				while (inputs.indexOf("/") != -1) {
					int index = inputs.indexOf("/");
					double temp = divideNums(inputs, index);
					inputs.remove(index + 1);
					inputs.set(index - 1, "" + temp);
					inputs.remove(index);
				}
				/*
				 * Checks for all the multiplication operators in the list and computes their respective
				 * operands second.
				 */
				while (inputs.indexOf("*") != -1) {
					int index = inputs.indexOf("*");
					double temp = multiplyNums(inputs, index);
					inputs.remove(index + 1);
					inputs.set(index - 1, "" + temp);
					inputs.remove(index);
				}
				/*
				 * Checks for all the subtraction operators in the list and computes their respective
				 * operands third.
				 */
				while (inputs.indexOf("-") != -1) {
					int index = inputs.indexOf("-");
					double temp = subtractNums(inputs, index);
					inputs.remove(index + 1);
					inputs.set(index - 1, "" + temp);
					inputs.remove(index);
				}
				/*
				 * Checks for all the addition operators in the list and computes their respective
				 * operands last.
				 */
				while (inputs.indexOf("+") != -1) {
					int index = inputs.indexOf("+");
					double temp = addNums(inputs, index);
					inputs.remove(index + 1);
					inputs.set(index - 1, "" + temp);
					inputs.remove(index);
				}
				/*
				 * List will shrink to a size of 1, meaning the first index will be the answer in 
				 * the list and will be displayed on the answer screen. After that the list is cleared
				 * along with the the tracking pointer and the expression string.
				 */
				answerScreen.setText("" + inputs.get(0));
				inputs.clear(); current = 0;
				expression = "";
			}
		});
		
	}
	/**
	 * The insertNumber() method is a helper method that adds a number to the current
	 * string that the tracking pointer is currently on and continues doing this until
	 * a operator is added to the list.
	 * @param String representation of the number to be added to the current string
	 * in the list
	 */
	private void insertNumber(String num) {
		if (inputs.size() == 0) {
			expressionScreen.setText(""); answerScreen.setText("");
			inputs.add(num);
		} else {
			inputs.set(current, inputs.get(current) + num);
		}
		expression += num;
		expressionScreen.setText(expression);
	}
	/**
	 * The insertOperator() method is a helper method that adds a operator to the string
	 * based list and updates the tracking pointer to the current empty string (next
	 * possible operand).
	 * @param String representation of the operator to be added to the current string
	 * in the list.
	 */
	private void insertOperator(String operator) {
		if (inputs.size() == 0) {
			expressionScreen.setText(""); answerScreen.setText("");
		}
		inputs.add(operator);
		inputs.add(""); current = inputs.indexOf("");
		expression += operator; expressionScreen.setText(expression);
	}
	/**
	 * The addNums() is a helper method that adds the two operands to the left and right of the 
	 * current operator, then returns that result as a double.
	 * @param List of user inputs of the operands and operators from the buttons.
	 * @param The index of the current operator.
	 * @returns The evaluation between the operator and its operands.
	 */
	private double addNums(List<String> inputs, int indexOperator) {
		return Double.parseDouble(inputs.get(indexOperator - 1)) + Double.parseDouble(inputs.get(indexOperator + 1));
	}
	/**
	 * The subtractNums() is a helper method that subtracts the two operands to the left and right of the 
	 * current operator, then returns that result as a double.
	 * @param List of user inputs of the operands and operators from the buttons.
	 * @param The index of the current operator.
	 * @returns The evaluation between the operator and its operands.
	 */
	private double subtractNums(List<String> inputs, int indexOperator) {
		return Double.parseDouble(inputs.get(indexOperator - 1)) - Double.parseDouble(inputs.get(indexOperator + 1));
	}
	/**
	 * The multiplyNums() is a helper method that multiplies the two operands to the left and right of the 
	 * current operator, then returns that result as a double.
	 * @param List of user inputs of the operands and operators from the buttons.
	 * @param The index of the current operator.
	 * @returns The evaluation between the operator and its operands.
	 */
	private double multiplyNums(List<String> inputs, int indexOperator) {
		return Double.parseDouble(inputs.get(indexOperator - 1)) * Double.parseDouble(inputs.get(indexOperator + 1));
	}
	/**
	 * The divideNums() is a helper method that divides the two operands to the left and right of the 
	 * current operator, then returns that result as a double.
	 * @param List of user inputs of the operands and operators from the buttons.
	 * @param The index of the current operator.
	 * @returns The evaluation between the operator and its operands.
	 */
	private double divideNums(List<String> inputs, int indexOperator) {
		return Double.parseDouble(inputs.get(indexOperator - 1)) / Double.parseDouble(inputs.get(indexOperator + 1));
	}
}
