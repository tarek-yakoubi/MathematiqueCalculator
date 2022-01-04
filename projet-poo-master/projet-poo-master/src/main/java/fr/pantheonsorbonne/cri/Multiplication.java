package fr.pantheonsorbonne.cri;



public class Multiplication extends OpBinaire {

	public Multiplication(ExpressionArithmetique left, ExpressionArithmetique right) {
		super(left, right, "*");
	}

	@Override
	public ExpressionArithmetique simplifier() {

		if (left.simplifier() instanceof ConstanteN) {
			if (((ConstanteN) left.simplifier()).value == 0) {
				return new ConstanteN(0);
			} else if (((ConstanteN) left.simplifier()).value == 1)
				return right.simplifier();
		}
		if (right.simplifier() instanceof ConstanteN) {
			if (((ConstanteN) right.simplifier()).value == 0) {
				return new ConstanteN(0);
			} else if (((ConstanteN) right.simplifier()).value == 1)
				return left.simplifier();
		}

		if (left.simplifier() instanceof Sqrt && right.simplifier() instanceof Sqrt) {

			Sqrt l = (Sqrt) left.simplifier();
			Sqrt r = (Sqrt) right.simplifier();
			if (l.value instanceof VariableSymbolique && l.value instanceof VariableSymbolique) {
				VariableSymbolique x = (VariableSymbolique) l.value.simplifier();
				return new VariableSymbolique(x.symbol);
			}


			return new Sqrt(new Multiplication(l.value.simplifier(), r.value.simplifier()).simplifier()).simplifier();
		}

		return super.simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifier(ConstanteN valLeft, ConstanteN valRight) {
		return new ConstanteN(valLeft.value * valRight.value).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifier(ConstanteQ valLeft, ConstanteQ valRight) {
		return new ConstanteQ(valLeft.getNum() * valRight.getNum(), valLeft.getDenum() * valRight.getDenum())
				.simplifier();
	}


	public ExpressionArithmetique distributivite() {

		ExpressionArithmetique resultat = this;

		// N*(x+-x)
		if (this.left instanceof ConstanteExpressionArithmetique && this.right instanceof OpBinaire) {

			if (this.left instanceof ConstanteQ) {
				// N*(x+x)
				if (this.right instanceof Addition) {
					return new Addition(new Multiplication(this.left, ((Addition) this.right).getLeft()).simplifier(),
							new Multiplication(this.left, ((Addition) this.right).getRight()).simplifier());
				}
				// N*(x-x)
				if (this.right instanceof Soustraction) {
					return resultat = new Soustraction(
							(new Multiplication(this.left, ((Soustraction) this.right).getLeft()).simplifier()),
							new Multiplication(this.left, ((Soustraction) this.right).getRight()).simplifier());
				}
			}

			// N*(x+x)
			if (this.right instanceof Addition) {
				return new Addition(new Multiplication(this.left, ((Addition) this.right).getLeft()).simplifier(),
						new Multiplication(this.left, ((Addition) this.right).getRight()).simplifier());
			}
			// N*(x-x)
			if (this.right instanceof Soustraction) {
				return resultat = new Soustraction(
						(new Multiplication(this.left, ((Soustraction) this.right).getLeft()).simplifier()),
						new Multiplication(this.left, ((Soustraction) this.right).getRight()).simplifier());
			}

		}

		// (x+-x)*N
		if (this.left instanceof OpBinaire && this.right instanceof ConstanteExpressionArithmetique) {

			// (x+x)*N
			if (this.left instanceof Addition) {
				return new Addition(new Multiplication(this.right, ((Addition) this.left).getLeft()).simplifier(),
						new Multiplication(this.right, ((Addition) this.left).getRight()).simplifier());
			}

			// (x-x)*N
			if (this.left instanceof Soustraction) {
				resultat = new Soustraction(
						(new Multiplication(this.right, ((Soustraction) this.left).getLeft()).simplifier()),
						new Multiplication(this.right, ((Soustraction) this.left).getRight()).simplifier());
			}
		}

		// (x+-x)(x+-x)
		if (this.left instanceof OpBinaire && this.right instanceof OpBinaire) {

			if (this.left instanceof Addition) {

				// (x+x)*(x+x)
				if (this.right instanceof Addition) {
					return new Addition(
							new Addition(
									new Multiplication(((Addition) this.left).getLeft().simplifier(),
											((Addition) this.right).getLeft().simplifier()),
									new Multiplication(((Addition) this.left).getLeft().simplifier(),
											((Addition) this.right).getRight().simplifier())),
							new Addition(
									new Multiplication(((Addition) this.left).getRight().simplifier(),
											((Addition) this.right).getLeft().simplifier()),
									new Multiplication(((Addition) this.left).getRight().simplifier(),
											((Addition) this.right).getRight().simplifier())));
				}

				// (x+x)*(x-x)
				if (this.right instanceof Soustraction) {

					return new Addition(
							new Soustraction(
									new Multiplication(((Addition) this.left).getLeft().simplifier(),
											((Soustraction) this.right).getLeft().simplifier()),
									new Multiplication(((Addition) this.left).getLeft().simplifier(),
											((Soustraction) this.right).getRight().simplifier())),
							new Soustraction(
									new Multiplication(((Addition) this.left).getRight().simplifier(),
											((Soustraction) this.right).getLeft().simplifier()),
									new Multiplication(((Addition) this.left).getRight().simplifier(),
											((Soustraction) this.right).getRight().simplifier()))).simplifier();
				}

			}

		}

		// (x-x)*(x+-x)
		if (this.left instanceof Soustraction) {

			if (this.right instanceof Addition) {

				return new Soustraction(
						new Addition(
								new Multiplication(((Soustraction) this.left).getLeft().simplifier(),
										((Addition) this.right).getLeft().simplifier()),
								new Multiplication(((Soustraction) this.left).getLeft().simplifier(),
										((Addition) this.right).getRight().simplifier())),
						new Soustraction(
								new Multiplication(((Soustraction) this.left).getRight().simplifier(),
										((Addition) this.right).getLeft().simplifier()),
								new Multiplication(((Soustraction) this.left).getRight().simplifier(),
										((Addition) this.right).getRight().simplifier()))).simplifier();
			}

		}
		if (this.right instanceof Soustraction) {

			return new Soustraction(
					new Soustraction(
							new Multiplication(((Soustraction) this.left).getLeft().simplifier(),
									((Soustraction) this.right).getLeft().simplifier()),
							new Multiplication(((Soustraction) this.left).getLeft().simplifier(),
									((Soustraction) this.right).getRight().simplifier())),
					new Addition(
							new Multiplication(((Soustraction) this.left).getRight().simplifier(),
									((Soustraction) this.right).getLeft().simplifier()),
							new Multiplication(((Soustraction) this.left).getRight().simplifier(),
									((Soustraction) this.right).getRight().simplifier()))).simplifier();
		}

		return resultat.simplifier();

	}


	@Override
	protected ExpressionArithmetique simplifier(ConstanteN valLeft, ConstanteQ valRight) {
		return new ConstanteQ(valLeft.value * valRight.getNum(), valRight.getDenum()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifier(ConstanteN n, VariableSymbolique vs) {
		if (vs.getValue() != null) {
			return super.simplifier();
		}
		return new Multiplication(n, vs.simplifier());
	}

	@Override
	protected ExpressionArithmetique simplifier(VariableSymbolique valLeft, VariableSymbolique valRight) {
		if (valLeft.getSymbol() == valRight.getSymbol())
			return new Puissance(valLeft, new ConstanteN(2)).simplifier();
		return this;
	}

	@Override
	public ExpressionArithmetique deriver() {
		return new Addition(new Multiplication(left.deriver(), right),
				new Multiplication(left, right.deriver()).simplifier()).simplifier();
	}

	@Override
	public ExpressionArithmetique deriverPart(VariableSymbolique vs) {
		if (this.left.simplifier() instanceof VariableSymbolique && vs.equals(this.left.simplifier())) {
			return new Multiplication(left.deriver(), right).simplifier();
		}
		else if (this.right.simplifier() instanceof VariableSymbolique && vs.equals(this.right.simplifier())) {
			return new Multiplication(left, right.deriver()).simplifier();
		}
		return new Multiplication(this.left.deriverPart(vs).simplifier(), right.deriverPart(vs).simplifier()).simplifier();
	}

	@Override
	public double calculer() {

		double resultat = this.left.simplifier().calculer() * this.right.simplifier().calculer();
		return Utils.arrondir(resultat);
	}

	@Override
	protected ExpressionArithmetique simplifier(VariableSymbolique vs, ConstanteN n) {
		return this.simplifier(n, vs);
	}

	public ExpressionArithmetique associativite() {

		ExpressionArithmetique resultat = this.simplifier();

		if (this.left instanceof ConstanteExpressionArithmetique) {

			if (this.left instanceof ConstanteN && this.right instanceof Multiplication) {

				if ((((Multiplication) this.right).getLeft() instanceof VariableSymbolique)
						|| (((Multiplication) this.right).getLeft() instanceof VariableSymboliqueIndicee)) {

					if (!(((Multiplication) this.right).getRight() instanceof VariableSymbolique)
							&& !(((Multiplication) this.right).getRight() instanceof VariableSymboliqueIndicee)) {

						// C'est le cas ou on a un N droite
						if ((((Multiplication) this.right).getRight() instanceof ConstanteN)) {
							resultat = new Multiplication(
									new ConstanteN((int) ((Multiplication) this.right).getRight().calculer()
											* (int) this.left.calculer()),
									((Multiplication) this.right).left);
							return resultat.simplifier();
						}

						// C'est le cas ou on a un Q droite
						if ((((Multiplication) this.right).getRight() instanceof ConstanteQ)) {

							resultat = new Multiplication(
									new ConstanteQ((((int) this.left.calculer())
											* ((int) ((ConstanteQ) ((Multiplication) this.right).getRight()).getNum())),
											((int) ((ConstanteQ) ((Multiplication) this.right).getRight()).getDenum())),
									(((Multiplication) this.right).left));

							return resultat.simplifier();

						}
					}
				}
				if ((((Multiplication) this.right).getRight() instanceof VariableSymbolique)
						|| (((Multiplication) this.right).getRight() instanceof VariableSymboliqueIndicee)) {
					if (!(((Multiplication) this.right).getLeft() instanceof VariableSymbolique)
							&& !(((Multiplication) this.right).getLeft() instanceof VariableSymboliqueIndicee)) {

						// C'est le cas ou on a un N gauche
						if ((((Multiplication) this.right).getLeft() instanceof ConstanteN)) {
							resultat = new Multiplication(
									new ConstanteN((int) ((Multiplication) this.right).getLeft().calculer()
											* (int) this.left.calculer()),
									((Multiplication) this.right).right);
							return resultat.simplifier();
						}
						// C'est le cas ou on a un Q gauche
						if ((((Multiplication) this.right).getLeft() instanceof ConstanteQ)) {
							resultat = new Multiplication(
									new ConstanteQ(((int) this.left.calculer()
											* (int) ((ConstanteQ) ((Multiplication) this.right).getLeft()).getNum()),
											(int) ((ConstanteQ) ((Multiplication) this.right).getLeft()).getDenum()),
									((Multiplication) this.right).getRight()); // essayer de changer de place le
																				// this.left
							return resultat.simplifier();
						}
					}
				}

				if ((((Multiplication) this.right).getLeft() instanceof ConstanteN)
						&& (((Multiplication) this.right).getLeft() instanceof ConstanteN)) {
					// Cas ou on a que des N dans l'addition :
					resultat = new ConstanteN((int) this.left.calculer() * (int) this.right.calculer());
					return resultat.simplifier();
				}
			}

			if (this.left instanceof ConstanteQ && this.right instanceof Multiplication) {

				if ((((Multiplication) this.right).getLeft() instanceof VariableSymbolique)
						|| (((Multiplication) this.right).getLeft() instanceof VariableSymboliqueIndicee)) {

					if (!(((Multiplication) this.right).getRight() instanceof VariableSymbolique)
							&& !(((Multiplication) this.right).getRight() instanceof VariableSymboliqueIndicee)) {

						// C'est le cas ou on a un N droite
						if ((((Multiplication) this.right).getRight() instanceof ConstanteN)) {
							resultat = new Multiplication(
									new ConstanteQ(
											((int) ((Multiplication) this.right).right.calculer())
													* ((int) ((ConstanteQ) this.left).getNum()),
											((int) ((ConstanteQ) this.left).getDenum())),
									((Multiplication) this.right).left);
							return resultat.simplifier();
						}
						if ((((Multiplication) this.right).getRight() instanceof ConstanteQ)) {

							resultat = new Multiplication(new ConstanteQ(
									(int) ((ConstanteQ) ((Multiplication) this.right).right).getNum()
											* ((int) ((ConstanteQ) this.left).getNum()),
									((int) ((ConstanteQ) this.left).getDenum())
											* (int) ((ConstanteQ) ((Multiplication) this.right).right).getDenum()),
									((Multiplication) this.right).left);
							return resultat.simplifier();

						}
					}
				}

				if ((((Multiplication) this.right).getRight() instanceof VariableSymbolique)
						|| (((Multiplication) this.right).getRight() instanceof VariableSymboliqueIndicee)) {
					if (!(((Multiplication) this.right).getLeft() instanceof VariableSymbolique)
							&& !(((Multiplication) this.right).getLeft() instanceof VariableSymboliqueIndicee)) {
						if ((((Multiplication) this.right).getLeft() instanceof ConstanteN)) {
							resultat = new Multiplication(
									new ConstanteQ(
											((int) ((Multiplication) this.right).left.calculer())
													* ((int) ((ConstanteQ) this.left).getNum()),
											((int) ((ConstanteQ) this.left).getDenum())),
									((Multiplication) this.right).right);
							return resultat.simplifier();
						}
						// C'est le cas ou on a un Q gauche
						if ((((Multiplication) this.right).getLeft() instanceof ConstanteQ)) {
							resultat = new Multiplication(new ConstanteQ(
									(int) ((ConstanteQ) ((Multiplication) this.right).left).getNum()
											* ((int) ((ConstanteQ) this.left).getNum()),
									((int) ((ConstanteQ) this.left).getDenum())
											* (int) ((ConstanteQ) ((Multiplication) this.right).left).getDenum())

									, ((Multiplication) this.right).right);
							return resultat.simplifier();
						}
					}
				}
			}
		}
		return resultat;
	}
}
