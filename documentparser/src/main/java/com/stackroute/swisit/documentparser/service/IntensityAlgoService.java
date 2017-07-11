package com.stackroute.swisit.documentparser.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.stackroute.swisit.documentparser.domain.ContentSchema;

/**
 * Created by user on 30/6/17.
 */
public interface IntensityAlgoService {
	public ArrayList<ContentSchema> calculateIntensity(HashMap<String,HashMap<String,Integer>> parsedDocumentMap) ;
}
