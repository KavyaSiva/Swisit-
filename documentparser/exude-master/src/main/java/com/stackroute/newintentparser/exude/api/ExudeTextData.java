
package com.stackroute.newintentparser.exude.api;

import com.stackroute.newintentparser.exude.common.ExudeRequest;
import com.stackroute.newintentparser.exude.common.ExudeResponse;
import com.stackroute.newintentparser.exude.exception.InvalidDataException;
import com.stackroute.newintentparser.exude.swear.SwearParser;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExudeTextData implements ExudeAPI {

    Logger logger = Logger.getLogger("ExudeTextData");

    @Override
    public ExudeResponse filterStoppings(ExudeRequest exudeRequest)throws InvalidDataException{
        try {
            return ExudeAPIImpl.getInstance().filterStoppings(exudeRequest);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new InvalidDataException("Invalid Data");
        }
    }

    @Override
    public ExudeResponse filterStoppingKeepDuplicate(ExudeRequest exudeRequest) throws InvalidDataException{
        try {
            return ExudeAPIImpl.getInstance().filterStoppingWithDuplicate(exudeRequest);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new InvalidDataException("Invalid Data");
        }
    }

    @Override
    public ExudeResponse getSwearWords(ExudeRequest exudeRequest) throws InvalidDataException{
        StringBuilder finalFilteredData = new StringBuilder();
        try {
            if (exudeRequest.getData().isEmpty()) {
                throw new InvalidDataException("Invalid Data");
            }
            SwearParser swearParser = SwearParser.getInstance();
            finalFilteredData.append(swearParser.getSwearWords(exudeRequest.getData()));
            swearParser.resetSwearWords();
            ExudeResponse response = new ExudeResponse();
            response.setResultData(finalFilteredData.toString());
            return response;
        } catch (Exception e) {
           logger.log(Level.SEVERE, e.getMessage());
            throw new InvalidDataException("Invalid Data");
        }
    }

}
