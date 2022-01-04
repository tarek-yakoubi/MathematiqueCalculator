package fr.pantheonsorbonne.cri;

import static fr.pantheonsorbonne.cri.Utils.*;

public abstract class OpBinaire implements ExpressionArithmetique {

	protected ExpressionArithmetique right;
	protected ExpressionArithmetique left;
	protected String symbol;

	protected OpBinaire(ExpressionArithmetique leftOp, ExpressionArithmetique rightOp, String symbol) {
		left = leftOp;
		right = rightOp;
		this.symbol = symbol;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof OpBinaire)) {
			return false;
		}

		OpBinaire other = (OpBinaire) obj;
		if (this.left.equals(other.left) && this.right.equals(other.right)) {
			return this.symbol == other.symbol;
		}

		return false;
	}

	/* A voir */
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public String afficher() {
		return "(" + left.afficher() + this.symbol + right.afficher() + ")";
	}

	@Override
	public ExpressionArithmetique simplifier() {

		ExpressionArithmetique gauche = this.left.simplifier();
		ExpressionArithmetique droite = this.right.simplifier();
		if (gauche instanceof ConstanteN && droite instanceof ConstanteN) {
			return simplifier(toN(gauche), toN(droite));
		} else if (gauche instanceof ConstanteQ && droite instanceof ConstanteQ) {
			return simplifier(toQ(gauche), toQ(droite));
		} else if (gauche instanceof ConstanteN && droite instanceof ConstanteQ) {
			return simplifier(toN(gauche), toQ(droite));
		} else if (gauche instanceof ConstanteQ && droite instanceof ConstanteN) {
			return simplifier(toQ(gauche), toN(droite));

		} else if (gauche instanceof VariableSymbolique && droite instanceof VariableSymbolique) {
			return simplifier(toVS(gauche), toVS(droite));
		} else if (gauche instanceof ConstanteN && droite instanceof VariableSymbolique) {
			return simplifier(toN(gauche), toVS(droite));
		} else if (droite instanceof ConstanteN && gauche instanceof VariableSymbolique) {
			return simplifier(toVS(gauche), toN(droite));
		}
		left = gauche;
		right = droite;
		return this;

	}

	protected abstract ExpressionArithmetique simplifier(VariableSymbolique vs, ConstanteN n);

	protected ExpressionArithmetique simplifier(ConstanteN n, VariableSymbolique vs) {

		if (vs.getValue() instanceof ConstanteN) {
			return simplifier(n, toN(vs.getValue()));
		}
		if (vs.getValue() instanceof ConstanteQ) {
			return simplifier(n, toQ(vs.getValue()));
		}

		return this;
	}

	protected ExpressionArithmetique simplifier(ConstanteQ toQ, ConstanteN toN) {
		return simplifier(toN(this.right), toQ(this.left));
	}

	protected abstract ExpressionArithmetique simplifier(ConstanteN valLeft, ConstanteN valRight);

	protected abstract ExpressionArithmetique simplifier(ConstanteQ valLeft, ConstanteQ valRight);

	protected abstract ExpressionArithmetique simplifier(VariableSymbolique valLeft, VariableSymbolique valRight);

	protected ExpressionArithmetique simplifier(ConstanteN valLeft, ConstanteQ valRight) {
		return simplifier(new ConstanteQ(valLeft.value, 1), valRight);
	}

	public ExpressionArithmetique getRight() {
		return right;
	}

	public void setRight(ExpressionArithmetique right) {
		this.right = right;
	}

	public ExpressionArithmetique getLeft() {
		return left;
	}

	public void setLeft(ExpressionArithmetique left) {
		this.left = left;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	

	@Override
	public ExpressionArithmetique deriver(int ordre) {
		ExpressionArithmetique resultat = this.simplifier();
		for (int i = 0; i < ordre; i++) {
			resultat = resultat.deriver().simplifier();
		}
		return resultat.simplifier();
	}

}
