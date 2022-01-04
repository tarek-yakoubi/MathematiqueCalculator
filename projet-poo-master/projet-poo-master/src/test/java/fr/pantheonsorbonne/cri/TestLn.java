package fr.pantheonsorbonne.cri;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestLn {

	@Test
	void test() {
		
		ExpressionArithmetique lnUn = new Ln(new ConstanteN(1));
		assertEquals("(ln(1))", lnUn.afficher());
		assertEquals("0", lnUn.simplifier().afficher());
		assertEquals(0.0000, lnUn.simplifier().calculer());
		
		ExpressionArithmetique lnE = new Ln(ConstanteSymbolique.E);
		assertEquals("(ln(e))", lnE.afficher());
		assertEquals("1", lnE.simplifier().afficher());
		
		ExpressionArithmetique lnO = new Ln(new ConstanteQ(2, 4));
		assertEquals("(ln((2/4)))", lnO.afficher());
		assertEquals("(ln((1/2)))", lnO.simplifier().afficher());
		assertEquals(-0.6931, lnO.simplifier().calculer());
	}

}
