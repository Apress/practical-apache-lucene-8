void GetHitTerms(Query query,IndexSearcher searcher,int docId,List<Term>
hitTerms,List<Term>rest)
{
	if (query is TermQuery)
	{
		if (searcher.Explain(query, docId).IsMatch() == true)
			hitTerms.Add((query as TermQuery).GetTerm());
		else
			rest.Add((query as TermQuery).getTerm());
	return;
	}
}
