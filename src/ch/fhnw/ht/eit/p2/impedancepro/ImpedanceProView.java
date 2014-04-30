package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.UIManager;

/**
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

	public InputView inputView;
	public SolutionView solutionView;
	public GraphView graphView;
	public PropertiesView propertiesView;
	
	public ImpedanceProView(ImpedanceProController controller) {
		super();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			System.out.println("Look and feel not supported");
		}
		
		UIManager.getLookAndFeelDefaults().put("Panel.background", Color.WHITE);
		
		setController(controller);
		
		inputView = new InputView(controller);
		solutionView = new SolutionView(controller);
		graphView = new GraphView();
		propertiesView = new PropertiesView(controller);
				
		setTitle("Impedance Pro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(PREF_WINDOW_SIZE);
		setMinimumSize(MIN_WINDOW_SIZE);
		setLocation(50,50);
		setLayout(new GridBagLayout());
				
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		getContentPane().add(inputView, gbc);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 0.1;
		getContentPane().add(solutionView, gbc);
		
		gbc.weighty = 0.9;
		getContentPane().add(graphView, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		getContentPane().add(propertiesView, gbc);
		
		setVisible(true);
		
		for(int i=0; i<6; i++){
			inputView.sourceInput.setTopology(i);
			inputView.sourceInput.frequencyPanel.tfFrequency.setText("100M");
			inputView.sourceInput.getActiveValuePanel().tfValue1.setText("100");
			inputView.sourceInput.getActiveValuePanel().tfValue2.setText("100u");
			inputView.sourceInput.getActiveValuePanel().tfTolerance1.setText("5");
			inputView.sourceInput.getActiveValuePanel().tfTolerance2.setText("5");
			
			inputView.loadInput.setTopology(i);
			inputView.loadInput.getActiveValuePanel().tfValue1.setText("50");
			inputView.loadInput.getActiveValuePanel().tfValue2.setText("10m");
			inputView.loadInput.getActiveValuePanel().tfTolerance1.setText("5");
			inputView.loadInput.getActiveValuePanel().tfTolerance2.setText("5");
		}
		
		inputView.sourceInput.setTopology(1);
		inputView.loadInput.setTopology(2);
		
		propertiesView.monteCarloPanel.tfFu.setText("80M");
		propertiesView.monteCarloPanel.tfFo.setText("120M");
		propertiesView.monteCarloPanel.tfH.setText("0.2");
		propertiesView.monteCarloPanel.tfN.setText("1000");
	}
	
	public ImpedanceProController getController() {
		return controller;
	}

	public void setController(ImpedanceProController controller) {
		this.controller = controller;
	}

	public void update(Observable o, Object arg) {

	}
}