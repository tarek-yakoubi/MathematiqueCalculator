package fr.pantheonsorbonne.cri;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public abstract class Utils {

    public static ConstanteQ toQ(ExpressionArithmetique ea) {
        return (ConstanteQ) ea;
    }

    public static ConstanteN toN(ExpressionArithmetique ea) {
        return (ConstanteN) ea;
    }
    public static VariableSymbolique toVS(ExpressionArithmetique ea) {
        return (VariableSymbolique) ea;
    }
   
    public static double arrondir(double val) {
    	
    	DecimalFormat d = new DecimalFormat(".####");
		d.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));
		
		return Double.parseDouble(d.format(val));
    }
}
