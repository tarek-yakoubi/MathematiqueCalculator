package fr.pantheonsorbonne.cri;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



class TestValeurSymboliqueIndicee {
	@Test
    void test() {
        VariableSymboliqueIndicee vsi = new VariableSymboliqueIndicee(new VariableSymbolique("x"), new VariableSymbolique("i"));
        VariableSymboliqueIndicee vsiVal= new VariableSymboliqueIndicee(new VariableSymbolique("a"), new ConstanteN(5));
        
        assertEquals("x▼i", vsi.afficher());
        assertEquals("a▼5", vsiVal.afficher());
        
        vsi.setValue(new ConstanteQ(1,2));
        assertEquals("(1/2)", vsi.simplifier().afficher());
        assertEquals(0.5000, vsi.calculer());
        
	}
}