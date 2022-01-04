package fr.pantheonsorbonne.cri;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



class TestSomme {
	@Test
	void test() {		
		
		ExpressionArithmetique s = new Somme(new ConstanteN(5), 0, 5);
		assertEquals("(Σ i=0→5 (5))", s.afficher());
		assertEquals("30", s.simplifier().afficher());

		ExpressionArithmetique varI= new VariableSymbolique("i");

		ExpressionArithmetique s3 = new Somme(varI, 0, 5);
		assertEquals("15", s3.simplifier().afficher());

		ExpressionArithmetique s4 = new Somme(
				new VariableSymboliqueIndicee(new VariableSymbolique("x"), varI), 0, 5);
		assertEquals("(((((x▼0+x▼1)+x▼2)+x▼3)+x▼4)+x▼5)", s4.simplifier().afficher());

		ExpressionArithmetique s5 = new Somme(new Sqrt(varI), 0, 4);
		assertEquals("(Σ i=0→4 ((sqrt(i))))", s5.afficher());
		assertEquals("(((1+(sqrt(2)))+(sqrt(3)))+2)", s5.simplifier().afficher());

		ExpressionArithmetique sMulx = new Somme(new Multiplication(new VariableSymboliqueIndicee(new VariableSymbolique("y"), varI), new ConstanteN(4)), 1, 3);
		assertEquals("(((4*y▼1)+(4*y▼2))+(4*y▼3))", sMulx.simplifier().afficher());
		
		ExpressionArithmetique sMul = new Somme(new Multiplication(varI, new ConstanteN(4)), 1, 3);
		assertEquals("24", sMul.simplifier().afficher());
		
		ExpressionArithmetique sAdd = new Somme(new Addition(new ConstanteN(4), new VariableSymbolique("i")), 0, 3);
		assertEquals("22", sAdd.simplifier().afficher());
		assertEquals("0", sAdd.deriver().afficher());
		assertEquals(22.0000, sAdd.calculer());
	}
}
