public IndexWriter getIndexWriter(String path) throws IOException {
	// Step 1
	IndexWriter indexWriter;
	// Step 2
	FSDirectory.open(new File(path));
	// Step 3
	indexWriter = new IndexWriter(indexDirectory,new StandardAnalyzer(Version.LUCENE_80), true,
			IndexWriter.MaxFieldLength.UNLIMITED);


	return indexWriter;
}
