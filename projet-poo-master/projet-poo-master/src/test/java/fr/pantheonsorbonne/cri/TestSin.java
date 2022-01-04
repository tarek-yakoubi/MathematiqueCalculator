package fr.pantheonsorbonne.cri;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSin {

	@Test
	void test() {
		
		ExpressionArithmetique sinPi = new Sin(ConstanteSymbolique.PI);
		assertEquals("(sin(π))", sinPi.afficher());
		assertEquals("0", sinPi.simplifier().afficher());
		assertEquals(0.0000, sinPi.simplifier().calculer());
		
		ExpressionArithmetique sinUn = new Sin(new ConstanteN(0));
		assertEquals("(sin(0))", sinUn.afficher());
		assertEquals("0", sinUn.simplifier().afficher());
		assertEquals(0.0000, sinUn.simplifier().calculer());
		
		ExpressionArithmetique sinO = new Sin(new ConstanteN(2));
		assertEquals("(sin(2))", sinO.afficher());
		assertEquals("(sin(2))", sinO.simplifier().afficher());
		assertEquals(0.9093, sinO.simplifier().calculer());
		
		ExpressionArithmetique sinPi2 = new Sin(new Division(ConstanteSymbolique.PI,new ConstanteN(2)));
		assertEquals("(sin((π÷2)))", sinPi2.afficher());
		assertEquals("1", sinPi2.simplifier().afficher());
		assertEquals(1, sinPi2.simplifier().calculer());
		
		ExpressionArithmetique sinPi3 = new Sin(new Division(ConstanteSymbolique.PI,new ConstanteN(3)));
		assertEquals("(sin((π÷3)))", sinPi3.afficher());
		assertEquals("((sqrt(3))÷2)", sinPi3.simplifier().afficher());
		assertEquals(0.8660, sinPi3.simplifier().calculer());
		
		ExpressionArithmetique sinPi4 = new Sin(new Division(ConstanteSymbolique.PI,new ConstanteN(4)));
		assertEquals("(sin((π÷4)))", sinPi4.afficher());
		assertEquals("((sqrt(2))÷2)", sinPi4.simplifier().afficher());
		assertEquals(0.7071, sinPi4.simplifier().calculer());
		
		ExpressionArithmetique sinPi6 = new Sin(new Division(ConstanteSymbolique.PI,new ConstanteN(6)));
		assertEquals("(sin((π÷6)))", sinPi6.afficher());
		assertEquals("(1/2)", sinPi6.simplifier().afficher());
		assertEquals(0.5000, sinPi6.simplifier().calculer());
	}

}
