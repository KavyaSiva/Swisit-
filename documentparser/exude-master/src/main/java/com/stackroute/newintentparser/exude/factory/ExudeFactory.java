
package com.stackroute.newintentparser.exude.factory;

import com.stackroute.newintentparser.exude.api.ExudeAPI;
import com.stackroute.newintentparser.exude.api.ExudeFileData;
import com.stackroute.newintentparser.exude.api.ExudeTextData;
import com.stackroute.newintentparser.exude.api.ExudeWebLinkData;
import com.stackroute.newintentparser.exude.common.Constants;
import com.stackroute.newintentparser.exude.common.ExudeRequest;
import com.stackroute.newintentparser.exude.common.ExudeResponse;
import com.stackroute.newintentparser.exude.exception.InvalidDataException;

import java.util.regex.Pattern;


public class ExudeFactory {

    protected ExudeAPI exudeAPI = null;
    private ExudeRequest exudeRequest;

    public ExudeFactory(ExudeRequest exudeRequest) {
        boolean isFile = Pattern.matches(Constants.FILE_PATH_REGULAR_EXPRESSION, exudeRequest.getData());
        boolean isURL = Pattern.matches(Constants.URL_REGULAR_EXPRESSION, exudeRequest.getData());
        if (isFile || isURL) {
            exudeRequest.setType(isFile == true ? Constants.ExudeType.FILE.name()
                    : Constants.ExudeType.WEB_LINK.name());
        } else {
            exudeRequest.setType(Constants.ExudeType.TEXT_DATA.name());
        }
        this.exudeRequest = exudeRequest;
    }

    private ExudeAPI getExudeAPI() {
        switch (Constants.ExudeType.valueOf(getExudeRequest().getType())) {
            case TEXT_DATA:
                exudeAPI = new ExudeTextData();
                break;
            case FILE:
                exudeAPI = new ExudeFileData();
                break;
            case WEB_LINK:
                exudeAPI = new ExudeWebLinkData();
                break;
        }
        return exudeAPI;
    }

    public ExudeResponse filterStopppingData() throws InvalidDataException {
        try {
            exudeAPI = getExudeAPI();
            return exudeRequest.isKeepDuplicate() ? exudeAPI.filterStoppingKeepDuplicate(exudeRequest)
                    : exudeAPI.filterStoppings(exudeRequest);
        } catch (Exception e) {
            throw new InvalidDataException("Invalid Input Data ", e);
        }
    }
    
     public ExudeResponse getSwearWords() throws InvalidDataException {
         try {
            exudeAPI = getExudeAPI();
            return exudeAPI.getSwearWords(exudeRequest);
        } catch (Exception e) {
            throw new InvalidDataException("Invalid Input Data ", e);
        }
     }

    public ExudeRequest getExudeRequest() {
        return exudeRequest;
    }

    public void setExudeRequest(ExudeRequest exudeRequest) {
        this.exudeRequest = exudeRequest;
    }

}
