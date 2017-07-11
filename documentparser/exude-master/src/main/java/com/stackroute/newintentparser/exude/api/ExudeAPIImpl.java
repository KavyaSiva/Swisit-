
package com.stackroute.newintentparser.exude.api;

import com.stackroute.newintentparser.exude.common.Constants;
import com.stackroute.newintentparser.exude.common.ExudeRequest;
import com.stackroute.newintentparser.exude.common.ExudeResponse;
import com.stackroute.newintentparser.exude.exception.InvalidDataException;
import com.stackroute.newintentparser.exude.stopping.StoppingParser;
import com.stackroute.newintentparser.exude.stopping.TrushDuplicates;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ExudeAPIImpl {

    protected static ExudeAPIImpl instance = null;

    TrushDuplicates trushDuplicates = TrushDuplicates.getInstance();

    public static ExudeAPIImpl getInstance() {
        if (instance == null) {
            instance = new ExudeAPIImpl();
        }
        return instance;
    }

    protected ExudeAPIImpl() {
    }

    ;

    public ExudeResponse filterStoppings(ExudeRequest exudeRequest) throws InvalidDataException {
        String tempData = "";
        StringBuilder finalFilteredData = new StringBuilder();
        try {
            String fileData = exudeRequest.getData();
            tempData = trushDuplicates.filterDuplicates(fileData);
            StoppingParser stoppingParser = StoppingParser.getInstance();
            Set<String> dataSet = trushDuplicates.filterData(tempData);
            Iterator<String> iterable = dataSet.iterator();
            while (iterable.hasNext()) {
                String line = iterable.next();
                stoppingParser.filterStoppingWords(line.replaceAll(Constants.MULTIPLE_SPACE_TAB_NEW_LINE, " "));
            }
            trushDuplicates.filterDuplicate(stoppingParser.getResultSet());
            Iterator<String> _iterable = trushDuplicates.getTempSet().iterator();
            while (_iterable.hasNext()) {
                String line = _iterable.next();
                finalFilteredData.append(line.trim() + " ");
            }
            stoppingParser.reset();
            trushDuplicates.reset();
            return populateResponse(Constants.STATUS.SUCCESS.name(), finalFilteredData.toString());
        } catch (Exception e) {
            return populateResponse(Constants.STATUS.FAILURE.name(), e.getMessage());
        }
    }

    public ExudeResponse filterStoppingWithDuplicate(ExudeRequest exudeRequest) throws InvalidDataException {
        String tempData = "";
        StringBuilder finalFilteredData = new StringBuilder();
        try {
            tempData = exudeRequest.getData();
            StoppingParser stoppingParser = StoppingParser.getInstance();
            List<String> dataSet = trushDuplicates.filterDataKeepDuplicate(tempData);
            Iterator<String> iterable = dataSet.iterator();
            while (iterable.hasNext()) {
                String line = iterable.next();
                stoppingParser.filterStoppingWordsKeepDuplicates(line.replaceAll(Constants.MULTIPLE_SPACE_TAB_NEW_LINE, " "));
            }
            Iterator<String> _iterable = trushDuplicates.getTempList().iterator();
            while (_iterable.hasNext()) {
                String line = _iterable.next();
                finalFilteredData.append(line.trim() + " ");
            }
            stoppingParser.reset();
            trushDuplicates.reset();
            return populateResponse(Constants.STATUS.SUCCESS.name(), finalFilteredData.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new InvalidDataException("Invalid Data");
    }

    public ExudeResponse getSwearWords(ExudeRequest exudeRequest) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private ExudeResponse populateResponse(String status, String result) throws InvalidDataException {
        ExudeResponse response = new ExudeResponse();

        switch (Constants.STATUS.valueOf(status)) {
            case SUCCESS:
                response.setStatus(Constants.STATUS.SUCCESS.name().toUpperCase());
                response.setMessage("Sucessfully Processed the data");
                response.setResultData(result);
                return response;
            case FAILURE:
                response.setStatus(Constants.STATUS.FAILURE.name().toUpperCase());
                response.setMessage("Sucessfully Processed the data");
                response.setResultData(result);
                return response;
            default:
                break;

        }
        throw new InvalidDataException("Invalid Data");
    }

}
