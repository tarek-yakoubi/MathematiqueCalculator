package fr.pantheonsorbonne.cri;


import org.junit.jupiter.api.Test;


class TestFactorisation {
	@Test
	void test() {
		/*Factoriser fact = new Factoriser(new Addition(
				new Addition(
						new Multiplication(new ConstanteN(3),
								new Puissance(new VariableSymbolique("a"), new ConstanteN(2))),
						new Multiplication(new ConstanteN(6), new VariableSymbolique("ab"))),
				new Multiplication(new ConstanteN(3), new Puissance(new VariableSymbolique("b"), new ConstanteN(2)))));

		Factoriser fact2 = new Factoriser(new Soustraction(
				new Multiplication(new ConstanteN(9), new Puissance(new VariableSymbolique("b"), new ConstanteN(2))),
				new Multiplication(new ConstanteN(16), new Puissance(new VariableSymbolique("b"), new ConstanteN(2)))));

		Factoriser fact3 = new Factoriser(new Addition(
				new Soustraction(new Puissance(new VariableSymbolique("a"), new ConstanteN(2)),
						new Multiplication(new ConstanteN(2), new VariableSymbolique("ab"))),
				new Puissance(new VariableSymbolique("b"), new ConstanteN(2))));

		Factoriser fact4 = new Factoriser(new Addition(
				new Addition(
						new Addition(
								new Addition(
										new Addition(new Puissance(new VariableSymbolique("a"), new ConstanteN(2)),
												new Puissance(new VariableSymbolique("b"), new ConstanteN(2))),
										new Puissance(new VariableSymbolique("c"), new ConstanteN(2))),
								new Multiplication(new ConstanteN(2), new VariableSymbolique("ab"))),
						new Multiplication(new ConstanteN(2), new VariableSymbolique("bc"))),
				new Multiplication(new ConstanteN(2), new VariableSymbolique("ac"))));
		
		
		Factoriser fact5 = new Factoriser(new Addition(
				new Addition(
						new Multiplication(new ConstanteN(4),
								new Puissance(new VariableSymbolique("a"), new ConstanteN(2))),
						new Multiplication(new ConstanteN(6), new VariableSymbolique("ab"))),
				new Multiplication(new ConstanteN(9), new Puissance(new VariableSymbolique("b"), new ConstanteN(2)))));


		assertEquals("(2a+3b)^2", fact5.factoriser().afficher());
		assertEquals("3*(a+b)^2", fact.factoriser());
		assertEquals("(3a-4b)*(3a+4b)", fact2.factoriser());
		assertEquals("(a-b)^2", fact3.factoriser());
		assertEquals("(a+b+c)^2", fact4.factoriser());
		assertEquals("(a+b+c)^2", fact5.factoriser().afficher());
		*/
	}
}
