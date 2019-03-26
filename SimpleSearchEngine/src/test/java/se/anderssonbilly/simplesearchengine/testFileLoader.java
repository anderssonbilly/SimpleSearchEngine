package se.anderssonbilly.simplesearchengine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.anderssonbilly.simplesearchengine.documentloader.DocumentLoader;
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
	

	@Test
	void testReturnValue() {
		expected.put("Document 1.txt", new TfIdf(0, 0));
		expected.put("Document 2.txt", new TfIdf(0, 0));

		Set<String> test = index.getInvertedIndex().get("brown").keySet();

		assertTrue(test.containsAll(expected.keySet()) && !test.contains("Document 3.txt"));
	}
	
	@Test
	void testTfIdf() {
		double expected = 0.05068313851352055;
		
		double testTfIdf = index.getInvertedIndex().get("fox").get("Document 1.txt").getTfIdf();
		
		assertEquals(expected,testTfIdf);
	}
	
	@Test
	void testFilter() {
		List<String> filter = new ArrayList<String>();
		filter.add("test");

		loader.setFilter(filter);
		index.setLoader(loader);

		assertTrue(index.getInvertedIndex().get("ipsum") != null);
	}

	@Test
	void testFilterMultiple() {
		List<String> filter = new ArrayList<String>();
		filter.add("txt");
		filter.add("test");

		loader.setFilter(filter);
		index.setLoader(loader);
		Set<String> test = index.getInvertedIndex().keySet();

		assertTrue(test.contains("dog") && test.contains("ipsum"));
	}

	@Test
	void testDepth() {
		List<String> filter = new ArrayList<String>();
		filter.add("txt");
		filter.add("test");

		loader.setFilter(filter);
		loader.setDepth(1);
		index.setLoader(loader);

		assertNull(index.getInvertedIndex().get("ipsum"));
	}

	@Test
	void testReturnType() {
		assertEquals(HashMap.class, loader.getDocumentStringMap().getClass());
	}

	@Test
	void testClassType() {
		assertTrue(loader instanceof DocumentLoader);
	}

	@Test
	void testNoResult() {
		assertNull(index.getInvertedIndex().get("test"));
	}

	@Test
	void testTermCount() {
		assertEquals(index.getInvertedIndex().size(), 12);
	}

	@Test
	void testInvalidPath() {
		loader.setPath("%");
		assertTrue(loader.getDocumentStringMap().isEmpty());
	}

	@Test
	void testGetDepth() {
		loader = new FileLoader("", 5);
		assertEquals(5, loader.getDepth());
	}

	@Test
	void testGetPath() {
		loader.setPath("test");
		assertEquals("test", loader.getPath().toString());
	}

	@Test
	void testGetFilter() {
		List<String> testFilter = new ArrayList<String>();
		testFilter.add("test");

		loader = new FileLoader("", testFilter);

		assertEquals(testFilter, loader.getFilter());
	}
	
	@Test
	void testDepthAndFilterConstructor() {
		List<String> testFilter = new ArrayList<String>();
		testFilter.add("test");

		loader = new FileLoader("", testFilter, 5);

		assertTrue(loader.getFilter().equals(testFilter) && loader.getDepth() == 5);
	}
}
