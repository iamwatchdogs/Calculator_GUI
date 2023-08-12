package calculator;

import java.awt.Frame;

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
public class CalculatorView extends Frame {

	/**
     * Constructs a new CalculatorModel instance.
     * This constructor initializes any required data or resources.
     */
	public CalculatorView() {
		this.setSize(400, 350);
		this.setVisible(true);
	}
}
