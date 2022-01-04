package fr.pantheonsorbonne.cri;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



class TestDerivation {

	@Test
	void test() {		
		ExpressionArithmetique derN = new ConstanteN(5);
		assertEquals("0", derN.deriver().afficher());
		ExpressionArithmetique derQ = new ConstanteQ(5, 3);
		assertEquals("0", derQ.deriver().simplifier().afficher());
		
		ExpressionArithmetique derAddNN = new Addition(new ConstanteN(5), new ConstanteN(3));
		assertEquals("0", derAddNN.deriver().simplifier().afficher());
		ExpressionArithmetique derAddQN = new Addition(new ConstanteQ(5, 4), new ConstanteN(3));
		assertEquals("0", derAddQN.deriver().simplifier().afficher());
		
		ExpressionArithmetique derAddVsN = new Addition(new VariableSymbolique("x"), new ConstanteN(3));
		assertEquals("1", derAddVsN.deriver().simplifier().afficher());
		
		ExpressionArithmetique derAddVsVs = new Addition(new VariableSymbolique("x"), new VariableSymbolique("x"));
		assertEquals("2", derAddVsVs.deriver().afficher());
		
		ExpressionArithmetique derMulVsN = new Multiplication(new VariableSymbolique("x"), new ConstanteN(3));
		assertEquals("3", derMulVsN.deriver().simplifier().afficher());
		
		ExpressionArithmetique derMulVsVs = new Multiplication(new VariableSymbolique("x"), new VariableSymbolique("x"));
		assertEquals("(2*x)", derMulVsVs.deriver().simplifier().afficher());
		
		ExpressionArithmetique derSin = new Sin(new VariableSymbolique("x"));
		assertEquals("(cos(x))", derSin.simplifier().deriver().afficher());
		
		ExpressionArithmetique derCos = new Cos(new VariableSymbolique("x"));
		assertEquals("((-1)*(sin(x)))", derCos.simplifier().deriver().afficher());
		
		ExpressionArithmetique derLn = new Ln(new VariableSymbolique("x"));
		assertEquals("(1÷x)", derLn.simplifier().deriver().afficher());
		
		ExpressionArithmetique derExp = new Exponentielle(new VariableSymbolique("x"));
		assertEquals("(exp(x))", derExp.deriver().simplifier().afficher());
		
		ExpressionArithmetique derSqrt = new Sqrt(new VariableSymbolique("x"));
		assertEquals("(1÷(2*(sqrt(x))))", derSqrt.simplifier().deriver().afficher());
	}
	
	@Test
	void testOrdre() {		
		ExpressionArithmetique derN = new ConstanteN(5);
		assertEquals("0", derN.deriver(2).afficher());
		ExpressionArithmetique derQ = new ConstanteQ(5, 3);
		assertEquals("0", derQ.deriver(2).simplifier().afficher());
		
		ExpressionArithmetique derAddNN = new Addition(new ConstanteN(5), new ConstanteN(3));
		assertEquals("0", derAddNN.deriver(2).simplifier().afficher());
		ExpressionArithmetique derAddQN = new Addition(new ConstanteQ(5, 4), new ConstanteN(3));
		assertEquals("0", derAddQN.deriver(2).simplifier().afficher());
		
		ExpressionArithmetique derAddVsN = new Addition(new VariableSymbolique("x"), new ConstanteN(3));
		assertEquals("0", derAddVsN.deriver(2).simplifier().afficher());
		
		ExpressionArithmetique derAddVsVs = new Addition(new VariableSymbolique("x"), new VariableSymbolique("x"));
		assertEquals("0", derAddVsVs.deriver(2).afficher());
		
		ExpressionArithmetique derMulVsN = new Multiplication(new VariableSymbolique("x"), new ConstanteN(3));
		assertEquals("0", derMulVsN.simplifier().deriver(2).afficher());
		
		ExpressionArithmetique derMulVsVs = new Multiplication(new VariableSymbolique("x"), new VariableSymbolique("x"));
		assertEquals("2", derMulVsVs.simplifier().deriver(2).afficher());
		
		ExpressionArithmetique derSin = new Sin(new VariableSymbolique("x"));
		assertEquals("((-1)*(sin(x)))", derSin.simplifier().deriver(2).simplifier().afficher());
		
		ExpressionArithmetique derCos = new Cos(new VariableSymbolique("x"));
		assertEquals("((-1)*(cos(x)))", derCos.simplifier().deriver(2).simplifier().afficher());
		
		ExpressionArithmetique derLn = new Ln(new VariableSymbolique("x"));
		assertEquals("((-1)÷(x^2))", derLn.simplifier().deriver(2).afficher());
		
		ExpressionArithmetique derExp = new Exponentielle(new VariableSymbolique("x"));
		assertEquals("(exp(x))", derExp.deriver().simplifier().afficher());
		
		ExpressionArithmetique derSqrt = new Sqrt(new VariableSymbolique("x"));
		assertEquals("(1÷(2*(sqrt(x))))", derSqrt.simplifier().deriver().afficher());
	}

}
