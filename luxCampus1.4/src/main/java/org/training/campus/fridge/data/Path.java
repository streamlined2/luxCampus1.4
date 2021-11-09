package org.training.campus.fridge.data;

import java.util.Arrays;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.StringJoiner;
import java.util.TreeSet;

import org.training.campus.fridge.data.Matrix.Position;

public class Path implements Iterable<Position>, Comparable<Path> {
	private SortedSet<Position> positions;

	public Path() {
		positions = new TreeSet<>();
	}

	public Path(Path path, Position... positionList) {
		this();
		positions.addAll(path.positions);
		positions.addAll(Arrays.asList(positionList));
	}

	public void add(Position position) {
		positions.add(position);
	}

	public int size() {
		return positions.size();
	}

	@Override
	public String toString() {
		var join = new StringJoiner(",", "[", "]");
		positions.forEach(position -> join.add(position.toString()));
		return join.toString();
	}

	public boolean contains(Position position) {
		return positions.contains(position);
	}

	@Override
	public Iterator<Position> iterator() {
		return positions.iterator();
	}

	@Override
	public int compareTo(Path other) {
		if (size() < other.size()) {
			return -1;
		} else if (size() > other.size()) {
			return 1;
		} else {
			Iterator<Position> iterator = other.iterator();
			for (Position position : positions) {
				int comp = position.compareTo(iterator.next());
				if (comp != 0)
					return comp;
			}
			return 0;
		}
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Path path) {
			return compareTo(path) == 0;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return positions.hashCode();
	}

}
