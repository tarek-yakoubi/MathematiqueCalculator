package fr.pantheonsorbonne.cri;

import java.util.HashMap;
import java.util.Map;

public class ConstanteSymbolique extends ConstanteExpressionArithmetique {

	public static ExpressionArithmetique PI = new ConstanteSymbolique(ConstantesSymboliqueConnues.PI);

	public static ExpressionArithmetique E = new ConstanteSymbolique(ConstantesSymboliqueConnues.EXPONENTIELLE);
	
	private Map<ConstanteSymbolique, ExpressionArithmetique> values;

	public ConstanteSymbolique(ConstantesSymboliqueConnues constanteConnue) {
		this.constant = constanteConnue;
		values = new HashMap<ConstanteSymbolique, ExpressionArithmetique>();
		this.setValue();
	}

	private void setValue() {
		values.put((ConstanteSymbolique) PI, new ConstanteQ(31416, 10000));
		values.put((ConstanteSymbolique) E, new ConstanteQ(27183, 10000));
	}

	ConstantesSymboliqueConnues constant;

	public enum ConstantesSymboliqueConnues {
		PI("π"), EXPONENTIELLE("e");

		String strRepresentation;

		ConstantesSymboliqueConnues(String strRepresentation) {
			this.strRepresentation = strRepresentation;
		}

		public String getStrPresentation() {
			return this.strRepresentation;
		}

	}

	@Override
	public String afficher() {
		return this.constant.getStrPresentation();
	}

	@Override
	public double calculer() {
		double resultat = this.simplifier().calculer();
		try {
			return Utils.arrondir(resultat);
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		if(this.constant == ConstanteSymbolique.ConstantesSymboliqueConnues.EXPONENTIELLE) {
			resultat = this.values.get(E).simplifier().calculer();
			String formatted = String.format("%.4f", resultat);
			try {
				resultat = Double.parseDouble(formatted);
			}catch(NumberFormatException e) {
				e.printStackTrace();
			}
			return resultat;
		}
		
		if(this.constant == ConstanteSymbolique.ConstantesSymboliqueConnues.PI) {
			resultat = this.values.get(PI).simplifier().calculer();
			String formatted = String.format("%.4f", resultat);
			try {
				resultat = Double.parseDouble(formatted);
			}catch(NumberFormatException e) {
				e.printStackTrace();
			}
			return resultat;
		}
		
		return 0;
	}
}
