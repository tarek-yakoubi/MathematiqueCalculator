package fr.pantheonsorbonne.cri;



public class ConstanteQ extends ConstanteExpressionArithmetique {

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ConstanteQ))
			return false;
		ConstanteQ other = (ConstanteQ) obj;
		return denum == other.denum && num == other.num;
	}

	long num;
    long denum;
    double resultat;


    public ConstanteQ(long num, long denum) {
        this.num = num;
        if(denum == 0) {
        	throw new ArithmeticException();
        } 
        else {
        	this.denum = denum;
        }
    }
    

    private static long pgcd(long n1, long n2) {

        while (n1 != n2) {
            if (n1 > n2)
                n1 -= n2;
            else
                n2 -= n1;
        }
        return n2;
    }

    @Override
    public String afficher() {
        return "(" + this.getNum() + "/" + this.getDenum() + ")";
    }

    @Override
    public ExpressionArithmetique simplifier() {
        if(num == 0) {
        	return new ConstanteN(0);
        }
    	long pgcd = pgcd(this.num, this.denum);
        if (pgcd == 1) {
            return super.simplifier();
        } else if (getDenum() / pgcd == 1) {
            return new ConstanteN(getNum() / pgcd);
        } else {
            return new ConstanteQ(getNum() / pgcd, getDenum() / pgcd);
        }
       
    }

    public long getNum() {
        return this.num;
    }

    public long getDenum() {
        return this.denum;
    }

  //@Override
  		public double calculer() {		
  			
  			 resultat =((double)num/(double)denum);
 
  			 return Utils.arrondir(resultat);
  			 
  			
  		}
    

}
