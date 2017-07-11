package com.stackroute.swisit.searcher.hateoes;

import java.util.List;

import com.stackroute.swisit.searcher.domain.SavingSearcherResult;
import com.stackroute.swisit.searcher.domain.SearcherJob;
import com.stackroute.swisit.searcher.domain.SearcherResult;
public interface HateoesAssembler {

	public List<SavingSearcherResult> getAllLinks(List<SavingSearcherResult> all);
	public List<SearcherJob> getAllQuery(List<SearcherJob> all);
	public List getLinksPost();
	public List getLinksPostError();
}
