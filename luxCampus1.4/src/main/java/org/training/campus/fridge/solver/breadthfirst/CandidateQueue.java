package org.training.campus.fridge.solver.breadthfirst;

import java.util.SortedSet;
import java.util.TreeSet;

import org.training.campus.fridge.data.Path;

public class CandidateQueue {
	private SortedSet<Path> candidates;

	public CandidateQueue() {
		candidates = new TreeSet<>();
	}

	public void addCandidate(Path path) {
		candidates.add(path);
	}

	public Path getCandidate() {
		Path candidate = candidates.first();
		candidates.remove(candidate);
		return candidate;
	}

	public boolean isEmpty() {
		return candidates.isEmpty();
	}

}
