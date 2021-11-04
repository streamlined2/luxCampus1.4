package org.training.campus.fridge;

import java.io.File;
import java.io.IOException;

import org.training.campus.fridge.data.Matrix;
import org.training.campus.fridge.data.Path;
import org.training.campus.fridge.data.Matrix.State;
import org.training.campus.fridge.solver.SimpleSolver;
import org.training.campus.fridge.solver.breadthfirst.BreadthFirstSolver;

public class Fridge {

	private static final State SAMPLE[][] = {//OutOfMemoryError 
			{ State.HORIZONTAL, State.VERTICAL, State.HORIZONTAL, State.HORIZONTAL },
			{ State.VERTICAL, State.HORIZONTAL, State.HORIZONTAL, State.VERTICAL },
			{ State.HORIZONTAL, State.HORIZONTAL, State.VERTICAL, State.HORIZONTAL },
			{ State.VERTICAL, State.VERTICAL, State.HORIZONTAL, State.HORIZONTAL } };

	private static final State SAMPLE2[][] = {
		{State.VERTICAL,State.VERTICAL,State.VERTICAL},
		{State.VERTICAL,State.HORIZONTAL,State.HORIZONTAL},
		{State.VERTICAL,State.HORIZONTAL,State.HORIZONTAL}
	};

	public static void main(String[] args) throws IOException {
		var matrix = new Matrix(SAMPLE2);
		System.out.printf("Original matrix is%n%s%n%n", matrix);
		var solver = new SimpleSolver(matrix);
		Path positions = solver.solve();
		if (matrix.isSolved()) {
			System.out.printf(
					"Successfully solved!%nSolution took %d steps%nList of handles turned: %s%nResulting matrix is%n%s%n",
					positions.size(), positions, matrix);
		} else {
			System.out.println("Quick search failed, no solution found.");
		}

		var bfsSolver = new BreadthFirstSolver(new Matrix(SAMPLE2));
		var solutionList = bfsSolver.solve();
		if (solutionList.isEmpty()) {
			System.out.println("\nBFS search haven't yielded any results");
		} else {
			File output = new File("output");
			solutionList.save(output);
			System.out.printf("%n%d solutions discovered by breadth-first search solver and saved to file %s%n",
					solutionList.size(), output.getCanonicalPath());
		}
	}

}
