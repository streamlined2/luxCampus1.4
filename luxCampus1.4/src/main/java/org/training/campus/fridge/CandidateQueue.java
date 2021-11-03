package org.training.campus.fridge;

import java.util.Deque;
import java.util.LinkedList;

public class CandidateQueue {
	private Deque<Path> deque;

	public CandidateQueue() {
		deque = new LinkedList<>();
	}

	public void addCandidate(Path path) {
		deque.addLast(path);
	}

	public Path getCandidate() {
		return deque.pollFirst();
	}
	
	public boolean isEmpty() {
		return deque.isEmpty();
	}

}
