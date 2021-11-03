package org.training.campus.fridge;

import java.util.LinkedList;
import java.util.List;

import org.training.campus.fridge.Matrix.Position;

public class Solver {
	private Matrix matrix;

	public Solver(Matrix matrix) {
		this.matrix = matrix;
	}

	private boolean flippable(Position position) {
		return matrix.getCrossWeight(position) % 2 == 1;
	}

	public List<Position> solve() {
		List<Position> positions = new LinkedList<>();
		for (Position position : matrix) {
			if (flippable(position)) {
				matrix.turnHandle(position);
				positions.add(position);
			}
		}
		return positions;
	}

}
