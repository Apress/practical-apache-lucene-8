private void performIndexSearch(File indexDir, String query, int maxHits) throws Exception {
	Directory directory = FSDirectory.open(indexDir);
	// Contents is the default field to be analysed
	QueryParser parser = new QueryParser(Version.LUCENE_80, “contents’, new
	StandardAnalyzer());
	Query query = parser.parse(query);
	TopDocs topDocs = searcher.search(query. maxHits);
	// Get the top documents that were returned for this query (specified by maxHIts(
	ScoreDoc[] hits = topDocs.scoreDocs;
	for (int i = 0; i < hits.length; i++) {
		int docId = hits[I].doc;
		System.out.println(docId);
	}
}
