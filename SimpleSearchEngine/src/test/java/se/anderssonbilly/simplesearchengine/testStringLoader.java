package se.anderssonbilly.simplesearchengine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import se.anderssonbilly.simplesearchengine.documentloader.StringListLoader;
import se.anderssonbilly.simplesearchengine.invertedindex.InvertedIndex;
import se.anderssonbilly.simplesearchengine.tfidf.TfIdf;

class testStringLoader {

	static List<String> docStrings;
	Map<String,TfIdf> expected;
	StringListLoader loader;
	InvertedIndex index;
	
	@BeforeAll
	static void initAll() {
		String doc1 = "the brown fox jumped over the brown dog";
		String doc2 = "the lazy brown dog sat in thecorner";
		String doc3 = "the red fox bit the lazy dog";

		docStrings = new ArrayList<String>();
		docStrings.add(doc1);
		docStrings.add(doc2);
		docStrings.add(doc3);
	}

	@BeforeEach
	void init() {
		expected = new HashMap<>();
		
		loader = new StringListLoader(docStrings);
		index = new InvertedIndex(loader);
	}

}
