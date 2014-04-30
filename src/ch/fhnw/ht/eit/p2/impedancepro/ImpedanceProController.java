package ch.fhnw.ht.eit.p2.impedancepro;

/**
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
	
	public void viewHasChanged() {		
		
		int sourceTopology = getView().inputView.sourceInput.getTopology();
		int loadTopology = getView().inputView.loadInput.getTopology();

		if(getView().propertiesView.monteCarloPanel.btnMonteCarlo.isSelected()) {
			for (int i = 0; i < getView().solutionView.solutionPanels.length; i++) {
				getView().solutionView.solutionPanels[i].valuePanel.setVisible(true);
			}
			
			for(int i=0; i<6; i++){
				getView().inputView.sourceInput.setTopology(i);
				getView().inputView.sourceInput.getActiveValuePanel().tfTolerance1.setEnabled(true);
				getView().inputView.sourceInput.getActiveValuePanel().tfTolerance2.setEnabled(true);
				
				getView().inputView.loadInput.setTopology(i);
				getView().inputView.loadInput.getActiveValuePanel().tfTolerance1.setEnabled(true);
				getView().inputView.loadInput.getActiveValuePanel().tfTolerance2.setEnabled(true);
			}
			
			getView().propertiesView.monteCarloPanel.tfFo.setEnabled(true);
			getView().propertiesView.monteCarloPanel.tfFu.setEnabled(true);
			getView().propertiesView.monteCarloPanel.tfH.setEnabled(true);
			getView().propertiesView.monteCarloPanel.tfN.setEnabled(true);
		} else {
			for (int i = 0; i < getView().solutionView.solutionPanels.length; i++) {
				getView().solutionView.solutionPanels[i].valuePanel.setVisible(false);
			}
			
			for(int i=0; i<6; i++){
				getView().inputView.sourceInput.setTopology(i);
				getView().inputView.sourceInput.getActiveValuePanel().tfTolerance1.setEnabled(false);
				getView().inputView.sourceInput.getActiveValuePanel().tfTolerance2.setEnabled(false);
				
				getView().inputView.loadInput.setTopology(i);
				getView().inputView.loadInput.getActiveValuePanel().tfTolerance1.setEnabled(false);
				getView().inputView.loadInput.getActiveValuePanel().tfTolerance2.setEnabled(false);
			}
			
			getView().propertiesView.monteCarloPanel.tfFo.setEnabled(false);
			getView().propertiesView.monteCarloPanel.tfFu.setEnabled(false);
			getView().propertiesView.monteCarloPanel.tfH.setEnabled(false);
			getView().propertiesView.monteCarloPanel.tfN.setEnabled(false);
		}
		
		getView().inputView.sourceInput.setTopology(sourceTopology);
		getView().inputView.loadInput.setTopology(loadTopology);
		
		System.out.println("View has changed");
	}
}
