package fr.pantheonsorbonne.cri;


public class Sin extends OpUnaire {

	public Sin(ExpressionArithmetique op) {
		super(op, "sin");

	}

	@Override
	public ExpressionArithmetique simplifier() {
		ExpressionArithmetique val = this.value.simplifier();

		if (val instanceof ConstanteSymbolique) {
			ConstanteSymbolique cons = (ConstanteSymbolique) val;
			if (cons.constant == ConstanteSymbolique.ConstantesSymboliqueConnues.PI)
				return new ConstanteN(0);
		}
		if (val instanceof ConstanteN) {
			ConstanteN value = (ConstanteN) val;
			if (value.value == 0)
				return new ConstanteN(0);
		}
		if(val instanceof Division){
			Division cons = (Division) val;
			ConstanteN rg = (ConstanteN) cons.right;
			ConstanteSymbolique lf = (ConstanteSymbolique) cons.left;
			if (lf.constant ==  ConstanteSymbolique.ConstantesSymboliqueConnues.PI && rg.value == 2) {
				return new ConstanteN(1);
			}
			if (lf.constant ==  ConstanteSymbolique.ConstantesSymboliqueConnues.PI && rg.value == 3) {
				Sqrt s = new Sqrt(new ConstanteN(3));
				return new Division(s.simplifier(),new ConstanteN(2));
			}
			if (lf.constant ==  ConstanteSymbolique.ConstantesSymboliqueConnues.PI && rg.value == 4) {
				Sqrt s = new Sqrt(new ConstanteN(2));
				return new Division(s.simplifier(),new ConstanteN(2));
			}
			
			if (lf.constant ==  ConstanteSymbolique.ConstantesSymboliqueConnues.PI && rg.value == 6) {
				return new ConstanteQ(1,2);
			}	
		}
		return new Sin(val);
	}

	@Override
	public double calculer() {
		double resultat = Math.sin(this.value.simplifier().calculer());
		return Utils.arrondir(resultat);
	}
	@Override
    public ExpressionArithmetique deriver() {
        return new Multiplication(value.deriver(), new Cos(value)).simplifier();
    }
}
