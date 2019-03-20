package se.anderssonbilly.simplesearchengine.documentloader;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileLoader extends DocumentLoader{

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
		if(filter.size() == 0)
			filter.add("txt");
		this.filter = filter;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	private List<Path> getFilePaths(){
		List<Path> filePaths = new ArrayList<>();
		
		return filePaths;
	}
	
	private Map<String,String> getFileContent(){
		Map<String, String> documentMap = new HashMap<>();
		
		return documentMap;
	}
	
	@Override
	public Map<String, String> getDocumentStringMap() {
		return getFileContent();
	}
	
}
