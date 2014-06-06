package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.Desktop;
import java.io.IOException;
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
	private int newYieldGoalSpan;

	private String[] oldInputValues;
	private String[] oldMonteCarloValues;
	private String[] oldValue1MonteCarlo;
	private String[] oldValue2MonteCarlo;
	private String[] oldTolerance1MonteCarlo;
	private String[] oldTolerance2MonteCarlo;
	private int oldSourceTopology;
	private int oldLoadTopology;
	private int oldYieldGoalSpan;

	private boolean monteCarloDisplayed = false;
	private int graphType = 0;
	private boolean changed = false;

	public static final double MAX_YIELD_GOAL_SPAN = 0.2;

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
		checkViewHasChanged();

		if (getChanged()) {
			ImpedanceProView view = getView();
			InputPanel sourceInput = view.inputView.sourceInput;
			InputPanel loadInput = view.inputView.loadInput;
			SolutionPanel[] solutionPanels = view.solutionView
					.getSolutionPanels();
			SettingsPanel settingsPanel = view.propertiesView.settingsPanel;
			MonteCarloPanel monteCarloPanel = view.propertiesView.monteCarloPanel;

			int sourceTopology = sourceInput.getTopology();
			int loadTopology = loadInput.getTopology();

			setMonteCarloEnabled(!(sourceTopology == SourceLoadNetwork.Z || loadTopology == SourceLoadNetwork.Z));

			if (vertifyAllTextFields()) {
				double frequency = sourceInput.frequencyPanel.tfFrequency
						.getValue();

				double yieldGoalSpan = view.propertiesView.settingsPanel.btnSpan
						.getValue() / 20.0;

				double lowerFrequency = monteCarloPanel.tfFu.getValue();
				double upperFrequency = monteCarloPanel.tfFo.getValue();

				ElectricalComponent[] sourceComponents = new ElectricalComponent[] {
						new ElectricalComponent(
								sourceInput.valuePanel.tfValue1.getValue(),
								sourceInput.valuePanel.tfTolerance1.getValue()),
						new ElectricalComponent(
								sourceInput.valuePanel.tfValue2.getValue(),
								sourceInput.valuePanel.tfTolerance2.getValue()) };

				ElectricalComponent[] loadComponents = new ElectricalComponent[] {
						new ElectricalComponent(
								loadInput.valuePanel.tfValue1.getValue()),
						new ElectricalComponent(
								loadInput.valuePanel.tfValue2.getValue()) };

				SourceLoadNetwork sourceNetwork = new SourceLoadNetwork(
						sourceTopology, sourceComponents);
				SourceLoadNetwork loadNetwork = new SourceLoadNetwork(
						loadTopology, loadComponents);

				if (settingsPanel.btnMonteCarlo.isSelected()
						&& settingsPanel.btnMonteCarlo.isEnabled()) {

					double h = monteCarloPanel.tfH.getValue();
					int n = monteCarloPanel.tfN.getValue();

					MatchingNetwork[] monteCarloMatchingNetworks = new MatchingNetwork[4];
					for (int i = 0; i < monteCarloMatchingNetworks.length; i++) {
						monteCarloMatchingNetworks[i] = new MatchingNetwork();

						if (solutionPanels[i].valuePanel.tfValue1.getText()
								.isEmpty()) {
							monteCarloMatchingNetworks[i]
									.getElectricalComponents()[0].setValue(-1);
						} else {
							monteCarloMatchingNetworks[i]
									.getElectricalComponents()[0]
									.setValue(solutionPanels[i].valuePanel.tfValue1
											.getValue());
						}

						if (solutionPanels[i].valuePanel.tfValue2.getText()
								.isEmpty()) {
							monteCarloMatchingNetworks[i]
									.getElectricalComponents()[1].setValue(-1);
						} else {
							monteCarloMatchingNetworks[i]
									.getElectricalComponents()[1]
									.setValue(solutionPanels[i].valuePanel.tfValue2
											.getValue());
						}

						if (solutionPanels[i].valuePanel.tfTolerance1.getText()
								.isEmpty()) {
							monteCarloMatchingNetworks[i]
									.getElectricalComponents()[0]
									.setTolerance(-1);
						} else {
							monteCarloMatchingNetworks[i]
									.getElectricalComponents()[0]
									.setTolerance(solutionPanels[i].valuePanel.tfTolerance1
											.getValue());
						}

						if (solutionPanels[i].valuePanel.tfTolerance2.getText()
								.isEmpty()) {
							monteCarloMatchingNetworks[i]
									.getElectricalComponents()[1]
									.setTolerance(-1);
						} else {
							monteCarloMatchingNetworks[i]
									.getElectricalComponents()[1]
									.setTolerance(solutionPanels[i].valuePanel.tfTolerance2
											.getValue());
						}
					}

					model.triggerCalculations(sourceNetwork,
							monteCarloMatchingNetworks, loadNetwork, frequency,
							lowerFrequency, upperFrequency, h, n,
							yieldGoalSpan, true);
				} else {
					model.triggerCalculations(sourceNetwork, null, loadNetwork,
							frequency, 0.0, 0.0, 0.0, 0, yieldGoalSpan, false);
				}

				view.graphView.returnLossGraph.axis.setRange(frequency
						* (1 - yieldGoalSpan), frequency * (1 + yieldGoalSpan));
			}

			if (sourceInput.frequencyPanel.tfFrequency.verify()) {
				double frequency = sourceInput.frequencyPanel.tfFrequency
						.getValue();

				monteCarloPanel.tfFu.setRange(frequency
						* (1 - MAX_YIELD_GOAL_SPAN), frequency
						* (1 + MAX_YIELD_GOAL_SPAN));
				monteCarloPanel.tfFo.setRange(frequency
						* (1 - MAX_YIELD_GOAL_SPAN), frequency
						* (1 + MAX_YIELD_GOAL_SPAN));

				if (!(monteCarloPanel.tfFu.verify() && monteCarloPanel.tfFo
						.verify())) {
					monteCarloPanel.tfFu.setValue(0.9 * frequency);
					monteCarloPanel.tfFo.setValue((1.1) * frequency);
				}
			}
		}

		setChanged(false);
	}

	private boolean vertifyAllTextFields() {
		ImpedanceProView view = getView();
		InputPanel sourceInput = view.inputView.sourceInput;
		InputPanel loadInput = view.inputView.loadInput;
		SolutionPanel[] solutionPanels = view.solutionView.getSolutionPanels();
		MonteCarloPanel monteCarloPanel = view.propertiesView.monteCarloPanel;

		boolean verify = true;

		verify &= sourceInput.frequencyPanel.tfFrequency.verify();
		verify &= sourceInput.valuePanel.tfValue1.verify();
		verify &= loadInput.valuePanel.tfValue1.verify();
		verify &= sourceInput.valuePanel.tfTolerance1.verify();
		verify &= loadInput.valuePanel.tfTolerance1.verify();
		
		if(sourceInput.getTopology() != SourceLoadNetwork.R) {
			verify &= sourceInput.valuePanel.tfValue2.verify();
			verify &= sourceInput.valuePanel.tfTolerance2.verify();
		}
	
		if(loadInput.getTopology() != SourceLoadNetwork.R) {
			verify &= loadInput.valuePanel.tfValue2.verify();
			verify &= loadInput.valuePanel.tfTolerance2.verify();
		}

		for (int i = 0; i < solutionPanels.length; i++) {
			verify &= solutionPanels[i].valuePanel.tfValue1.verify();
			verify &= solutionPanels[i].valuePanel.tfValue2.verify();
			verify &= solutionPanels[i].valuePanel.tfTolerance1.verify();
			verify &= solutionPanels[i].valuePanel.tfTolerance2.verify();
		}

		verify &= monteCarloPanel.tfN.verify();
		verify &= monteCarloPanel.tfFu.verify();
		verify &= monteCarloPanel.tfFo.verify();
		verify &= monteCarloPanel.tfH.verify();

		return verify;
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

		newInputValues = new String[] {
				sourceInput.frequencyPanel.tfFrequency.getText(),
				sourceInput.valuePanel.tfValue1.getText(),
				sourceInput.valuePanel.tfValue2.getText(),
				sourceInput.valuePanel.tfTolerance1.getText(),
				sourceInput.valuePanel.tfTolerance2.getText(),
				loadInput.valuePanel.tfValue1.getText(),
				loadInput.valuePanel.tfValue2.getText(),
				loadInput.valuePanel.tfTolerance1.getText(),
				loadInput.valuePanel.tfTolerance2.getText() };

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
		newYieldGoalSpan = view.propertiesView.settingsPanel.btnSpan.getValue();
	}

	/**
	 * Checks whether the values of the view have changed since the last action.
	 * 
	 * @return Returns true if values have changed and false if not
	 */
	private void checkViewHasChanged() {
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
				&& newYieldGoalSpan == oldYieldGoalSpan) {

		} else {
			oldInputValues = newInputValues;
			oldMonteCarloValues = newMonteCarloValues;
			oldValue1MonteCarlo = newValue1MonteCarlo;
			oldValue2MonteCarlo = newValue2MonteCarlo;
			oldTolerance1MonteCarlo = newTolerance1MonteCarlo;
			oldTolerance2MonteCarlo = newTolerance2MonteCarlo;
			oldSourceTopology = newSourceTopology;
			oldLoadTopology = newLoadTopology;
			oldYieldGoalSpan = newYieldGoalSpan;

			setChanged(true);

		}

	}

	private void setChanged(boolean changed) {
		this.changed = changed;
	}

	private boolean getChanged() {
		return this.changed;
	}

	/**
	 * Enables or disables all the textfields, that are used for the monte-carlo
	 * simulation.
	 * 
	 * @param display
	 *            Whether the monte-carlo components should be enabled or not
	 */
	public void displayMonteCarlo(boolean display) {
		this.monteCarloDisplayed = display;

		ImpedanceProView view = getView();
		InputPanel sourceInput = view.inputView.sourceInput;
		InputPanel loadInput = view.inputView.loadInput;
		MonteCarloPanel monteCarloPanel = view.propertiesView.monteCarloPanel;
		SolutionPanel[] solutionPanels = view.solutionView.getSolutionPanels();

		sourceInput.valuePanel.tfTolerance1.setEnabled(display);
		sourceInput.valuePanel.tfTolerance2.setEnabled(display);

		loadInput.valuePanel.tfTolerance1.setEnabled(display);
		loadInput.valuePanel.tfTolerance2.setEnabled(display);

		monteCarloPanel.tfFo.setEnabled(display);
		monteCarloPanel.tfFu.setEnabled(display);
		monteCarloPanel.tfH.setEnabled(display);
		monteCarloPanel.tfN.setEnabled(display);

		for (int i = 0; i < solutionPanels.length; i++) {
			solutionPanels[i].valuePanel.setVisible(display);
		}

		setChanged(true);
	}

	public void setMonteCarloEnabled(boolean enabled) {
		ImpedanceProView view = getView();
		SettingsPanel settingsPanel = view.propertiesView.settingsPanel;

		view.graphView.setEnabled(enabled);
		settingsPanel.btnMonteCarlo.setEnabled(enabled);
		settingsPanel.cbReturnLoss.setEnabled(enabled);

		if (settingsPanel.btnMonteCarlo.isSelected()) {
			displayMonteCarlo(enabled);
		} else {
			displayMonteCarlo(false);
		}

		setChanged(true);
	}

	public boolean isMonteCarloDisplayed() {
		return monteCarloDisplayed;
	}

	public void setGraphType(int type) {
		this.graphType = type;

		getView().graphView.update(model);
	}

	public int getGraphType() {
		return graphType;
	}

	public void setYieldGoalSpan(int yieldGoalSpan) {
		WebIncDecButton btnSpan = view.propertiesView.settingsPanel.btnSpan;
		btnSpan.setValue(view.propertiesView.settingsPanel.btnSpan.getValue()
				+ yieldGoalSpan);
		viewAction();
	}

	public void openInfoPDF() {
		try {
			Desktop.getDesktop().open(DocumentUtil.loadResourcePDF("info.pdf"));
		} catch (IOException e) {
			System.out.println("Couldn't load info.pdf");
		}
	}

	public void openHelpPDF() {
		try {
			Desktop.getDesktop().open(DocumentUtil.loadResourcePDF("info.pdf"));
		} catch (IOException e) {
			System.out.println("Couldn't load help.pdf");
		}
	}
}
