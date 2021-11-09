package org.training.campus.fridge;

import java.io.File;
import java.io.IOException;

import org.training.campus.fridge.data.Matrix;
import org.training.campus.fridge.data.Path;
import org.training.campus.fridge.data.Matrix.State;
import org.training.campus.fridge.solver.SimpleSolver;
import org.training.campus.fridge.solver.breadthfirst.BreadthFirstSolver;

public class Fridge {

	public static final int MAX_NUMBER_OF_SOLUTIONS = 1_000_000_000;
	public static final int MAX_LEVEL_TO_REACH = 100;

	private static final State SAMPLE_4x4_3[][] = {
			{ State.HORIZONTAL, State.HORIZONTAL, State.HORIZONTAL, State.VERTICAL },
			{ State.HORIZONTAL, State.VERTICAL, State.HORIZONTAL, State.VERTICAL },
			{ State.VERTICAL, State.HORIZONTAL, State.HORIZONTAL, State.HORIZONTAL },
			{ State.VERTICAL, State.HORIZONTAL, State.HORIZONTAL, State.VERTICAL } };

	private static final State SAMPLE_4x4_2[][] = {
			{ State.HORIZONTAL, State.HORIZONTAL, State.HORIZONTAL, State.HORIZONTAL },
			{ State.HORIZONTAL, State.VERTICAL, State.HORIZONTAL, State.VERTICAL },
			{ State.VERTICAL, State.HORIZONTAL, State.HORIZONTAL, State.HORIZONTAL },
			{ State.VERTICAL, State.HORIZONTAL, State.HORIZONTAL, State.HORIZONTAL } };

	private static final State SAMPLE_4x4_1[][] = {
			{ State.HORIZONTAL, State.VERTICAL, State.HORIZONTAL, State.HORIZONTAL },
			{ State.VERTICAL, State.HORIZONTAL, State.HORIZONTAL, State.VERTICAL },
			{ State.HORIZONTAL, State.HORIZONTAL, State.VERTICAL, State.HORIZONTAL },
			{ State.VERTICAL, State.VERTICAL, State.HORIZONTAL, State.HORIZONTAL } };

	public static void main(String[] args) throws IOException {
		var matrix = new Matrix(SAMPLE_4x4_1);
		System.out.printf("Original matrix is%n%s%n%n", matrix);
		var solver = new SimpleSolver(matrix);
		Path positions = solver.solve();
		if (matrix.isSolved()) {
			System.out.printf(
					"Successfully solved by simple solver%nSolution took %d step(s), discovered solution(s): %n%s%n%nResulting matrix is%n%s%n",
					positions.size(), positions, matrix);
		} else {
			System.out.println("Quick search failed, no solution found.");
		}

		var bfsSolver = new BreadthFirstSolver(new Matrix(SAMPLE_4x4_1), MAX_NUMBER_OF_SOLUTIONS, MAX_LEVEL_TO_REACH);
		var solutionList = bfsSolver.solve();
		if (solutionList.isEmpty()) {
			System.out.println("\nBFS search hasn't yielded any results.");
		} else {
			File output = new File("output");
			solutionList.save(output);
			System.out.printf("%n%d solution(s) discovered by breadth-first search solver and saved to file %s%n%s%n",
					solutionList.size(), output.getCanonicalPath(), solutionList);
		}
	}

}
