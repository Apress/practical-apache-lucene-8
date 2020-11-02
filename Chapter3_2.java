RAMDirectory ramDir = new RAMDirectory();
//Index some made up content
IndexWriter writer = new IndexWriter(ramDir, new StandardAnalyzer(), true,
IndexWriter.MaxFieldLength.UNLIMITED);
Document doc = new Document();
Field id = new Field(“id”, “doc_”, Field.Store.YES);
doc.add(id);
// Enable term vectors on this field
Field text = new Field(“content”, DOCS[I]. Field.Store.NO, Field.Index.ANALYZED,
Field.TermVector.WITH.POSITIONS.OFFSETS);
doc.add(text);
writer.addDocument(doc);
}
writer.close();
