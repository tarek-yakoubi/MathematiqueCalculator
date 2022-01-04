package fr.pantheonsorbonne.cri;


public abstract class OpUnaire implements ExpressionArithmetique {

	protected ExpressionArithmetique value;
    protected String operationName;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof OpUnaire))
			return false;
		OpUnaire other = (OpUnaire) obj;
		return operationName.equals(other.operationName) && value.equals(other.value);
	}
    
    public OpUnaire(ExpressionArithmetique op, String operationName) {
        this.operationName = operationName;
        this.value = op;
    }
    
	

    @Override
    public String afficher() {

        return "(" + this.operationName + "(" + value.afficher() + "))";
    }
    
    @Override
	public ExpressionArithmetique deriverPart(VariableSymbolique vs) {
		if(value instanceof VariableSymbolique && vs.equals(value)) {
			return this.deriver();
		}
		return new ConstanteN(0); 
	}
    
    @Override
	public ExpressionArithmetique deriver(int ordre) {
		ExpressionArithmetique resultat = this;
		for(int i=0; i<ordre; i++) {
			resultat = resultat.deriver();
		}
		return resultat;
	}
}
