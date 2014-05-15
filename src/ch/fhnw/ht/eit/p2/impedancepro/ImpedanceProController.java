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

	private String[] oldInputValues;
	private String[] oldMonteCarloValues;
	private String[] oldValue1MonteCarlo;
	private String[] oldValue2MonteCarlo;
	private String[] oldTolerance1MonteCarlo;
	private String[] oldTolerance2MonteCarlo;
	private int oldSourceTopology;
	private int oldLoadTopology;

	private static final double YIELD_GOAL_RANGE_DEFAULT = 0.05;
	private static final double YIELD_GOAL_RANGE_MAX = 0.2;

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
			ImpedanceProView view = getView();
			InputPanel sourceInput = view.inputView.sourceInput;
			InputPanel loadInput = view.inputView.loadInput;
			SolutionPanel[] solutionPanels = view.solutionView
					.getSolutionPanels();
			SettingsPanel settingsPanel = view.propertiesView.settingsPanel;
			MonteCarloPanel monteCarloPanel = view.propertiesView.monteCarloPanel;

			Network network = model.getNetwork();
			MatchingNetwork[] matchingNetworks = network.getMatchingNetworks();

			int sourceTopology = sourceInput.getTopology();
			int loadTopology = loadInput.getTopology();

			if (vertifyAllTextFields()) {
				double frequency = sourceInput.frequencyPanel.tfFrequency
						.getValue();

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

				network.calculateMatchingNetworks(sourceNetwork, loadNetwork,
						frequency);
				network.calculateReturnLossOfAllSolutions(frequency
						* (1 - YIELD_GOAL_RANGE_MAX), frequency
						* (1 + YIELD_GOAL_RANGE_MAX));

				for (int i = 0; i < matchingNetworks.length; i++) {
					ElectricalComponent ec1, ec2;

					if (solutionPanels[i].valuePanel.tfValue1.getText()
							.isEmpty()) {
						ec1 = new ElectricalComponent(
								matchingNetworks[i].getElectricalComponents()[0]
										.getValue(), 0.0);
					} else {
						ec1 = new ElectricalComponent(
								solutionPanels[i].valuePanel.tfValue1
										.getValue(),
								solutionPanels[i].valuePanel.tfTolerance1
										.getValue());
					}

					if (solutionPanels[i].valuePanel.tfValue1.getText()
							.isEmpty()) {
						ec2 = new ElectricalComponent(
								matchingNetworks[i].getElectricalComponents()[1]
										.getValue(), 0.0);
					} else {
						ec2 = new ElectricalComponent(
								solutionPanels[i].valuePanel.tfValue2
										.getValue(),
								solutionPanels[i].valuePanel.tfTolerance2
										.getValue());
					}

					setMonteCarloEnabled(!(sourceTopology == SourceLoadNetwork.Z || loadTopology == SourceLoadNetwork.Z));

					if (settingsPanel.btnMonteCarlo.isSelected()) {
						model.calculateMonteCarlo(
								sourceNetwork,
								new MatchingNetwork(new ElectricalComponent[] {
										ec1, ec2 }, model.getNetwork()
										.getMatchingNetworks()[i].getTopology()),
								loadNetwork, monteCarloPanel.tfFo.getValue(),
								monteCarloPanel.tfFu.getValue(),
								monteCarloPanel.tfH.getValue(),
								monteCarloPanel.tfN.getValue());
					}
				}
			}

			if (sourceInput.frequencyPanel.tfFrequency.verify()) {
				double frequency = sourceInput.frequencyPanel.tfFrequency
						.getValue();

				monteCarloPanel.tfFu.setRange(frequency
						* (1 - YIELD_GOAL_RANGE_MAX), frequency
						* (1 + YIELD_GOAL_RANGE_MAX));
				monteCarloPanel.tfFo.setRange(frequency
						* (1 - YIELD_GOAL_RANGE_MAX), frequency
						* (1 + YIELD_GOAL_RANGE_MAX));

				if (!(monteCarloPanel.tfFu.verify() && monteCarloPanel.tfFo
						.verify())) {
					monteCarloPanel.tfFu
							.setValue((1 - YIELD_GOAL_RANGE_DEFAULT)
									* frequency);
					monteCarloPanel.tfFo.setValue((1 + YIELD_GOAL_RANGE_MAX)
							* frequency);
				}
			}
		}
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
		verify &= sourceInput.valuePanel.tfValue2.verify();
		verify &= loadInput.valuePanel.tfValue1.verify();
		verify &= loadInput.valuePanel.tfValue2.verify();

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
				&& newLoadTopology == oldLoadTopology) {

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

		if (display) {
			view.graphView.returnLossGraph.setYieldGoal(
					monteCarloPanel.tfFu.getValue(),
					monteCarloPanel.tfFo.getValue(),
					monteCarloPanel.tfH.getValue());
		} else {
			view.graphView.returnLossGraph.removeYieldGoal();
		}
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
	}

	public void setGraphType(int type) {

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
