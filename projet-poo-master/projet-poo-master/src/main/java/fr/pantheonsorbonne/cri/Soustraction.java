package fr.pantheonsorbonne.cri;



public class Soustraction extends OpBinaire {

	public Soustraction(ExpressionArithmetique leftOp, ExpressionArithmetique rightOp) {
		super(leftOp, rightOp, "-");
	}
	
	@Override
	public ExpressionArithmetique simplifier() {
		if(left.simplifier() instanceof ConstanteN) {
			if (((ConstanteN) left.simplifier()).value == 0) {
				if (right.simplifier() instanceof ConstanteN)
					return new ConstanteN(0-((ConstanteN) right.simplifier()).value);
				else if (right.simplifier() instanceof ConstanteQ)
					return new ConstanteQ(0-((ConstanteQ) right.simplifier()).num, ((ConstanteQ) right.simplifier()).denum);
				return new Multiplication(new ConstanteN(-1), right.simplifier()).simplifier();
			}
		}
		if (right.simplifier() instanceof ConstanteN) {
			if (((ConstanteN) right.simplifier()).value == 0) {
				return left.simplifier();
			}
		}
		return super.simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifier(ConstanteN valLeft, ConstanteN valRight) {
		return new ConstanteN(valLeft.value - valRight.value);
	}

	@Override
	public ExpressionArithmetique deriverPart(VariableSymbolique vs) {
		if (this.left.simplifier() instanceof VariableSymbolique && vs.equals(this.left.simplifier())) {
			return left.deriver().simplifier();
		}
		else if (this.right.simplifier() instanceof VariableSymbolique && vs.equals(this.right.simplifier())) {
			return new Soustraction(new ConstanteN(0),right.deriver()).simplifier();
		}
		return new Soustraction(this.left.deriverPart(vs).simplifier(), right.deriverPart(vs).simplifier()).simplifier();
	}
	
	@Override
	protected ExpressionArithmetique simplifier(ConstanteQ valLeft, ConstanteQ valRight) {
		long num = valLeft.getNum() * valRight.getDenum() - valLeft.getDenum() * valRight.getNum();
		long denum = valLeft.getDenum() * valRight.getDenum();

		return new ConstanteQ(num, denum);
	}

	@Override
	protected ExpressionArithmetique simplifier(ConstanteQ valLeft, ConstanteN valRight) {
		return simplifier(valLeft, new ConstanteQ(valRight.value, 1));
	}

	@Override
	public ExpressionArithmetique deriver() {
		return new Soustraction(left.deriver(), right.deriver()).simplifier();

	}
		
	public double calculer() {
		double resultat = this.left.simplifier().calculer() - this.right.simplifier().calculer();
		return Utils.arrondir(resultat);
	}

	@Override
	protected ExpressionArithmetique simplifier(VariableSymbolique vs, ConstanteN n) {
		return this.simplifier(n, vs);
	}

	@Override
	protected ExpressionArithmetique simplifier(VariableSymbolique valLeft, VariableSymbolique valRight) {
		
		return this;
	}


}
