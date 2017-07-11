package com.stackroute.swisit.documentparser.domain;


import java.util.HashMap;

/**
* Created by user on 10/7/17.
*/
public class DocumentModel {

   public HashMap<String, HashMap<String, Integer>> webDocumentModel;

   public DocumentModel() { }

   public DocumentModel( HashMap<String, HashMap<String, Integer>> webDocumentModel ) {
       this.webDocumentModel = webDocumentModel;
   }

   public HashMap<String, HashMap<String, Integer>> getWebDocumentModel() {
       return webDocumentModel;
   }

   public void setWebDocumentModel( HashMap<String, HashMap<String, Integer>> webDocumentModel ) {
       this.webDocumentModel = webDocumentModel;
   }
}