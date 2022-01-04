package fr.pantheonsorbonne.cri;

public abstract class OpNaire implements ExpressionArithmetique {

	protected long borneMin;
	protected long borneMax;
	protected String symbol;
	protected final ExpressionArithmetique op;

	protected OpNaire(String symb, long borneMin, long borneMax, ExpressionArithmetique op) {
		this.op = op;
		this.symbol = symb;
		this.borneMin = borneMin;
		this.borneMax = borneMax;
	}
	
	
	@Override
	public String afficher() {
		return "(" + this.symbol + " i=" + this.borneMin + "→" + this.borneMax + " (" + this.op.afficher() + "))";
	}
	
	@Override
	public ExpressionArithmetique deriverPart(VariableSymbolique vs) {
		if(op.simplifier() instanceof VariableSymbolique && vs.equals(op.simplifier())) {
			return this.deriver();
		}
		return new ConstanteN(0); 
	}
	
	@Override
	public ExpressionArithmetique deriver(int ordre) {
		this.simplifier();
		ExpressionArithmetique resultat = this;
		for(int i=0; i<ordre; i++) {
			resultat = resultat.deriver().simplifier();
		}
		return resultat.simplifier();
	}

}
