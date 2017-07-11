package com.stackroute.swisit.searcher.hateoes;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import com.stackroute.swisit.searcher.controller.SearchController;
import com.stackroute.swisit.searcher.domain.SavingSearcherResult;
import com.stackroute.swisit.searcher.domain.SearcherJob;
import com.stackroute.swisit.searcher.domain.SearcherResult;
@Service
public class HateoesAssemblerImpl implements HateoesAssembler{
	/* link used in post method */
	@Override
	public List getLinksPost() {
		List sb=new ArrayList();
		Link postQuery = linkTo(SearchController.class).slash("").withSelfRel();
		sb.add(postQuery);
		        
		Link getAll = linkTo(SearchController.class).slash("").withRel("GetSearcherResult");
		sb.add(getAll);
		
		Link getQuery = linkTo(SearchController.class).slash("/getsearcherjob").withRel("GetJob");
        sb.add(getQuery);
		    
		 return sb;
		}

	/* Hateoas link used in urlgetquery */
	@Override
	public List<SearcherJob> getAllQuery(List<SearcherJob> all) {
		 for ( SearcherJob sb : all) {
		        Link postQuery = linkTo(SearchController.class).slash("").withRel("PostJob");
		        sb.add(postQuery);
		        Link getQuery = linkTo(SearchController.class).slash("/getsearcherjob").withRel("GetJob");
		        sb.add(getQuery);
		        Link getAll = linkTo(SearchController.class).slash("").withRel("GetSearcherResult");
		        sb.add(getAll);
		    }
		 return all;
		}

	@Override
	public List getLinksPostError() {
		List sb=new ArrayList();
		sb.add("Document Already Inserted");
		Link postQuery = linkTo(SearchController.class).slash("").withSelfRel();
		sb.add(postQuery);
		        
		Link getAll = linkTo(SearchController.class).slash("").withRel("GetSearcherResult");
		sb.add(getAll);
		
		Link getQuery = linkTo(SearchController.class).slash("/getsearcherjob").withRel("GetJob");
        sb.add(getQuery);    
		return sb;
	}
	/* Hateoas link used in get method */
	@Override
	public List<SavingSearcherResult> getAllLinks(List<SavingSearcherResult> all) {
		for ( SavingSearcherResult sb : all) {
	        Link postQuery = linkTo(SearchController.class).slash("").withRel("PostJob");
	        sb.add(postQuery);
	        Link getQuery = linkTo(SearchController.class).slash("/getsearcherjob").withRel("GetJob");
	        sb.add(getQuery);
	        Link getAll = linkTo(SearchController.class).slash("").withSelfRel();
	        sb.add(getAll);
	    }
	 return all;
	}

	

}
