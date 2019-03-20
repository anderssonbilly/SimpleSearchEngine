package se.anderssonbilly.simplesearchengine.tfidf;

public class TfIdf {
	private double tf;
	private double idf;
	private double tfIdf;
	
	private double termCount;
	private double docTermsCount;
	private double docsCount;
	
	public TfIdf(double docTermsCount, double docsCount) {
		this.setTermCount(0);
		this.setDocTermsCount(docTermsCount);
		this.setDocsCount(docsCount);
	}
	
	public void calcTfIdf(double docsTermCount) {
		this.tf = termCount/docTermsCount;
		this.idf = Math.log(docsCount/docsTermCount);
		this.tfIdf = this.tf * this.idf;
	}
	
	public double getTf() {
		return this.tf;
	}
	
	public double getIdf() {
		return this.idf;
	}
	
	public double getTfIdf() {
		return this.tfIdf;
	}
	
	public double getTermCount() {
		return termCount;
	}

	public void setTermCount(double termCount) {
		this.termCount = termCount;
	}

	public double getDocTermsCount() {
		return docTermsCount;
	}

	public void setDocTermsCount(double docTermsCount) {
		this.docTermsCount = docTermsCount;
	}

	public double getDocsCount() {
		return docsCount;
	}

	public void setDocsCount(double docsCount) {
		this.docsCount = docsCount;
	}
}
