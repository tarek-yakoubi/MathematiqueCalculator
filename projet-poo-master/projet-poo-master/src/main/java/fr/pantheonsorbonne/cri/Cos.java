
package fr.pantheonsorbonne.cri;



public class Cos extends OpUnaire {

    public Cos(ExpressionArithmetique op) {
        super(op, "cos");
    }


    @Override
    public ExpressionArithmetique simplifier() {
    	
        ExpressionArithmetique val = this.value.simplifier();
        
        if (val instanceof ConstanteSymbolique) {
            ConstanteSymbolique cons = (ConstanteSymbolique) val;
            if (cons.constant == ConstanteSymbolique.ConstantesSymboliqueConnues.PI)
                return new ConstanteN(-1);
        }
        if (val instanceof ConstanteN) {
            ConstanteN value = (ConstanteN) val;
            if (value.value == 0)
                return new ConstanteN(1);
        }

        if(val instanceof Division){
			Division cons = (Division) val;
			ConstanteN rg = (ConstanteN) cons.right;
			ConstanteSymbolique lf = (ConstanteSymbolique) cons.left;
			if (lf.constant ==  ConstanteSymbolique.ConstantesSymboliqueConnues.PI && rg.value == 2) {
				return new ConstanteN(0);
			}
			if (lf.constant ==  ConstanteSymbolique.ConstantesSymboliqueConnues.PI && rg.value == 6) {
				Sqrt s = new Sqrt(new ConstanteN(3));
				return new Division(s.simplifier(),new ConstanteN(2));
			}
			if (lf.constant ==  ConstanteSymbolique.ConstantesSymboliqueConnues.PI && rg.value == 4) {
				Sqrt s = new Sqrt(new ConstanteN(2));
				return new Division(s.simplifier(),new ConstanteN(2));
			}
			
			if (lf.constant ==  ConstanteSymbolique.ConstantesSymboliqueConnues.PI && rg.value == 3) {
				return new ConstanteQ(1,2);
			}	
		}
		return new Cos(val);
      }

	@Override
	public double calculer() {	
		double resultat = Math.cos(this.value.simplifier().calculer());
		return Utils.arrondir(resultat);
	}

	@Override
    public ExpressionArithmetique deriver() {
        return new Multiplication(new Soustraction(new ConstanteN(0), value.deriver()), new Sin(value)).simplifier();
    }

}

