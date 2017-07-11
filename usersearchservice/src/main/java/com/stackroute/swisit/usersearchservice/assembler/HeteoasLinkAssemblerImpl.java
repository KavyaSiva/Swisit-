/*******This Class is used for Testing the Service only,
 * will finally be removed from the Final product******/
package com.stackroute.swisit.usersearchservice.assembler;
/*-----Importing Libraries-----*/

import com.stackroute.swisit.usersearchservice.domain.UserSearchResult;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import java.util.List;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/*-------Implementation to HateoasLinkAssembler interface------*/
@Service
public class HeteoasLinkAssemblerImpl implements HeteoasLinkAssembler {
/*------Overriding CalculateConfidence method of Interface Class------*/
@Override
    public  List<UserSearchResult> fetchNeoData(List<UserSearchResult> results)
    {
        for(UserSearchResult userSearchResult : results) {
            Link selfLink = linkTo(UserSearchResult.class).slash(userSearchResult.getUrl()).withSelfRel();
            userSearchResult.add(selfLink);
        }
        return results;
    }
}
