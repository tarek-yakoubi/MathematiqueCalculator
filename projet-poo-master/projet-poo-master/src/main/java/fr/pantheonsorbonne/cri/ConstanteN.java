package fr.pantheonsorbonne.cri;



public class ConstanteN extends ConstanteExpressionArithmetique {

     public long value;

    public ConstanteN(long value) {
        this.value = value;
    }


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ConstanteN))
			return false;
		ConstanteN other = (ConstanteN) obj;
		return value == other.value;
	}

	@Override
    public String afficher() {
    	if(this.value<0)
    		return "(" + value + ")";
        return Long.toString(value);
    }

	
	public double calculer() {
		return (double)this.value;	 	
	}
}
