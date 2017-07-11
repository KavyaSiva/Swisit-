package UserSearchRestTest;

import com.stackroute.swisit.usersearchservice.assembler.HeteoasLinkAssembler;
import com.stackroute.swisit.usersearchservice.controller.UserSearchServiceController;
import com.stackroute.swisit.usersearchservice.domain.*;
import com.stackroute.swisit.usersearchservice.repository.UserSearchServiceRepository;
import com.stackroute.swisit.usersearchservice.service.UserSearchService;
import org.junit.Assert;
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
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = UserSearchServiceController.class)
@WebMvcTest(controllers= UserSearchServiceController.class)
public class UserSearchRestTest {
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserSearchService userSearchService;

    @MockBean
    private UserSearchServiceRepository userSearchServiceRepository;

    @MockBean
    private UserSearchResult userSearchResult;

    @MockBean
    private UserInput userInput;

    @MockBean
    private HeteoasLinkAssembler heteoasLinkAssembler;

    @InjectMocks
    private UserSearchServiceController userSearchServiceController;
//
//    @Before
//    public void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//
//    }
//
//    @Test
//    public void getUserSearch() throws Exception
//    {
//
//       // String expectedstring="{"+"\"message\":\"Data received successfully\""+"}";
//        mockMvc.perform(post("v1/api/swisit/usersearch"))
//                .andExpect(status().isOk());
//    }

    @Test
    public  void fetchNeoData() throws Exception
    {
        UserSearchResult userSearchResult = new UserSearchResult();
        userSearchResult.setUrl("http://localhost:8090/javaworld/jw-05-2002/jw-0503-java101.html");
        userSearchResult.setDescription("null");
        userSearchResult.setConfidenceScore(9f);
        assertEquals("http://localhost:8090/javaworld/jw-05-2002/jw-0503-java101.html",userSearchResult.getUrl());
        assertEquals("null",userSearchResult.getDescription());
        assertEquals(9f,userSearchResult.getConfidenceScore(),9);
    }
    @Test
    public void equalGraphsWhichMatchTerms(){

        Term term = new Term();
        term.setName("tutorials");
        //term.setNodeid("24");
        userSearchServiceRepository.save(term);
        List<String> termList = userSearchServiceRepository.findTerms();
        Assert.assertEquals("tutorials", term.getName());
       // Assert.assertEquals("24", term.getNodeid());
    }


    @Test
    public void equalGraphsWhichMatchIntent() {

        Intent intent= new Intent();
        intent.setName("basics");
        intent.setNodeid("2");
        List<Intent> intent1= userSearchServiceRepository.findIntents();
        Assert.assertEquals("basics", intent.getName());
        Assert.assertEquals("2", intent.getNodeid());
    }

    @Test
    public void equalGraphsWithRelatesRelation()
    {
        UserInput userInput = new UserInput();
        userInput.setConcept("Thread");
        UserSearchResult userSearchResult = new UserSearchResult();
        userSearchResult.setUrl("http://localhost:8090/javaworld/jw-05-2002/jw-0503-java101.html");
        userSearchResult.setDescription("null");
        userSearchResult.setConfidenceScore(9f);
        List<Map<String,Object>> conceptDocumentRelation = userSearchServiceRepository.getAllRelatesRelation("Thread");
        assertEquals("http://localhost:8090/javaworld/jw-05-2002/jw-0503-java101.html",userSearchResult.getUrl());
        assertEquals("null",userSearchResult.getDescription());
        assertEquals(9f,userSearchResult.getConfidenceScore(),9);

    }

}