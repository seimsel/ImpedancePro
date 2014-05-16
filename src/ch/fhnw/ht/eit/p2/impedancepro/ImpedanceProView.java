package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * The <code>ImpedanceProView</code> class is the main view of ImpedancePro. It
 * displays all the relevant data.
 * 
 * @author Simon Zumbrunnen
 */
public class ImpedanceProView extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;

	public static final Color LIGHT_BLUE = new Color(100, 100, 255, 100);
	public static final Color LIGHT_GREEN = new Color(100, 170, 100, 100);
	public static final Color LIGHT_RED = new Color(255, 100, 100, 100);
	public static final Color LIGHT_YELLOW = new Color(255, 200, 100, 100);

	public static final Color BLUE = new Color(100, 100, 255);
	public static final Color GREEN = new Color(100, 170, 100);
	public static final Color RED = new Color(255, 100, 100);
	public static final Color YELLOW = new Color(255, 200, 100);

	public static final Dimension PREF_WINDOW_SIZE = new Dimension(800, 600);
	public static final Dimension MIN_WINDOW_SIZE = new Dimension(800, 600);

	private ImpedanceProController controller;

	private Image icon;

	public InputView inputView;
	public SolutionView solutionView;
	public GraphView graphView;
	public PropertiesView propertiesView;

	public ImpedanceProView(ImpedanceProController controller) {
		super();

		try {
			icon = ImageUtil.loadResourceImage("icon_512.png");
		} catch (Exception e) {
			System.out.println("Could not load icon_512.png");
		}

		setLookAndFeel(); // Has to be first
		initializeWindow();
		setController(controller);
		addComponents();
		setDefaultValues();
		setVisible(true);
		setIconImage(icon);
	}

	/**
	 * Sets size, location, title etc. of the window.
	 */
	private void initializeWindow() {
		setTitle("Impedance Pro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(PREF_WINDOW_SIZE);
		setMinimumSize(MIN_WINDOW_SIZE);
		setLocation(50, 50);
	}

	/**
	 * Adds the 4 main panels <code>InputView</code>, <code>SolutionView</code>,
	 * <code>GraphView</code> and <code>PropertiesView</code>.
	 */
	private void addComponents() {
		Container contentPane = getContentPane();

		setLayout(new GridBagLayout());

		inputView = new InputView(controller);
		solutionView = new SolutionView(controller);
		graphView = new GraphView(controller);
		propertiesView = new PropertiesView(controller);

		contentPane.add(inputView, new GridBagConstraints(0, // gridx
				GridBagConstraints.RELATIVE, // gridy
				2, // gridwidth
				1, // gridheigth
				1.0, // weightx
				0.0, // weighty
				GridBagConstraints.CENTER, // anchor
				GridBagConstraints.HORIZONTAL, // fill
				new Insets(0, 0, 0, 0), // insets
				0, // ipadx
				0 // ipady
				));

		contentPane.add(solutionView, new GridBagConstraints(0, // gridx
				GridBagConstraints.RELATIVE, // gridy
				2, // gridwidth
				1, // gridheigth
				1.0, // weightx
				0.1, // weighty
				GridBagConstraints.CENTER, // anchor
				GridBagConstraints.BOTH, // fill
				new Insets(0, 0, 0, 0), // insets
				0, // ipadx
				0 // ipady
				));

		contentPane.add(graphView, new GridBagConstraints(0, // gridx
				GridBagConstraints.RELATIVE, // gridy
				1, // gridwidth
				1, // gridheigth
				1.0, // weightx
				0.9, // weighty
				GridBagConstraints.CENTER, // anchor
				GridBagConstraints.BOTH, // fill
				new Insets(0, 0, 0, 0), // insets
				0, // ipadx
				0 // ipady
				));

		contentPane.add(propertiesView, new GridBagConstraints(1, // gridx
				GridBagConstraints.RELATIVE, // gridy
				1, // gridwidth
				1, // gridheigth
				0.0, // weightx
				0.9, // weighty
				GridBagConstraints.CENTER, // anchor
				GridBagConstraints.VERTICAL, // fill
				new Insets(0, 0, 0, 0), // insets
				0, // ipadx
				0 // ipady
				));
	}

	/**
	 * Sets the look and feel to the system look and feel and changes the
	 * default background color of all panels to white.
	 */
	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Look and feel not supported");
		}

		UIManager.getLookAndFeelDefaults().put("Panel.background", Color.WHITE);
	}

	/**
	 * Fills all the inputs (e.g. textfields) with an example value.
	 */
	public void setDefaultValues() {
		InputPanel sourceInput = inputView.sourceInput;
		InputPanel loadInput = inputView.loadInput;
		MonteCarloPanel monteCarloPanel = propertiesView.monteCarloPanel;

		for (int i = 0; i < 6; i++) {
			sourceInput.setTopology(i);
			sourceInput.frequencyPanel.tfFrequency.setValue(100e6);
			sourceInput.valuePanel.tfValue1.setValue(50);
			sourceInput.valuePanel.tfValue2.setValue(159.2e-9);
			sourceInput.valuePanel.tfTolerance1.setValue(5);
			sourceInput.valuePanel.tfTolerance2.setValue(5);

			loadInput.setTopology(i);
			loadInput.valuePanel.tfValue1.setValue(60);
			loadInput.valuePanel.tfValue2.setValue(39.8e-12);
			loadInput.valuePanel.tfTolerance1.setValue(5);
			loadInput.valuePanel.tfTolerance2.setValue(5);

			double frequency = sourceInput.frequencyPanel.tfFrequency.getValue();
			
			propertiesView.monteCarloPanel.tfFu.setValue(0.98 * frequency);
			propertiesView.monteCarloPanel.tfFo.setValue(1.02 * frequency);
		}

		sourceInput.setTopology(2);
		loadInput.setTopology(1);

		monteCarloPanel.tfH.setValue(0.2);
		monteCarloPanel.tfN.setValue(1000);
	}

	public ImpedanceProController getController() {
		return controller;
	}

	public void setController(ImpedanceProController controller) {
		this.controller = controller;
	}

	/**
	 * Is triggered when the model calls its <code>notifyObservers</code>
	 * method. Synchronizes the displayed data to match with the model.
	 */
	public void update(Observable o, Object arg) {
		ImpedanceProModel model = (ImpedanceProModel) o;
		solutionView.update(model);
		graphView.update(model);
	}
}