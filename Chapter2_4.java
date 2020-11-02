private void doIndexing(File file, IndexWriter indexWriter) throws IOException {
	// Check that the indexWriter is not null and is open
	if (indexWriter == null) {
	throw new IllegalArgumentsException(“IndexWriter instance must not be null”);
	}

	if (!indexWriter.isOpen()) {
		throw new IllegalArgumentsException(“IndexWriter instance must be open”);
	}

	Document document = parseAndCreateDocument(file);
	indexWriter.addDocument(document);
}
