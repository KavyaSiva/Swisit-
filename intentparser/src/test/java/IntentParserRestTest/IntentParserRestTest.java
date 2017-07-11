package IntentParserRestTest;
//import static org.hamcrest.CoreMatchers.is;

import com.stackroute.swisit.intentparser.assembler.HeteoasLinkAssembler;
import com.stackroute.swisit.intentparser.controller.IntentParserRestController;
import com.stackroute.swisit.intentparser.domain.*;
import com.stackroute.swisit.intentparser.assembler.HeteoasLinkAssembler;
import com.stackroute.swisit.intentparser.controller.IntentParserRestController;
import com.stackroute.swisit.intentparser.domain.*;
import com.stackroute.swisit.intentparser.repository.IntentRepository;
import com.stackroute.swisit.intentparser.repository.RelationshipRepository;
import com.stackroute.swisit.intentparser.repository.TermRepository;
import com.stackroute.swisit.intentparser.service.IntentParseAlgo;
import com.stackroute.swisit.intentparser.service.IntentParserService;
import com.stackroute.swisit.intentparser.subscriber.SubscriberImpl;
import com.stackroute.swisit.intentparser.repository.IntentRepository;
import com.stackroute.swisit.intentparser.repository.RelationshipRepository;
import com.stackroute.swisit.intentparser.repository.TermRepository;
import com.stackroute.swisit.intentparser.service.IntentParseAlgo;
import com.stackroute.swisit.intentparser.service.IntentParserService;
import com.stackroute.swisit.intentparser.subscriber.SubscriberImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = IntentParserRestController.class)
@WebMvcTest(controllers= IntentParserRestController.class)

public	class IntentParserRestTest{
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IntentParseAlgo intentParseAlgo;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private RelationshipRepository relationshipRepository;

	@MockBean
	private IntentRepository intentRepository;

	@MockBean
	private TermRepository termRepository;


	@MockBean
	private IntentParserService intentParserService;

	@MockBean
	private IntentParserResult intentParserResult;

	@MockBean
	private CrawlerResult crawlerResult;

	@MockBean
	private HeteoasLinkAssembler heteoasLinkAssembler;

	@MockBean
	private SubscriberImpl subscriberImpl;

	@InjectMocks
	private IntentParserRestController intentParserRestController;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

	}

	@Test
	public void fetchNeoData() throws Exception
	{

		String expectedstring="{"+"\"message\":\"Data received successfully\""+"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/api/parse/fetch"))
				.andExpect(status().isOk());
	}

	@Test
	public void intentParserResponse() throws Exception
	{

		String expectedstring="{"+"\"message\":\"Data received successfully\""+"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/api/parse/"))
				.andExpect(status().isOk());
	}

	@Test
	public void calculateConfidenceScore() throws Exception {

		IntentParserResult intentParserResult = new IntentParserResult();
		intentParserResult.setUrl("https://www.mbusa.com/");
		intentParserResult.setIntent("example");
		intentParserResult.setConfidenceScore(15f);
		intentParserResult.setConcept("car benz");

		assertEquals("https://www.mbusa.com/", intentParserResult.getUrl());
		assertEquals("example", intentParserResult.getIntent());
		assertEquals(15f, intentParserResult.getConfidenceScore());
		assertEquals("car benz", intentParserResult.getConcept());

	}
	@Test
	public void equalGraphsWhichMatchTerms(){
		//String assertCypher = "MATCH (n:`tutorial`) RETURN n";
		Term term = new Term();
		term.setName("tutorials");
		term.setNodeid("24");
		termRepository.save(term);
		List<Term> termList = termRepository.findTerms();
		Assert.assertEquals("tutorials", term.getName());
		Assert.assertEquals("24", term.getNodeid());
	}


	@Test
	public void equalGraphsWhichMatchIntent() {

		Intent intent= new Intent();
		intent.setName("basics");
		intent.setNodeid("2");
		List<Intent> intent1= intentRepository.findIntents();
		Assert.assertEquals("basics", intent.getName());
		Assert.assertEquals("2", intent.getNodeid());
	}

//
//	@Test
//	public void equalGraphsWhichHaveAllIndicatorOf()
//	{
//		Term term= new Term();
//		term.setName("how to code");
//		Intent intent= new Intent();
//		intent.setName("getting started");
//		IndicatorOf indicatorOf=new IndicatorOf();
//		indicatorOf.setIntent(indicatorOf.getIntent());
//		indicatorOf.setTerm(indicatorOf.getTerm());
//		indicatorOf.setWeight("7");
//		Iterable<Map<String,Object>> indicator = relationshipRepository.getAllIndicator();
//
//	}

	@Test
	public void equalGraphsWithBothRelationShip()
	{
		Relationships relationships = new Relationships();
		relationships.setIntentName("example");
		relationships.setTermName("snippets");
		relationships.setRelName("indicatorOf");
		relationships.setWeight(7f);
		Iterable<Map<String,Object>> bothRelationships = relationshipRepository.getBothRelationships();
		assertEquals("example",relationships.getIntentName());
		assertEquals("snippets",relationships.getTermName());
		assertEquals("indicatorOf",relationships.getRelName());
		assertEquals(7f,relationships.getWeight());
	}
	@Test
	public void equalGraphsWithAllTermsRelationOfIntent(){
		Relationships relationships = new Relationships();
		relationships.setTermName("essential");
		relationships.setIntentName("example");
		relationships.setRelName("counterIndicatorOf");
		relationships.setWeight(3f);
		List<Map<String,String>> allTermsRelationOfIntent = relationshipRepository.getAllTermsRelationOfIntent("example");
		assertEquals("essential",relationships.getTermName());
		assertEquals("example",relationships.getIntentName());
		assertEquals("counterIndicatorOf",relationships.getRelName());
		assertEquals(3f,relationships.getWeight());

	}
	@Test
	public void equalGraphsWhichFetchAllRelationship()
	{
		Relationships relationships = new Relationships();
		relationships.setTermName("helloworld");
		relationships.setIntentName("getting started");
		relationships.setRelName("indicatorOf");
		relationships.setWeight(7f);
		Relationships relationships1 = new Relationships();
		relationships1.setIntentName("getting started");
		relationships1.setTermName("how to code");
		relationships1.setRelName("indicatorOf");
		relationships1.setWeight(7f);
		Arrays.asList(relationships);
		List<Map<String,String>> fetchAllRelationships = relationshipRepository.fetchAllRelationships();
		assertEquals("getting started",relationships.getIntentName());
		assertEquals("helloworld", relationships.getTermName());
		assertEquals("indicatorOf",relationships.getRelName());
		assertEquals(7f,relationships.getWeight());
		assertEquals("getting started",relationships1.getIntentName());
		assertEquals("how to code",relationships1.getTermName());
		assertEquals("indicatorOf",relationships1.getRelName());
		assertEquals(7f,relationships1.getWeight());

	}
//	@Test
//	public void equalGraphsWhichGetAllRelationships()
//	{
//
//	}


	@Test
	public void toCalculateConfidenceScore()
	{
		ContentSchema contentSchema = new ContentSchema();
		contentSchema.setWord("how to code");
		contentSchema.setIntensity(2f);
		CrawlerResult crawlerResult = new CrawlerResult();
		crawlerResult.setQuery("Decorator");
		crawlerResult.setLink("https://docs.angularjs.org/guide/component");
		ArrayList<ContentSchema> contentSchemaList= new ArrayList<ContentSchema>();
		contentSchemaList.add(contentSchema);
		crawlerResult.setTerms(contentSchemaList);
		crawlerResult.setSnippet("In AngularJS, a Component is a special kind of directive that uses a simpler configuration which is suitable for a component-based application structure.");
		crawlerResult.setTitle("AngularJS Documentation for component");
		crawlerResult.setLastindexedof(new Date());

		IntentParserResult intentParserResult = new IntentParserResult();
		intentParserResult.setUrl("https://www.mbusa.com/");
		intentParserResult.setIntent("example");
		intentParserResult.setConfidenceScore(15f);
		intentParserResult.setConcept("car benz");

		ArrayList<IntentParserResult> calculateConfidence = new ArrayList<IntentParserResult> ();
		assertEquals("https://www.mbusa.com/",intentParserResult.getUrl());
		assertEquals("example",intentParserResult.getIntent());
		assertEquals(15f,intentParserResult.getConfidenceScore());
		assertEquals("car benz",intentParserResult.getConcept());
	}

}



