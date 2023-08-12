package calculator;

import java.awt.Component;
import java.awt.Container;
import java.util.HashMap;

/**
 * This class represents the model in the MVC architecture for a calculator application.
 * It handles all the functional operations and calculations.
 * 
 * @author Shamith Nakka
 * @version 1.0.0
 * @since 12 Aug 2023
 *
 */
public class CalculatorModel {
	
	/**
	 * Constructs a new CalculatorModel instance.
	 * This constructor initializes any required data or resources.
	 */
	public CalculatorModel() {}
	
	/**
	 * This function returns all the children(in whole hierarchy) of given container.
	 * 
	 * @param container[java.awt.Container] : container object
	 * 
	 * @return children[HashMap] : list of children mapped with their name
	 */
	public HashMap<String,Component> getAllChildren(Container container) {
		
		// Initialization
		HashMap<String,Component> children = new HashMap<>();
		
		// Retrieving Components
		for(Component child : container.getComponents()) {
			
			// Storing them w.r.t their name
			children.put(child.getName(), child);
			
			// Collecting the grand children of Container
			if (child instanceof Container) {
				children.putAll(this.getAllChildren((Container) child));
			}
		}
		return children;
	}
}
