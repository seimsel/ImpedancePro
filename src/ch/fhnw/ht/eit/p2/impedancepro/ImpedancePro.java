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
 *
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
 * @author Simon Zumbrunnen
 */
public class ImpedancePro {

	public static void main(String[] args) {
		ImpedanceProModel model = new ImpedanceProModel();
		ImpedanceProController controller = new ImpedanceProController(model);
		ImpedanceProView view = new ImpedanceProView(controller);
		controller.setView(view);
		model.addObserver(view);
	}
}