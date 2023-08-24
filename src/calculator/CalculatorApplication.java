/*
 * @(#)CalculatorApplication.java        1.0.0 23/08/12
 *
 * This is an simple application program that uses AWT package,
 * following MVC Architecture.
 *
 * Built using Eclipse IDE Version: 2023-03 (4.27.0)
 * OpenJDK Runtime Environment Temurin-17.0.7+7 (build 17.0.7+7)
 *
 */


package calculator;

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

	/**
	 * This the driver program that initializes the application.
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		// Creating view and model objects for the application
		final CalculatorView view = new CalculatorView();
		final CalculatorModel model = new CalculatorModel();

		@SuppressWarnings("unused")
		final CalculatorController controller = new CalculatorController(view, model);	// Patching all together using a controller object

	}
}
