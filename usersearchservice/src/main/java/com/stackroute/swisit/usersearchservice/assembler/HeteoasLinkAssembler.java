/*******This Class is used for Testing the Service only,
 * will finally be removed from the Final product******/

package com.stackroute.swisit.usersearchservice.assembler;

/*------Importing Liberaries------*/
import com.stackroute.swisit.usersearchservice.domain.UserSearchResult;

import java.util.List;

/*------Interface Class for Assembling Links-----*/
public interface HeteoasLinkAssembler{

    /*------Calculate Confidence Score Method for Response-----*/
    public  List<UserSearchResult> fetchNeoData(List<UserSearchResult> results);
}