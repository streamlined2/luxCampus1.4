package org.training.campus.fridge.data;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

import org.training.campus.fridge.data.Matrix.Position;

public class Path implements Iterable<Position> {
	private List<Position> positions;

	public Path() {
		positions = new LinkedList<>();
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

}
