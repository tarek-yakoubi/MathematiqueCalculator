package fr.pantheonsorbonne.cri;

public class Somme extends OpNaire {

	public Somme(ExpressionArithmetique val, long min, long max) {
		super("Σ", min, max, val);
	}

	@Override
	public ExpressionArithmetique simplifier() {		
		VariableSymbolique ind = new VariableSymbolique("i");
		ind.setValue(new ConstanteN(this.borneMin));
		ExpressionArithmetique res = op.simplifier();
		for (long j = this.borneMin; j < this.borneMax; ++j) {
			ind.setValue(new ConstanteN(j+1));
			ExpressionArithmetique resAux = new Addition(res, op.simplifier()).simplifier();
			res = resAux;
		}
		return res.simplifier();
	}

	@Override
	public ExpressionArithmetique deriver() {
		return new Somme(op.deriver(), borneMin, borneMax).simplifier();
	}

	@Override
	public double calculer() {

		return this.simplifier().calculer();
	}

}
