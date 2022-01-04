package fr.pantheonsorbonne.cri;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestPuissance {

	@Test
	void test() {

		ExpressionArithmetique puissN = new Puissance(new ConstanteN(8), new ConstanteN(2));
		assertEquals("(8^2)", puissN.afficher());
		assertEquals("64", puissN.simplifier().afficher());
		assertEquals(64.0000, puissN.calculer());
		
		ExpressionArithmetique puissNNN = new Puissance(new ConstanteN(0), new ConstanteN(2));
		assertEquals("(0^2)", puissNNN.afficher());
		assertEquals("0", puissNNN.simplifier().afficher());
		
		ExpressionArithmetique puissNNNN = new Puissance(new ConstanteN(1), new ConstanteN(2));
		assertEquals("(1^2)", puissNNNN.afficher());
		assertEquals("1", puissNNNN.simplifier().afficher());
		
		ExpressionArithmetique puissQ = new Puissance(new ConstanteQ(1,2), new ConstanteN(4));
		assertEquals("((1/2)^4)", puissQ.afficher());
		assertEquals("(1/16)", puissQ.simplifier().afficher());
		assertEquals(0.0625, puissQ.calculer());
		
		ExpressionArithmetique puissNN = new Puissance(new ConstanteN(8), new ConstanteN(-2));
		assertEquals("(8^(-2))", puissNN.afficher());
		assertEquals("(1/64)", puissNN.simplifier().afficher());
		
		ExpressionArithmetique puissQQ = new Puissance(new ConstanteQ(1,2), new ConstanteN(-2));
		assertEquals("((1/2)^(-2))", puissQQ.afficher());
		assertEquals("4", puissQQ.simplifier().afficher());
		
		ExpressionArithmetique puissQQN = new Puissance(new ConstanteQ(4,3), new ConstanteN(-3));
		assertEquals("((4/3)^(-3))", puissQQN.afficher());
		assertEquals("(27/64)", puissQQN.simplifier().afficher());
		
		ExpressionArithmetique puiss1 = new Puissance(new ConstanteQ(1,4), new ConstanteN(0));
		assertEquals("((1/4)^0)", puiss1.afficher());
		assertEquals("1", puiss1.simplifier().afficher());
		assertEquals(1.0000, puiss1.calculer());
		
		ExpressionArithmetique puissS = new Puissance(ConstanteSymbolique.E, new ConstanteN(4));
		assertEquals("(e^4)", puissS.afficher());
		assertEquals("(e^4)", puissS.simplifier().afficher());
		
		ExpressionArithmetique puissVS = new Puissance(new VariableSymbolique("x"), new ConstanteN(4));
		assertEquals("(x^4)", puissVS.afficher());
		assertEquals("(x^4)", puissVS.simplifier().afficher());
		
		ExpressionArithmetique puissVS1 = new Puissance(new VariableSymbolique("x"), new ConstanteN(0));
		assertEquals("(x^0)", puissVS1.afficher());
		assertEquals("1", puissVS1.simplifier().afficher());
		
		ExpressionArithmetique puissVS2 = new Puissance(new VariableSymbolique("x"), new ConstanteN(1));
		assertEquals("(x^1)", puissVS2.afficher());
		assertEquals("x", puissVS2.simplifier().afficher());
		

		ExpressionArithmetique puissZ = new Puissance(new ConstanteN(0), new ConstanteN(0));
		assertEquals("(0^0)", puissZ.afficher());
		assertEquals("1", puissZ.simplifier().afficher());
		
		ExpressionArithmetique puissSn = new Puissance(new Sqrt(new ConstanteN(2)), new ConstanteN(2));
		assertEquals("((sqrt(2))^2)", puissSn.afficher());
		assertEquals("2", puissSn.simplifier().afficher());
		
		ExpressionArithmetique puissSn2 = new Puissance(new Sqrt(new ConstanteN(4)), new ConstanteN(2));
		assertEquals("((sqrt(4))^2)", puissSn2.afficher());
		assertEquals("4", puissSn2.simplifier().afficher());
		
		ExpressionArithmetique puissSn3 = new Puissance(new Sqrt(new VariableSymbolique("x")), new ConstanteN(2));
		assertEquals("((sqrt(x))^2)", puissSn3.afficher());
		assertEquals("x", puissSn3.simplifier().afficher());
		
		ExpressionArithmetique puissSn4 = new Puissance(new Sqrt(new VariableSymbolique("x")), new ConstanteN(4));
		assertEquals("((sqrt(x))^4)", puissSn4.afficher());
		assertEquals("(x^2)", puissSn4.simplifier().afficher());
		
		ExpressionArithmetique puissSn5 = new Puissance(new Sqrt(new ConstanteN(2)), new ConstanteN(4));
		assertEquals("((sqrt(2))^4)", puissSn5.afficher());
		assertEquals("4", puissSn5.simplifier().afficher());
		
		ExpressionArithmetique puissSn6 = new Puissance(new Sqrt(new ConstanteQ(1,2)), new ConstanteN(6));
		assertEquals("((sqrt((1/2)))^6)", puissSn6.afficher());
		assertEquals("(1/8)", puissSn6.simplifier().afficher());
		
		ExpressionArithmetique puissSn7 = new Puissance(new Sqrt(new ConstanteQ(1,2)), new ConstanteN(2));
		assertEquals("((sqrt((1/2)))^2)", puissSn7.afficher());
		assertEquals("(1/2)", puissSn7.simplifier().afficher());
		
		
	}
}
	
	
