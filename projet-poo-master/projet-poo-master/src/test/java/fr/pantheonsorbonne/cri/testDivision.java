package fr.pantheonsorbonne.cri;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestDivision {

	@Test
	void test() {
		
		ExpressionArithmetique divNN = new Division(new ConstanteN(8), new ConstanteN(2));
		assertEquals("(8÷2)", divNN.afficher());
		assertEquals("4", divNN.simplifier().afficher());
		
		ExpressionArithmetique divQQ = new Division(new ConstanteQ(1, 2), new ConstanteQ(1, 4));
		assertEquals("((1/2)÷(1/4))", divQQ.afficher());
		assertEquals("2", divQQ.simplifier().afficher());
		
		ExpressionArithmetique divNQ = new Division(new ConstanteN(2), new ConstanteQ(4, 7));
		assertEquals("(2÷(4/7))", divNQ.afficher());
		assertEquals("(7/2)", divNQ.simplifier().afficher());
		
		ExpressionArithmetique divQN = new Division(new ConstanteQ(4, 7), new ConstanteN(2));
		assertEquals("((4/7)÷2)", divQN.afficher());
		assertEquals("(7/2)", divQN.simplifier().afficher());
		
		ExpressionArithmetique mulON = new Division(ConstanteSymbolique.PI, new ConstanteN(5));
		assertEquals("(π÷5)", mulON.afficher());
		assertEquals("(π÷5)", mulON.simplifier().afficher());
		
		ExpressionArithmetique divCC = new Division(new ConstanteQ(1,2), new ConstanteQ(2,5));
		assertEquals("((1/2)÷(2/5))", divCC.afficher());
		assertEquals(1.25, divCC.simplifier().calculer());
		
		ExpressionArithmetique divCl = new Division(new ConstanteQ(1,2), new ConstanteN(5));
		assertEquals("((1/2)÷5)", divCl.afficher());
		assertEquals(10.0, divCl.simplifier().calculer());
	
		ExpressionArithmetique divNNN = new Division(new ConstanteN(7), new ConstanteN(3));
		assertEquals(divNNN.afficher(), "(7÷3)");
		assertEquals(divNNN.simplifier().calculer(), 2.3333);
		
		ExpressionArithmetique divZN = new Division(new ConstanteN(0), new ConstanteN(3));
		assertEquals("(0÷3)", divZN.afficher());
		assertEquals("0", divZN.simplifier().afficher());
		assertEquals(0.0000, divZN.simplifier().calculer());
		
		ExpressionArithmetique div1N = new Division(new ConstanteN(12), new ConstanteN(1));
		assertEquals("(12÷1)", div1N.afficher());
		assertEquals("12", div1N.simplifier().afficher());
		assertEquals(12.0000, div1N.simplifier().calculer());
		
		VariableSymbolique y = new VariableSymbolique("y");
		
		ExpressionArithmetique divVsN = new Division(y , new ConstanteN(5));
		assertEquals("(y÷5)", divVsN.afficher());
		assertEquals("(y÷5)", divVsN.simplifier().afficher());
		y.setValue(new ConstanteN(4));
		assertEquals("(y÷5)", divVsN.afficher());
		assertEquals("(4/5)", divVsN.simplifier().afficher());
		
		
		ExpressionArithmetique divVsVs = new Division(y , y);
		assertEquals("(y÷y)", divVsVs.afficher());
		assertEquals("1", divVsVs.simplifier().afficher());
		
		
		
	}

}
