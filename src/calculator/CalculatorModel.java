package calculator;

import java.awt.Component;
import java.awt.Container;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Stack;

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
	
	// Private members
	private Stack<String> previousValues;
	private Stack<String> operations;
	
	/**
	 * Constructs a new CalculatorModel instance.
	 * This constructor initializes any required data or resources.
	 */
	public CalculatorModel() {
		this.previousValues = new Stack<>();
		this.operations = new Stack<>();
	}
	
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
		String regexForNumericValues = "^\\-?[0-9]*(\\.[0-9]*)?$";
		boolean isValid = matchesRegex(regexForNumericValues, inputString);
		return isValid;
	}
	
	/**
	 * This methods take a String value and return true if the String has decimal value or not.
	 * 
	 * @param inputString
	 * @return
	 */
	public static boolean hasDecimalValue(String inputString) {
		String regexForCheckingDecimalPoint = "^[0-9]*\\.[0-9]*$";
		boolean isValid = matchesRegex(regexForCheckingDecimalPoint, inputString);
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
		String output = inputStringBuilder.toString();
		if(output.charAt(0) == '-') {
			replaceStringBuilderValue(inputStringBuilder, output.substring(1));
		} else {
			inputStringBuilder.insert(0, "-");
		}
	}
	
	/**
	 * This function executes the operation provided according the values passed to it.
	 * 
	 * @param operand1 : a String value representing an numeric value.
	 * @param operand2 : another String value representing an numeric value.
	 * @param handleOperation : Lambda function to perform provided operation.
	 * @return value : a String value representing the end result of the operation.
	 */
	private static String executeOperation(String operand1, String operand2, ArithmeticOperation<Number> handleOperation) {
		
		String value = null;
		
		// Checking for decimal values
		boolean operand1HasDecimalValue = hasDecimalValue(operand1);
		boolean operand2HasDecimalValue = hasDecimalValue(operand2);
		
		if(operand1HasDecimalValue || operand2HasDecimalValue) {
			
			// Parsing Values
			double numericOperand1 = Double.parseDouble(operand1);
			double numericOperand2 = Double.parseDouble(operand2);
			
			// Getting the round value
			double result = Math.round((Double)handleOperation.operation(numericOperand1, numericOperand2) * 100.0)/100.0;
			
			// Final result
			value = String.valueOf(result);
		} else {
			
			// Parsing Values
			long numericOperand1 = Long.parseLong(operand1);
			long numericOperand2 = Long.parseLong(operand2);
			
			// Getting the round value
			long result = (Long)handleOperation.operation(numericOperand1, numericOperand2);
			
			// Final result
			value = String.valueOf(result);
		}
		
		return value;
	}
	
	/**
	 * This method performs the addition operation.
	 * 
	 * @param inputStringBuilder
	 * @return void
	 */
	private void addition(StringBuilder inputStringBuilder) {
		String output = inputStringBuilder.toString();
		this.previousValues.push(output);
		this.operations.push("+");
		clearTextFeild(inputStringBuilder);
	}
	
	/**
	 * This method performs the subtraction operation.
	 * 
	 * @param inputStringBuilder
	 * @return void
	 */
	private void subtraction(StringBuilder inputStringBuilder) {
		String output = inputStringBuilder.toString();
		this.previousValues.push(output);
		this.operations.push("-");
		clearTextFeild(inputStringBuilder);
	}
	
	/**
	 * This method performs the multiplication operation.
	 * 
	 * @param inputStringBuilder
	 * @return void
	 */
	private void multiplication(StringBuilder inputStringBuilder) {
		
		// Getting values
		String output = inputStringBuilder.toString();
		String previousValue = this.previousValues.pop();
		
		// Creating a lambda expression
		ArithmeticOperation<Number> multiply = (operand1, operand2) -> 
			(operand1 instanceof Double || operand2 instanceof Double)
	        	? operand1.doubleValue() * operand2.doubleValue()
	        	: operand1.longValue() * operand2.longValue();
	    
		// Getting end result
	    String value = executeOperation(previousValue, output, multiply); 
		
	    // Pushes back to Stack and cleans TextField
		this.previousValues.push(value);
		clearTextFeild(inputStringBuilder);
	}
	
	/**
	 * This method performs the division operation.
	 * 
	 * @param inputStringBuilder
	 * @return void
	 */
	private void division(StringBuilder inputStringBuilder) {

		// Getting values
		String output = inputStringBuilder.toString();
		String previousValue = this.previousValues.pop();
		
		// Creating a lambda expression
		ArithmeticOperation<Number> divide = (operand1, operand2) -> operand1.doubleValue()/operand2.doubleValue();
	    
		// Getting end result
	    String value = executeOperation(previousValue, output, divide); 
		
	    // Pushes back to Stack and cleans TextField
		this.previousValues.push(value);
		clearTextFeild(inputStringBuilder);
	}
	
	/**
	 * This method performs the modular division operation.
	 * 
	 * @param inputStringBuilder
	 * @return void
	 */
	private void modularDivision(StringBuilder inputStringBuilder) {
		
		// Getting values
		String output = inputStringBuilder.toString();
		String previousValue = this.previousValues.pop();
		
		// Creating a lambda expression
		ArithmeticOperation<Number> mod = (operand1, operand2) -> 
			(operand1 instanceof Double || operand2 instanceof Double)
	        	? operand1.doubleValue() % operand2.doubleValue()
	        	: operand1.longValue() % operand2.longValue();
	    
		// Getting end result
	    String value = executeOperation(previousValue, output, mod); 
		
	    // Pushes back to Stack and cleans TextField
		this.previousValues.push(value);
		clearTextFeild(inputStringBuilder);
	}
	
	/**
	 * This method evaluates the current expression.
	 * 
	 * @param inputStringBuilder
	 * @return void
	 */
	private void evaluateExpression(StringBuilder inputStringBuilder) {
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
		if(argumentStringBuilder.length() == 0)		return;
		switch(operationName) {
			case "C" -> clearTextFeild(argumentStringBuilder);
			case "BS" -> backspaceTextFeild(argumentStringBuilder);
			case "+/-" -> negateInputString(argumentStringBuilder);
			case "+" -> this.addition(argumentStringBuilder);
			case "-" -> this.subtraction(argumentStringBuilder);
			case "*" -> this.multiplication(argumentStringBuilder);
			case "/" -> this.division(argumentStringBuilder);
			case "%" -> this.modularDivision(argumentStringBuilder);
			case "=" -> this.evaluateExpression(argumentStringBuilder);
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

/**
 * Created for the usage of an generic lambda function.
 * 
 * @author Shamith Nakka
 * @see CalculatorModel::executeOperation()
 * 
 */
@FunctionalInterface
interface ArithmeticOperation <T extends Number> {
	public T operation(T operand1, T operand2);
}