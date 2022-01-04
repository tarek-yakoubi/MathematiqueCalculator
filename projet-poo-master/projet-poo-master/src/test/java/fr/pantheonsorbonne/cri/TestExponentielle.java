package fr.pantheonsorbonne.cri;


import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;


class TestExponentielle {

	@Test
	void test() {
		
		ExpressionArithmetique expZero = new Exponentielle(new ConstanteN(0));
		assertEquals("(exp(0))", expZero.afficher());
		assertEquals("1", expZero.simplifier().afficher());
		assertEquals(1.0000, expZero.simplifier().calculer());
		
		ExpressionArithmetique expO = new Exponentielle(new ConstanteQ(2, 4));
		assertEquals("(exp((2/4)))", expO.afficher() );
		assertEquals( "(exp((1/2)))", expO.simplifier().afficher());
		assertEquals(1.6487, expO.simplifier().calculer());
	}

}
