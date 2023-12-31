package calculator;

import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This class represents the controller in the MVC architecture for a calculator application.
 * It handles all the listener event from view and perform operation using model.
 *
 * @author Shamith Nakka
 * @version 1.0.0
 * @since 24 Aug 2023
 *
 */
public class CalculatorController implements TextListener, ActionListener{

	// Saving their references to increase their scope for other methods
	private final CalculatorModel model;
	private StringBuilder textFieldCurrentText;
	private final CalculatorView view;

	/**
	 * Constructs a new CalculatorModel instance.
	 * This constructor initializes any required data or resources.
	 */
	public CalculatorController(){
		this(new CalculatorView(), new CalculatorModel());
	}

	/**
	 * Constructs a new CalculatorModel instance.
	 * This constructor initializes with provided data or resources.
	 * 
	 * @param view : a CalculatorView instance.
	 * @param model: a CalculatorModel instance.
	 * 
	 */
	public CalculatorController(final CalculatorView view, final CalculatorModel model){

		// Initialization
		this.model = model;
		this.view = view;
		this.textFieldCurrentText = new StringBuilder();

		// Register the components with the listener
		CalculatorModel.getAllChildren(view.keypad).forEach((button)->{
			((Button)button).addActionListener(this);
		});

		view.textField.addTextListener(this);

		// windows listener to close the application
		view.addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				view.dispose();
			}
		});
	}

	/**
 	 * This is an event-handler will handle all the button events.
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent event) {

		// Type-casted the event source into triggering Button object
		final Button clickedButton = (Button)event.getSource();

		// Setting up regex parameters
		final String regex = "^C|BS|=|\\+/\\-|\\+|\\-|\\*|/|%$";
		final String label = clickedButton.getLabel();

		// Evaluating regex
		final boolean shouldPerformOperation = CalculatorModel.matchesRegex(regex, label);

		// Performing respective operation
		if(shouldPerformOperation) {
			try {
				this.model.handleOperation(label, textFieldCurrentText);
			} catch(InvaildOperatorException exception) {											// Handling operator exception (For developers)
				System.err.println(exception + "\nCheck the regex on actionPerform() method");
			} catch(ArithmeticException exception) {												// Handling ArithmeticException
				System.err.println(exception);
				this.textFieldCurrentText.delete(0, this.textFieldCurrentText.length());
				this.textFieldCurrentText.append("ERROR");
			}
		} else if(CalculatorModel.isNumericValue(this.textFieldCurrentText.toString() + label, false)){	// Check whether it's a numeric or not
			if(this.model.hasDisplayedResult())	{
				this.textFieldCurrentText.delete(0, this.textFieldCurrentText.length());
			}
			this.textFieldCurrentText.append(label);
		}
		this.view.textField.setText(this.textFieldCurrentText.toString());
	}

	/**
	 * This is an event-handler that make sure only numeric character
	 * are present within the text field.
	 *
	 */
	@Override
	public void textValueChanged(TextEvent event) {

		// Initialization
		final TextField textField = this.view.textField;

		// Setting up regex parameters
		final String newInputText = textField.getText();

		// Checking whether it's numeric or not
		final boolean shouldUpdateCurrentText = CalculatorModel.isNumericValue(newInputText, false);

		// Performing respective operation
		if(shouldUpdateCurrentText) {
			CalculatorModel.replaceStringBuilderValue(textFieldCurrentText, newInputText);
		} else if(!newInputText.equals(textFieldCurrentText.toString())) {
			textField.setText(textFieldCurrentText.toString());
		}
		textField.setCaretPosition(textFieldCurrentText.length());	// Sets the cursor to end of the setter string
	}
}