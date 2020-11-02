IndexReader reader = DirectoryReader.open(dir);
IndexSearcher searcher = new IndexSearcher(reader);
TermRangeQuery query = new TermRangeQuery(“content”, new Term(“foo”), new Term(“bar”), true, true /* first
true for
Including upper term in range, second true for including lower term in range */);
searcher.search(query);
