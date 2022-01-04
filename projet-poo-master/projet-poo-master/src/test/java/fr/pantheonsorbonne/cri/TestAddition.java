package fr.pantheonsorbonne.cri;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



class TestAddition {
	@Test
	void test() {
		
		ExpressionArithmetique addNN = new Addition(new ConstanteN(2), new ConstanteN(8));
		assertEquals("(2+8)", addNN.afficher());
		assertEquals("10", addNN.simplifier().afficher());
		assertEquals(10.0000, addNN.calculer());
		
		ExpressionArithmetique addQQ = new Addition(new ConstanteQ(2, 7), new ConstanteQ(4, 7));
		assertEquals("((2/7)+(4/7))", addQQ.afficher());
		assertEquals("(6/7)", addQQ.simplifier().afficher());
		//assertEquals(0.8571, addQQ.calculer());
		
		ExpressionArithmetique addNQ = new Addition(new ConstanteN(2), new ConstanteQ(4, 7));
		assertEquals("(2+(4/7))", addNQ.afficher());
		assertEquals("(18/7)", addNQ.simplifier().afficher());
		assertEquals(2.5714, addNQ.calculer());
		
		ExpressionArithmetique addQN = new Addition(new ConstanteQ(4, 7), new ConstanteN(2));
		assertEquals("((4/7)+2)", addQN.afficher());
		assertEquals("(18/7)", addQN.simplifier().afficher());
		
		ExpressionArithmetique addON = new Addition(ConstanteSymbolique.E, new ConstanteN(5));
		assertEquals("(e+5)", addON.afficher());
		assertEquals("(e+5)", addON.simplifier().afficher());
		
		ExpressionArithmetique divCC = new Addition(new ConstanteQ(1,2), new ConstanteQ(2,5));
		assertEquals("((1/2)+(2/5))", divCC.afficher());
		assertEquals("(9/10)", divCC.simplifier().afficher());
		assertEquals(0.9000, divCC.calculer());

		ExpressionArithmetique addNQ2 = new Addition(new ConstanteN(1), new ConstanteQ(3,4));
		assertEquals("(1+(3/4))", addNQ2.afficher());
		assertEquals("(7/4)", addNQ2.simplifier().afficher());
		assertEquals(1.7500, addNQ2.calculer());
		
		ExpressionArithmetique addLnN = new Addition(new Ln(new ConstanteN(4)), new ConstanteN(2));
		assertEquals("((ln(4))+2)", addLnN.afficher());
		assertEquals("((ln(4))+2)", addLnN.simplifier().afficher());
		assertEquals(3.3863, addLnN.calculer());
			
		ExpressionArithmetique add1 = new Addition(new ConstanteN(2), new ConstanteN(8));
		ExpressionArithmetique add2 = new Addition(new ConstanteN(2), new ConstanteN(8));
		ExpressionArithmetique add3 = new Addition(new ConstanteN(3), new ConstanteN(8));
		assertTrue(add1.equals(add2));
		assertFalse(add1.equals(add3));
		
		
		ExpressionArithmetique addQ1 = new Addition(new ConstanteQ(2,3), new ConstanteQ(1,2));
		ExpressionArithmetique addQ2 = new Addition(new ConstanteQ(2,3), new ConstanteQ(1,2));
		ExpressionArithmetique addQ3 = new Addition(new ConstanteQ(3,2), new ConstanteQ(2,1));
		assertTrue(addQ1.equals(addQ2));
		assertFalse(addQ1.equals(addQ3));
		
		Addition addAss1 = new Addition(new ConstanteN(2), new Addition(new ConstanteN(1), new ConstanteN(3)));
		assertEquals("(2+(1+3))", addAss1.afficher());
		assertEquals("6", addAss1.associativite().afficher());
		assertEquals("6", addAss1.simplifier().afficher());
		
		
		Addition addAss5 = new Addition(new ConstanteN(2), new Addition(new VariableSymbolique("x"), new ConstanteQ(1,5)));
		assertEquals("(2+(x+(1/5)))", addAss5.afficher());
		assertEquals("((11/5)+(2+x))", addAss5.associativite().afficher());
		
		
		Addition addAss6 = new Addition(new ConstanteN(2), new Addition(new ConstanteQ(1,3), new VariableSymbolique("x")));
		assertEquals("(2+((1/3)+x))", addAss6.afficher());
		assertEquals("((7/3)+(x+2))", addAss6.associativite().afficher());
		
		
		Addition addAss7 = new Addition(new ConstanteQ(2,5), new Addition(new VariableSymbolique("x"), new ConstanteN(1)));
		assertEquals("((2/5)+(x+1))", addAss7.afficher());
		assertEquals("((7/5)+x)", addAss7.associativite().afficher());
		
		Addition addAss8 = new Addition(new ConstanteQ(2,5), new Addition(new ConstanteN(2), new VariableSymbolique("x")));
		assertEquals("((2/5)+(2+x))", addAss8.afficher());
		assertEquals("((12/5)+x)", addAss8.associativite().afficher());
		
		Addition addAss9 = new Addition(new ConstanteQ(2,5), new Addition(new VariableSymbolique("x"), new ConstanteQ(4,7)));
		assertEquals("((2/5)+(x+(4/7)))", addAss9.afficher());
		assertEquals("((34/35)+x)", addAss9.associativite().afficher());
		
		
		Addition addAss10 = new Addition(new ConstanteQ(2,5), new Addition(new ConstanteQ(4,7),new VariableSymbolique("x")));
		assertEquals("((2/5)+((4/7)+x))", addAss10.afficher());
		assertEquals("((34/35)+x)", addAss10.associativite().afficher());
		
	}
}