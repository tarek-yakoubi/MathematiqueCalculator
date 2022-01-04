package fr.pantheonsorbonne.cri;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSqrt {

	@Test
	void test() {
		 ExpressionArithmetique exp3 = new Sqrt(new ConstanteN(4));
		 assertEquals("(sqrt(4))", exp3.afficher());
	     assertEquals("2",exp3.simplifier().afficher());

	     ExpressionArithmetique exp4 = new Sqrt(new ConstanteN(36));
	     assertEquals("(sqrt(36))", exp4.afficher());
	     assertEquals("6",exp4.simplifier().afficher());
	     
	     ExpressionArithmetique exp5 = new Sqrt(new ConstanteQ(8, 2));
	     assertEquals("(sqrt((8/2)))", exp5.afficher());
	     assertEquals("2",exp5.simplifier().afficher());

	     
	     ExpressionArithmetique exp8 = new Sqrt(new ConstanteQ(9, 4));
	     assertEquals("(sqrt((9/4)))", exp8.afficher());
	     assertEquals("(3/2)",exp8.simplifier().afficher());
	     
	   
	     ExpressionArithmetique exp6 = new Sqrt(ConstanteSymbolique.PI);
	     assertEquals("(sqrt(π))", exp6.afficher());
	     assertEquals("(sqrt(π))",exp6.simplifier().afficher());
	     
	     ExpressionArithmetique exp9 = new Sqrt(new Puissance(new ConstanteN(9), new ConstanteN(4)));
	     assertEquals("(sqrt((9^4)))", exp9.afficher());
	     assertEquals("81",exp9.simplifier().afficher());
	     
	     ExpressionArithmetique exp10 = new Sqrt(new Puissance(new ConstanteN(9), new ConstanteN(2)));
	     assertEquals("(sqrt((9^2)))", exp10.afficher());
	     assertEquals("9",exp10.simplifier().afficher());
	     
	     ExpressionArithmetique exp13 = new Sqrt(new Puissance(new VariableSymbolique("y"), new ConstanteN(2)));
	     assertEquals("(sqrt((y^2)))", exp13.afficher());
	     assertEquals("y",exp13.simplifier().afficher());
	     
	    }

}
