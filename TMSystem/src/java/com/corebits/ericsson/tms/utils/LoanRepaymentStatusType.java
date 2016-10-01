
package com.corebits.ericsson.tms.utils;

/**
 *
 * @author xtphere
 */
public enum LoanRepaymentStatusType {
    OUTSTANDING("Outstanding"), COMPLETED("Completed");
    String value;
    
    private LoanRepaymentStatusType(String value){
        this.value = value;        
    }
    
    public String getValue(){
        return value;
    }
    
}
