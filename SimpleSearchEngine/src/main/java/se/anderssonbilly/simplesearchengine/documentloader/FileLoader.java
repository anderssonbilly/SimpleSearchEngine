package se.anderssonbilly.simplesearchengine.documentloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class FileLoader extends DocumentLoader {

	private Path path;
	private List<String> filter;
	private int depth;

	public FileLoader(String path) {
		setPath(path);
		setFilter(new ArrayList<>());
		setDepth(5);
	}

	public FileLoader(String path, List<String> filter) {
		setPath(path);
		setFilter(filter);
		setDepth(5);
	}

	public FileLoader(String path, int depth) {
		setPath(path);
		setFilter(new ArrayList<>());
		setDepth(depth);
	}

	public FileLoader(String path, List<String> filter, int depth) {
		setPath(path);
		setFilter(filter);
		setDepth(depth);
	}

	public Path getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = Paths.get(path);
	}

	public List<String> getFilter() {
		return filter;
	}

	public void setFilter(List<String> filter) {
		if (filter.size() == 0)
			filter.add("txt");
		this.filter = filter;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	private List<Path> getFilePaths() throws IOException {
		List<Path> filePaths = new ArrayList<>();

		Files.walk(path, depth).map(Path::toAbsolutePath)
				.filter(p -> filter.indexOf(
						p.getFileName().toString().substring(p.getFileName().toString().lastIndexOf(".") + 1)) != -1)
				.forEach(filePaths::add);

		return filePaths;
	}

	private Map<String, String> getFileContent() {
		Map<String, String> documentMap = new HashMap<>();

		try {
			for (Path filePath : getFilePaths()) {
				StringJoiner sj = new StringJoiner(" ");
				Files.readAllLines(filePath).forEach(sj::add);
				documentMap.put(filePath.getFileName().toString(), sj.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
			return documentMap;
		}
		
		return documentMap;
	}

	@Override
	public Map<String, String> getDocumentStringMap() {
		return getFileContent();
	}

}
