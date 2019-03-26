package se.anderssonbilly.simplesearchengine.invertedindex;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
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
		
		docsMap.forEach((docName, content) -> {
			String[] terms = content.split(SPLIT_REGEX);
			for(String term: terms) {
				term = term.trim().toLowerCase();
				if(term.length()>0) {
					Map<String,TfIdf> docTfIdfMap = invertedIndexMap.get(term);
					if(docTfIdfMap == null) {
						docTfIdfMap = new HashMap<>();
						invertedIndexMap.put(term, docTfIdfMap);
					}
					TfIdf tfIdf = docTfIdfMap.get(docName);
					if(tfIdf == null)
						tfIdf = new TfIdf(terms.length, docsMap.size());
					tfIdf.setTermCount(tfIdf.getTermCount() +1);
					docTfIdfMap.put(docName, tfIdf);
				}
			}
		});
		
		return calcTfIdfAndSort(invertedIndexMap);
	}
	
	private Map<String,Map<String,TfIdf>> calcTfIdfAndSort(Map<String, Map<String,TfIdf>> unSortedIndex){
		Map<String,Map<String,TfIdf>> sortedIndex = new TreeMap<>();
		
		Comparator<Map.Entry<String,TfIdf>> byTfIdf = (Entry<String,TfIdf> e1, Entry<String,TfIdf> e2) -> {
			return Double.compare(e2.getValue().getTfIdf(), e1.getValue().getTfIdf());
		};
		
		unSortedIndex.forEach((term,docs)->{
			docs.entrySet().forEach(doc->{
				doc.getValue().calcTfIdf(docs.size());
			});
			LinkedHashMap<String,TfIdf> sortedDocs = new LinkedHashMap<>();
			docs.entrySet().stream().sorted(byTfIdf).forEach(entry -> sortedDocs.put(entry.getKey(), entry.getValue()));
			sortedIndex.put(term, sortedDocs);
		});
		
		return sortedIndex;
	}
}
