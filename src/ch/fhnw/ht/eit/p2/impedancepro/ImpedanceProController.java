package ch.fhnw.ht.eit.p2.impedancepro;

/**
 * The <code>ImpedanceProController</code> class triggers the models
 * calculations, as soon as there are changes in the view. It also changes
 * settings
 * 
 * @author Simon Zumbrunnen
 */
public class ImpedanceProController {
	private ImpedanceProModel model;
	private ImpedanceProView view;

	public ImpedanceProController(ImpedanceProModel model) {
		setModel(model);
	}

	public ImpedanceProView getView() {
		return view;
	}

	public void setView(ImpedanceProView view) {
		this.view = view;
	}

	public ImpedanceProModel getModel() {
		return model;
	}

	public void setModel(ImpedanceProModel model) {
		this.model = model;
	}

	/**
	 * Is triggered by the view, as soon as an action occurs.
	 */
	public void viewHasChanged() {
		ImpedanceProView view = getView();
		InputPanel sourceInput = view.inputView.sourceInput;
		InputPanel loadInput = view.inputView.loadInput;

		int sourceTopology = sourceInput.getTopology();
		int loadTopology = loadInput.getTopology();

		if (getView().propertiesView.monteCarloPanel.btnMonteCarlo.isSelected()) {
			displayMonteCarlo(true);
		} else {
			displayMonteCarlo(false);
		}

		sourceInput.setTopology(sourceTopology);
		loadInput.setTopology(loadTopology);

		System.out.println("View has changed");
	}

	/**
	 * Enables or disables all the components, that are used for the monte-carlo
	 * simulation.
	 * 
	 * @param display
	 *            Whether the monte-carlo components should be enabled or not
	 */
	private void displayMonteCarlo(boolean display) {
		ImpedanceProView view = getView();
		SolutionView solutionView = view.solutionView;
		InputPanel sourceInput = view.inputView.sourceInput;
		InputPanel loadInput = view.inputView.loadInput;
		MonteCarloPanel monteCarloPanel = view.propertiesView.monteCarloPanel;

		for (int i = 0; i < solutionView.solutionPanels.length; i++) {
			solutionView.solutionPanels[i].valuePanel.setVisible(display);
		}

		for (int i = 0; i < 6; i++) {
			sourceInput.setTopology(i);
			sourceInput.getActiveValuePanel().tfTolerance1.setEnabled(display);
			sourceInput.getActiveValuePanel().tfTolerance2.setEnabled(display);

			loadInput.setTopology(i);
			loadInput.getActiveValuePanel().tfTolerance1.setEnabled(display);
			loadInput.getActiveValuePanel().tfTolerance2.setEnabled(display);
		}

		monteCarloPanel.tfFo.setEnabled(display);
		monteCarloPanel.tfFu.setEnabled(display);
		monteCarloPanel.tfH.setEnabled(display);
		monteCarloPanel.tfN.setEnabled(display);
	}
}
