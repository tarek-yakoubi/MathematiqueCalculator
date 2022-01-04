package fr.pantheonsorbonne.cri;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestDerivationPartielle {

	@Test
	void test() {
		ExpressionArithmetique derPart = new Addition(new Addition(
				new Addition(
						new Addition(new Puissance(new VariableSymbolique("x"), new ConstanteN(2)),
								new Puissance(new VariableSymbolique("y"), new ConstanteN(2))),
						new Multiplication(new VariableSymbolique("x"), new VariableSymbolique("y"))),
				new ConstanteN(4)), new Puissance(new VariableSymbolique("z"), new ConstanteN(3)));
		
		assertEquals("(((((x^2)+(y^2))+(x*y))+4)+(z^3))", derPart.afficher());
		assertTrue(new VariableSymbolique("x").equals(new VariableSymbolique("x")));
		/*assertEquals("((2*x)+y)", derPart.deriverPart(new VariableSymbolique("x")).afficher());
		assertEquals("((2*y)+x)", derPart.deriverPart(new VariableSymbolique("y")).afficher());
		assertEquals("(3*(z^2))", derPart.deriverPart(new VariableSymbolique("z")).afficher());*/
		
	}

}
