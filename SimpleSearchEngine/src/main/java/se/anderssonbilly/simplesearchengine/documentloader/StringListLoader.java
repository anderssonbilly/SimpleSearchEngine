package se.anderssonbilly.simplesearchengine.documentloader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringListLoader extends DocumentLoader {

	private List<String> stringList;

	public StringListLoader() {
		setStringList(new ArrayList<>());
	}

	public StringListLoader(List<String> stringList) {
		setStringList(stringList);
	}

	public void setStringList(List<String> stringList) {
		this.stringList = stringList;
	}

	@Override
	public Map<String, String> getDocumentStringMap() {
		Map<String, String> documentStringMap = new HashMap<>();
		for (int i = 0; i < stringList.size(); i++)
			documentStringMap.put("doc_" + (i + 1), stringList.get(i));

		return documentStringMap;
	}

}
