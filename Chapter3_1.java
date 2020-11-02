private void searchPhraseQuery(IndexReader reader, String[] phrases) throws IOException,
ParseException {
	IndexSearcher searcher = new IndexSearcher(indexReader);
	PhraseQuery query = new PhraseQuery();
	query.setSlop(0);
	for (String word : phrases) {
		query.add(new Term(LuceneConstants.FILE_NAME, word));
	}

	TopDocs hits = searcher.search(query);

	for (ScoreDoc scoreDoc : hits.scoreDocs) {
		Document doc = searcher.getDocument(scoreDoc);
	System.out.println(“File:” + doc.get(LuceneConstants.FILE_PATH));
	}

	searcher.close();
}

