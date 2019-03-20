package se.anderssonbilly.simplesearchengine.invertedindex;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import se.anderssonbilly.simplesearchengine.documentloader.DocumentLoader;
import se.anderssonbilly.simplesearchengine.tfidf.TfIdf;

public class InvertedIndex implements IInvertedIndex{

	private Map<String, String> documentMap;
	private Map<String, Map<String,TfIdf>> invertedIndexMap;
	private final String SPLIT_REGEX = "[ !\"\\#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]+";
	
	public InvertedIndex() {
		this.invertedIndexMap = new HashMap<>();
	}
	
	public InvertedIndex(DocumentLoader loader) {
		this.documentMap = loader.getDocumentStringMap();
		this.invertedIndexMap = createIndex(this.documentMap);
	}
	
	@Override
	public void setLoader(DocumentLoader loader) {
		this.documentMap = loader.getDocumentStringMap();
		this.invertedIndexMap = createIndex(this.documentMap);
	}

	@Override
	public Map<String, Map<String, TfIdf>> getInvertedIndex() {
		return this.invertedIndexMap;
	}

	private Map<String,Map<String,TfIdf>> createIndex(Map<String,String> docsMap){
		this.invertedIndexMap = new HashMap<>();
		
		
		
		return calcTfIdfAndSort(invertedIndexMap);
	}
	
	private Map<String,Map<String,TfIdf>> calcTfIdfAndSort(Map<String, Map<String,TfIdf>> unSortedIndex){
		Map<String,Map<String,TfIdf>> sortedIndex = new TreeMap<>();
		
		return sortedIndex;
	}
}
