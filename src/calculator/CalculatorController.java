package calculator;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * This class represents the controller in the MVC architecture for a calculator application.
 * It handles all the listener event from view and perform operation using model.
 * 
 * @author Shamith Nakka
 * @version 1.0.0
 * @since 12 Aug 2023
 *
 */
public class CalculatorController {
	
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
		
		// windows listener to close the application
		view.addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				view.dispose();
			}
		});
	}
}
