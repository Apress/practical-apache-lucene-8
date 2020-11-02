LuceneStorageConfiguration luceneStorageConf = new
LuceneStorageConfiguration(configuration, asList(index),
seqFilesOutputPath, “id”, asList(“title”, “description”));
LuceneIndexToSequenceFiles lucene2Seq =
new LuceneIndexToSequenceFiles();
lucene2Seq.run(luceneStorageConf);
