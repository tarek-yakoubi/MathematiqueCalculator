package fr.pantheonsorbonne.cri;

public class Addition extends OpBinaire {

	public Addition(ExpressionArithmetique left, ExpressionArithmetique right) {
		super(left, right, "+");
	}
	
	
	@Override
	public ExpressionArithmetique simplifier() {
		if(left.simplifier() instanceof ConstanteN) {
			if (((ConstanteN) left.simplifier()).value == 0) {
				return right.simplifier();
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
		return new ConstanteN(valLeft.value + valRight.value).simplifier();
	}

	@Override 
	protected ExpressionArithmetique simplifier(ConstanteQ valLeft, ConstanteQ valRight) {
		long num = valLeft.getNum() * valRight.getDenum() + valLeft.getDenum() * valRight.getNum();
		long denum = valLeft.getDenum() * valRight.getDenum();
		return new ConstanteQ(num, denum).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifier(ConstanteN valLeft, ConstanteQ valRight) {
		return simplifier(new ConstanteQ(valLeft.value, 1), valRight).simplifier();
	}


	protected ExpressionArithmetique simplifier(VariableSymbolique valLeft, VariableSymbolique valRight) {
		if(valLeft.getSymbol().equals(valRight.getSymbol()))
			return new Multiplication(new ConstanteN(2), valLeft).simplifier();
		return this;
	}


	@Override
	public ExpressionArithmetique deriver() {
		return new Addition(left.deriver(),right.deriver()).simplifier();
		
	}

	@Override
	public double calculer() {
		
		double resultat = this.left.simplifier().calculer() + this.right.simplifier().calculer();
		return Utils.arrondir(resultat);
	}

	@Override
	protected ExpressionArithmetique simplifier(VariableSymbolique vs, ConstanteN n) {
		return this.simplifier(n, vs);
	}
	
	@Override
	public ExpressionArithmetique deriverPart(VariableSymbolique vs) {
		if (this.left.simplifier() instanceof VariableSymbolique && vs.equals(this.left.simplifier())) {
			return left.deriver().simplifier();
		}
		else if (this.right.simplifier() instanceof VariableSymbolique && vs.equals(this.right.simplifier())) {
			return right.deriver().simplifier();
		}
		return new Addition(this.left.deriverPart(vs).simplifier(), right.deriverPart(vs).simplifier()).simplifier();
	}
	
	
	public ExpressionArithmetique associativite() {
		
		
		ExpressionArithmetique resultat = this;
		
		if(this.left instanceof ConstanteExpressionArithmetique) {

			if(this.left instanceof ConstanteN &&  this.right instanceof Addition ) {

				if((((Addition) this.right).getLeft() instanceof VariableSymbolique) || (((Addition) this.right).getLeft() instanceof VariableSymboliqueIndicee)) {

					if(!(((Addition) this.right).getRight() instanceof VariableSymbolique) && !(((Addition) this.right).getRight() instanceof VariableSymboliqueIndicee)) {
						
						//C'est le cas ou on a un N droite
						if((((Addition) this.right).getRight() instanceof ConstanteN)){
							resultat = new Addition(new ConstanteN((int)((Addition) this.right).getRight().calculer()+(int)this.left.calculer()), ((Addition) this.right).left);
							return resultat.simplifier();
						}
						
						//C'est le cas ou on a un Q droite
						if((((Addition) this.right).getRight() instanceof ConstanteQ)) {
							
							resultat = 
								 new Addition(new ConstanteQ((((int)this.left.calculer()) * ((int)((ConstanteQ) ((Addition) this.right).getRight()).getDenum())+ ((int)((ConstanteQ) ((Addition) this.right).getRight()).getNum()) )  , ((int)((ConstanteQ) ((Addition) this.right).getRight()).getDenum()))
								,new Addition(this.left,(((Addition) this.right).left)));
							
							return resultat;
						
						}
					}
				}

				if((((Addition) this.right).getRight() instanceof VariableSymbolique) || (((Addition) this.right).getRight() instanceof VariableSymboliqueIndicee)) {
					if(!(((Addition) this.right).getLeft() instanceof VariableSymbolique) && !(((Addition) this.right).getLeft() instanceof VariableSymboliqueIndicee)) {
						
						//C'est le cas ou on a un N gauche 
						if((((Addition) this.right).getLeft() instanceof ConstanteN)) {
							resultat = new Addition(new ConstanteN((int)((Addition) this.right).getLeft().calculer()+(int)this.left.calculer()), ((Addition) this.right).right);
							return resultat.simplifier();
						}
						//C'est le cas ou on a un Q gauche 
						if((((Addition) this.right).getLeft() instanceof ConstanteQ)) {
							resultat = new Addition(new ConstanteQ( ((int)this.left.calculer()* ((int)((ConstanteQ) ((Addition) this.right).getLeft()).getDenum())) + (int)((ConstanteQ) ((Addition) this.right).getLeft()).getNum() , (int)((ConstanteQ) ((Addition) this.right).getLeft()).getDenum()) , new Addition(((Addition) this.right).getRight(), this.left)); //essayer de changer de place le this.left
							return resultat.simplifier();
						}
					}
				}
		
				if((((Addition) this.right).getLeft() instanceof ConstanteN) && (((Addition) this.right).getLeft() instanceof ConstanteN)) {
					//Cas ou on a que des N dans l'addition :
					resultat = new ConstanteN((int)this.left.calculer()+(int)this.right.calculer());
					return resultat;
				}
			}		
			if(this.left instanceof ConstanteQ &&  this.right instanceof Addition ) {

				if((((Addition) this.right).getLeft() instanceof VariableSymbolique) || (((Addition) this.right).getLeft() instanceof VariableSymboliqueIndicee)) {

					if(!(((Addition) this.right).getRight() instanceof VariableSymbolique) && !(((Addition) this.right).getRight() instanceof VariableSymboliqueIndicee)) {
						
						//C'est le cas ou on a un N droite
						if((((Addition) this.right).getRight() instanceof ConstanteN)){
							resultat = new Addition(new ConstanteQ( ((int)((Addition)this.right).right.calculer()) * ((int)((ConstanteQ) this.left).getDenum()) + ((int)((ConstanteQ) this.left).getNum()) , ((int)((ConstanteQ) this.left).getDenum())),((Addition) this.right).left);
							return resultat.simplifier();
						}
						
						//C'est le cas ou on a un Q droite
						if((((Addition) this.right).getRight() instanceof ConstanteQ)) {
							
							resultat = new Addition(new ConstanteQ( ((int)((ConstanteQ) this.left).getDenum()) * (int) ((ConstanteQ)((Addition)this.right).right).getNum()  + ((int)((ConstanteQ) this.left).getNum()) * (int) ((ConstanteQ)((Addition)this.right).right).getDenum()  ,  ((int) ((ConstanteQ)((Addition)this.right).right).getDenum() * ((int)((ConstanteQ) this.left).getDenum()) )), ((Addition) this.right).left );
							return resultat;
						
						} 
					}
				}

				if((((Addition) this.right).getRight() instanceof VariableSymbolique) || (((Addition) this.right).getRight() instanceof VariableSymboliqueIndicee)) {
					if(!(((Addition) this.right).getLeft() instanceof VariableSymbolique) && !(((Addition) this.right).getLeft() instanceof VariableSymboliqueIndicee)) {
						
						//C'est le cas ou on a un N gauche 
						if((((Addition) this.right).getLeft() instanceof ConstanteN)) {
							resultat = new Addition(new ConstanteQ( ((int)((Addition)this.right).left.calculer()) * ((int)((ConstanteQ) this.left).getDenum()) + ((int)((ConstanteQ) this.left).getNum()) , ((int)((ConstanteQ) this.left).getDenum())), ((Addition) this.right).right);
							return resultat.simplifier();
						}
						//C'est le cas ou on a un Q gauche 
						if((((Addition) this.right).getLeft() instanceof ConstanteQ)) {
							resultat = new Addition(new ConstanteQ( ((int)((ConstanteQ) this.left).getDenum()) * (int) ((ConstanteQ)((Addition)this.right).left).getNum()  + ((int)((ConstanteQ) this.left).getNum()) * (int) ((ConstanteQ)((Addition)this.right).left).getDenum()  ,  ((int) ((ConstanteQ)((Addition)this.right).left).getDenum() * ((int)((ConstanteQ) this.left).getDenum()) )), ((Addition) this.right).right );
							return resultat.simplifier();
						}
					}
				}
			}	
		}	
		return this;
	}
}
