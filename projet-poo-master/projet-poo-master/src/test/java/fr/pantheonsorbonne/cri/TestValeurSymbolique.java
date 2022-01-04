package fr.pantheonsorbonne.cri;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestValeurSymbolique {

	@Test
	void test() {		
		ExpressionArithmetique val = new VariableSymbolique("x");
		assertEquals("x", val.afficher());
		assertNull(((VariableSymbolique) val).getValue());
		assertEquals("x", val.simplifier().afficher());
		((VariableSymbolique) val).setValue(new ConstanteN(5));
		assertEquals("5", val.simplifier().afficher());
		
		
		((VariableSymbolique) val).setValue(new ConstanteN(4));
		assertTrue(((VariableSymbolique) val).getValue() instanceof ConstanteN);
		ExpressionArithmetique v = ((VariableSymbolique) val).getValue();
		assertEquals(4, ((ConstanteN) v).value);
		assertEquals(4.0000, val.calculer());
		

		
	}

}
