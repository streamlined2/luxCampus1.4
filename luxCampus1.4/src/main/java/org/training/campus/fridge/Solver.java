package org.training.campus.fridge;

import org.training.campus.fridge.Matrix.Position;

public class Solver {
	private Matrix matrix;

	public Solver(Matrix matrix) {
		this.matrix = matrix;
	}

	private boolean flippable(Position position) {
		return matrix.getCrossWeight(position) % 2 == 1;
	}

	public Path solve() {
		Path positions = new Path();
		for (Position position : matrix) {
			if (flippable(position)) {
				matrix.turnHandle(position);
				positions.add(position);
			}
		}
		return positions;
	}

}
