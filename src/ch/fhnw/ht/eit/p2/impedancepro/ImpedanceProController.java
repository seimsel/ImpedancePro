package ch.fhnw.ht.eit.p2.impedancepro;

import java.util.Arrays;

/**
 * The <code>ImpedanceProController</code> class triggers the models
 * calculations, as soon as there are changes in the view. It also changes
 * settings of the view.
 * 
 * @author Simon Zumbrunnen
 */
public class ImpedanceProController {
	private ImpedanceProModel model;
	private ImpedanceProView view;

	private String[] newInputValues;
	private String[] newMonteCarloValues;
	private String[] newValue1MonteCarlo;
	private String[] newValue2MonteCarlo;
	private String[] newTolerance1MonteCarlo;
	private String[] newTolerance2MonteCarlo;
	private int newSourceTopology;
	private int newLoadTopology;
	private int newAmplitudePlotType;
	private int newReflectionPlotType;
	private boolean newMonteCarloState;

	private String[] oldInputValues;
	private String[] oldMonteCarloValues;
	private String[] oldValue1MonteCarlo;
	private String[] oldValue2MonteCarlo;
	private String[] oldTolerance1MonteCarlo;
	private String[] oldTolerance2MonteCarlo;
	private int oldSourceTopology;
	private int oldLoadTopology;
	private int oldAmplitudePlotType;
	private int oldReflectionPlotType;
	private boolean oldMonteCarloState;

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
	public void viewAction() {

		getNewValues();

		if (viewHasChanged()) {
			model.getNetwork().getSourceNetwork()
					.setTopology(getView().inputView.sourceInput.getTopology());
			model.getNetwork().getLoadNetwork()
					.setTopology(getView().inputView.loadInput.getTopology());
			model.getNetwork()
					.getSourceNetwork()
					.setElectricalComponents(
							new ElectricalComponent[] {
									new ElectricalComponent(
											getView().inputView.sourceInput
													.getActiveValuePanel().tfValue1
													.getText()),
									new ElectricalComponent(
											getView().inputView.sourceInput
													.getActiveValuePanel().tfValue2
													.getText()) });
			model.getNetwork()
					.getLoadNetwork()
					.setElectricalComponents(
							new ElectricalComponent[] {
									new ElectricalComponent(
											getView().inputView.loadInput
													.getActiveValuePanel().tfValue1
													.getText()),
									new ElectricalComponent(
											getView().inputView.loadInput
													.getActiveValuePanel().tfValue2
													.getText()) });
			model.getNetwork().setFrequencyString(
					getView().inputView.sourceInput.frequencyPanel.tfFrequency
							.getText());
			model.getNetwork().calculateMatchingNetworks();
			displayMonteCarlo(getView().propertiesView.monteCarloPanel.btnMonteCarlo
					.isSelected());
		}
	}

	/**
	 * Gets all the values of the view.
	 */
	private void getNewValues() {
		ImpedanceProView view = getView();
		InputPanel sourceInput = view.inputView.sourceInput;
		InputPanel loadInput = view.inputView.loadInput;
		SolutionPanel[] solutionPanels = view.solutionView.getSolutionPanels();
		MonteCarloPanel monteCarloPanel = view.propertiesView.monteCarloPanel;
		ReflectionPanel reflectionPanel = view.propertiesView.reflectionPanel;

		newInputValues = new String[] {
				sourceInput.frequencyPanel.tfFrequency.getText(),
				sourceInput.getActiveValuePanel().tfValue1.getText(),
				sourceInput.getActiveValuePanel().tfValue2.getText(),
				sourceInput.getActiveValuePanel().tfTolerance1.getText(),
				sourceInput.getActiveValuePanel().tfTolerance2.getText(),
				loadInput.getActiveValuePanel().tfValue1.getText(),
				loadInput.getActiveValuePanel().tfValue2.getText(),
				loadInput.getActiveValuePanel().tfTolerance1.getText(),
				loadInput.getActiveValuePanel().tfTolerance2.getText() };

		if (solutionPanels != null) {
			int numberOfSolutions = solutionPanels.length;

			newValue1MonteCarlo = new String[numberOfSolutions];
			newValue2MonteCarlo = new String[numberOfSolutions];
			newTolerance1MonteCarlo = new String[numberOfSolutions];
			newTolerance2MonteCarlo = new String[numberOfSolutions];

			for (int i = 0; i < numberOfSolutions; i++) {
				newValue1MonteCarlo[i] = solutionPanels[i].valuePanel.tfValue1
						.getText();
				newValue2MonteCarlo[i] = solutionPanels[i].valuePanel.tfValue2
						.getText();
				newTolerance1MonteCarlo[i] = solutionPanels[i].valuePanel.tfTolerance1
						.getText();
				newTolerance2MonteCarlo[i] = solutionPanels[i].valuePanel.tfTolerance2
						.getText();
			}
		}

		newMonteCarloValues = new String[] { monteCarloPanel.tfN.getText(),
				monteCarloPanel.tfFo.getText(), monteCarloPanel.tfFu.getText(),
				monteCarloPanel.tfH.getText() };

		newSourceTopology = sourceInput.getTopology();
		newLoadTopology = loadInput.getTopology();

		newAmplitudePlotType = reflectionPanel.cbAmplitude.getSelectedIndex();
		newReflectionPlotType = reflectionPanel.cbReflection.getSelectedIndex();

		newMonteCarloState = monteCarloPanel.btnMonteCarlo.isSelected();
	}

	/**
	 * Checks whether the values of the view have changed since the last action.
	 * 
	 * @return Returns true if values have changed and false if not
	 */
	private boolean viewHasChanged() {
		if (Arrays.deepEquals(newInputValues, oldInputValues)
				&& Arrays.deepEquals(newMonteCarloValues, oldMonteCarloValues)
				&& Arrays.deepEquals(newValue1MonteCarlo, oldValue1MonteCarlo)
				&& Arrays.deepEquals(newValue2MonteCarlo, oldValue2MonteCarlo)
				&& Arrays.deepEquals(newTolerance1MonteCarlo,
						oldTolerance1MonteCarlo)
				&& Arrays.deepEquals(newTolerance2MonteCarlo,
						oldTolerance2MonteCarlo)
				&& newSourceTopology == oldSourceTopology
				&& newLoadTopology == oldLoadTopology
				&& newAmplitudePlotType == oldAmplitudePlotType
				&& newReflectionPlotType == oldReflectionPlotType
				&& newMonteCarloState == oldMonteCarloState) {

			return false;

		} else {

			oldInputValues = newInputValues;
			oldMonteCarloValues = newMonteCarloValues;
			oldValue1MonteCarlo = newValue1MonteCarlo;
			oldValue2MonteCarlo = newValue2MonteCarlo;
			oldTolerance1MonteCarlo = newTolerance1MonteCarlo;
			oldTolerance2MonteCarlo = newTolerance2MonteCarlo;
			oldSourceTopology = newSourceTopology;
			oldLoadTopology = newLoadTopology;
			oldAmplitudePlotType = newAmplitudePlotType;
			oldReflectionPlotType = newReflectionPlotType;
			oldMonteCarloState = newMonteCarloState;

			return true;
		}

	}

	/**
	 * Enables or disables all the textfields, that are used for the monte-carlo
	 * simulation.
	 * 
	 * @param display
	 *            Whether the monte-carlo components should be enabled or not
	 */
	public void displayMonteCarlo(boolean display) {
		ImpedanceProView view = getView();
		InputPanel sourceInput = view.inputView.sourceInput;
		InputPanel loadInput = view.inputView.loadInput;
		MonteCarloPanel monteCarloPanel = view.propertiesView.monteCarloPanel;

		int sourceTopology = sourceInput.getTopology();
		int loadTopology = loadInput.getTopology();

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

		sourceInput.setTopology(sourceTopology);
		loadInput.setTopology(loadTopology);
	}
}
