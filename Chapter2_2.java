private Document parseAndCreateDocument(File file) throws IOException {
	// Assume we have a parser which returns different fields from the file
	FancyParser fancyParser = FancyParser.getParser().parse(file);
	// Step 1
	String firstTest = fancyParser.getFirst();
	String secondText = fancyParser.getSecond();
	String thirdText = fancyParser.getThird();
	// Step 2 and 3
	Field firstField = new Field(“first_field”, firstText, Field.Store.YES, Field.Index.NOT_ANALYZED);
	Field secondField = new Field(“second_field”, secondText, Field.Store.YES, Field.Index.NOT_ANALYZED);
	Field thirdField = new Field(“third_field”, thirdText, Field.Store.YES, Field.Index.NOT_ANALYZED);
	//Step 4
	Document document = new Document();
	//Step 5
	document.add(firstField);
	document.add(secondField);
	document.add(thirdField);

	return document;
}
