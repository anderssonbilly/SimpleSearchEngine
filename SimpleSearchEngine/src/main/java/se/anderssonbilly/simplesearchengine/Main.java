package se.anderssonbilly.simplesearchengine;

import java.util.Map;

import se.anderssonbilly.simplesearchengine.documentloader.FileLoader;
import se.anderssonbilly.simplesearchengine.invertedindex.InvertedIndex;
import se.anderssonbilly.simplesearchengine.tfidf.TfIdf;

public class Main {

	public static void main(String[] args) {
		Map<String,Map<String,TfIdf>> index = new InvertedIndex(new FileLoader("src/test/resources")).getInvertedIndex();
		
		index.forEach((term,docs) -> {
			System.out.println("---");
			System.out.println("Term: \"" + term + "\" occurs in:");
			docs.forEach((docName,tfidf)->{
				System.out.println(" \"" + docName + "\"");
				System.out.println("  tf   : " + tfidf.getTf());
				System.out.println("  idf  : " + tfidf.getIdf());
				System.out.println("  tfidf: " + tfidf.getTfIdf());
			});
		});
	}

}
