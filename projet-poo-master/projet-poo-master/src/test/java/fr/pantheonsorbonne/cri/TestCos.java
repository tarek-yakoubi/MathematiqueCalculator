package fr.pantheonsorbonne.cri;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestCos {

	@Test
	void test() {
		
		ExpressionArithmetique cosPi = new Cos(ConstanteSymbolique.PI);
		assertEquals("(cos(π))", cosPi.afficher());

		assertEquals("(-1)", cosPi.simplifier().afficher());

		assertEquals("(-1)", cosPi.simplifier().afficher());

		
		ExpressionArithmetique cosUn = new Cos(new ConstanteN(0));
		assertEquals("(cos(0))", cosUn.afficher());
		assertEquals("1", cosUn.simplifier().afficher());
		
		ExpressionArithmetique cosO = new Cos(new ConstanteN(2));
		assertEquals("(cos(2))", cosO.afficher());
		assertEquals("(cos(2))", cosO.simplifier().afficher());
		assertEquals(-0.4161,cosO.simplifier().calculer());
		
		ExpressionArithmetique cos = new Cos(new ConstanteN(4));
		ExpressionArithmetique cosBis = new Cos(new ConstanteN(4));
		assertEquals(true,cos.equals(cosBis));
		
		ExpressionArithmetique cosPi2 = new Cos(new Division(ConstanteSymbolique.PI,new ConstanteN(2)));
		assertEquals("(cos((π÷2)))", cosPi2.afficher());
		assertEquals("0", cosPi2.simplifier().afficher());
		assertEquals(0.0000, cosPi2.simplifier().calculer());
		
		ExpressionArithmetique cosPi3 = new Cos(new Division(ConstanteSymbolique.PI,new ConstanteN(3)));
		assertEquals("(cos((π÷3)))", cosPi3.afficher());
		assertEquals("(1/2)", cosPi3.simplifier().afficher());
		assertEquals(0.5000, cosPi3.simplifier().calculer());
		
		ExpressionArithmetique cosPi4 = new Cos(new Division(ConstanteSymbolique.PI,new ConstanteN(4)));
		assertEquals("(cos((π÷4)))", cosPi4.afficher());
		assertEquals("((sqrt(2))÷2)", cosPi4.simplifier().afficher());
		assertEquals(0.7071, cosPi4.simplifier().calculer());
		
		ExpressionArithmetique CosPi6 = new Cos(new Division(ConstanteSymbolique.PI,new ConstanteN(6)));
		assertEquals("(cos((π÷6)))", CosPi6.afficher());
		assertEquals("((sqrt(3))÷2)", CosPi6.simplifier().afficher());
		assertEquals(0.8660, CosPi6.simplifier().calculer());
	}

}
