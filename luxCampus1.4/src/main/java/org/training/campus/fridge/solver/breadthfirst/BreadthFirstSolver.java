package org.training.campus.fridge.solver.breadthfirst;

import org.training.campus.fridge.data.Matrix;
import org.training.campus.fridge.data.Path;
import org.training.campus.fridge.data.SolutionList;

public class BreadthFirstSolver {
	private Matrix matrix;

	public BreadthFirstSolver(Matrix matrix) {
		this.matrix = matrix;
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
				}else {
					candidates.addCandidate(newPath);
				}
				matrix.turnHandles(newPath);
			}
			path = candidates.getCandidate();
		} while (path != null);
		return solutions;
	}

}
