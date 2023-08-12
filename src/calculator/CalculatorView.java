package calculator;

import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/**
 * This class represents the model in the MVC architecture for a calculator application.
 * It handles the GUI part of the application.
 * 
 * @author Shamith Nakka
 * @version 1.0.0
 * @since 12 Aug 2023
 *
 */
@SuppressWarnings("serial")
public class CalculatorView extends Frame implements Keypad {
	
	private final Panel keypad;
	private final GridBagConstraints gridBagConstraints;
	private final TextField textField;

	/**
	 * Constructs a new CalculatorModel instance.
	 * This constructor initializes any required data or resources.
	 */
	public CalculatorView() {
		
		// Initialization
		this.keypad = createKeypad();
		this.gridBagConstraints = new GridBagConstraints();
		this.textField = new TextField();
		
		// Setting GridBag Layout
		this.setLayout(new GridBagLayout());
		
		// Adding Components
		this.addComponentsToView(this.textField, 0, 0, 1, 1);
		this.addComponentsToView(this.keypad, 0, 1, 1, 10);
        
		// Setting Frame properties
		this.setSize(400, 600);
		this.setVisible(true);
	}
	
	/**
	 * This function adds components to the main Frame window w.r.t provided constraint values.
	 * 
	 * @param comp : an object of java.awt.Component
	 * @param gridx : x value on grid
	 * @param gridy : y value on grid
	 * @param weightx : weight value of x-coordinate on grid
	 * @param weighty  : weight value of y-coordinate on grid
	 * 
	 * @return void
	 * 
	 */
	private void addComponentsToView(Component comp, int gridx, int gridy,  int weightx, int weighty) {
		
		// Setting component's grid coordinates
		this.gridBagConstraints.gridx = gridx;
		this.gridBagConstraints.gridy = gridy;
		
		// Setting component's weights
		this.gridBagConstraints.weightx = weightx;
		this.gridBagConstraints.weighty = weighty;
		
		// Setting the fill constraint to fill both directions
		this.gridBagConstraints.fill = GridBagConstraints.BOTH;
		
		// Adding the component to the view (or) main Frame window
		this.add(comp, this.gridBagConstraints);
	}
	
}

/**
 * This Interface the keypad for the calculator.
 * 
 * @author Shamith Nakka
 * @version 1.0.0
 * @since 12 Aug 2023
 *
 */
interface Keypad {
	
	public final String [] BUTTON_LABLES = {
			"%", "/", "C", "<-",
			"7", "8", "9", "*",
			"4", "5", "6", "-",
			"1", "2", "3", "+",
			"+/-", "0", ".", "="
	   };
	
	/**
	 * This function returns a java.awt.Panel object that contains
	 * all the buttons required for a simple calculator.
	 * 
	 * @param No Parameter required
	 * 
	 * @return keypad : java.awt.Panel object
	 * 
	 */
	default Panel createKeypad() {
		Panel keypad = new Panel();
		return keypad;
	}
}