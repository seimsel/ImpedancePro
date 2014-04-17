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
}
