package fr.pantheonsorbonne.cri;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestConstanteQ {
	@Test
	void test() {
		ConstanteQ  cons = new ConstanteQ(6, 8);
		assertEquals(cons.getNum(), 6);
		assertEquals(cons.getDenum(), 8);
		assertEquals("(6/8)", cons.afficher());
		assertEquals("(3/4)", cons.simplifier().afficher());
		
		ConstanteQ  cons1 = new ConstanteQ(4, 2);
		assertEquals("2", cons1.simplifier().afficher());
		
		ConstanteQ  cons2 = new ConstanteQ(1, 3);
		assertEquals("(1/3)", cons2.simplifier().afficher());
		
		ConstanteQ  cons3 = new ConstanteQ(0, 3);
		assertEquals("(0/3)", cons3.afficher());
		assertEquals("0", cons3.simplifier().afficher());
		
		ConstanteQ  cons4 = new ConstanteQ(0, 3);
		ConstanteQ  cons5 = new ConstanteQ(1, 4);

		assertEquals(true,cons3.equals(cons4));
		assertEquals(false,cons3.equals(cons5));
		
	
		assertThrows(ArithmeticException.class, () -> new ConstanteQ(3, 0));
		
		
	}

}
