package org.training.campus.fridge.data;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

public class SolutionList implements Iterable<Path> {
	private List<Path> list;

	public SolutionList() {
		list = new LinkedList<>();
	}

	public void add(Path path) {
		list.add(path);
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public String toString() {
		var join = new StringJoiner("\n");
		forEach(path -> join.add(path.toString()));
		return join.toString();
	}

	@Override
	public Iterator<Path> iterator() {
		return list.iterator();
	}
	
	public int size() {
		return list.size();
	}
	
	public void save(File file) throws IOException {
		try(DataOutputStream os = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))){
			for(Path path:this) {
				os.writeChars(path.toString());
				os.writeChars("\n");
			}
		}
	}

}
