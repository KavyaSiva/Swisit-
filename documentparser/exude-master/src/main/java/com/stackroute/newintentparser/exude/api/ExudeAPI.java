
package com.stackroute.newintentparser.exude.api;

import com.stackroute.newintentparser.exude.common.ExudeRequest;
import com.stackroute.newintentparser.exude.common.ExudeResponse;
import com.stackroute.newintentparser.exude.exception.InvalidDataException;



public interface ExudeAPI {
    public ExudeResponse filterStoppings(ExudeRequest exudeRequest) throws InvalidDataException;
    public ExudeResponse filterStoppingKeepDuplicate(ExudeRequest exudeRequest) throws InvalidDataException;
    public ExudeResponse getSwearWords(ExudeRequest exudeRequest) throws InvalidDataException;
}
