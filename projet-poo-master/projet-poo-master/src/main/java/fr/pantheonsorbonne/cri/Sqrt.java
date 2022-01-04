package fr.pantheonsorbonne.cri;


public class Sqrt extends OpUnaire {

	public Sqrt(ExpressionArithmetique op) {
		super(op, "sqrt");
	}

	private ExpressionArithmetique simplifieN() {

		ExpressionArithmetique val = this.value.simplifier();
		long t = 1;
		if ((val instanceof ConstanteN)) {
			ConstanteN x = ((ConstanteN) val);
			while ((t * t) < x.value) {
				t++;
			}
			if ((t * t) == x.value) {

				return new ConstanteN(t);
			}
		}
		return new Sqrt(this.value.simplifier());
	}

	private ExpressionArithmetique simplifieQ() {

		ConstanteQ val = (ConstanteQ) this.value.simplifier();
		long tnum = 1;
		long tdenum = 1;
		ConstanteN numQ = new ConstanteN(val.num);
		ConstanteN denumQ = new ConstanteN(val.denum);

		if ((numQ instanceof ConstanteN) && (denumQ instanceof ConstanteN)) {

			while ((tnum * tnum) < numQ.value) {
				tnum++;
			}

			while ((tdenum * tdenum) < denumQ.value) {
				tdenum++;
			}

			if ((tnum * tnum) == numQ.value && (tdenum * tdenum) == denumQ.value) {

				return new ConstanteQ(tnum, tdenum);
			}
		}
		return new Sqrt(this.value.simplifier());
	}

	@Override
	public ExpressionArithmetique simplifier() {
		ExpressionArithmetique val = this.value.simplifier();
		if ((val instanceof ConstanteN)) {
			if (((ConstanteN) val).value == 0)
				return new ConstanteN(0);
			return this.simplifieN();
		}

		if ((val instanceof ConstanteSymbolique)) {
			return this;
		}

		if ((val instanceof ConstanteQ)) {
			return this.simplifieQ();
		}
		if(val instanceof Puissance) {
			Puissance sqrtP = (Puissance) val;
			ConstanteN puiss = (ConstanteN) sqrtP.right;
			if(puiss.value == 2){
				return sqrtP.left;
			}
			if( ((ConstanteN) puiss).value > 2 ){
				ConstanteQ p = new ConstanteQ(puiss.value,2);
				return new Sqrt(new Puissance(sqrtP.left.simplifier() ,p.simplifier()).simplifier()).simplifier();
			}

		}
		return new Sqrt(this.value.simplifier());
	}

	@Override
	public String afficher() {
		return "(sqrt" + "(" + value.afficher() + ")" + ")";
	}

	@Override
	public double calculer() {

		double resultat = Math.sqrt(this.value.simplifier().calculer());
		return Utils.arrondir(resultat);
	}

	@Override
	public ExpressionArithmetique deriver() {
		return new Division(value.deriver(), new Multiplication(new ConstanteN(2), new Sqrt(value))).simplifier();
	}

	
	
	
}
