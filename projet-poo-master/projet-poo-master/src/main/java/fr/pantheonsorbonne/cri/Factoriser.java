package fr.pantheonsorbonne.cri;

public class Factoriser {
	/*private ExpressionArithmetique ea;
	private ExpressionArithmetique a;
	private ExpressionArithmetique b;
	private ExpressionArithmetique c;
	private ExpressionArithmetique x;
	private ExpressionArithmetique y;

	public Factoriser(ExpressionArithmetique ea) {
		this.ea = ea;
	}
	
	public ExpressionArithmetique factoriser() {
		ExpressionArithmetique val = this.ea;
		if (val instanceof Addition) {
			Addition addExt = (Addition) val;
			if(addExt.left instanceof Addition)
			{
				Addition addInt = (Addition) addExt.simplifier();
				if(addInt.right instanceof Multiplication) {

					Multiplication multRight = (Multiplication) addInt.right.simplifier();
					if(multRight.left instanceof ConstanteN && multRight.right instanceof Puissance) {
						ConstanteN multN = (ConstanteN) multRight.left.simplifier() ;
						Puissance vsRight = (Puissance) multRight.right.simplifier() ;
						this.c = new Sqrt(multN.simplifier()).simplifier();
						this.y = new Sqrt(vsRight.simplifier()).simplifier();
					}
				}

				if(addInt.right instanceof VariableSymbolique) {
					VariableSymbolique vsLeft = (VariableSymbolique) addInt.simplifier();
					this.y = new Sqrt(vsLeft.simplifier()).simplifier();
				}

				if(addInt.right instanceof ConstanteN) {
					ConstanteN c3 = (ConstanteN) addInt.simplifier();
					this.c = new Sqrt(c3.simplifier()).simplifier();
				}


				if(addInt.left instanceof Multiplication) {
					ConstanteN b2 = (ConstanteN) addInt.left.simplifier();
					this.b = b2.simplifier();
				}
			}

			if(addExt.left instanceof Multiplication){

				Multiplication multLeft = (Multiplication) addExt.simplifier();
				if(multLeft.left instanceof ConstanteN && multLeft.right instanceof Puissance) {
					ConstanteN multN = (ConstanteN) multLeft.left.simplifier() ;
					Puissance vsRight = (Puissance) multLeft.right.simplifier() ;
					this.a = new Sqrt(multN.simplifier());
					this.x = new Sqrt(vsRight.simplifier()).simplifier();
				}	
			}
			if(addExt.left instanceof Multiplication) {
				ConstanteN a1 = (ConstanteN) addExt.left.simplifier();
				this.a = new Sqrt(a1.simplifier());
			}
			if(addExt.left instanceof VariableSymbolique) {
				VariableSymbolique vsLeft = (VariableSymbolique) addExt.simplifier();
				this.x = new Sqrt(vsLeft.simplifier()).simplifier();
			}
			ConstanteN a1 = (ConstanteN) this.a;
			ConstanteN c1 = (ConstanteN) this.c;;
		    VariableSymbolique x1 = (VariableSymbolique) this.x;
		    VariableSymbolique y1 = (VariableSymbolique) this.y;
			
		    return new Puissance(new Addition(new Multiplication(a1,x1).simplifier(),new Multiplication(c1,y1).simplifier()).simplifier(),new ConstanteN(2)).simplifier();
		}
		if (val instanceof Soustraction) {
			Soustraction addExt = (Soustraction) val.simplifier();
			if(addExt.left instanceof Addition)
			{
				Addition addInt = (Addition) addExt.simplifier();
				if(addInt.right instanceof Multiplication) {

					Multiplication multRight = (Multiplication) addInt.simplifier();
					if(multRight.left instanceof ConstanteN && multRight.right instanceof Puissance) {
						ConstanteN multN = (ConstanteN) multRight.left.simplifier() ;
						Puissance vsRight = (Puissance) multRight.right.simplifier() ;
						this.c = new Sqrt(multN.simplifier());
						this.y = new Sqrt(vsRight.simplifier()).simplifier();
					}
				}

				if(addInt.right instanceof VariableSymbolique) {
					VariableSymbolique vsLeft = (VariableSymbolique) addInt.simplifier();
					this.y = new Sqrt(vsLeft.simplifier()).simplifier();
				}

				if(addInt.right instanceof ConstanteN) {
					ConstanteN c3 = (ConstanteN) addInt.simplifier();
					this.y = new Sqrt(c3.simplifier()).simplifier();
				} 


				if(addInt.left instanceof Multiplication) {
					ConstanteN b2 = (ConstanteN) addInt.left.simplifier();
					this.b = new ConstanteN(b2.value).simplifier();
				}
			}

			if(addExt.left instanceof Multiplication){

				Multiplication multLeft = (Multiplication) addExt.simplifier();
				if(multLeft.left instanceof ConstanteN && multLeft.right instanceof Puissance) {
					ConstanteN multN = (ConstanteN) multLeft.left.simplifier() ;
					Puissance vsRight = (Puissance) multLeft.simplifier() ;
					this.a = new Sqrt(multN.simplifier());
					this.x = new Sqrt(vsRight.simplifier()).simplifier();
				}	
			}
			if(addExt.left instanceof Multiplication) {
				ConstanteN a1 = (ConstanteN) addExt.left.simplifier();
				this.a = new Sqrt(a1.simplifier());
			}
			if(addExt.left instanceof VariableSymbolique) {
				VariableSymbolique vsLeft = (VariableSymbolique) addExt.simplifier();
				this.x = new Sqrt(vsLeft.simplifier()).simplifier();
			}
			return new Puissance(new Addition(new VariableSymbolique("x"),new VariableSymbolique("x")),new ConstanteN(2));
		}
		return val;	
		
	}
	
	private boolean isPolynome() {
        // vÃ©rifier si l'ea est de la forme a^2+2ab+b^2 :
        // 1. verifier que l'ea est une addition/soustraction entre 2 ea ou entre 1 addition/soustraction et
        // une ea
        // extraire un a un les ea dans les additions/soustractions
        if(isAddition(ea)) {
            Addition ad = (Addition) ea;
            a = ad.left;
            b = ad.right;
            if(isAddition(a)) {
                a = ad.left;
                b = ad.right;
                if(a instanceof VariableSymbolique) {
                    
                }
            }else if(isSoustraction(ea)) {
                Soustraction sous = (Soustraction) ea;
                a = sous.left;
                b = sous.right;
            }
        }else if(isSoustraction(ea)) {
            Soustraction sous = (Soustraction) ea;
            a = sous.left;
            b = sous.right;
            
            if(isSoustraction(b)) {
                a = sous.left;
                b = sous.right;
            }else if(isAddition(b)) {
                Addition ad = (Addition) ea;
                a = ad.left;
                b = ad.right ;
            }
        }
		return false;
    }

    public boolean isCarre(ExpressionArithmetique ea) {
        if (isAddition(ea) || isMultiplication(ea) || isSoustraction(ea)) {
            Puissance carre = (Puissance) ea;
            if (carre instanceof Puissance) {
                return true;
            }
        }
        return false;
    }

    public boolean isAddition(ExpressionArithmetique ea) {
        if (ea instanceof Addition) {
            Addition ad = (Addition) ea;
            if (ad.left instanceof Addition) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isSoustraction(ExpressionArithmetique ea) {
        if(ea instanceof Soustraction) {
            Soustraction sous = (Soustraction) ea;
            if(sous.right instanceof Soustraction) {
                return true;
            }
        }
        return false;
    }

    public boolean isMultiplication(ExpressionArithmetique ea) {
        if (ea instanceof Multiplication) {
            Multiplication mult = (Multiplication) ea;
            if (mult.left instanceof Multiplication) {
                return true;
            }
        }
        return false;
    }
	
	
	
	*/
	
}