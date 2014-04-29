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

	public static final Color BLUE = new Color(150, 150, 255);
	public static final Color GREEN = new Color(150, 220, 150);
	public static final Color RED = new Color(255, 150, 150);
	public static final Color YELLOW = new Color(255, 220, 150);
		
	public static final Dimension PREF_WINDOW_SIZE = new Dimension(800, 600);
	public static final Dimension MIN_WINDOW_SIZE = new Dimension(800, 600);
	
	private ImpedanceProController controller;

	public InputView inputView;
	public SolutionView solutionView;
	public GraphView graphView;
	public PropertiesView propertiesView;
	
	public ImpedanceProView() {
		super();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			System.out.println("Look and feel not supported");
		}
		
		UIManager.getLookAndFeelDefaults().put("Panel.background", Color.WHITE);
		
		inputView = new InputView();
		solutionView = new SolutionView();
		graphView = new GraphView();
		propertiesView = new PropertiesView();
				
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
	}
	
	public ImpedanceProView(ImpedanceProController controller) {
		this();
		
		setController(controller);
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