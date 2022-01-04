package fr.pantheonsorbonne.cri;

public interface ExpressionArithmetique {
	
	String afficher();
	
	ExpressionArithmetique simplifier();

	ExpressionArithmetique deriver();

	ExpressionArithmetique deriver(int ordre);
	
	ExpressionArithmetique deriverPart(VariableSymbolique vs);


	double calculer();
}