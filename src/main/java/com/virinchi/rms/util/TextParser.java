/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virinchi.rms.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Risab
 */
public class TextParser {
    private String leftDelim="{{", rightDelim="}}";
    private Map<String, String> data=new HashMap<>(); 
    public TextParser maspData(String key, String value){
        if(!data.containsKey(key)){
            data.put(key, value);
        }
        
        return this;
    }
    
    public void setleftDelim(String leftDelim){
        this.leftDelim=leftDelim;
    }
    public void setRightDelim(){
        this.rightDelim=rightDelim;
    }
    public String Parse(String content){
       Set<Map.Entry<String, String>> pairs=data.entrySet();
       for(Map.Entry<String, String> pair: pairs){
           content=content.replace(leftDelim+pair.getKey()+rightDelim, pair.getValue());
       }
          return content;     
    }
}
