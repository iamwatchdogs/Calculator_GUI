package calculator;

import java.awt.Component;
import java.awt.Container;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created for designing custom lambda function for different Arithmetic Operation.
 *
 * @author Shamith Nakka
 * Demonstrates the usage of {@link #executeOperation()}.
 *
 */
@FunctionalInterface
interface ArithmeticOperation {

	/**
	 *
	 * @param operand1: Subclass object of Number.
	 * @param operand2: Subclass object of Number.
	 *
	 * @return Subclass object of Number class through Reference Widening.
	 */
	public Number operation(Number operand1, Number operand2);
}

/**
 * This class represents the model in the MVC architecture for a calculator application.
 * It handles all the functional operations and calculations.
 *
 * @author Shamith Nakka
 * @version 1.0.0
 * @since 24 Aug 2023
 *
 */
public class CalculatorModel {

	// Collection to store and evaluate expressions.
	final private List<String> expression;
	final private Stack<String> operations;
	final private Stack<String> values;
	private boolean hasDisplayed;

	/**
	 * Constructs a new CalculatorModel instance.
	 * This constructor initializes any required data or resources.
	 */
	public CalculatorModel() {
		this.expression = new LinkedList<>();
		this.operations = new Stack<>();
		this.values = new Stack<>();
		this.hasDisplayed = false;
	}

	/**
	 * This method removes the last character of the String value for the given StringBuilder.
	 *
	 * @param input : a StringBuilder object containing data for the text field.
	 * 
	 */
	private static void backspaceTextFeild(final StringBuilder input) {
		if(input.length() != 0) {
			input.deleteCharAt(input.length()-1);
		}
	}

	/**
	 * This function returns all the children of given container.
	 *
	 * @param container[java.awt.Container] : Container object.
	 *
	 * @return children[HashSet] : set of component children.
	 */
	public static Set<Component> getAllChildren(final Container container) {

		// Initialization
		final Set<Component> children = new HashSet<>();

		// Retrieving Components
		for(Component child : container.getComponents()) {
			children.add(child);
		}

		return children;
	}

	/**
	 * This methods takes a String input and return true if it's possible numeric/decimal value or else false.
	 *
	 * @param inputString : Input string that need to be checked.
	 * @param checkOnlyForDecimalValue : a boolean value that decides the regex checking.
	 *
	 * @return isVaild[boolean] : Returns true if it's numeric value else false.
	 */
	public static boolean isNumericValue(final String inputString, final boolean checkOnlyForDecimalValue) {

		// Regex for both numeric and decimal values
		final String regexForNumericValues = "^(\\-?[0-9]+(\\.[0-9]*)?)?$";
		final String regexForCheckingDecimalPoint = "^(\\-?[0-9]*\\.[0-9]*)?$";

		// Selecting regex for situation
		final String regex = checkOnlyForDecimalValue ? regexForCheckingDecimalPoint : regexForNumericValues;

		// Evaluating and returning boolean value
		return matchesRegex(regex, inputString);
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
	public static boolean matchesRegex(final String regex, final String targetString) {
		final Pattern pattern = Pattern.compile(regex);
		final Matcher matcher = pattern.matcher(targetString);
		return matcher.matches();
	}

	/**
	 * This method negates the value of given StringBuilder.
	 *
	 * @param input : a StringBuilder object containing data for the text field
	 * 
	 */
	private static void negateInputString(final StringBuilder input) {

		// Retrieving output
		final String output = input.toString();

		// Performing operation
		if(output.charAt(0) == '-') {
			replaceStringBuilderValue(input, output.substring(1));
		} else {
			input.insert(0, "-");
		}
	}

	/**
	 * This method takes an StringBuilder object and replace it's String value
	 * with given string value.
	 *
	 * @param stringBuilder : Given StringBuidler object.
	 * @param stringValue : String value needed to be replaced.
	 *
	 */
	public static void replaceStringBuilderValue(final StringBuilder stringBuilder, final String stringValue) {
		stringBuilder.replace(0, stringBuilder.length(), stringValue);
	}

	/**
	 * This method clears the String value of the given StringBuilder.
	 *
	 * @param inputSB : a StringBuilder object containing data for the text field.
	 * @param forTextField : boolean value that represents the origins of the method call.
	 *
	 */
	private void clearTextFeild(final StringBuilder inputSB,final boolean forTextField) {
		if(inputSB.length() != 0)
			inputSB.delete(0, inputSB.length());
		if(forTextField)
			this.expression.clear();
	}

	/**
	 * This method handles the operation based on the precedence.
	 *
	 * @param operator : a String value representing operator.
	 * @param isHighPrecedence : a boolean value to determine level of precedence.
	 *
	 * @throws InvaildOperatorException If any other option is selected that the one defined.
	 * @throws ArithmeticException To handle the "Division by zero" case.
	 */
	private void handleOperationsBasedOnPrecedence(final String operator, final boolean isHighPrecedence)  throws InvaildOperatorException, ArithmeticException {

		// Checking for division operation
		boolean isDivision = false;

		// Handling remaining arithmetic operations
		ArithmeticOperation operation = null;

		if(isHighPrecedence) {
			switch(operator) {
				case "*" -> operation = (operand1, operand2)-> (operand1 instanceof Double || operand2 instanceof Double) ? operand1.doubleValue()*operand2.doubleValue() : operand1.longValue()*operand2.longValue();
				case "/" -> operation = (operand1, operand2)-> (operand1 instanceof Double || operand2 instanceof Double) ? operand1.doubleValue()/operand2.doubleValue() : operand1.longValue()/operand2.longValue();
				case "%" -> operation = (operand1, operand2)-> (operand1 instanceof Double || operand2 instanceof Double) ? operand1.doubleValue()%operand2.doubleValue() : operand1.longValue()%operand2.longValue();
				default -> new InvaildOperatorException();
			}
			isDivision = operator.equals("/");
		} else {
			switch(operator) {
				case "+" -> operation = (operand1, operand2)-> (operand1 instanceof Double || operand2 instanceof Double) ? operand1.doubleValue()+operand2.doubleValue() : operand1.longValue()+operand2.longValue();
				case "-" -> operation = (operand1, operand2)-> (operand1 instanceof Double || operand2 instanceof Double) ? operand1.doubleValue()-operand2.doubleValue() : operand1.longValue()-operand2.longValue();
				default -> new InvaildOperatorException();
			}
		}

		// evaluating operators and pushes it back to stack
		final String result = this.executeOperation(operation, isDivision);
		this.values.push(result);
	}

	/**
	 * This method evaluates the current expression.
	 *
	 * @param inputStringBuilder : a string builder object representing TextField object.
	 *
	 * @throws InvaildOperatorException If any other option is selected that the one defined.
	 * @throws ArithmeticException To handle the "Division by zero" case.
	 */
	private void evaluateExpression(final StringBuilder inputStringBuilder) throws InvaildOperatorException, ArithmeticException {

		// Appending the last value before the evaluation
		this.selecteArithmeticdOperation(inputStringBuilder, null);

		// Traversing the whole expression List and pushing into stack
		for(int index = 0; index < this.expression.size(); index++) {

			// Retrieving value
			final String value = this.expression.get(index);

			// Handling the numeric and basic arithmetic (like add & subtract)
			if(isNumericValue(value, false)) {
				this.values.push(value);
				continue;
			} else if(matchesRegex("^\\+|\\-$",value)) {
				this.operations.push(value);
				continue;
			}

			// Adding the other operand before performing the operation..
			this.values.push(this.expression.get(++index));

			// Performing High precedence operations
			this.handleOperationsBasedOnPrecedence(value, true);

		}

		// handling lower precedence operations
		while(!this.operations.isEmpty()) {

			// Retrieving value
			final String operator = this.operations.pop();

			// Performing lower precedence operations
			this.handleOperationsBasedOnPrecedence(operator, false);
		}

		// Returning final result of expression to TextFeild
		replaceStringBuilderValue(inputStringBuilder, this.values.pop());

		// Clearing
		this.expression.clear();
		this.hasDisplayed = true;
	}

	/**
	 * This function executes the operation provided according the values passed to it.
	 *
	 * @param handleOperation : Lambda function to perform provided operation.
	 * @param isDivisionOperation : Special case for Division operation.
	 *
	 * @return value : a String value representing the end result of the operation.
	 *
	 * @throws ArithmeticException To handle the "Division by zero" case.
	 */
	private String executeOperation(final ArithmeticOperation handleOperation, final boolean isDivisionOperation) throws ArithmeticException {

		// Getting values
		final String operand2 = this.values.pop();
		final String operand1 = this.values.pop();

		// Checking for decimal values
		final boolean operand1HasDecimalValue = isNumericValue(operand1, true);
		final boolean operand2HasDecimalValue = isNumericValue(operand2, true);

		// Checking for zero division exception
		if(isDivisionOperation && matchesRegex("^0*(\\.?0*)?$",operand2)) {
			throw new ArithmeticException("Divide by zero");
		}

		// For decimal values only
		if(operand1HasDecimalValue || operand2HasDecimalValue || isDivisionOperation) {

			// Parsing Values
			final double numericOperand1 = Double.parseDouble(operand1);
			final double numericOperand2 = Double.parseDouble(operand2);

			// Getting the round value
			final double result = Math.round(handleOperation.operation(numericOperand1, numericOperand2).doubleValue() * 100.0)/100.0;

			// Final result
			return (isDivisionOperation && result == Math.round(result)) ? String.valueOf((long)result) : String.valueOf(result);
		}

		// Parsing Values
		final long numericOperand1 = Long.parseLong(operand1);
		final long numericOperand2 = Long.parseLong(operand2);

		// Getting the round value
		final long result = handleOperation.operation(numericOperand1, numericOperand2).longValue();

		// Final result
		return  String.valueOf(result);
	}

	/**
	 * This method will handle all the special operation of the calculator.
	 *
	 * @param operationName : Defines the type of operation.
	 * @param argumentStringBuilder : User Input data.
	 *
	 * @throws InvaildOperatorException If any other option is selected that the one defined.
	 * @throws ArithmeticException To handle the "Division by zero" case.
	 */
	public void handleOperation(final String operationName, final StringBuilder argumentStringBuilder) throws InvaildOperatorException, ArithmeticException {
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

	/**
	 * This method returns true when the evaluated value is displayed or not.
	 *
	 * @return hasDisplayedResult : returns true to denote the evaluated result has been displayed.
	 */
	public boolean hasDisplayedResult() {

		// Assigning false after knowing evaluated result as been displayed
		if(this.hasDisplayed)	{
			this.hasDisplayed = false;
			return true;
		}

		return this.hasDisplayed;
	}

	/**
	 * Pushes the input TextField with operation into respective stacks.
	 *
	 * @param inputStringBuilder : StringBuilder object of TextFeild
	 * @param operator : String value of Operator.
	 *
	 */
	private void selecteArithmeticdOperation(final StringBuilder inputStringBuilder, final String operator) {

		// Retrieving and adding values into the expression
		final String output = inputStringBuilder.toString();
		this.expression.add(output);
		if(operator != null) {
			this.expression.add(operator);
		}

		// Clearing TextFeild
		clearTextFeild(inputStringBuilder, false);
	}
}

/**
 * Created for handling the unexpected operationName value.
 *
 * @author Shamith Nakka
 * Demonstrates the usage of {@link #handleOperation()}.
 *
 */
@SuppressWarnings("serial")
class InvaildOperatorException extends Exception {

	@Override
	public String toString() {
		return "Invaild OperationName value.";
	}
}