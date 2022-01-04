package fr.pantheonsorbonne.cri;

import java.util.Map;
import java.util.TreeMap;

public class VariableSymbolique extends ConstanteExpressionArithmetique implements Comparable<VariableSymbolique> {
	
	public static Map<String, ExpressionArithmetique> listVar = new TreeMap<>();
	protected String symbol;

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VariableSymbolique other = (VariableSymbolique) obj;
		return symbol.equals(other.symbol);
	}

	public VariableSymbolique(String symb) {
		symbol = symb;
		if(!exists()) {
			listVar.put(symbol, null);
		}
	}
	
	public void setValue(ExpressionArithmetique val) {
		listVar.put(symbol, val);
		//value=listVar.get(symbol);
	}
	
	public boolean exists() {
		return listVar.containsKey(symbol);
	}
	
	@Override
	public ExpressionArithmetique simplifier() {
		if (listVar.get(symbol) != null) {
			return getValue().simplifier();
		}
		return this;
	}
	
	@Override
	public String afficher() {
		return symbol;
	}

	public ExpressionArithmetique getValue() {
		return listVar.get(symbol);
	}
	public String getSymbol() {
		return symbol;
	}
	
	@Override
    public ExpressionArithmetique deriver() {
        if(getValue() == null) {
        	return new ConstanteN(1);
        }
		return new ConstanteN(0);
    }

	@Override
	public double calculer() {
		double resultat =  this.getValue().calculer();
		return Utils.arrondir(resultat);
	}

	@Override
	public int compareTo(VariableSymbolique o) {
		return this.symbol.compareTo(o.symbol);
	}

	@Override
	public ExpressionArithmetique deriverPart(VariableSymbolique vs) {
		if(vs.equals(this)) {
			return this.deriver();
		}
		return new ConstanteN(0); 
	}
}
