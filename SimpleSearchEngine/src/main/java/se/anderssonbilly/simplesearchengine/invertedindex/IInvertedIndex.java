package se.anderssonbilly.simplesearchengine.invertedindex;

import java.util.Map;

import se.anderssonbilly.simplesearchengine.documentloader.DocumentLoader;
import se.anderssonbilly.simplesearchengine.tfidf.TfIdf;

public interface IInvertedIndex {

	public void setLoader(DocumentLoader loader);
	public Map<String,Map<String,TfIdf>> getInvertedIndex();
	
	
}
