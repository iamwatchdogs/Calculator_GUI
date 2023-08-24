package calculator;

import java.awt.Component;
import java.awt.Container;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.LinkedList;
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
	private List<String> expression;
	private Stack<String> operations;
	private Stack<String> values;
	private boolean hasDisplayedResult;
	
	/**
	 * Constructs a new CalculatorModel instance.
	 * This constructor initializes any required data or resources.
	 */
	public CalculatorModel() {
		this.expression = new LinkedList<>();
		this.operations = new Stack<>();
		this.values = new Stack<>();
		this.hasDisplayedResult = false;
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
	private void clearTextFeild(StringBuilder argumentStringBuilder, boolean forTextField) {
		if(argumentStringBuilder.length() != 0)
			argumentStringBuilder.delete(0, argumentStringBuilder.length());
		if(forTextField)	this.expression.clear();
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
	
	public boolean hasDisplayedResult() {
		if(this.hasDisplayedResult)	{
			this.hasDisplayedResult = false;
			return true;
		}
		return this.hasDisplayedResult;
	}	
	
	/**
	 * This function executes the operation provided according the values passed to it.
	 * 
	 * @param handleOperation : Lambda function to perform provided operation.
	 * @return value : a String value representing the end result of the operation.
	 */
	private String executeOperation(ArithmeticOperation handleOperation) {
		
		// Getting values
		String operand2 = this.values.pop();
		String operand1 = this.values.pop();
		
		// Checking for decimal values
		boolean operand1HasDecimalValue = hasDecimalValue(operand1);
		boolean operand2HasDecimalValue = hasDecimalValue(operand2);
		
		if(operand1HasDecimalValue || operand2HasDecimalValue) {
			
			// Parsing Values
			double numericOperand1 = Double.parseDouble(operand1);
			double numericOperand2 = Double.parseDouble(operand2);
			
			// Getting the round value
			double result = Math.round(handleOperation.operation(numericOperand1, numericOperand2).doubleValue() * 100.0)/100.0;
			
			// Final result
			return String.valueOf(result);
		}

		// Parsing Values
		long numericOperand1 = Long.parseLong(operand1);
		long numericOperand2 = Long.parseLong(operand2);
		
		// Getting the round value
		long result = handleOperation.operation(numericOperand1, numericOperand2).longValue();
		
		// Final result
		return  String.valueOf(result);
	}
	
	/**
	 * Pushes the input TextField with operation into respective stacks.
	 * 
	 * @param inputStringBuilder : StringBuilder object of TextFeild
	 * @param operator : String value of Operator
	 * @return void
	 */
	private void selecteArithmeticdOperation(StringBuilder inputStringBuilder, String operator) {
		String output = inputStringBuilder.toString();
		this.expression.add(output);
		if(operator != null)
			this.expression.add(operator);
		clearTextFeild(inputStringBuilder, false);
	}
	
	/**
	 * This method evaluates the current expression.
	 * 
	 * @param inputStringBuilder
	 * @return void
	 */
	private void evaluateExpression(StringBuilder inputStringBuilder) throws InvaildOperatorException {
		
		// Appending the last value before the evaluation
		this.selecteArithmeticdOperation(inputStringBuilder, null);
		
		for(int index = 0; index < this.expression.size(); index++) {
			
			String value = this.expression.get(index);
			
			if(isNumericValue(value)) {
				this.values.push(value);
				continue;
			} else if(matchesRegex("^\\+|\\-$",value)) {
				this.operations.push(value);
				continue;
			}
			
			String operator = value;
			value = this.expression.get(++index);
			
			this.values.push(value);
			
			ArithmeticOperation operation = null;
			switch(operator) {
				case "*" -> operation = (operand1, operand2)-> (operand1 instanceof Double || operand2 instanceof Double) ? operand1.doubleValue()*operand2.doubleValue() : operand1.longValue()*operand2.longValue();
				case "/" -> operation = (operand1, operand2)-> (operand1 instanceof Double || operand2 instanceof Double) ? operand1.doubleValue()/operand2.doubleValue() : operand1.longValue()/operand2.longValue();
				case "%" -> operation = (operand1, operand2)-> (operand1 instanceof Double || operand2 instanceof Double) ? operand1.doubleValue()%operand2.doubleValue() : operand1.longValue()%operand2.longValue();
				default -> new InvaildOperatorException();
			}
			
			String result = this.executeOperation(operation);
			
			this.values.push(result);
		}
		
		while(!this.operations.isEmpty()) {
			String operator = this.operations.pop();
			ArithmeticOperation operation = null;
			switch(operator) {
				case "+" -> operation = (operand1, operand2)-> (operand1 instanceof Double || operand2 instanceof Double) ? operand1.doubleValue()+operand2.doubleValue() : operand1.longValue()+operand2.longValue();
				case "-" -> operation = (operand1, operand2)-> (operand1 instanceof Double || operand2 instanceof Double) ? operand1.doubleValue()-operand2.doubleValue() : operand1.longValue()-operand2.longValue();
				default -> new InvaildOperatorException();
			}
			String result = this.executeOperation(operation);
			this.values.push(result);
		}
		
		replaceStringBuilderValue(inputStringBuilder, this.values.pop());
		this.expression.clear();
		this.hasDisplayedResult = true;
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
			case "C" -> clearTextFeild(argumentStringBuilder, true);
			case "BS" -> backspaceTextFeild(argumentStringBuilder);
			case "+/-" -> negateInputString(argumentStringBuilder);
			case "+" -> this.selecteArithmeticdOperation(argumentStringBuilder, operationName);
			case "-" -> this.selecteArithmeticdOperation(argumentStringBuilder, operationName);
			case "*" -> this.selecteArithmeticdOperation(argumentStringBuilder, operationName);
			case "/" -> this.selecteArithmeticdOperation(argumentStringBuilder, operationName);
			case "%" -> this.selecteArithmeticdOperation(argumentStringBuilder, operationName);
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
interface ArithmeticOperation {
	public Number operation(Number operand1, Number operand2);
}