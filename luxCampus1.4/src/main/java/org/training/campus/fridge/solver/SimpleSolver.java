package org.training.campus.fridge.solver;

import org.training.campus.fridge.data.Matrix;
import org.training.campus.fridge.data.Path;
import org.training.campus.fridge.data.Matrix.Position;

public class SimpleSolver {
	private Matrix matrix;

	public SimpleSolver(Matrix matrix) {
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
