package fr.pantheonsorbonne.cri;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



class TestProduit {
	@Test
	void test() {
		
		
		ExpressionArithmetique p = new Produit(new ConstanteN(5), 1, 5);
		assertEquals("(∏ i=1→5 (5))", p.afficher());
		assertEquals("3125", p.simplifier().afficher());

		ExpressionArithmetique varI= new VariableSymbolique("i");
				

		ExpressionArithmetique pI = new Produit(varI, 1, 5);
		assertEquals("120", pI.simplifier().afficher());

		ExpressionArithmetique pXi = new Produit(
				new VariableSymboliqueIndicee(new VariableSymbolique("z"), varI), 0, 5);
		assertEquals("(((((z▼0*z▼1)*z▼2)*z▼3)*z▼4)*z▼5)", pXi.simplifier().afficher());
		
		
		ExpressionArithmetique pSqrt = new Produit(new Sqrt(varI), 1, 4);
		assertEquals("(∏ i=1→4 ((sqrt(i))))", pSqrt.afficher());
		assertEquals("((sqrt(6))*2)", pSqrt.simplifier().afficher());
		
		ExpressionArithmetique pMulx = new Produit(new Multiplication(new VariableSymboliqueIndicee(new VariableSymbolique("y"), varI), new ConstanteN(4)), 1, 3);
		assertEquals("(((4*y▼1)*(4*y▼2))*(4*y▼3))", pMulx.simplifier().afficher());
		
		ExpressionArithmetique pMul = new Produit(new Multiplication(varI, new ConstanteN(4)), 1, 3);
		assertEquals("384", pMul.simplifier().afficher());
		
		ExpressionArithmetique pAdd = new Produit(new Addition(new ConstanteN(4), new VariableSymbolique("i")), 1, 3);
		assertEquals("210", pAdd.simplifier().afficher());
		assertEquals("0", pAdd.deriver().afficher());
		assertEquals("0", pAdd.deriver(3).afficher());
		assertEquals(210.0000, pAdd.calculer());

		
	}
}
