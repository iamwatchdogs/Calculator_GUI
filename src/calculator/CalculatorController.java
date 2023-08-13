package calculator;

import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents the controller in the MVC architecture for a calculator application.
 * It handles all the listener event from view and perform operation using model.
 * 
 * @author Shamith Nakka
 * @version 1.0.0
 * @since 13 Aug 2023
 *
 */
public class CalculatorController implements TextListener, ActionListener{
	
	// Saving their references to increase their scope for other methods
	private final CalculatorModel model;
	private final CalculatorView view;
	private StringBuilder textFieldCurrentText;
	
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
	 */
	public CalculatorController(CalculatorView view, CalculatorModel model){
		
		// Initialization
		this.model = model;
		this.view = view;
		this.textFieldCurrentText = new StringBuilder();
		
		// Register the components with the listener.
		model.getAllChildren(view.keypad).forEach((button)->{
			((Button)button).addActionListener(this);
		});
		
		view.textField.addTextListener(this);
		
		// windows listener to close the application
		view.addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				view.dispose();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Type-casted the event source into triggering Button object
		Button clickedButton = (Button)e.getSource();
		
		// Regex pattern checking
		Pattern pattern = Pattern.compile("^C|BS|=|\\+/\\-*$");
		Matcher matcher = pattern.matcher(clickedButton.getLabel());
		
		// Performing respective operation
		if(matcher.find()) {
			// Performs operation w.r.t input
		} else {
			this.textFieldCurrentText.append(clickedButton.getLabel());
			this.view.textField.setText(this.textFieldCurrentText.toString());
		}
	}

	/**
	 * This is an event-handler that make sure only numeric character
	 * are present within the text field.
	 * 
	 */
	@Override
	public void textValueChanged(TextEvent e) {
		
		// Initialization
		TextField textField = this.view.textField;
		String newInputText = textField.getText();
		
		// Regex pattern checking
		Pattern pattern = Pattern.compile("^[\\d\\+\\-\\*/%\\.]*$");
		Matcher matcher = pattern.matcher(newInputText);
		
		// Performing respective operation
		if(matcher.find()) {
			this.textFieldCurrentText.replace(0, this.textFieldCurrentText.length(), newInputText);
		} else if(!newInputText.equals(textFieldCurrentText.toString())) {
			textField.setText(textFieldCurrentText.toString());
			textField.setCaretPosition(textFieldCurrentText.length());	// Sets the cursor to end of the setter string
		}
	}
}
