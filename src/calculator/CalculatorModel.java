package calculator;

import java.awt.Component;
import java.awt.Container;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents the model in the MVC architecture for a calculator application.
 * It handles all the functional operations and calculations.
 * 
 * @author Shamith Nakka
 * @version 1.0.0
 * @since 13 Aug 2023
 *
 */
public class CalculatorModel {
 
	/**
	 * Constructs a new CalculatorModel instance.
	 * This constructor initializes any required data or resources.
	 */
	public CalculatorModel() {}
	
	/**
	 * This function returns all the children of given container.
	 * 
	 * @param container[java.awt.Container] : Container object.
	 * 
	 * @return children[HashSet] : set of component children.
	 */
	public static HashSet<Component> getAllChildren(Container container) {
		
		// Initialization
		HashSet<Component> children = new HashSet<>();
		
		// Retrieving Components
		for(Component child : container.getComponents()) {
			children.add(child);
		}
		
		return children;
	}
	
	/**
	 * This method will check whether the given target string matches the
	 * given regular expression.
	 * 
	 * @param regex : Regular Expression.
	 * @param targetString : String need to checked.
	 * 
	 * @return [boolean]: return true if given string matches given regex.
	 */
	public static boolean matchesRegex(String regex, String targetString) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(targetString);
		return matcher.matches();
	}
	
	/**
	 * This method takes an StringBuilder object and replace it's String value
	 * with given string value.
	 * 
	 * @param stringBuilder : Given StringBuidler object.
	 * @param stringValue : String value needed to be replaced.
	 * 
	 * @return void
	 */
	public static void replaceStringBuilderValue(StringBuilder stringBuilder, String stringValue) {
		stringBuilder.replace(0, stringBuilder.length(), stringValue);
	}
	
	/**
	 * This methods takes a String input and return true if it's possible numeric value else false.
	 * 
	 * @param inputString : Input string that need to be checked.
	 * @return isVaild[boolean] : Returns true if it's numeric value else false
	 */
	public static boolean isNumericValue(String inputString) {
		String regexForNumericValues = "^[0-9]*(\\.[0-9]*)?$";
		boolean isValid = matchesRegex(regexForNumericValues, inputString);
		return isValid;
	}
	
	/**
	 * This method clears the String value of the given StringBuilder.
	 * 
	 * @param argumentStringBuilder : a StringBuilder object containing data for the text field
	 * 
	 * @return void
	 */
	private static void clearTextFeild(StringBuilder argumentStringBuilder) {
		if(argumentStringBuilder.length() != 0)
			argumentStringBuilder.delete(0, argumentStringBuilder.length());
	}
	
	/**
	 * This method removes the last character of the String value for the given StringBuilder.
	 * 
	 * @param argumentStringBuilder : a StringBuilder object containing data for the text field
	 * 
	 * @return void
	 */
	private static void backspaceTextFeild(StringBuilder argumentStringBuilder) {
		if(argumentStringBuilder.length() != 0)
			argumentStringBuilder.deleteCharAt(argumentStringBuilder.length()-1);
	}
	
	/**
	 * This method negates the value of given StringBuilder.
	 * 
	 * @param inputStringBuilder
	 * @return void
	 */
	private static void negateInputString(StringBuilder inputStringBuilder) {
		String output = "Still working on it";
		replaceStringBuilderValue(inputStringBuilder, output);
	}
	
	/**
	 * This method evaluates the current expression.
	 * 
	 * @param inputStringBuilder
	 * @return void
	 */
	private static void evaluateExpression(StringBuilder inputStringBuilder) {
		String output = "Still working on it";
		replaceStringBuilderValue(inputStringBuilder, output);
	}
	
	/**
	 * This method performs the addition operation.
	 * 
	 * @param inputStringBuilder
	 * @return void
	 */
	private static void addition(StringBuilder inputStringBuilder) {
		String output = "Still working on it";
		replaceStringBuilderValue(inputStringBuilder, output);
	}
	
	/**
	 * This method performs the subtraction operation.
	 * 
	 * @param inputStringBuilder
	 * @return void
	 */
	private static void subtraction(StringBuilder inputStringBuilder) {
		String output = "Still working on it";
		replaceStringBuilderValue(inputStringBuilder, output);
	}
	
	/**
	 * This method performs the multiplication operation.
	 * 
	 * @param inputStringBuilder
	 * @return void
	 */
	private static void multiplication(StringBuilder inputStringBuilder) {
		String output = "Still working on it";
		replaceStringBuilderValue(inputStringBuilder, output);
	}
	
	/**
	 * This method performs the division operation.
	 * 
	 * @param inputStringBuilder
	 * @return void
	 */
	private static void division(StringBuilder inputStringBuilder) {
		String output = "Still working on it";
		replaceStringBuilderValue(inputStringBuilder, output);
	}
	
	/**
	 * This method performs the modular division operation.
	 * 
	 * @param inputStringBuilder
	 * @return void
	 */
	private static void modularDivision(StringBuilder inputStringBuilder) {
		String output = "Still working on it";
		replaceStringBuilderValue(inputStringBuilder, output);
	}
	
	/**
	 * This method will handle all the special operation of the calculator.
	 * 
	 * @param operationName : Defines the type of operation.
	 * @param argumentString : User Input data.
	 * 
	 * @return void
	 */
	public void handleOperation(String operationName, StringBuilder argumentStringBuilder) throws Exception {
		switch(operationName) {
			case "C" -> clearTextFeild(argumentStringBuilder);
			case "BS" -> backspaceTextFeild(argumentStringBuilder);
			case "+/-" -> negateInputString(argumentStringBuilder);
			case "+" -> addition(argumentStringBuilder);
			case "-" -> subtraction(argumentStringBuilder);
			case "*" -> multiplication(argumentStringBuilder);
			case "/" -> division(argumentStringBuilder);
			case "%" -> modularDivision(argumentStringBuilder);
			case "=" -> evaluateExpression(argumentStringBuilder);
			default -> new InvaildOperatorException();
		}
	}
}

/**
 * Created for handling the unexpected operationName value.
 * 
 * @author Shamith Nakka
 * @see CalculatorModel::handleOperation()
 *
 */
@SuppressWarnings("serial")
class InvaildOperatorException extends Exception{
	@Override
	public String toString() {
		return "Invaild OperationName value.";
	}
}