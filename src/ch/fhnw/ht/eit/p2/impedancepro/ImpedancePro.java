/*******************************************************************************
 * 
 * Impedance Pro
 *
 *******************************************************************************
 *
 * An impedance matching calculation tool.
 * 
 * Management:				Patrick Sutter
 * 							Fabian Schwager
 * 
 * Software development:	Simon Zumbrunnen
 * 							Stephan Fahrni
 * 
 * Electrical engineering:	David Zingg
 * 							Michael Floriancic
 * 
 * Version History
 * -----------------------------------------------------------------------------
 * 
 * Version 1.0.0: In Progress
 * 
 * License
 * -----------------------------------------------------------------------------
 * 
 ******************************************************************************/
package ch.fhnw.ht.eit.p2.impedancepro;

/**
 * The <code>ImpedancePro</code> class contains the main method.
 * @author Simon Zumbrunnen
 */
public class ImpedancePro {

	/**
	 * Creates model, view and controller and connects them.
	 * 
	 * @param args
	 *            ImpedancePro does not use any command-line arguments
	 */
	public static void main(String[] args) {
		ImpedanceProModel model = new ImpedanceProModel();
		ImpedanceProController controller = new ImpedanceProController(model);
		ImpedanceProView view = new ImpedanceProView(controller);
		
		controller.setView(view);
		model.addObserver(view);
		
		controller.viewAction();
	}
}