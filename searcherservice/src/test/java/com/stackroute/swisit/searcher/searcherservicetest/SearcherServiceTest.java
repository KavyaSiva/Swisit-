package com.stackroute.swisit.searcher.searcherservicetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.not;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.assertj.core.api.filter.NotFilter;
import org.hamcrest.Matcher;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Assert;
import org.junit.Test;

import com.stackroute.swisit.searcher.domain.SearcherJob;
import com.stackroute.swisit.searcher.domain.SearcherResult;

public class SearcherServiceTest {
    SearcherResult searcherResult=new SearcherResult();
    SearcherJob searcherJob=new SearcherJob();
    @Test
    public void SearcherResultTest()
    {
        
        searcherResult.setTitle("GitHub - brillout/awesome-angular-components: Catalog of Angular ...");
        searcherResult.setUrl("https://docs.angularjs.org/guide/component");
        searcherResult.setDescription("Nov 13, 2015 ... AngularJS 1.5 introduced the .component() helper method, which is much \nsimpler than the .directive() definition and advocates best practices ...");
        
        
        
        assertEquals("https://docs.angularjs.org/guide/component",searcherResult.getUrl());
        assertEquals("GitHub - brillout/awesome-angular-components: Catalog of Angular ...", searcherResult.getTitle());
        assertEquals("Nov 13, 2015 ... AngularJS 1.5 introduced the .component() helper method, which is much \nsimpler than the .directive() definition and advocates best practices ...", searcherResult.getDescription());
        
    }
    @Test
    public void SearcherJobTest()
    {
        List<String> l=new ArrayList<String>();
        l.add("class");
        l.add("interface");
        searcherJob.setResults("10");
        searcherJob.setSitesearch("none");
        searcherJob.setConcept(l);
    
        assertEquals("class",searcherJob.getConcept().get(0));
        assertEquals("10",searcherJob.getResults());
        assertEquals("none",searcherJob.getSitesearch());
    }
    @Test
    public void SearcherJobFindall()
    {
    	List<String>actual=Arrays.asList("AngularComponent","Github-brillout/awesome","Nov 13, 2015 ... AngularJS 1.5 introduced the .component() helper method, which is much \nsimpler than the .directive() definition and advocates best practices");
    	List<String>expected=Arrays.asList("AngularComponent","Github-brillout/awesome","Nov 13, 2015 ... AngularJS 1.5 introduced the .component() helper method, which is much \nsimpler than the .directive() definition and advocates best practices");
    	Assert.assertThat(actual, is(expected));
    	assertThat(new ArrayList<>(), IsEmptyCollection.empty());
    }
    
}
