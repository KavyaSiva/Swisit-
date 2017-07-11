/*******This Class is used for Testing the Service only,
 * will finally be removed from the Final product******/
package com.stackroute.swisit.usersearchservice.controller;

/*-----Importing Libraries-----*/
import com.stackroute.swisit.usersearchservice.assembler.HeteoasLinkAssembler;
import com.stackroute.swisit.usersearchservice.domain.*;
import com.stackroute.swisit.usersearchservice.service.UserSearchService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Locale;

/*----------------------Rest API Controller Class------------------------*/
@RestController
@RequestMapping(value = "v1/api/swisit/usersearch")
public class UserSearchServiceController {
    /*----------Autowired Instances of Classes----------*/
    @Autowired
    UserSearchService userSearchService;
    @Autowired
    HeteoasLinkAssembler heteoasLinkAssembler;
    @Autowired
    private MessageSource messageSource;

    /*----------------Swagger API Operations-----------------*/
    @ApiOperation(value="Fetch Neo4j Data",response =UserInput.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Crawler"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
/*----------- REST Controller To Show Data From Neo4j Database------------*/
    @RequestMapping(value="", method = RequestMethod.POST)
    public ResponseEntity getUserSearch(@RequestBody UserInput userInput) {
        /*------Locale which identify a specific language and geographic region, used for Internationalization-----*/
        Locale locale = LocaleContextHolder.getLocale();
        /*-------Resulted List from User Search Service-------*/
        List<UserSearchResult> userSearchResults = userSearchService.fetchNeoData(userInput);
        /*-------Hateoas Link Assembling to Response-------*/
        List<UserSearchResult> userSearchResultList=heteoasLinkAssembler.fetchNeoData(userSearchResults);
       /*------Try Catch block for Handling Exceptions-----*/
        try {
            if (userInput == null) {
                String message = messageSource.getMessage("user.excep.data", null, locale);
                return new ResponseEntity(message, HttpStatus.OK);
            }
        }
        catch(Exception e){
                e.printStackTrace();
            }
            return new ResponseEntity(userSearchResults, HttpStatus.OK);
        }
    @CrossOrigin
    @RequestMapping(value="/getConcept", method = RequestMethod.POST)
    public ResponseEntity getConcept(@RequestBody String domain) {
        /*------Locale which identify a specific language and geographic region, used for Internationalization-----*/
        Locale locale = LocaleContextHolder.getLocale();
        /*-------Resulted List from User Search Service-------*/
        System.out.println("my concepr doamin is "+domain);
        List<String> conceptResults = userSearchService.fetchConcept();


        return new ResponseEntity(conceptResults, HttpStatus.OK);
    }
    @CrossOrigin
    @RequestMapping(value="/getTerms", method = RequestMethod.GET)
    public ResponseEntity getTerms() {
        /*------Locale which identify a specific language and geographic region, used for Internationalization-----*/
        Locale locale = LocaleContextHolder.getLocale();
        /*-------Resulted List from User Search Service-------*/
        System.out.println("my term is");
        List<String> termResults = userSearchService.fetchTerm();

        return new ResponseEntity(termResults, HttpStatus.OK);
    }
}


