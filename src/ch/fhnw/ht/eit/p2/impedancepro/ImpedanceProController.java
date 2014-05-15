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

		setMonteCarloEnabled(!(getView().inputView.sourceInput.getTopology() == SourceLoadNetwork.Z || getView().inputView.loadInput
				.getTopology() == SourceLoadNetwork.Z));

		getNewValues();

		if (viewHasChanged()) {
			if (vertifyAllTextFields()) {
				double frequency = getView().inputView.sourceInput.frequencyPanel.tfFrequency
						.getValue();

				int sourceTopology = getView().inputView.sourceInput
						.getTopology();

				ElectricalComponent[] sourceComponents = new ElectricalComponent[] {
						new ElectricalComponent(
								getView().inputView.sourceInput.valuePanel.tfValue1
										.getValue()),
						new ElectricalComponent(
								getView().inputView.sourceInput.valuePanel.tfValue2
										.getValue()) };

				int loadTopology = getView().inputView.loadInput.getTopology();

				ElectricalComponent[] loadComponents = new ElectricalComponent[] {
						new ElectricalComponent(
								getView().inputView.loadInput.valuePanel.tfValue1
										.getValue()),
						new ElectricalComponent(
								getView().inputView.loadInput.valuePanel.tfValue2
										.getValue()) };

				SourceLoadNetwork sourceNetwork = new SourceLoadNetwork(
						sourceTopology, sourceComponents);
				SourceLoadNetwork loadNetwork = new SourceLoadNetwork(
						loadTopology, loadComponents);

				model.getNetwork().calculateMatchingNetworks(sourceNetwork,
						loadNetwork, frequency);
				model.getNetwork().calculateReturnLossOfAllSolutions(
						frequency * 0.8, frequency * 1.2);
			}

			if (getView().inputView.sourceInput.frequencyPanel.tfFrequency
					.verify()) {
				double frequency = getView().inputView.sourceInput.frequencyPanel.tfFrequency
						.getValue();

				getView().propertiesView.monteCarloPanel.tfFu.setRange(
						frequency * 0.8, frequency * 1.2);
				getView().propertiesView.monteCarloPanel.tfFo.setRange(
						frequency * 0.8, frequency * 1.2);

				if (!(getView().propertiesView.monteCarloPanel.tfFu.verify() && getView().propertiesView.monteCarloPanel.tfFo
						.verify())) {
					getView().propertiesView.monteCarloPanel.tfFu
							.setValue(0.9 * frequency);
					getView().propertiesView.monteCarloPanel.tfFo
							.setValue(1.1 * frequency);
				}
			}
		}
	}

	private boolean vertifyAllTextFields() {
		boolean verify = true;
		verify &= getView().inputView.sourceInput.frequencyPanel.tfFrequency
				.verify();
		verify &= getView().inputView.sourceInput.valuePanel.tfValue1.verify();
		verify &= getView().inputView.sourceInput.valuePanel.tfValue2.verify();
		verify &= getView().inputView.loadInput.valuePanel.tfValue1.verify();
		verify &= getView().inputView.loadInput.valuePanel.tfValue2.verify();

		for (int i = 0; i < getView().solutionView.getSolutionPanels().length; i++) {
			verify &= getView().solutionView.getSolutionPanels()[i].valuePanel.tfValue1
					.verify();
			verify &= getView().solutionView.getSolutionPanels()[i].valuePanel.tfValue2
					.verify();
			verify &= getView().solutionView.getSolutionPanels()[i].valuePanel.tfTolerance1
					.verify();
			verify &= getView().solutionView.getSolutionPanels()[i].valuePanel.tfTolerance2
					.verify();
		}

		verify &= getView().propertiesView.monteCarloPanel.tfN.verify();
		verify &= getView().propertiesView.monteCarloPanel.tfFu.verify();
		verify &= getView().propertiesView.monteCarloPanel.tfFo.verify();
		verify &= getView().propertiesView.monteCarloPanel.tfH.verify();

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
	}

	public void setMonteCarloEnabled(boolean enabled) {
		getView().graphView.setEnabled(enabled);
		getView().propertiesView.settingsPanel.btnMonteCarlo
				.setEnabled(enabled);
		getView().propertiesView.settingsPanel.cbReturnLoss.setEnabled(enabled);

		if (getView().propertiesView.settingsPanel.btnMonteCarlo.isSelected()) {
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
