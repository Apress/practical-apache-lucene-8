public List<String> performAnalyze(String input, Analyzer analyser) throws IOException {
	List<String> result = new ArrayList<String>();
	TokenStream tokenStream = analyser.tokenStream(“field_name”, input);
	CharTermAttribute attr = tokenStream.addAttribute(CharTermAttribute.class);
	tokenStream.reset();
	while(tokenStream.incrementToken()) {
		result.add(attracts.toString());
	}

	return result;
}
