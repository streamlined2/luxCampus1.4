package org.training.campus.fridge.solver.breadthfirst;

import org.training.campus.fridge.data.Matrix;
import org.training.campus.fridge.data.Path;
import org.training.campus.fridge.data.SolutionList;

public class BreadthFirstSolver {
	private final Matrix matrix;
	private final int numberOfSolutions;
	private final int maxLevelToReach;

	public BreadthFirstSolver(Matrix matrix, int numberOfSolutions, int maxLevelToReach) {
		this.matrix = matrix;
		this.numberOfSolutions = numberOfSolutions;
		this.maxLevelToReach = maxLevelToReach;
	}

	public SolutionList solve() {
		var solutions = new SolutionList();
		var candidates = new CandidateQueue();
		var path = new Path();
		do {
			var iter = matrix.nonvisitedPositionsIterator(path);
			while (iter.hasNext()) {
				var newPath = new Path(path, iter.next());
				matrix.turnHandles(newPath);
				if (matrix.isSolved()) {
					solutions.add(newPath);
				} else {
					candidates.addCandidate(newPath);
				}
				matrix.turnHandles(newPath);
			}
			path = candidates.getCandidate();
			//System.gc();
		} while (path != null && proceed(solutions.size(), path.size()));
		return solutions;
	}

	private boolean proceed(int solutionsFound, int levelReached) {
		return solutionsFound < numberOfSolutions && levelReached <= maxLevelToReach;
	}

}
