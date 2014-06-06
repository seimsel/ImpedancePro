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
 * URL
 * -----------------------------------------------------------------------------
 * 
 * https://github.com/seimsel/ImpedancePro
 * 
 * License
 * -----------------------------------------------------------------------------
 * 
 * http://www.gnu.org/licenses/gpl-3.0
 * 
 ******************************************************************************/
package ch.fhnw.ht.eit.p2.impedancepro;


/**
 * <pre>
 * The <code>ImpedancePro</code> class contains the main method.
 * </pre>
 * 
 * @author Simon Zumbrunnen
 */
public class ImpedancePro {
	
	/**
	 * <pre>
	 * Creates model, view and controller and connects them.
	 * </pre>
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