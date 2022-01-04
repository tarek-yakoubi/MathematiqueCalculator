package fr.pantheonsorbonne.cri;

public class VariableSymboliqueIndicee extends VariableSymbolique {
	protected ExpressionArithmetique indice;
	protected VariableSymbolique nomVariable;

	public VariableSymboliqueIndicee(VariableSymbolique nomV, ExpressionArithmetique indice) {
		super(nomV.afficher() + "▼" + indice.afficher());
		this.indice = indice;
		this.nomVariable = nomV;
	}

	@Override
	public ExpressionArithmetique simplifier() {
		if (listVar.get(symbol) != null) {
			return listVar.get(symbol);
		} else if (this.indice instanceof VariableSymbolique
				&& listVar.get(((VariableSymbolique) this.indice).symbol) != null) {
			return new VariableSymboliqueIndicee(this.nomVariable, this.indice.simplifier());
		}
		return this;
	}

	@Override
	public double calculer() {
		double resultat = this.simplifier().calculer();
		return Utils.arrondir(resultat);
	}
}