package fr.pantheonsorbonne.cri;

abstract public class ConstanteExpressionArithmetique implements ExpressionArithmetique {

    @Override
    public ExpressionArithmetique simplifier() {
        return this;
    }
    

    @Override
    public ExpressionArithmetique deriver() {
        return new ConstanteN(0);
    }

    @Override
    public ExpressionArithmetique deriver(int ordre) {
        return new ConstanteN(0);
    }
    
    @Override
	public ExpressionArithmetique deriverPart(VariableSymbolique vs) {
		if(this instanceof VariableSymbolique && vs.equals(this)) {
			return this.deriver();
		}
		return new ConstanteN(0); 
	}
    
}
