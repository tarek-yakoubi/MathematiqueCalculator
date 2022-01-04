package fr.pantheonsorbonne.cri;

public class Produit extends OpNaire {

	public Produit(ExpressionArithmetique val, long min, long max) {
		super("∏", min, max, val);
	}

	@Override
	public ExpressionArithmetique simplifier() {		
		VariableSymbolique ind = new VariableSymbolique("i");
		ind.setValue(new ConstanteN(this.borneMin));
		ExpressionArithmetique res = op.simplifier();
		for (long j = this.borneMin; j < this.borneMax; ++j) {
			ind.setValue(new ConstanteN(j+1));
			
			ExpressionArithmetique resAux = new Multiplication(res, op.simplifier()).simplifier();
			res = resAux;
		}
		return res.simplifier();
	}

	@Override
	public ExpressionArithmetique deriver() {
		return this.simplifier().deriver();
	}

	@Override
	public double calculer() {

		return this.simplifier().calculer();
	}

}
