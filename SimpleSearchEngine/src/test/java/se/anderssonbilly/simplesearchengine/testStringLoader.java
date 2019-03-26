package se.anderssonbilly.simplesearchengine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.anderssonbilly.simplesearchengine.documentloader.DocumentLoader;
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

	@Test
	void testReturnValue() {
		expected.put("doc_1", new TfIdf(0,0));
		expected.put("doc_3", new TfIdf(0,0));
		
		Set<String> test = index.getInvertedIndex().get("fox").keySet();
		
		assertTrue(test.containsAll(expected.keySet()) && !test.contains("doc_2"));
	}
	
	@Test
	void testSetStringList() {
		List<String> testNewList = new ArrayList<>();
		testNewList.add("Lorem ipsum dolor ipsum sit ipsum");
		testNewList.add("Vituperata incorrupte at ipsum pro quo");
		testNewList.add("Has persius disputationi id simul");
		expected.put("doc_1", new TfIdf(0,0));
		expected.put("doc_2", new TfIdf(0,0));
		
		loader.setStringList(testNewList);
		index.setLoader(loader);
		Set<String> test = index.getInvertedIndex().get("ipsum").keySet();
		
		assertTrue(test.containsAll(expected.keySet()) && !test.contains("doc_3"));
	}
	
	@Test
	void testReturnType() {
		assertEquals(HashMap.class, loader.getDocumentStringMap().getClass());
	}

	@Test
	void testTfIdf() {
		double expected = 0.05068313851352055;
		
		double testTfIdf = index.getInvertedIndex().get("fox").get("doc_1").getTfIdf();
		
		assertEquals(expected,testTfIdf);
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
		assertEquals(index.getInvertedIndex().size(),12);
	}
	
	@Test
	void testEmptyConstructor() {
		loader = new StringListLoader();
		
		assertNotNull(loader.getDocumentStringMap());
	}
}
