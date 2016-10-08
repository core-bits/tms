
package com.corebits.ericsson.tms.utils;

/**
 *
 * @author xtphere
 */
public enum LoanStatusType {
    APPLIED(-1, "Applied"), RUNNING(1, "Running"), CLOSED(0, "Closed");
    
    Integer key;
    String value;
    
    private LoanStatusType(Integer key, String value){
        this.key = key;
        this.value = value;
    }
    
    public Integer getKey(){
        return key;
    }
    public String getValue(){
        return value;
    }
}
