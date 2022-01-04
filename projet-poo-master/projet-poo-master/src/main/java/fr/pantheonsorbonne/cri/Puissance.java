package fr.pantheonsorbonne.cri;



public class Puissance extends OpBinaire {

	public Puissance(ExpressionArithmetique val, ExpressionArithmetique power) {
		super(val, power, "^");

	}
	
	@Override
	public ExpressionArithmetique simplifier() {
		
		if (right.simplifier() instanceof ConstanteN) {
			if (((ConstanteN) right.simplifier()).value == 0) {
				return new ConstanteN(1);
			}else if (((ConstanteN) right.simplifier()).value == 1)
				return left.simplifier();
		}
		if(left.simplifier() instanceof ConstanteN) {
			
			if (((ConstanteN) left.simplifier()).value == 0) {
				return new ConstanteN(0);
			}else if (((ConstanteN) left.simplifier()).value == 1)
				return left.simplifier();
		}
		
		
		//sqrt

		if(left.simplifier() instanceof Sqrt) {
			Sqrt sqrt = (Sqrt) this.left.simplifier();
			if (((ConstanteN) right.simplifier()).value == 2) {
				if(sqrt.value instanceof ConstanteN) {
					ConstanteN n = (ConstanteN) sqrt.value.simplifier();
					return new ConstanteN(n.value);
				}
				if(sqrt.value instanceof ConstanteQ) {
					ConstanteQ n = (ConstanteQ) sqrt.value;
					return new ConstanteQ(n.num,n.denum).simplifier();
				}
				if(sqrt.value instanceof VariableSymbolique) {
					VariableSymbolique n = (VariableSymbolique) sqrt.value;
					return new VariableSymbolique(n.symbol).simplifier();
				}
			}
			if ((((ConstanteN) right.simplifier()).value) > 2) {
				ConstanteN pow = (ConstanteN) this.right.simplifier();
				if(sqrt.value instanceof ConstanteN) {
					ConstanteN n = (ConstanteN) sqrt.value.simplifier();
					return new Puissance(new ConstanteN(n.value).simplifier(),new ConstanteQ(pow.value,2).simplifier()).simplifier();
				}
				if(sqrt.value instanceof ConstanteQ) {
					ConstanteQ n = (ConstanteQ) sqrt.value;
					return new Puissance(new ConstanteQ(n.num,n.denum).simplifier(),new ConstanteQ(pow.value,2).simplifier()).simplifier();
				}
				if(sqrt.value instanceof VariableSymbolique) {
					VariableSymbolique n = (VariableSymbolique) sqrt.value;
					return new Puissance(new VariableSymbolique(n.symbol).simplifier(),new ConstanteQ(pow.value,2).simplifier()).simplifier();
				}	
			}
		}
		
		
		return super.simplifier();
	}
	
	@Override
	protected ExpressionArithmetique simplifier(ConstanteQ valLeft, ConstanteN puissance) {

		ConstanteQ val = valLeft;
		if (puissance.value >= 1) {
			long num = 1;
			long denum = 1;
			if (puissance.value == 0) {
				return new ConstanteN(1);
			}
			int i = 1;
			while (i <= puissance.value) {
				num = num * val.num;
				denum = denum * val.denum;
				i++;
			}

			return new ConstanteQ(num, denum).simplifier();

		}
		if (puissance.value < (-1)) {
			long num = 1;
			long denum = 1;
			puissance.value = -puissance.value;
			int i = 1;
			while (i <= puissance.value) {
				num = num * val.num;
				denum = denum * val.denum;
				i++;
			}
			if (num == 1)
				return new ConstanteN(denum);

			return new ConstanteQ(denum, num).simplifier();
		}
		return this;
	}

	@Override
	protected ExpressionArithmetique simplifier(ConstanteN valLeft, ConstanteN puissance) {

		if (puissance.value > 1) {
			long p = 1;
			int i = 1;
			while (i <= puissance.value) {
				p = p * (valLeft.value);
				i++;
			}
			return new ConstanteN(p);
		}
		if (puissance.value < (-1)) {
			long p = 1;
			long powPositif = -puissance.value;
			int i = 1;
			while (i <= powPositif) {
				p = p * valLeft.value;
				i++;
			}
			return new ConstanteQ(1, p);
		}
		return this;

	}

	@Override
	protected ExpressionArithmetique simplifier(ConstanteQ valLeft, ConstanteQ valRight) {

		return null;
	}

	@Override
	public double calculer() {

		double valeur = this.left.simplifier().calculer();
		double puissance = this.right.simplifier().calculer();
		double resultat = 1;
		
		while(puissance != 0) {
			resultat *= valeur;
			puissance--;	
		}
		
		return Utils.arrondir(resultat);
		
	}

	@Override
	protected ExpressionArithmetique simplifier(VariableSymbolique valLeft, VariableSymbolique valRight) {
		return this;
	}
	
	@Override
	public ExpressionArithmetique deriver() {
		return new Multiplication(right, new Puissance(left, new Soustraction(right, new ConstanteN(1)).simplifier())).simplifier();
	}
	
	@Override
	public ExpressionArithmetique deriverPart(VariableSymbolique vs) {
		if (this.left.simplifier() instanceof VariableSymbolique && vs.equals(this.left.simplifier())) {
			return this.deriver().simplifier();
		}
		
		return new ConstanteN(0);
	}

	@Override
	protected ExpressionArithmetique simplifier(VariableSymbolique vs, ConstanteN puissance) {
		if (puissance.value == 0) {
			return new ConstanteN(1);
		}
		if (puissance.value == 1) {
			return vs;
		}
		if (puissance.value > 1) {
			return this;
		}
		return this;
	}

	
}
