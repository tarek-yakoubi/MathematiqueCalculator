package fr.pantheonsorbonne.cri;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestMultiplication {
	@Test
	void test() {
		 
		
		
		ExpressionArithmetique mulNNC = new Multiplication( new Cos(ConstanteSymbolique.PI), new ConstanteN(1));
		assertEquals("((cos(π))*1)", mulNNC.afficher());
		assertEquals("(-1)", mulNNC.simplifier().afficher());
		
		ExpressionArithmetique mulNN = new Multiplication(new ConstanteN(2), new ConstanteN(8));
		assertEquals("(2*8)", mulNN.afficher());
		assertEquals("16", mulNN.simplifier().afficher());
		
		ExpressionArithmetique mulQQ = new Multiplication(new ConstanteQ(2, 7), new ConstanteQ(4, 7));
		assertEquals("((2/7)*(4/7))", mulQQ.afficher());
		assertEquals("(8/49)", mulQQ.simplifier().afficher());
		
		ExpressionArithmetique mulNQ = new Multiplication(new ConstanteN(2), new ConstanteQ(4, 7));

		assertEquals("(2*(4/7))", mulNQ.afficher());
		assertEquals("(8/7)", mulNQ.simplifier().afficher());
		assertEquals(1.1428, mulNQ.calculer());

		ExpressionArithmetique mulQN = new Multiplication(new ConstanteQ(4, 7), new ConstanteN(2));
		assertEquals("((4/7)*2)", mulQN.afficher());
		assertEquals("(8/7)", mulQN.simplifier().afficher());
		
		ExpressionArithmetique mulON = new Multiplication(ConstanteSymbolique.PI, new ConstanteN(5));
		assertEquals("(π*5)", mulON.afficher());
		assertEquals("(π*5)", mulON.simplifier().afficher());
		
		Multiplication mulON9 = new Multiplication(new ConstanteN(2), new Addition(new VariableSymbolique("x"), new ConstanteQ(1, 2)));
		assertEquals("(2*(x+(1/2)))",mulON9.afficher());
		assertEquals("(2*(x+(1/2)))",mulON9.simplifier().afficher());
		assertEquals("((2*x)+1)", mulON9.distributivite().afficher());
	
		
		Multiplication mulON91 = new Multiplication(new Addition(new VariableSymbolique("x"), new ConstanteQ(1, 2)), new ConstanteN(2));
		assertEquals("((x+(1/2))*2)",mulON91.afficher());
		assertEquals("((x+(1/2))*2)",mulON91.simplifier().afficher());
		assertEquals("((2*x)+1)", mulON91.distributivite().afficher());
		
		Multiplication mulON8 = new Multiplication(new ConstanteN(2), new Addition(new VariableSymbolique("x"), new ConstanteQ(1, 2)));
		assertEquals("(2*(x+(1/2)))",mulON8.afficher());
		assertEquals("(2*(x+(1/2)))",mulON8.simplifier().afficher());
		assertEquals("((2*x)+1)", mulON8.distributivite().afficher());
		
		Multiplication mulON81 = new Multiplication(new ConstanteN(2), new Soustraction(new VariableSymbolique("x"), new ConstanteQ(1, 2)));
		assertEquals("(2*(x-(1/2)))",mulON81.afficher());
		assertEquals("(2*(x-(1/2)))",mulON81.simplifier().afficher());
		assertEquals("((2*x)-1)", mulON81.distributivite().afficher());
		
		Multiplication mulON811 = new Multiplication(new Soustraction(new VariableSymbolique("x"), new ConstanteQ(1, 2)) , new ConstanteN(2));
		assertEquals("((x-(1/2))*2)",mulON811.afficher());
		assertEquals("((2*x)-1)", mulON811.distributivite().afficher());

		
		Multiplication mulON10 = new Multiplication(new ConstanteQ(2,4), new Addition(new VariableSymbolique("x"), new ConstanteQ(1, 2)));
		assertEquals("((2/4)*(x+(1/2)))",mulON10.afficher());
		assertEquals("((1/2)*(x+(1/2)))",mulON10.simplifier().afficher());
		assertEquals("(((1/2)*x)+(1/4))", mulON10.distributivite().afficher());
		
		Multiplication mulON101 = new Multiplication(new ConstanteQ(2,4), new Soustraction(new VariableSymbolique("x"), new ConstanteN(2)));
		assertEquals("((2/4)*(x-2))",mulON101.afficher());
		assertEquals("((1/2)*(x-2))",mulON101.simplifier().afficher());
		assertEquals("(((1/2)*x)-1)", mulON101.distributivite().afficher());
		
		Multiplication mulON11 = new Multiplication(ConstanteSymbolique.PI, new Addition(new VariableSymbolique("x"), new ConstanteQ(1, 2)));
		assertEquals("(π*(x+(1/2)))",mulON11.afficher());
		assertEquals("(π*(x+(1/2)))",mulON11.simplifier().afficher());
		assertEquals("((π*x)+(π*(1/2)))", mulON11.distributivite().afficher());
		
		Multiplication mulON12 = new Multiplication( new Addition(new VariableSymbolique("x"), new ConstanteQ(1, 2)), new Addition(new VariableSymbolique("x"), new ConstanteQ(1, 2)));
		assertEquals("((x+(1/2))*(x+(1/2)))",mulON12.afficher());
		assertEquals("(((x*x)+(x*(1/2)))+(((1/2)*x)+((1/2)*(1/2))))", mulON12.distributivite().afficher());
		assertEquals("((x+(1/2))*(x+(1/2)))",mulON12.simplifier().afficher());
		
		Multiplication mulON13 = new Multiplication(new Addition(new VariableSymbolique("x"), new ConstanteQ(1, 2)) , new Soustraction(new VariableSymbolique("x"), new ConstanteQ(1, 2)));
		assertEquals("((x+(1/2))*(x-(1/2)))",mulON13.afficher());		
		assertEquals("((x+(1/2))*(x-(1/2)))",mulON13.simplifier().afficher());
		
		
		Multiplication mulON14 = new Multiplication(new ConstanteN(2), new Multiplication(new VariableSymbolique("x"), new ConstanteN(5)));
		assertEquals("(2*(x*5))",mulON14.afficher());
		assertEquals("(10*x)",mulON14.associativite().afficher());
		
		Multiplication mulON15 = new Multiplication(new ConstanteN(2), new Multiplication(new VariableSymbolique("x"), new ConstanteQ(1,2)));
		assertEquals("(2*(x*(1/2)))",mulON15.afficher());
		assertEquals("x",mulON15.associativite().afficher());
		
		Multiplication mulON16 = new Multiplication(new ConstanteN(2), new Multiplication(new ConstanteN(5), new VariableSymbolique("x")));
		assertEquals("(2*(5*x))",mulON16.afficher());
		assertEquals("(10*x)",mulON16.associativite().afficher());
		
		Multiplication mulON17 = new Multiplication(new ConstanteN(2), new Multiplication(new ConstanteQ(1,2), new VariableSymbolique("x")));
		assertEquals("(2*((1/2)*x))",mulON17.afficher());
		assertEquals("x",mulON17.associativite().afficher());
		
		Multiplication mulON18 = new Multiplication(new ConstanteN(2), new Multiplication(new ConstanteN(5), new ConstanteN(3)));
		assertEquals("(2*(5*3))",mulON18.afficher());
		assertEquals("30",mulON18.associativite().afficher());
		
		Multiplication mulON19 = new Multiplication(new ConstanteQ(2,3), new Multiplication(new ConstanteN(5),new VariableSymbolique("x")));
		assertEquals("((2/3)*(5*x))",mulON19.afficher());
		assertEquals("((10/3)*x)",mulON19.associativite().afficher());
		
		Multiplication mulON20 = new Multiplication(new ConstanteQ(3,4), new Multiplication( new ConstanteQ(1,2), new VariableSymbolique("x")));
		assertEquals("((3/4)*((1/2)*x))",mulON20.afficher());
		assertEquals("((3/8)*x)",mulON20.associativite().afficher());
	
		Multiplication mulON21 = new Multiplication(new ConstanteQ(2,3), new Multiplication(new VariableSymbolique("x"), new ConstanteN(5)));
		assertEquals("((2/3)*(x*5))",mulON21.afficher());
		assertEquals("((10/3)*x)",mulON21.associativite().afficher());
		
		Multiplication mulON22 = new Multiplication(new ConstanteQ(3,4), new Multiplication( new VariableSymbolique("x"),  new ConstanteQ(1,2)));
		assertEquals("((3/4)*(x*(1/2)))",mulON22.afficher());
		assertEquals("((3/8)*x)",mulON22.associativite().afficher());
		
		Multiplication mulON23 = new Multiplication(new ConstanteN(2), new Multiplication(new VariableSymbolique("x"), new ConstanteN(-5)));
		assertEquals("(2*(x*(-5)))",mulON23.afficher());
		assertEquals("((-10)*x)",mulON23.associativite().afficher());
		
	
		Multiplication mul25 = new Multiplication(new Addition(new ConstanteN(2), new VariableSymbolique("x")) , new Soustraction(new ConstanteN(2), new VariableSymbolique("x")));
		assertEquals("((2+x)*(2-x))",mul25.afficher());
		assertEquals("((4-(2*x))+((2*x)-(x^2)))",mul25.distributivite().afficher());
		
		Multiplication mul26 = new Multiplication(new Soustraction(new ConstanteN(2), new VariableSymbolique("x")) , new Addition(new ConstanteN(2), new VariableSymbolique("x")));
		assertEquals("((4+(2*x))-((2*x)-(x^2)))",mul26.distributivite().afficher());
		
		Multiplication mul27 = new Multiplication(new Soustraction(new ConstanteN(2), new VariableSymbolique("x")) , new Soustraction(new ConstanteN(2), new VariableSymbolique("x")));
		assertEquals("((4-(2*x))-((2*x)+(x^2)))",mul27.distributivite().afficher());
		
		
		Multiplication mulsqrt = new Multiplication(new Sqrt(new ConstanteN(5)), new Sqrt(new ConstanteN(10)));
		assertEquals("((sqrt(5))*(sqrt(10)))", mulsqrt.afficher());
		assertEquals("(sqrt(50))", mulsqrt.simplifier().afficher());
		
		Multiplication mulsqrtvs = new Multiplication(new Sqrt(new VariableSymbolique("a")), new Sqrt(new VariableSymbolique("a")));
		assertEquals("((sqrt(a))*(sqrt(a)))", mulsqrtvs.afficher());
		assertEquals("a", mulsqrtvs.simplifier().afficher());
		
		
		Multiplication mulsqrtvs1 = new Multiplication(new VariableSymbolique("a"), new VariableSymbolique("a"));
		assertEquals("(a*a)", mulsqrtvs1.afficher());
		assertEquals("(a^2)", mulsqrtvs1.simplifier().afficher());
		
		Multiplication mulsqrtvs2 = new Multiplication(new VariableSymbolique("a"), new ConstanteN(4));
		assertEquals("(a*4)", mulsqrtvs2.afficher());
		assertEquals("(4*a)", mulsqrtvs2.simplifier().afficher());
		

	}

}
