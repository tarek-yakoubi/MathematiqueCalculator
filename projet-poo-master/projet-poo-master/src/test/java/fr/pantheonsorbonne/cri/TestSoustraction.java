package fr.pantheonsorbonne.cri;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

class TestSoustraction {
	@Test
    void test() {
		
		ExpressionArithmetique ssNN = new Soustraction(new ConstanteN(2), new ConstanteN(8));
		assertEquals("(2-8)", ssNN.afficher());
		assertEquals("(-6)", ssNN.simplifier().afficher());
		assertEquals(-6.0, ssNN.calculer(),0.001);
		assertEquals("(-6)", ssNN.simplifier().afficher());


		ExpressionArithmetique ssNN2 = new Soustraction(new ConstanteN(2), new ConstanteN(-8));
		assertEquals("(2-(-8))", ssNN2.afficher());
		assertEquals("10", ssNN2.simplifier().afficher());
		assertEquals(10.0000, ssNN2.calculer(),0.0001);

		ExpressionArithmetique ssNN3 = new Soustraction(new ConstanteN(0), new ConstanteN(8));
		assertEquals("(0-8)", ssNN3.afficher());
		assertEquals("(-8)", ssNN3.simplifier().afficher());
		
		ExpressionArithmetique ssNN4 = new Soustraction(new ConstanteN(8), new ConstanteN(0));
		assertEquals("(8-0)", ssNN4.afficher());
		assertEquals("8", ssNN4.simplifier().afficher());
		
		ExpressionArithmetique ssNQ5 = new Soustraction(new ConstanteN(0), new ConstanteQ(1,2));
		assertEquals("(0-(1/2))", ssNQ5.afficher());
		assertEquals("(-1/2)", ssNQ5.simplifier().afficher());

		ExpressionArithmetique ssNQ6 = new Soustraction(new ConstanteN(0), new ConstanteQ(-1,2));
		assertEquals("(0-(-1/2))", ssNQ6.afficher());


		ExpressionArithmetique ssNQ = new Soustraction(new ConstanteN(2), new ConstanteQ(4, 7));
		assertEquals("(2-(4/7))", ssNQ.afficher());
		assertEquals("(10/7)", ssNQ.simplifier().afficher());
		assertEquals(1.4285, ssNQ.calculer(),0.0001);


		ExpressionArithmetique ssNQ2 = new Soustraction(new ConstanteN(1), new ConstanteQ(7, 2));
		assertEquals("(1-(7/2))", ssNQ2.afficher());
		assertEquals("(-5/2)", ssNQ2.simplifier().afficher());
		assertEquals(-2.5, ssNQ2.calculer(),0.0001);
		
		ExpressionArithmetique ssNQ3 = new Soustraction(new ConstanteN(12), new ConstanteQ(4, 2));
		assertEquals("(12-(4/2))", ssNQ3.afficher());
		assertEquals("10", ssNQ3.simplifier().afficher());
		assertEquals(10.0000, ssNQ3.calculer(),0.0001);

		ExpressionArithmetique ssQN = new Soustraction(new ConstanteQ(4, 7), new ConstanteN(2));
		assertEquals("((4/7)-2)", ssQN.afficher());
		assertEquals("(-10/7)", ssQN.simplifier().afficher());
		assertEquals(-1.4285, ssQN.calculer(),0.0001);

		ExpressionArithmetique ssON = new Soustraction(ConstanteSymbolique.PI, new ConstanteN(5));
		assertEquals("(π-5)", ssON.afficher());
		assertEquals("(π-5)", ssON.simplifier().afficher());

		ExpressionArithmetique addNLn = new Soustraction(new Ln(new ConstanteN(1)), new ConstanteN(1));
		assertEquals("((ln(1))-1)", addNLn.afficher());
		assertEquals("(-1)", addNLn.simplifier().afficher());


		ExpressionArithmetique addNLne = new Soustraction(new Sqrt(new ConstanteN(4)), new ConstanteN(1));
		assertEquals("((sqrt(4))-1)", addNLne.afficher());
		assertEquals("1", addNLne.simplifier().afficher());
		//assertEquals(1.00, ssNQ2.calculer(),0.0001);

		//assertEquals(addNLne.simplifier().afficher(), "1");	



		ExpressionArithmetique addNSqrt = new Soustraction(new Ln(ConstanteSymbolique.E), new ConstanteN(1));
		assertEquals("((ln(e))-1)", addNSqrt.afficher());
		assertEquals("0", addNSqrt.simplifier().afficher());
		assertEquals(0.0000, addNSqrt.calculer(),0.0001);

		ExpressionArithmetique addNN = new Soustraction(new ConstanteN(2), new ConstanteN(8));
		assertEquals("(2-8)", addNN.afficher());
		assertEquals("(-6)", addNN.simplifier().afficher());

		ExpressionArithmetique addZN = new Soustraction(new ConstanteN(0), new ConstanteN(-8));
		assertEquals("(0-(-8))", addZN.afficher());
		assertEquals("8", addZN.simplifier().afficher());


		ExpressionArithmetique addNQ = new Soustraction(new ConstanteN(2), new ConstanteQ(4, 7));
		assertEquals("(2-(4/7))", addNQ.afficher());
		assertEquals("(10/7)", addNQ.simplifier().afficher());

		ExpressionArithmetique addON = new Soustraction(ConstanteSymbolique.E, new ConstanteN(5));
		assertEquals("(e-5)", addON.afficher());
		assertEquals("(e-5)", addON.simplifier().afficher());

		ExpressionArithmetique addQN = new Soustraction(new ConstanteQ(4, 7), new ConstanteN(2));
		assertEquals("((4/7)-2)", addQN.afficher());
		assertEquals("(-10/7)", addQN.simplifier().afficher());
		assertEquals(-1.4286, addQN.calculer(), 0.0001);

		ExpressionArithmetique ssVs = new Soustraction(new VariableSymbolique("x"), new VariableSymbolique("x"));
		assertEquals("(x-x)", ssVs.afficher());
		assertEquals("0", ssVs.simplifier().afficher());

	}
}
