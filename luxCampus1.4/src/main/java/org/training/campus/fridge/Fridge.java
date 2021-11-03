package org.training.campus.fridge;

import java.util.Iterator;
import java.util.List;
import org.training.campus.fridge.Matrix.Position;
import org.training.campus.fridge.Matrix.State;

public class Fridge {

	private static final State SAMPLE[][] = { 
			{ State.HORIZONTAL, State.VERTICAL, State.HORIZONTAL, State.HORIZONTAL },
			{ State.VERTICAL, State.HORIZONTAL, State.HORIZONTAL, State.VERTICAL },
			{ State.HORIZONTAL, State.HORIZONTAL, State.VERTICAL, State.HORIZONTAL },
			{ State.VERTICAL, State.VERTICAL, State.HORIZONTAL, State.HORIZONTAL } };

	public static void main(String[] args) {
		Matrix matrix = new Matrix(SAMPLE);
		System.out.printf("Original matrix is%n%s%n%n", matrix);
		Solver solver = new Solver(matrix);
		List<Position> positions = solver.solve();
		System.out.printf("Solution took %d steps%nList of handles turned: %s%nResulting matrix is%n%s%n",
				positions.size(), positions, matrix);
	}

}
