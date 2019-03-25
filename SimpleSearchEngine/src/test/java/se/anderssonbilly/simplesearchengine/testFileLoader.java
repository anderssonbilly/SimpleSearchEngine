package se.anderssonbilly.simplesearchengine;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;

import se.anderssonbilly.simplesearchengine.documentloader.FileLoader;
import se.anderssonbilly.simplesearchengine.invertedindex.InvertedIndex;
import se.anderssonbilly.simplesearchengine.tfidf.TfIdf;

class testFileLoader {

	Map<String, TfIdf> expected;
	FileLoader loader;
	InvertedIndex index;

	@BeforeEach
	void init() {
		expected = new HashMap<>();

		loader = new FileLoader("src/test/resources");
		index = new InvertedIndex(loader);
	}
	
}
