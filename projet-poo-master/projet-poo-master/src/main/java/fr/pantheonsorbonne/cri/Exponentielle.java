package fr.pantheonsorbonne.cri;

public class Exponentielle extends OpUnaire {

    public Exponentielle(ExpressionArithmetique op) {
        super(op, "exp");
    }


    @Override
    public ExpressionArithmetique simplifier() {
        this.value = this.value.simplifier();
        if (this.value instanceof ConstanteN) {
            ConstanteN cons = (ConstanteN) this.value;
            if (cons.value == 0)
                return new ConstanteN(1);
        }
        
        return this;
    }


	
	public double calculer() {
		double resultat = Math.exp(this.value.calculer());
		 
		return Utils.arrondir(resultat);
	}


	@Override
	public ExpressionArithmetique deriver() {
		return new Multiplication(value.deriver(), this).simplifier();
	}

}
