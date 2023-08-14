package calculator;

import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Insets;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Component;
import java.awt.GridLayout;
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
	
	protected final Panel keypad;
	protected final TextField textField;
	private final Font globalFont;
	private final Image icon;
	

	/**
	 * Constructs a new CalculatorModel instance.
	 * This constructor initializes any required data or resources.
	 */
	public CalculatorView() {
		
		// Initialization
		this.keypad = createKeypad();
		this.textField = new TextField();
		this.globalFont = new Font("Arial", Font.PLAIN, 30);
		this.icon = Toolkit.getDefaultToolkit().getImage(this.getIconLocation());
		
		// Setting Names to the component
		this.textField.setName("Textfield");
		
		// Setting GridBag Layout
		this.setLayout(new GridBagLayout());
		
		// Adding Components
		this.addComponentsToView(this.textField, 0, 0, 1, 1, new int[]{20, 20, 10, 20});
		this.addComponentsToView(this.keypad, 0, 1, 1, 2, new int[]{10, 20, 20, 20});
        
		// Setting Frame properties
		this.setSize(400, 600);
		this.setVisible(true);
		this.setBackground(new Color(24, 24, 24));
		this.setTitle("Calculator");
		this.setIconImage(icon);
		this.setResizable(false);
	}
	
	/**
	 * This function adds components to the main Frame window w.r.t provided constraint values.
	 * 
	 * @param component : java.awt.Component object.
	 * @param gridx : x value on grid.
	 * @param gridy : y value on grid.
	 * @param weightx : weight value of x-coordinate on grid.
	 * @param weighty : weight value of y-coordinate on grid.
	 * @param padding : array of padding value (Top, Left, Bottom, Right).
	 * 
	 * @return void
	 * 
	 */
	private void addComponentsToView(Component component, int gridx, int gridy,  int weightx, int weighty, int [] padding) {
		
		// Initialization
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		
		// Setting component's grid coordinates
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		
		// Setting component's weights
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		
		// Setting the fill constraint to fill both directions
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		
		// Setting Padding to the component
		gridBagConstraints.insets = new Insets(padding[0], padding[1], padding[2], padding[3]);
		
		// Setting global font to the component
		component.setFont(this.globalFont);
		
		// Setting background and foreground color
		component.setBackground(new Color(40, 40, 40));
		component.setForeground(new Color(255, 255, 255));
		
		// Adding the component to the view (or) main Frame window
		this.add(component, gridBagConstraints);
	}
	
	/**
	 * Returns the absolute path to icon
	 * 
	 * @param Not Required.
	 * 
	 * @return absolutePathToIcon[String]
	 */
	private String getIconLocation() {
		String relativePathToIcon = "\\src\\calculator\\images\\icon.png";						// Setting Relative Path to icon
		String absolutePathToIcon = System.getProperty("user.dir").concat(relativePathToIcon);	// Converting into absolute path
		return absolutePathToIcon;
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
			"%", "/", "C", "BS",
			"7", "8", "9", "*",
			"4", "5", "6", "-",
			"1", "2", "3", "+",
			"+/-", "0", ".", "="
	   };
	
	/**
	 * This function returns a java.awt.Panel object that contains
	 * all the buttons required for a simple calculator.
	 * 
	 * @param No Parameter required.
	 * 
	 * @return keypad[java.awt.Panel] : Panel object.
	 * 
	 */
	default Panel createKeypad() {
		
		// Initialization
		Panel keypad = new Panel();
		keypad.setName("Keypad");
		
		// Setting Grid Layout
		keypad.setLayout(new GridLayout(5,4));
		
		// Adding buttons to the keypad Panel
		for(String buttonName: BUTTON_LABLES) {
			Button button = new Button(buttonName);
			button.setBackground(new Color(64,64,64));		// Setting Background Color
			button.setForeground(new Color(255,255,255));	// Setting Font(Foreground) color
			button.setName(buttonName);
			keypad.add(button);
		}
		
		return keypad;
	}
}