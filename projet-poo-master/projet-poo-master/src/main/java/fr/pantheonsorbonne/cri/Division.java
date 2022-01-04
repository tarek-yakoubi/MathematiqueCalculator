package fr.pantheonsorbonne.cri;

import static fr.pantheonsorbonne.cri.Utils.*;

public class Division extends OpBinaire {

	public Division(ExpressionArithmetique leftOp, ExpressionArithmetique rightOp) {
		
		super(leftOp, rightOp, "÷");
		
	}

	@Override
	public ExpressionArithmetique simplifier() {
		if(left.simplifier() instanceof ConstanteN) {
			if (((ConstanteN) left.simplifier()).value == 0) {
				return new ConstanteN(0);
			}
		}
		if (right.simplifier() instanceof ConstanteN) {
			if (((ConstanteN) right.simplifier()).value == 1)
				return left.simplifier();
		}
		return super.simplifier();
	}
	
	@Override
	protected ExpressionArithmetique simplifier(ConstanteN valLeft, ConstanteN valRight) {

		ExpressionArithmetique constQ = new ConstanteQ(valLeft.value, valRight.value);
		return constQ.simplifier();

	}

	@Override
	protected ExpressionArithmetique simplifier(ConstanteQ valLeft, ConstanteQ valRight) {
		ExpressionArithmetique mult = new Multiplication(valLeft,
				new ConstanteQ(valRight.getDenum(), valRight.getNum())).simplifier();
		return mult.simplifier();
	}
	
	@Override
	public ExpressionArithmetique deriver() {
		return new Division(
				new Soustraction(new Multiplication(left.deriver(), right), new Multiplication(left, right.deriver())),
				new Puissance(right, new ConstanteN(2))).simplifier();
	}

	@Override
	public ExpressionArithmetique deriverPart(VariableSymbolique vs) {
		if (this.left.simplifier() instanceof VariableSymbolique && vs.equals(this.left.simplifier())) {
			return new Division(new Multiplication(left.deriver(), right), right);
		}
		else if (this.right.simplifier() instanceof VariableSymbolique && vs.equals(this.right.simplifier())) {
			return new Division(new Multiplication(left, right.deriver()), new Puissance(right, new ConstanteN(2)));
		}
		return new Division(this.left.deriverPart(vs).simplifier(), right.deriverPart(vs).simplifier()).simplifier();
	}
	
	@Override
	public double calculer() {
		
		if(this.right.calculer() == 0.0000) {
        	throw new ArithmeticException();
		}
		double resultat =  this.left.simplifier().calculer() /  this.right.simplifier().calculer();	 
		return Utils.arrondir(resultat);
	}
	
	protected ExpressionArithmetique simplifier(VariableSymbolique valLeft, VariableSymbolique valRight) {
		if (valLeft.getSymbol().equals(valRight.getSymbol()))
			return new ConstanteN(1);
		return this;

	}

	@Override
	protected ExpressionArithmetique simplifier(VariableSymbolique vs, ConstanteN n) {
		if (vs.getValue() == null) {
			return this;
		}
		if (vs.getValue() instanceof ConstanteN) {
			return simplifier(toN(vs.getValue()), n);
		}
		if (vs.getValue() instanceof ConstanteQ) {
			return simplifier(toQ(vs.getValue()), n);
		}
		return this;
	}

}
