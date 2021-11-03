package org.training.campus.fridge;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class Matrix implements Iterable<Matrix.Position> {

	public enum State {
		HORIZONTAL(0), VERTICAL(1);

		private int weight;

		private State(int weight) {
			this.weight = weight;
		}

		private int weight() {
			return weight;
		}

		private static State opposite(State state) {
			return state == HORIZONTAL ? VERTICAL : HORIZONTAL;
		}
	}

	public record Position(int row, int col) {
		@Override
		public String toString() {
			return new StringJoiner(",", "{", "}").add(Integer.toString(row)).add(Integer.toString(col)).toString();
		}
	}

	private State[][] data;

	public Matrix(State[][] data) {
		this.data = new State[data.length][data.length];
		for (int row = 0; row < data.length; row++) {
			if (data.length != data[row].length)
				throw new IllegalArgumentException("please pass square matrix as parameter");
			this.data[row] = Arrays.copyOf(data[row], data[row].length);
		}
	}

	private void flipCell(int row, int col) {
		data[row][col] = State.opposite(data[row][col]);
	}

	public void turnHandle(Position position) {
		for (int k = 0; k < data[position.row].length; k++) {
			flipCell(position.row, k);
		}
		for (int i = 0; i < position.row; i++) {
			flipCell(i, position.col);
		}
		for (int i = position.row + 1; i < data.length; i++) {
			flipCell(i, position.col);
		}
	}

	public int getCrossWeight(Position position) {
		int value = 0;
		for (int k = 0; k < data[position.row].length; k++) {
			value += data[position.row][k].weight();
		}
		for (int i = 0; i < position.row; i++) {
			value += data[i][position.col].weight();
		}
		for (int i = position.row + 1; i < data.length; i++) {
			value += data[i][position.col].weight();
		}
		return value;
	}

	@Override
	public String toString() {
		StringJoiner join = new StringJoiner("\n");
		Arrays.asList(data).forEach((State[] row) -> {
			StringJoiner rowJoin = new StringJoiner(",", "[", "]");
			Arrays.asList(row).forEach((State el) -> rowJoin.add(el.name()));
			join.add(rowJoin.toString());
		});
		return join.toString();
	}

	@Override
	public Iterator<Position> iterator() {
		return new Iterator<>() {
			private int row = 0;
			private int col = 0;

			@Override
			public boolean hasNext() {
				return row < data.length - 1 || (row == data.length - 1 && col < data[row].length);
			}

			@Override
			public Position next() {
				if (!hasNext())
					throw new NoSuchElementException("no more elements");
				Position position = new Position(row, col);
				if (col < data[row].length - 1) {
					col++;
				} else {
					row++;
					col = 0;
				}
				return position;
			}

		};
	}

}
