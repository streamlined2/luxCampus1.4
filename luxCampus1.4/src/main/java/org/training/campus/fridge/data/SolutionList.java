package org.training.campus.fridge.data;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.StringJoiner;
import java.util.TreeSet;

public class SolutionList implements Iterable<Path> {
	private SortedSet<Path> solutions;

	public SolutionList() {
		solutions = new TreeSet<>();
	}

	public void add(Path path) {
		solutions.add(path);
	}

	public boolean isEmpty() {
		return solutions.isEmpty();
	}

	@Override
	public String toString() {
		var join = new StringJoiner("\n");
		forEach(path -> join.add(path.toString()));
		return join.toString();
	}

	@Override
	public Iterator<Path> iterator() {
		return solutions.iterator();
	}

	public int size() {
		return solutions.size();
	}

	public void save(File file) throws IOException {
		try (DataOutputStream os = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
			for (Path path : this) {
				os.writeChars(path.toString());
				os.writeChars("\n");
			}
		}
	}

}
