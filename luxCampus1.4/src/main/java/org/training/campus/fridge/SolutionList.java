package org.training.campus.fridge;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SolutionList implements Iterable<Path> {
	private List<Path> list;

	public SolutionList() {
		list = new LinkedList<>();
	}

	@Override
	public Iterator<Path> iterator() {
		return list.iterator();
	}

}
