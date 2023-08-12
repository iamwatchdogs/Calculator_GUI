/*
 * @(#)CalculatorApplication.java        1.0.0 23/08/12
 * 
 * This is an simple application program that uses AWT package,
 * following MVC Architecture.
 * 
 */

package calculator;

/**
 * This class represents the model in the MVC architecture for a calculator application.
 * It handles all the functional operations and calculations.
 * 
 * @author Shamith Nakka
 * @version 1.0.0
 * @since 12 Aug 2023
 *
 */
class CalculatorModel {
	
	/**
     * Constructs a new CalculatorModel instance.
     * This constructor initializes any required data or resources.
     */
	public CalculatorModel() {}
}

/**
 * This class represents the model in the MVC architecture for a calculator application.
 * It handles the GUI part of the application.
 * 
 * @author Shamith Nakka
 * @version 1.0.0
 * @since 12 Aug 2023
 *
 */
class CalculatorView {
	
	/**
     * Constructs a new CalculatorModel instance.
     * This constructor initializes any required data or resources.
     */
	public CalculatorView() {}
}

/**
 * This class represents the controller in the MVC architecture for a calculator application.
 * It handles all the listener event from view and perform operation using model.
 * 
 * @author Shamith Nakka
 * @version 1.0.0
 * @since 12 Aug 2023
 *
 */
class CalculatorController {
	
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
	public CalculatorController(CalculatorView view, CalculatorModel model){}
}

/**
 * This class is the driver program for the application.
 * The application's execution begin from here.
 * 
 * @author Shamith Nakka
 * @version 1.0.0
 * @since 12 Aug 2023
 *
 */
public class CalculatorApplication {
	public static void main(String[] args) {
		
		// Creating view and model objects for the application
		CalculatorView view = new CalculatorView();
		CalculatorModel model = new CalculatorModel();
		
		// Patching all together using a controller object
		@SuppressWarnings("unused")
		CalculatorController controller = new CalculatorController(view,model);
		
	}
}
