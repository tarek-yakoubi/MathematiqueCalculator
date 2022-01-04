package fr.pantheonsorbonne.cri;


public class Ln extends OpUnaire {

	public Ln(ExpressionArithmetique op) {
		super(op, "ln");
	}

	@Override
	public String afficher() {
		return "(ln(" + value.afficher() + "))";
	}

	@Override
	public ExpressionArithmetique simplifier() {
		ExpressionArithmetique val = this.value.simplifier();
		if (val instanceof ConstanteN) {
			ConstanteN cons = (ConstanteN) val;
			if (cons.value == 1)
				return new ConstanteN(0);
		}
		if (val.equals(ConstanteSymbolique.E)) {
			return new ConstanteN(1);
		}
		return new Ln(val);
	}

	@Override
	public double calculer() {
		double resultat = Math.log(this.value.simplifier().calculer());
		return Utils.arrondir(resultat);
	}

	@Override
	public ExpressionArithmetique deriver() {
		return new Division(value.deriver(), value).simplifier();
	}


}
