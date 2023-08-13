package calculator;

import java.awt.Button;
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
 * @since 13 Aug 2023
 *
 */
public class CalculatorController implements TextListener, ActionListener{
	
	// Saving their references to increase their scope for other methods
	private final CalculatorModel model;
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
	 */
	public CalculatorController(CalculatorView view, CalculatorModel model){
		
		// Initialization
		this.model = model;
		this.view = view;
		
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
		// This is a test
		Button clickedButton = (Button)e.getSource();
		this.view.textField.setText(clickedButton.getLabel());
	}

	@Override
	public void textValueChanged(TextEvent e) {}
}
