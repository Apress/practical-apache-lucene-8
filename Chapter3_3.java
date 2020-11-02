private void booleanQueryExample(String firstQuery, String secondQuery, IndexReader reader) {
	IndexSearcher searcher = new IndexSearcher(reader);
	Term firstTerm = new Term(“foo”, firstQuery);
	// Build the first query
	Query internalFirstQuery = new TermQuery(firstTerm);
	Term secondTerm = new Term(“bar”, secondQuery);
	Query internalSecondQuery = new PrefixQuery(secondTerm);
	// Construct the boolean query from the above two queries
	BooleanQuery query = new BooleanQuery();
	query.add(firstInternalQuery, BooleanClause.Occur.MUST_NOT);
	query.add(secondInternalQuery, BooleanClause.Occur.MUST);
	// Search using the boolean query
	TopDocs hits = searcher.search(query);
	for (ScoreDoc scoreDoc = hits.scoreDocs) {
		Document doc = searcher.getDocument(scoreDoc);
	}	
	searcher.close();
}
